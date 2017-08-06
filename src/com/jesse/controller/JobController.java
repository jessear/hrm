package com.jesse.controller;

import com.jesse.bean.Job;
import com.jesse.service.JobService;
import com.jesse.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by public1 on 2017/6/26.
 */
@Controller
public class JobController {
    @Autowired
    @Qualifier("jobService")
    private JobService jobService;

    @RequestMapping(value = "/job/selectJob")
    public String selectJob(Model model, Integer pageIndex,
                            @ModelAttribute Job job){
        PageModel pageModel = new PageModel();
        if(pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        //查询职位管理
        List<Job> jobs=jobService.findJob(job,pageModel);
        model.addAttribute("jobs",jobs);
        model.addAttribute("pageModel",pageModel);
        return "job/job";
    }

    @RequestMapping(value = "/job/removeJob")
    public ModelAndView removeJob(String ids,ModelAndView modelAndView){
        String[] idArray=ids.split(",");
        for(String id : idArray){
            jobService.removeJobById(id);
        }
        modelAndView.setViewName("redirect:/job/selectJob");
        return modelAndView;
    }
    @RequestMapping(value = "/job/addJob")
    public ModelAndView addJob(String flag,
                               @ModelAttribute Job job,
                               ModelAndView modelAndView){
        if(flag.equals("1")){
            //跳转到添加页面
            modelAndView.setViewName("job/showAddJob");
        }else{
            jobService.addJob(job);
            modelAndView.setViewName("redirect:/job/selectJob");
        }
        return modelAndView;
    }

    /**
     * 修改职位
     * @param flag
     * @param job
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/job/updateJob")
    public ModelAndView updateDept(String flag,
                                   @ModelAttribute Job job,
                                   ModelAndView modelAndView){
        if(flag.equals("1")){
            Job target=jobService.findJobById(job.getId());
            modelAndView.addObject("job",target);
            //跳转修改部门页面
            modelAndView.setViewName("job/showUpdateJob");
        }else{
            jobService.modifyJob(job);
            modelAndView.setViewName("redirect:/job/selectJob");
        }
        return modelAndView;
    }
}
