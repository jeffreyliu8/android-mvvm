package com.askjeffreyliu.mvvmsample.dependency;

import com.askjeffreyliu.mvvmsample.viewmodel.MyViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {WebServiceModule.class})
public interface MyDaggerComponent {
    void inject(MyViewModel myViewModel);
}