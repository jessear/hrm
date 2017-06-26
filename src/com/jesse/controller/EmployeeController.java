package com.jesse.controller;

import com.jesse.bean.Dept;
import com.jesse.bean.Employee;
import com.jesse.bean.Job;
import com.jesse.service.DeptService;
import com.jesse.service.EmployeeService;
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
public class EmployeeController {
    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @Autowired
    @Qualifier("jobService")
    private JobService jobService;

    @Autowired
    @Qualifier("deptService")
    private DeptService deptService;

    /**
     * 分页查询员工
     * @param pageIndex
     * @param job_id
     * @param dept_id
     * @param employee
     * @param model
     * @return
     */
    @RequestMapping(value = "/employee/selectEmployee")
    public String selectEmployee(Integer pageIndex,
                                 Integer job_id, Integer dept_id,
                                 @ModelAttribute Employee employee,
                                 Model model){
        //模糊查询是判断是否有关联对象传递，如果有创建并封装关联对象
        this.genericAssociation(job_id,dept_id,employee);
        PageModel pageModel=new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Job> jobs=jobService.findAllJob();
        List<Dept> depts=deptService.findAllDept();
        List<Employee> employees=employeeService.findEmployee(employee,pageModel);
        model.addAttribute("employees",employees);
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        model.addAttribute("pageModel",pageModel);
        return "employee/employee";
    }

    @RequestMapping(value = "/employee/addEmployee")
    public ModelAndView addEmployee(
            String flag,
            Integer job_id,Integer dept_id,
            @ModelAttribute Employee employee,
            ModelAndView modelAndView){
        if(flag.equals("1")){
            //查询职位信息
            List<Job> jobs=jobService.findAllJob();
            //查询部门信息
            List<Dept> depts=deptService.findAllDept();
            modelAndView.addObject("jobs",jobs);
            modelAndView.addObject("depts",depts);
            modelAndView.setViewName("employee/showAddEmployee");
        }else{
            this.genericAssociation(job_id,dept_id,employee);
            employeeService.addEmployee(employee);
            modelAndView.setViewName("redirect:/employee/selectEmployee");
        }
        return modelAndView;
    }

    /**
     * 删除员工
     * @param ids
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/employee/removeEmployee")
    public ModelAndView removeEmployee(String ids,ModelAndView modelAndView){
        String[] idArray=ids.split(",");
        for(String id :idArray){
            employeeService.removeEmployeeById(Integer.parseInt(id));
        }
        modelAndView.setViewName("redirect:/employee/selectEmployee");
        return modelAndView;
    }

    @RequestMapping(value = "/employee/updateEmployee")
    public ModelAndView updateEmployee(String flag,
                                       Integer job_id,Integer dept_id,
                                       @ModelAttribute Employee employee,
                                       ModelAndView modelAndView){
        if(flag.equals("1")){
            Employee target=employeeService.findEmployeeById(employee.getId());
            //需要查询的职位信息
            List<Job> jobs=jobService.findAllJob();
            //需要查询的部门信息
            List<Dept> depts=deptService.findAllDept();
            modelAndView.addObject("jobs",jobs);
            modelAndView.addObject("depts",depts);
            modelAndView.addObject("employee",target);
            modelAndView.setViewName("employee/showUpdateEmployee");
        }else{
            this.genericAssociation(job_id,dept_id,employee);
            employeeService.modifyEmployee(employee);
            modelAndView.setViewName("redirect:/employee/selectEmployee");
        }
        return modelAndView;
    }

    /**
     * 由于部门和职位在Employee中是对象关联映射，
     * 所以不能直接接收参数，需要创建Job和Dept对象
     * @param job_id
     * @param dept_id
     * @param employee
     */
    private void genericAssociation(Integer job_id,Integer dept_id,Employee employee){
        if(job_id!=null){
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if(dept_id!=null){
            Dept dept=new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }
}
