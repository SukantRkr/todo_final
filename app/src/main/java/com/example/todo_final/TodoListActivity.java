package com.example.todo_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todo_final.adapter.TodoAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoRecyclerView = findViewById(R.id.activity_todo_list_rv_todo);
        todoAdapter = new TodoAdapter();
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoRecyclerView.setAdapter(todoAdapter);
        int categoryId = getIntent().getIntExtra("categoryId", 1);
        todoViewModel.getTodoList(categoryId).observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                todoAdapter.setTodoList(todos);
            }
        });

        newTodoBtn = findViewById(R.id.add_todo_floating_btn);

        newTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodoListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}