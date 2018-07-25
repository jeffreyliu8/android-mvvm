package com.askjeffreyliu.mvvmsample.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.askjeffreyliu.mvvmsample.room.model.Project;


import java.util.List;

@Dao
public interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project project);

    @Query("DELETE FROM project_table")
    void deleteAll();

    @Query("SELECT * from project_table ORDER BY id ASC")
    LiveData<List<Project>> getProjects();
}
