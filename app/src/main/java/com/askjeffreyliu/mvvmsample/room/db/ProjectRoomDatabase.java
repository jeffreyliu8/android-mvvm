package com.askjeffreyliu.mvvmsample.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.askjeffreyliu.mvvmsample.room.dao.ProjectDao;
import com.askjeffreyliu.mvvmsample.room.model.Project;

@Database(entities = {Project.class}, version = 1, exportSchema = false)
public abstract class ProjectRoomDatabase extends RoomDatabase {

    public abstract ProjectDao soundProfileDao();

    private static ProjectRoomDatabase INSTANCE;

    public static ProjectRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProjectRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProjectRoomDatabase.class, "project_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}