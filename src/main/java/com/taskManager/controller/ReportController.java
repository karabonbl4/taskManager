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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final DepartmentService departmentService;
    private final TaskService taskService;

    @GetMapping(value = "/report")
    public String getReport(@RequestParam Long departmentId, @NotNull Model model){
        var department = departmentService.findById(departmentId);
        var workdayToday = departmentService.getWorkdayToday();
        workdayToday.setDepartmentId(departmentId);
        var tasks = taskService.getFilteredTask(workdayToday);
        model.addAttribute("period", new PeriodDto());
        model.addAttribute("tasks", tasks);
        model.addAttribute("department", department);
        return "report";
    }
    @GetMapping(value = "/reportWeek")
    public String getHistoryOfWeek(@RequestParam Long departmentId, Model model){
        var department = departmentService.findById(departmentId);
        var tasks = taskService.getByLastWeek(departmentId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("department", department);
        model.addAttribute("period", new PeriodDto());
        return "report";
    }
    @PostMapping(value = "/report")
    public String getHistoryOfPeriod(@ModelAttribute PeriodDto periodWithDepartment, Model model){
        var department = departmentService.findById(periodWithDepartment.getDepartmentId());
        if(periodWithDepartment.getFromDate()!=null&&periodWithDepartment.getToDate()!=null){
            var tasks = taskService.getByPeriod(periodWithDepartment);
            model.addAttribute("tasks", tasks);
            model.addAttribute("department", department);
            model.addAttribute("period", periodWithDepartment);
            return "report";
        }
        var workdayToday = departmentService.getWorkdayToday();
        workdayToday.setDepartmentId(department.getId());
        var tasks = taskService.getFilteredTask(workdayToday);
        model.addAttribute("tasks", tasks);
        model.addAttribute("dateError", "No date selected!");
        model.addAttribute("department", department);
        model.addAttribute("period", new PeriodDto());
        return "report";
    }
}
