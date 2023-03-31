package com.webtech.todo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.webtech.todo.dao.TodoDAO;
import com.webtech.todo.pojo.Todo;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static final String DB_NAME = "todo.db";

    public static TodoDatabase getDb(Context context) {
        return Room.databaseBuilder(context, TodoDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }

    public abstract TodoDAO todoDAO();
}
