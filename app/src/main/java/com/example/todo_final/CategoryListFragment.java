package com.example.todo_final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todo_final.adapter.CategoryAdapter;
import com.example.todo_final.model.Category;
import com.example.todo_final.viewModel.CategoryViewModel;

import java.util.List;

public class CategoryListFragment extends Fragment implements CategoryAdapter.OnTaskClickListener{

    CategoryViewModel categoryViewModel;
    RecyclerView categoryRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        categoryViewModel = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this::onItemClick);
        categoryViewModel.getCategoryList().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdapter.setCategoryList(categories);
            }
        });

        categoryRecyclerView =view.findViewById(R.id.category_recycler_view);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryRecyclerView.setAdapter(categoryAdapter);

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "Item Clicked on position " + position, Toast.LENGTH_SHORT).show();
    }
}