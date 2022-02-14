package com.duyi.examonline.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.service.TeacherService;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.annotation.RequiredTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService ;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/teacher/teacher.html")
    public String toTeacher(Model model){
        //还需要携带数据。。。。
        PageVO pageVO = teacherService.find(CommonData.DEFAULT_PAGE, CommonData.DEFAULT_ROWS, null);
        model.addAttribute("pageVO",pageVO);
        return "teacher/teacher" ;
    }

    @RequestMapping("/teacher/pageTemplate.html")
    public String toPageTemplate(int pageNo , String tname,Model model){
        PageVO pageVO = teacherService.find(pageNo,CommonData.DEFAULT_ROWS,tname) ;
        model.addAttribute("pageVO",pageVO) ;
        return "teacher/teacher::#pageTemplate" ;
    }

    /**
     *
     * @param id 保存时获取模板，没有id 。 编辑时获得模板，有id
     * @return
     */
    @RequestMapping("/teacher/formTemplate.html")
    public String toFormTemplate(Long id,Model model){
        if(id != null){
            //是编辑需求，需要根据id获得对应的教师信息（tname）
            Teacher teacher = teacherService.findById(id);
            model.addAttribute("teacher",teacher) ;
        }
        return "teacher/formTemplate" ;
    }

    @RequestMapping("/teacher/save")
    @ResponseBody
    public boolean save(Teacher teacher){
        log.debug("tname : [{}]",teacher.getTname());

        teacher.setPass(CommonData.DEFAULT_PASS);
        try {
            teacherService.save(teacher);
            return true ;
        }catch (DuplicateKeyException e){
            return false ;
        }
    }

    @RequestMapping("/teacher/update")
    @ResponseBody
    public boolean update(Teacher teacher){
        try{
            teacherService.update(teacher);
            return true ;
        }catch (DuplicateKeyException e){
            return false ;
        }
    }

    @RequestMapping("/teacher/deleteAll")
    @ResponseBody
    public void deleteAll(String ids){
        teacherService.deleteAll(ids);
    }


    @RequestMapping("/teacher/importsTemplate.html")
    public String toImports(){
        return "teacher/importsTemplate" ;
    }

    @RequestMapping("/teacher/downTemplate")
    public ResponseEntity<byte[]> downTemplate() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("files/teacher.xlsx") ;
        byte[] bs = new byte[is.available()];
        IOUtils.read(is,bs);

        HttpHeaders headers = new HttpHeaders() ;
        headers.add("content-disposition","attachment;filename=teachers.xlsx");
        headers.add("content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return new ResponseEntity<byte[]>(bs,headers, HttpStatus.OK) ;
    }


    @RequestMapping(value="/teacher/imports",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String imports(MultipartFile excel) throws IOException {
        //使用POI技术读取excel
        //可以使用POI原生功能读取excel
        //也可以使用hutool工具，内部封装了poi
        //hutool -> POI -> excel.getInputStream()
        ExcelReader reader = ExcelUtil.getReader(excel.getInputStream());
        reader.addHeaderAlias("教师名称","tname") ;
        //List<Teacher> teachers = reader.readAll(Teacher.class);
        List<Teacher> teachers = reader.read(1, 2, Teacher.class);
        String msg = teacherService.saves(teachers);

        return msg ;

    }


    @RequestMapping("/teacher/exports")
    public ResponseEntity<byte[]> exports(){
        List<Teacher> teachers = teacherService.findAll();

        ExcelWriter writer = ExcelUtil.getWriter(true);

        writer.addHeaderAlias("id","教师编号") ;
        writer.addHeaderAlias("tname","教师名称") ;
        writer.addHeaderAlias("mnemonicCode","助记码");
        writer.addHeaderAlias("createTime","创建时间");

        //设置只输出有别名字段
        writer.setOnlyAlias(true) ;

        writer.write(teachers) ;

        ByteArrayOutputStream os = new ByteArrayOutputStream() ;
        writer.flush(os) ;
        writer.close();
        byte[] bs = os.toByteArray();

        HttpHeaders headers = new HttpHeaders() ;
        headers.add("content-disposition","attachment;filename=teachers.xlsx");
        headers.add("content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return new ResponseEntity<byte[]>(bs,headers, HttpStatus.OK) ;
    }


}
