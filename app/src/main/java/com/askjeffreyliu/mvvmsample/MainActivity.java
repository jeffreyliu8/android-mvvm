package com.askjeffreyliu.mvvmsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.askjeffreyliu.mvvmsample.adapter.MyAdapter;
import com.askjeffreyliu.mvvmsample.endpoint.Resource;

import com.askjeffreyliu.mvvmsample.room.model.Project;
import com.askjeffreyliu.mvvmsample.viewmodel.MyViewModel;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ProgressBar progressBar;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        errorText = findViewById(R.id.errorText);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter();

        mRecyclerView.setAdapter(adapter);

        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        myViewModel.getDeviceConfigs().observe(this, new Observer<Resource<List<Project>>>() {
            @Override
            public void onChanged(@NonNull final Resource<List<Project>> response) {
                adapter.updateList(response.data);
                switch (response.status) {
                    case SUCCESS: {
                        Log.d(TAG, "success");
                        progressBar.setVisibility(View.GONE);
                        errorText.setVisibility(View.GONE);
                        break;
                    }
                    case LOADING: {
                        Log.d(TAG, "loading");
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    }
                    case ERROR: {
                        Log.e(TAG, "error");
                        progressBar.setVisibility(View.GONE);
                        errorText.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });
    }
}
