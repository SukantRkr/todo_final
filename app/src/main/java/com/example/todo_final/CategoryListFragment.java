package com.example.todo_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todo_final.adapter.CategoryAdapter;
import com.example.todo_final.model.Category;
import com.example.todo_final.viewModel.CategoryViewModel;

import java.util.List;

public class CategoryListFragment extends Fragment implements CategoryAdapter.OnTaskClickListener {

    CategoryViewModel categoryViewModel;
    RecyclerView categoryRecyclerView;

    Category category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        category = new Category();

        categoryViewModel = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this::onItemClick);
        categoryViewModel.getCategoryList().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdapter.setCategoryList(categories);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
                categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                categoryRecyclerView.setAdapter(categoryAdapter);

                new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        if (direction == ItemTouchHelper.LEFT) {
                            int position = viewHolder.getAdapterPosition();
                            categoryViewModel.deleteCategory(categoryAdapter.getCategoryInPosition(position));
                            categoryAdapter.getCategoryList().remove(categoryAdapter.getCategoryInPosition(position));
                            categoryAdapter.notifyItemRemoved(position);

                            Toast.makeText(getActivity().getApplicationContext(), "Category Removed", Toast.LENGTH_SHORT).show();
                        } else if (direction == ItemTouchHelper.RIGHT) {
                            int position = viewHolder.getAdapterPosition();
                            UpdateCategoryFragment updateCategoryFragment = new UpdateCategoryFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", categoryAdapter.getCategoryInPosition(position).getCategoryId());
                            bundle.putString("name", categoryAdapter.getCategoryInPosition(position).getCategory());
                            updateCategoryFragment.setArguments(bundle);
                            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView4, updateCategoryFragment).addToBackStack(null).commit();
                        }

                    }
                }).attachToRecyclerView(categoryRecyclerView);


            }
        }, 1000);

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), TodoListActivity.class);
        startActivity(intent);
    }
}