package com.example.todo_final.database;

import androidx.lifecycle.LiveData;

import com.example.todo_final.model.Category;
import com.example.todo_final.model.Todo;

import java.util.List;

public class Repository {
    private CategoryDao categoryDao;
    private TodoDao todoDao;

    public Repository(AppDatabase appDatabase) {
        this.categoryDao = appDatabase.categoryDao();
        this.todoDao = appDatabase.todoDao();
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

    public LiveData<List<Category>> loadCategoryById(int categoryId) {
        return categoryDao.loadCategoryById(categoryId);
    }

    public void insertTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.insert(todo);
        });
    }

    public LiveData<List<Todo>> loadAllTodo(int categoryId) {
        return todoDao.loadTodoByCategoryId(categoryId);
    }
}
