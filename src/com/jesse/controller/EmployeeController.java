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
