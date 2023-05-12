package com.example.todo_final;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.todo_final.model.Category;
import com.example.todo_final.model.Todo;
import com.example.todo_final.viewModel.CategoryViewModel;
import com.example.todo_final.viewModel.TodoViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ToDoFragment extends Fragment {

    CategoryViewModel categoryViewModel;
    TodoViewModel todoViewModel;
    Spinner categoryDropdownList;
    EditText todoDate, todoTitle, todoDescription;

    RadioGroup todoPriority;

    CheckBox todoIsComplete;

    Button todoSaveBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);
        init(view);

        todoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        todoSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        return view;
    }

    private void init(View view) {
        categoryViewModel = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        categoryDropdownList = view.findViewById(R.id.category_spinner);
        todoDate = view.findViewById(R.id.editTextDate);
        todoTitle = view.findViewById(R.id.fragment_todo_txt_title);
        todoDescription = view.findViewById(R.id.fragment_todo_txtDescription);
        todoPriority = view.findViewById(R.id.fragment_todo_rg_priority);
        todoIsComplete = view.findViewById(R.id.checkBox);
        todoSaveBtn = view.findViewById(R.id.fragmentCategoryBtnSave);

        categoryViewModel.getCategoryList().observe(getViewLifecycleOwner(), this::setCategorySpinner);
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                todoDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year + "/");
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void setCategorySpinner(List<Category> categories) {
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);
        categoryDropdownList.setAdapter(adapter);
    }

    private void saveData() {
        String title = todoTitle.getText().toString();
        String desc = todoDescription.getText().toString();
        String date = todoDate.getText().toString();
        Category category = (Category) categoryDropdownList.getSelectedItem();
        int checkedRadio = todoPriority.getCheckedRadioButtonId();
        int prority = 0;
        switch (checkedRadio) {
            case R.id.fragment_todo_rb_low:
                prority = 2;
            case R.id.fragment_todo_rb_mid:
                prority = 1;
            case R.id.fragment_todo_rb_high:
                prority = 0;
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        Date todoDateOn = null;
        try {
            todoDateOn = formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        Date createdOn = new Date();
        boolean isComplete = todoIsComplete.isChecked();
        Todo todo = new Todo(title, desc, todoDateOn, isComplete, prority, category.getCategoryId(), createdOn);

        todoViewModel.saveTodo(todo);
        Toast.makeText(getActivity(), "Todo Saved", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), TodoListActivity.class);
        intent.putExtra("categoryId", category.getCategoryId());
        startActivity(intent);
    }
}