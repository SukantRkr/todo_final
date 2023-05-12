package com.example.todo_final.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_final.R;
import com.example.todo_final.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryView> {
    private List<Category> categoryList;

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    private OnTaskClickListener onTaskClickListener;

    public CategoryAdapter(OnTaskClickListener onTaskClickListener){
        this.onTaskClickListener = onTaskClickListener;
    }

    @NonNull
    @Override
    public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item_layout, parent, false);
        CategoryView categoryView = new CategoryView(view, onTaskClickListener);
        return categoryView;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryView holder, int position) {
        holder.tvCategoryName.setText(categoryList.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public class CategoryView extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvCategoryName;

        OnTaskClickListener onTaskClickListener;

        public CategoryView(@NonNull View itemView, OnTaskClickListener onTaskClickListener) {
            super(itemView);
            tvCategoryName = (TextView) itemView.findViewById(R.id.category_item_layout_txt_category);
            this.onTaskClickListener = onTaskClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTaskClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface  OnTaskClickListener{
        void onItemClick(int position);
    }

    public Category getCategoryInPosition(int position){
        return categoryList.get(position);
    }
}
