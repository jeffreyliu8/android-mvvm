package com.askjeffreyliu.mvvmsample.repository;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.askjeffreyliu.mvvmsample.endpoint.ApiResponse;
import com.askjeffreyliu.mvvmsample.endpoint.MyWebService;

import com.askjeffreyliu.mvvmsample.endpoint.NetworkBoundResource;
import com.askjeffreyliu.mvvmsample.endpoint.Resource;
import com.askjeffreyliu.mvvmsample.room.dao.ProjectDao;
import com.askjeffreyliu.mvvmsample.room.db.ProjectRoomDatabase;
import com.askjeffreyliu.mvvmsample.room.model.Project;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GithubRepository {

    private final ProjectDao projectDao;
    private final MyWebService myWebService;

    public GithubRepository(Application application, MyWebService myWebService) {
        ProjectRoomDatabase db = ProjectRoomDatabase.getDatabase(application);
        this.projectDao = db.projectDao();
        this.myWebService = myWebService;
    }

    public LiveData<Resource<List<Project>>> loadProjects() {
        return new NetworkBoundResource<List<Project>, List<Project>>() {
            @Override
            protected void saveCallResult(@NonNull List<Project> items) {
                projectDao.deleteAll();
                for (int i = 0; i < items.size(); i++) {
                    projectDao.insert(items.get(i));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Project> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Project>> loadFromDb() {
                return projectDao.getProjects();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Response<List<Project>>>> createCall() {
                final MutableLiveData<ApiResponse<Response<List<Project>>>> result = new MutableLiveData<>();

                myWebService.getProjectList("jeffreyliu8").enqueue(new Callback<List<Project>>() {
                    @Override
                    public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                        result.setValue(new ApiResponse<>(response, null));
                    }

                    @Override
                    public void onFailure(Call<List<Project>> call, Throwable t) {
                        result.setValue(new ApiResponse<>(null, t));
                    }
                });

                return result;
            }
        }.getAsLiveData();
    }
}
