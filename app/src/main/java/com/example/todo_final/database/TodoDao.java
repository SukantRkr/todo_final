package com.example.todo_final.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.todo_final.model.Todo;

import java.util.List;

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
}
