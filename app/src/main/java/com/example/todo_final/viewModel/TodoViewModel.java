package com.example.todo_final.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo_final.database.AppDatabase;
import com.example.todo_final.database.Repository;
import com.example.todo_final.model.Todo;

import java.util.Date;
import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private LiveData<List<Todo>> todoList;
    Repository repository;
    public TodoViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        repository = new Repository(appDatabase);
    }

    public void saveTodo(Todo todo){
        repository.insertTodo(todo);
    }

    public LiveData<List<Todo>> getTodoList(int categoryId){
        todoList = repository.loadAllTodo(categoryId);
        return todoList;
    }

    public void  removeAllTodo(){
        repository.deleteAllTodo();
    }

    public void  removeCompletedTodo(){
        repository.deleteCompletedTodo();
    }

    public void updateOnCompleteTodo(int todo) {
        repository.updateIsCompleteTodo(todo);
    }

    public void  removeTodo(Todo todo){
        repository.deleteTodo(todo);
    }

    public void ChangeTodo(int todoId, String title, String description, Date date, boolean completed, Date createdOn) {
        repository.updateAllTodo(todoId,title,description,date,completed,createdOn);
    }
}
