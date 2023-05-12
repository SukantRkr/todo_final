package com.example.todo_final.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo_final.database.AppDatabase;
import com.example.todo_final.database.Repository;
import com.example.todo_final.model.User;

import org.jetbrains.annotations.NotNull;

public class UserViewModel extends AndroidViewModel {
    Repository repository;

    private LiveData<User> user;

    public UserViewModel(@NotNull Application application){
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        repository = new Repository(appDatabase);
    }

    public LiveData<User> getUserByName(String userName){ return repository.loadUserByUserName(userName);
    }

    public void saveUser(User user){
        repository.insertUser(user);
    }
}

