package com.askjeffreyliu.mvvmsample;


import android.app.Application;

import com.askjeffreyliu.mvvmsample.dependency.DaggerMyDaggerComponent;
import com.askjeffreyliu.mvvmsample.dependency.MyDaggerComponent;
import com.askjeffreyliu.mvvmsample.dependency.WebServiceModule;

public class MyApplication extends Application {

    private MyDaggerComponent daggerComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        daggerComponent = createDaggerComponent();
    }

    public MyDaggerComponent getDaggerComponent() {
        return daggerComponent;
    }

    private MyDaggerComponent createDaggerComponent() {
        return DaggerMyDaggerComponent
                .builder()
                .webServiceModule(new WebServiceModule())
                .build();
    }
}
