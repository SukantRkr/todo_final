package com.example.todo_final.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_final.R;
import com.example.todo_final.model.Todo;
import com.example.todo_final.viewModel.TodoViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoView> {
    private List<Todo> todoList;

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_todo_list, parent, false);
        TodoView todoView = new TodoView(view);
        return todoView;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoView holder, int position) {
        holder.tvTitle.setText(todoList.get(position).getTitle());
        holder.tvDescription.setText(todoList.get(position).getDescription());
        holder.tvComplete.setText(todoList.get(position).isComplete() == true ? "Completed" : "Incomplete");
        int priority = todoList.get(position).getPriority();
        if (priority == 0)
            holder.tvPriority.setText(R.string.todo_high);
        else if (priority == 1)
            holder.tvPriority.setText(R.string.todo_medium);
        else
            holder.tvPriority.setText(R.string.todo_low);

        DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        holder.tvDate.setText(dateFormat.format(todoList.get(position).getTodoDate()));
    }

    @Override
    public int getItemCount() {
        return (todoList == null ? 0 : todoList.size());
    }

    public class TodoView extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvComplete, tvDate, tvPriority;

        public TodoView(@NonNull View itemView) {
            super(itemView);
            tvTitle =(TextView) itemView.findViewById(R.id.);
            tvDescription =(TextView) itemView.findViewById(R.id.);
            tvComplete =(TextView) itemView.findViewById(R.id.);
            tvDate =(TextView) itemView.findViewById(R.id.);
            tvPriority =(TextView) itemView.findViewById(R.id.);
        }
    }

}
