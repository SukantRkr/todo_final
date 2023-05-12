package com.example.todo_final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todo_final.viewModel.CategoryViewModel;

public class UpdateCategoryFragment extends Fragment {

    EditText txtCategory;
    CategoryViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_category, container, false);
        viewModel = new CategoryViewModel(getActivity().getApplication());

        Button btnSave = view.findViewById(R.id.fragment_Category_update_btn_save);
        txtCategory = view.findViewById(R.id.fragment_category_txt_category_update);

        Bundle bundle= getArguments();
        if(bundle !=null){
            String name= bundle.getString("name");
            int Id=bundle.getInt("id");

            txtCategory.setText(name);



            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String categoryName =txtCategory.getText().toString();
                    if(!categoryName.isEmpty()){
                        viewModel.updateCategory(Id,categoryName );

                        Toast.makeText(getActivity(), "Category Saved", Toast.LENGTH_SHORT).show();
                        ((MainActivity) getActivity()).replaceFragmentCategoryList();
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(), "Category cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else {
            Toast.makeText(getActivity().getApplicationContext(), "Error Fetching Data", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}