package com.jesse.controller;

import com.jesse.bean.Document;
import com.jesse.bean.User;
import com.jesse.common.HrmConstants;
import com.jesse.service.DocumentService;
import com.jesse.util.PageModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Jesse on 2017/6/26 0026.
 */
@Controller
public class DocumentController {
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    /**
     * 查询文件
     * @param model
     * @param pageIndex
     * @param document
     * @return
     */
    @RequestMapping(value = "/document/selectDocument")
    public String selectDocument(Model model, Integer pageIndex, @ModelAttribute Document document){
        PageModel pageModel=new PageModel();
        if(pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        //查询文件
        List<Document> documents=documentService.findDocument(document,pageModel);
        model.addAttribute("documents",documents);
        model.addAttribute("pageModel",pageModel);
        return "document/document";
    }

    /**
     * 添加文件
     * @param flag
     * @param document
     * @param modelAndView
     * @param session
     * @return
     */
    @RequestMapping(value = "/document/addDocument")
    public ModelAndView addDocument(String flag, @ModelAttribute Document document,
                                  ModelAndView modelAndView, HttpSession session) throws IOException {
        if(flag.equals("1")){
            modelAndView.setViewName("document/showAddDocument");
        }else{
            //上传文件路径
            String path=session.getServletContext().getRealPath("/upload/");
            //上传文件名
            String fileName=document.getFile().getOriginalFilename();
            //将上传文件保存到目标文件中
            File file=new File(path+File.separator+fileName);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            document.getFile().transferTo(file);
            //设置filename
            document.setFilename(fileName);
            //设置关联User对象
            User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
            document.setUser(user);
            //插入数据库
            documentService.addDocument(document);
            modelAndView.setViewName("redirect:/document/selectDocument");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/document/updateDocument")
    public ModelAndView updateDocument(String flag, @ModelAttribute Document document,
                                  ModelAndView modelAndView){
        if(flag.equals("1")){
            Document target=documentService.findDocumentById(document.getId());
            modelAndView.addObject("document",target);
            modelAndView.setViewName("document/showUpdateDocument");
        }else{
            documentService.modifyDocument(document);
            modelAndView.setViewName("redirect:/document/selectDocument");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/document/removeDocument")
    public ModelAndView removeDocument(String ids,ModelAndView modelAndView){
        String[] idArray=ids.split(",");
        for(String id :idArray){
            documentService.removeDocumentById(id);
        }
        modelAndView.setViewName("redirect:/document/selectDocument");
        return modelAndView;
    }

    @RequestMapping(value = "/document/downLoad")
    public ResponseEntity<byte[]> downLoad(String id,HttpSession httpSession) throws Exception {
        //根据id查询文档
        Document target=documentService.findDocumentById(id);
        String fileName=target.getFilename();
        //上传文件路径
        String path=httpSession.getServletContext().getRealPath("/upload/");
        //获取将要下载的文件
        File file=new File(path+File.separator+fileName);
        //创建springframework的HttpHeaders对象
        HttpHeaders headers=new HttpHeaders();
        //解决下载显示文件名乱码问题
        String downloadFileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment(下载方式)打开图片
        headers.setContentDispositionFormData("attachment",downloadFileName);
        //二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }

}
