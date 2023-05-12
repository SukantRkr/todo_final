package com.example.todo_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void replaceFragmentCategoryList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView4, CategoryListFragment.class, null)
                .addToBackStack("CategoryList")
                .setReorderingAllowed(true)
                .commit();
    }

    public void replaceFragmentCategory(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView4, CategoryFragment.class, null)
                .addToBackStack("Category")
                .setReorderingAllowed(true)
                .commit();
    }
    public void replaceFragmentTodo(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView4, ToDoFragment.class, null)
                .addToBackStack("Todo")
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_newcategory:
                replaceFragmentCategory();
                return true;
            case R.id.main_menu_clear_category:
                return true;
            case R.id.main_menu_logout:
                SharedPreferences sharedPreferences =  getSharedPreferences("Login", MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return  true;
        }
        return true;
    }
}