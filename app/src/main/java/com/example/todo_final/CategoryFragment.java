package com.example.todo_final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.todo_final.model.Category;
import com.example.todo_final.viewModel.CategoryViewModel;

public class CategoryFragment extends Fragment {

    EditText categoryTxt;

    CategoryViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_category, container, false);
        viewModel = new CategoryViewModel(getActivity().getApplication());

        Button btnSave = view.findViewById(R.id.fragmentCategoryBtnSave);
        categoryTxt = view.findViewById(R.id.fragmentCategoryTxtCategory);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Category category = new Category();
                category.setCategory(categoryTxt.getText().toString());
                viewModel.saveCategory(category);
                ((MainActivity) getActivity()).replaceFragmentCategoryList();
            }
        });

        return view;
    }
}