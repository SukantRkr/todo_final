package com.example.todo_final.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.todo_final.model.Todo;

import java.util.Date;
import java.util.List;

@Dao
public interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("delete from todo")
    void deleteAll();


    @Query("delete from todo where isComplete=1")
    void deleteAllCompleted();

    @Query("update todo set isComplete =1 where todoId =:todoId")
    void completeTask(int todoId);

    @Query("select * from todo where todoId=:todoId")
    LiveData<Todo> loadTodoById(int todoId);

    @Query("select * from todo where categoryId =:categoryId")
    LiveData<List<Todo>> loadTodoByCategoryId(int categoryId);

    @Query("update todo set title=:title,description=:description, todoDate=:date, isComplete=:completed, createdOn=:createddate where todoId = :todoId")
    void updateTodo(int todoId, String title, String description, Date date, boolean completed, Date createddate);
}
