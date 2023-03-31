package com.webtech.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.webtech.todo.pojo.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivity context = this;
    private TextView listContent;
    private List<Todo> todoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> labels = createItems(initializeData());
        listContent = findViewById(R.id.todo_list);
        listContent.setText("");
        for (String label : labels) {
            listContent.append(label);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create:
                Intent intent = new Intent(context, CreateActivity.class);
                intent.putExtra("todoListSize", todoList.size());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public List<Todo> initializeData() {
        todoList.add(new Todo(1, "Buy Milk", "High"));
        todoList.add(new Todo(2, "Buy Bread", "Medium"));
        todoList.add(new Todo(3, "Buy Eggs", "Low"));
        return todoList;
    }

    public List<String> createItems(List<Todo> todoList) {
        List<String> items = new ArrayList<>();
        for (Todo todo : todoList) {
            items.add(todo.getName() + " - " + todo.getUrgency() + "\n");
        }
        return items;
    }
}