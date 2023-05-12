package com.example.todo_final.database;

import androidx.lifecycle.LiveData;

import com.example.todo_final.model.Category;
import com.example.todo_final.model.Todo;
import com.example.todo_final.model.User;

import java.util.Date;
import java.util.List;

public class Repository {
    private CategoryDao categoryDao;
    private TodoDao todoDao;

    private UserDao userDao;

    public Repository(AppDatabase appDatabase) {
        this.categoryDao = appDatabase.categoryDao();
        this.todoDao = appDatabase.todoDao();
        this.userDao = appDatabase.userDao();
    }

    public void insertCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> categoryDao.insertCategory(category));
    }

    public void updateCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> categoryDao.updateCategory(category));
    }

    public void deleteCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> categoryDao.deleteCategory(category));
    }

    public LiveData<List<Category>> loadAllCategory() {
        return categoryDao.loadAllCategory();
    }

    public LiveData<Category> loadCategoryById(int categoryId) {
        return categoryDao.loadCategoryById(categoryId);
    }

    public void insertTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.insert(todo);
        });
    }

    public void deleteAllTodo() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.deleteAll();
        });
    }

    public LiveData<List<Todo>> loadAllTodo(int categoryId) {
        return todoDao.loadTodoByCategoryId(categoryId);
    }

    public LiveData<User> loadUserByUserName(String userName){
        return userDao.loadUserByUserName(userName);
    }

    public void insertUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public void updateAllCategory(int categoryId, String title){
        AppDatabase.databaseWriteExecutor.execute(()->{
            categoryDao.updateAllCategory(categoryId,title);
        });
    }

    public void deleteCompletedTodo() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.deleteAllCompleted();
        });
    }

    public void updateIsCompleteTodo(int todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.completeTask(todo);
        });
    }

    public void deleteTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.delete(todo);
        });
    }

    public void updateAllTodo(int todoId, String title, String description, Date date, boolean completed, Date createdOn) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.updateTodo(todoId,title,description,date,completed,createdOn);
        });
    }
}
