package com.webtech.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.webtech.todo.database.TodoDatabase;
import com.webtech.todo.pojo.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void onStart() {
        super.onStart();
        new TodoAsyncTask().execute();
    }

    public List<Todo> initializeData() {
        return TodoDatabase.getDb(context).todoDao().list();
    }

    public List<String> createItems(List<Todo> todoList) {
        List<String> items = new ArrayList<>();
        for (Todo todo : todoList) {
            items.add(todo.getName() + " - " + todo.getUrgency() + "\n");
        }
        return items;
    }

    public class TodoAsyncTask extends AsyncTask<Nullable, Nullable, List<Todo>> {
        @Override
        protected List<Todo> doInBackground(Nullable... nullables) {
            return TodoDatabase.getDb(context).todoDao().list();
        }

        @Override
        protected void onPostExecute(List<Todo> todos) {
            StringBuilder stringBuilder = new StringBuilder();

            for (Todo todo : todos) {
                stringBuilder.append(String.format("%s // %s\n", todo.getName(), todo.getUrgency()));
            }

            listContent.setText(stringBuilder.toString());
        }
    }
}