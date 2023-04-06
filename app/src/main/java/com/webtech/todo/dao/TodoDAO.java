package com.webtech.todo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.webtech.todo.pojo.Todo;

import java.util.List;

@Dao
public interface TodoDAO {

    @Query("SELECT * FROM todo WHERE id = :id")
    public Todo find(int id);

    @Query("SELECT * FROM todo")
    public List<Todo> list();

    @Insert
    public void add(Todo... todo);

    @Update
    public void update(Todo... todo);

    @Delete
    public void delete(Todo... todo);
}
