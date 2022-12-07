package com.taskManager.controller;

import com.taskManager.service.DepartmentService;
import com.taskManager.service.TaskService;
import com.taskManager.service.dto.PeriodDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final DepartmentService departmentService;
    private final TaskService taskService;

    @GetMapping(value = "/report")
    public String getReport(@RequestParam Long departmentId, @RequestParam Integer page, @NotNull Model model){
        var department = departmentService.findById(departmentId);
        var workday = departmentService.getWorkdayToday();
        workday.setDepartmentId(departmentId);
        workday.setPage(page);
        var tasks = taskService.getFilteredTask(workday);
        model.addAttribute("period", new PeriodDto());
        model.addAttribute("tasks", tasks);
        model.addAttribute("department", department);
        model.addAttribute("countPage", 1);
        model.addAttribute("page", page);
        return "report";
    }
    @GetMapping(value = "/reportWeek")
    public String getHistoryOfWeek(@RequestParam Long departmentId, @RequestParam Integer page, Model model){
        var department = departmentService.findById(departmentId);
        var countPage = taskService.getCountPageForWeek(departmentId);
        var tasks = taskService.getByLastWeek(departmentId, page);
        model.addAttribute("tasks", tasks);
        model.addAttribute("department", department);
        model.addAttribute("period", new PeriodDto());
        model.addAttribute("countPage", countPage);
        model.addAttribute("page", page);
        return "reportWeek";
    }
    @GetMapping(value = "/reportPeriod")
    public String getHistoryOfPeriod(@ModelAttribute PeriodDto periodWithDepartment, Model model){
        Integer page;
        if (periodWithDepartment.getPage()==null){
            page = 1;
        }else {
            page = periodWithDepartment.getPage();
        }
        var countPage = taskService.getCountPageForPeriod(periodWithDepartment);
        var department = departmentService.findById(periodWithDepartment.getDepartmentId());
        if(periodWithDepartment.getFromDate()!=null&&periodWithDepartment.getToDate()!=null){
            var tasks = taskService.getByPeriod(periodWithDepartment, page);
            model.addAttribute("tasks", tasks);
            model.addAttribute("department", department);
            model.addAttribute("period", periodWithDepartment);
            model.addAttribute("countPage", countPage);
            model.addAttribute("page", page);
            return "reportPeriod";
        }
        var workdayToday = departmentService.getWorkdayToday();
        workdayToday.setDepartmentId(department.getId());
        var tasks = taskService.getFilteredTask(workdayToday);
        model.addAttribute("tasks", tasks);
        model.addAttribute("dateError", "No date selected!");
        model.addAttribute("department", department);
        model.addAttribute("page", page);
        model.addAttribute("period", new PeriodDto());  //add countPage
        return "report";
    }

}
