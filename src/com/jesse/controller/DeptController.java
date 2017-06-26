package com.jesse.controller;

import com.jesse.bean.Dept;
import com.jesse.service.DeptService;
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
public class DeptController {

    @Autowired
    @Qualifier("deptService")
    private DeptService deptService;

    /**
     * 分页查询用户
     * @param model
     * @param pageIndex
     * @param dept
     * @return
     */
    @RequestMapping(value = "/dept/selectDept")
    public String selectDept(Model model, Integer pageIndex,
                             @ModelAttribute Dept dept){
        PageModel pageModel=new PageModel();
        if(pageIndex!=null){
            pageModel.setPageIndex(pageIndex);
        }
        //查询部门信息
        List<Dept> depts=deptService.findDept(dept,pageModel);
        model.addAttribute("depts",depts);
        model.addAttribute("pageModel",pageModel);
        return "dept/dept";
    }

    /**
     * 批量删除部门
     * @param ids
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/dept/removeDept")
    public ModelAndView removeDept(String ids,ModelAndView modelAndView){
        String[] idArray = ids.split(",");
        for(String id : idArray){
            deptService.removeDeptById(Integer.parseInt(id));
        }
        modelAndView.setViewName("redirect:/dept/selectDept");
        return modelAndView;
    }

    /**
     *
     * @param flag flag标记，1表示跳转到添加页面,2表示执行添加操作
     * @param dept
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/dept/addDept")
    public ModelAndView addDept(String flag,
                                @ModelAttribute Dept dept,
                                ModelAndView modelAndView){
        if(flag.equals("1")){
            //跳转添加部门页面
            modelAndView.setViewName("dept/showAddDept");
        }else{
            deptService.addDept(dept);
            modelAndView.setViewName("redirect:/dept/selectDept");
        }
        return modelAndView;
    }

    /**
     * 修改部门
     * @param flag
     * @param dept
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/dept/updateDept")
    public ModelAndView updateDept(String flag,
                                   @ModelAttribute Dept dept,
                                   ModelAndView modelAndView){
        if(flag.equals("1")){
            Dept target=deptService.findDeptById(dept.getId());
            modelAndView.addObject("dept",target);
            //跳转修改部门页面
            modelAndView.setViewName("dept/showUpdateDept");
        }else{
            deptService.modifyDept(dept);
            modelAndView.setViewName("redirect:/dept/selectDept");
        }
        return modelAndView;
    }
}
