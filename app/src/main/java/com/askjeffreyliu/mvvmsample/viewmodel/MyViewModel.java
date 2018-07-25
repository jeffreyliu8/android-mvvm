package com.askjeffreyliu.mvvmsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.askjeffreyliu.mvvmsample.MyApplication;
import com.askjeffreyliu.mvvmsample.endpoint.MyWebService;
import com.askjeffreyliu.mvvmsample.endpoint.Resource;
import com.askjeffreyliu.mvvmsample.repository.GithubRepository;
import com.askjeffreyliu.mvvmsample.room.model.Project;

import java.util.List;

import javax.inject.Inject;


public class MyViewModel extends AndroidViewModel {

    @Inject
    MyWebService service;

    private LiveData<Resource<List<Project>>> deviceConfigs;

    public MyViewModel(@NonNull Application application) {
        super(application);

        ((MyApplication) getApplication())
                .getDaggerComponent()
                .inject(this);

        GithubRepository githubRepository = new GithubRepository(application, service);
        deviceConfigs = githubRepository.loadProjects();
    }

    @NonNull
    public LiveData<Resource<List<Project>>> getDeviceConfigs() {
        return deviceConfigs;
    }
}
