package com.example.todo_final.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CategoryId")
    private int categoryId;

    @ColumnInfo(name = "Category")
    private String category;

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
