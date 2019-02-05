package com.example.aotuman.network.dagger.component;


import com.example.aotuman.network.dagger.activity.DaggerSecondActivity;
import com.example.aotuman.network.dagger.module.SecondActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Frank on 2017/7/19.
 */
@Singleton
@Component(modules = SecondActivityModule.class)
public interface ActivityComponent {
    void inject(DaggerSecondActivity activity);
}
