package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/img")
public class ImgController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fname = req.getParameter("fname");
        //根据图片名字，利用is 读取图片内容，并响应
        InputStream is = new FileInputStream("f:/z/"+fname) ;
        OutputStream os = resp.getOutputStream() ;
        while(true){
            int b = is.read() ;
            if(b == -1){
                break ;
            }
            os.write(b);
        }
        is.close();
    }
}
