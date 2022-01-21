package com;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/upload-img")
public class uploadController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //文件上传处理，需要apache-commons-upload/io
        DiskFileItemFactory factory = new DiskFileItemFactory() ;
        ServletFileUpload upload = new ServletFileUpload(factory) ;
        try {
            List<FileItem> files = upload.parseRequest(req);

            //获得文件信息
            FileItem file = files.get(0);
            byte[] bs = file.get();
            String fname = file.getName();

            //存储文件信息
            OutputStream os = new FileOutputStream("f:/z/"+fname) ;
            os.write(bs);
            os.close();

            //需要给浏览器的编辑器一个反馈，告诉编辑器，发送这样的请求，就可以获得刚才的这个图片了
            // url="img/"+fname
            //需要按照一定的格式反馈

            Map result = new HashMap() ;
            result.put("errno",0);

            List data = new ArrayList();
            result.put("data",data);

            Map imgMap = new HashMap();
            imgMap.put("url","img?fname="+fname) ;
            imgMap.put("alt","一个图片");
            imgMap.put("href","");
            data.add(imgMap);

            String json = JSON.toJSONString(result);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);


        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
