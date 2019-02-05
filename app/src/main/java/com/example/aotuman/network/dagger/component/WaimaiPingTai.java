package com.example.aotuman.network.dagger.component;


import com.example.aotuman.network.dagger.activity.DaggerMainActivity;
import com.example.aotuman.network.dagger.enity.ZhaiNan;
import com.example.aotuman.network.dagger.module.ActivityModule;
import com.example.aotuman.network.dagger.module.ShangjiaAModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Frank on 2017/7/18.
 */
@Singleton
@Component(modules = {ShangjiaAModule.class,ActivityModule.class})
public interface WaimaiPingTai {
    ZhaiNan waimai();

    void zhuru(ZhaiNan zhaiNan);

    void inject(DaggerMainActivity daggerMainActivity);
}
