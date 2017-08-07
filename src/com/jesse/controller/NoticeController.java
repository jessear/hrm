package com.jesse.controller;

import com.jesse.bean.Notice;
import com.jesse.bean.User;
import com.jesse.common.HrmConstants;
import com.jesse.service.NoticeService;
import com.jesse.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jesse on 2017/6/26 0026.
 */
@Controller
public class NoticeController {
    @Autowired
    @Qualifier("noticeService")
    private NoticeService noticeService;

    /**
     * 查询通告
     * @param model
     * @param pageIndex
     * @param notice
     * @return
     */
    @RequestMapping(value = "/notice/selectNotice")
    public String selectNotice(Model model, Integer pageIndex, @ModelAttribute Notice notice){
        PageModel pageModel=new PageModel();
        if(pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        //查询公告
        List<Notice> notices=noticeService.findNotice(notice,pageModel);
        model.addAttribute("notices",notices);
        model.addAttribute("pageModel",pageModel);
        return "notice/notice";
    }

    /**
     * 预览通告
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/notice/previewNotice")
    public String previewNotice(String id,Model model){
        Notice notice=noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return "notice/previewNotice";
    }

    /**
     * 添加通告
     * @param flag
     * @param notice
     * @param modelAndView
     * @param session
     * @return
     */
    @RequestMapping(value = "/notice/addNotice")
    public ModelAndView addNotice(String flag, @ModelAttribute Notice notice,
                                  ModelAndView modelAndView, HttpSession session){
        if(flag.equals("1")){
            modelAndView.setViewName("notice/showAddNotice");
        }else{
            User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
            notice.setUser(user);
            noticeService.addNotice(notice);
            modelAndView.setViewName("redirect:/notice/selectNotice");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/notice/updateNotice")
    public ModelAndView updateNotice(String flag, @ModelAttribute Notice notice,
                                  ModelAndView modelAndView, HttpSession session){
        if(flag.equals("1")){
            Notice target=noticeService.findNoticeById(notice.getId());
            modelAndView.addObject("notice",target);
            modelAndView.setViewName("notice/showUpdateNotice");
        }else{
            noticeService.modifyNotice(notice);
            modelAndView.setViewName("redirect:/notice/selectNotice");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/notice/removeNotice")
    public ModelAndView removeNotice(String ids,ModelAndView modelAndView){
        String[] idArray=ids.split(",");
        for(String id :idArray){
            noticeService.removeNoticeById(id);
        }
        modelAndView.setViewName("redirect:/notice/selectNotice");
        return modelAndView;
    }
}
