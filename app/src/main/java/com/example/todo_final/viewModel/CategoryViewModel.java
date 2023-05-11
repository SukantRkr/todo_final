package com.example.todo_final.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo_final.database.AppDatabase;
import com.example.todo_final.database.Repository;
import com.example.todo_final.model.Category;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    Repository repository;
    private LiveData<List<Category>> categoryList;
    public CategoryViewModel(@NotNull Application application){
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);

        repository = new Repository(appDatabase);
        categoryList = repository.loadAllCategory();
    }

public void saveCategory(Category category){
        repository.insertCategory(category);
}

public LiveData<List<Category>>getCategoryList(){ return categoryList;}
}
