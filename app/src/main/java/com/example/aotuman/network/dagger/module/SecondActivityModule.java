package com.example.aotuman.network.dagger.module;


import com.example.aotuman.network.dagger.annotation.Computer;
import com.example.aotuman.network.dagger.annotation.Phone;
import com.example.aotuman.network.dagger.enity.TestSingleton;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Frank on 2017/7/19.
 */
@Module
public class SecondActivityModule {

    @Provides
    @Singleton
    public TestSingleton provideTestSingleton(){
        return new TestSingleton();
    }

    @Provides
    @Phone
    public String providePhone() {
        return "手机";
    }

    @Provides
    @Computer
    public String provideComputer() {
        return "电脑";
    }

    @Provides
    @Named("TestLazy")
    public String provideTestLazy() {
        return "TestLazy";
    }

    @Provides
    public int provideRandomValue(){
        return (int) Math.random();
    }

}
