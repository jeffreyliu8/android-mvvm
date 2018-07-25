package com.askjeffreyliu.mvvmsample.endpoint;


import com.askjeffreyliu.mvvmsample.room.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyWebService {
    @GET("users/{user}/repos")
    Call<List<Project>> getProjectList(@Path("user") String user);
}
