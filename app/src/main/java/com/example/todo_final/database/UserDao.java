package com.example.todo_final.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.todo_final.model.Todo;
import com.example.todo_final.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("select * from user where userName=:userName")
    LiveData<User> loadUserByUserName(String userName);
}
