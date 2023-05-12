package com.example.todo_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo_final.adapter.TodoAdapter;
import com.example.todo_final.model.Category;
import com.example.todo_final.model.Todo;
import com.example.todo_final.viewModel.CategoryViewModel;
import com.example.todo_final.viewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    TodoViewModel todoViewModel;
    RecyclerView todoRecyclerView;
    TodoAdapter todoAdapter;
    CategoryViewModel categoryViewModel;
    FloatingActionButton newTodoBtn;

    TextView activityTodoListCategory;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        context = getApplicationContext();
        activityTodoListCategory = findViewById(R.id.activity_todo_list_category);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoRecyclerView = findViewById(R.id.activity_todo_list_rv_todo);

        todoAdapter = new TodoAdapter();
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoRecyclerView.setAdapter(todoAdapter);

        newTodoBtn = findViewById(R.id.floatingActionButton);
        int categoryId = getIntent().getIntExtra("categoryId", 1);
        Toast.makeText(this, "cATEGORY ID IS NOT NULL " + categoryId, Toast.LENGTH_SHORT).show();


        todoViewModel.getTodoList(categoryId).observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                todoAdapter.setTodoList(todos,context);
            }
        });

        newTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityTodoListCategory.setVisibility(View.GONE);
                newTodoBtn.setVisibility(View.GONE);
                todoRecyclerView.setVisibility(View.GONE);
                Fragment frag = new ToDoFragment();
                FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
                transition.replace(R.id.fragmentContainer, frag).commit();
            }
        });
        todoViewModel.getTodoList(categoryId).observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {

                todoAdapter.setTodoList(todos,context);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                todoViewModel.updateOnCompleteTodo(todoAdapter.getUpdateNote(viewHolder.getAdapterPosition()));
                todoRecyclerView.getRecycledViewPool().clear();
                todoAdapter.notifyDataSetChanged();

            }
        }).attachToRecyclerView(todoRecyclerView);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                todoViewModel.removeTodo(todoAdapter.getNote(viewHolder.getAdapterPosition()));
                todoRecyclerView.getRecycledViewPool().clear();
                todoAdapter.notifyDataSetChanged();

                Toast.makeText(TodoListActivity.this, "TODO DELETED", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(todoRecyclerView);
    }

    public void replaceFragmentCategory(){
        activityTodoListCategory.setVisibility(View.GONE);
        newTodoBtn.setVisibility(View.GONE);
        todoRecyclerView.setVisibility(View.GONE);
        Fragment frag = new ToDoFragment();
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.replace(R.id.fragmentContainerView4, frag).commit();
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.taskactivity, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_add_task:
                replaceFragmentCategory();
                return true;
            case R.id.main_menu_delete_all_task:
                RemoveAllTodo();
                return true;
            case R.id.main_menu_delete_all_completed:
                RemoveCompleted();
                return true;
            case R.id.main_menu_logout:
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
        }
        return true;
    }

    private void RemoveAllTodo() {
        todoViewModel. removeAllTodo();
        todoRecyclerView.getRecycledViewPool().clear();
        todoAdapter.notifyDataSetChanged();
    }

    private void RemoveCompleted() {
        todoViewModel. removeCompletedTodo();
        todoRecyclerView.getRecycledViewPool().clear();
        todoAdapter.notifyDataSetChanged();
    }
}
