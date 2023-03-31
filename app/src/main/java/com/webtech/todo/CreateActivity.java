package com.webtech.todo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.webtech.todo.pojo.Todo;

public class CreateActivity extends AppCompatActivity {

    private Spinner urgencySpinner;
    private Button createButton;
    private Button cancelButton;
    private EditText titleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        initializeViews();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.todo_priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        urgencySpinner.setAdapter(adapter);

        Intent intent = getIntent();
        int todoListSize = intent.getIntExtra("todoListSize", 0);

        createButton.setOnClickListener(v -> {
            Todo todo = new Todo();
            todo.setName(titleEditText.getText().toString());
            todo.setUrgency(urgencySpinner.getSelectedItem().toString());
            todo.setId(todoListSize + 1);
        });

        cancelButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void initializeViews() {
        urgencySpinner = findViewById(R.id.todo_spinner);
        createButton = findViewById(R.id.todo_add);
        cancelButton = findViewById(R.id.todo_cancel);
        titleEditText = findViewById(R.id.todo_input);
    }
}
