package com.example.aotuman.network.dagger.module;


import com.example.aotuman.network.dagger.enity.TestSingleton;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Frank on 2017/7/19.
 */
@Module
public class ActivityModule {

    @Provides
    public int provideActivityTest(){
        return 1234567890;
    }

    @Provides
    @Singleton
    public TestSingleton provideSingleton(){
        return new TestSingleton();
    }
}
