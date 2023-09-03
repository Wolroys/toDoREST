package com.wolroys.todorest.repository;

import com.wolroys.todorest.entity.Note;
import com.wolroys.todorest.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByUserId(int id);
}
