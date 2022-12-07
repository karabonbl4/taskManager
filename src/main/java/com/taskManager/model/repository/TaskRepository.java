package com.taskManager.model.repository;

import com.taskManager.model.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByWorkday(Date workday);
    List<Task> findByEmployees_Department_Id(long id);
    List<Task> findDistinctByWorkdayBetweenAndEmployees_Department_Id(Date start, Date end, Long departmentId, Pageable pageable);
    List<Task> findDistinctByWorkdayAndEmployees_Department_Id(Date workday, Long departmentId, Pageable pageable);
    List<Task> findByWorkdayAndEmployees_NameAndEmployees_Department_Id(Date workday, String function, Long departmentId, Pageable pageable);
    int countDistinctTaskByWorkdayBetweenAndEmployees_Department_Id(Date start, Date end, Long departmentId);
    int countDistinctByWorkdayAndEmployees_Department_Id(Date workday, Long departmentId);
    int countByWorkdayAndEmployees_NameAndEmployees_Department_Id(Date workday, String function, Long departmentId);

}
