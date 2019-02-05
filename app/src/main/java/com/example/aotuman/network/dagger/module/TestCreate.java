package com.example.aotuman.network.dagger.module;


import com.example.aotuman.network.dagger.enity.Test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Frank on 2017/7/20.
 */
@Module
public class TestCreate {

    @Provides
    public int provideTest1() {
        return 1;
    }

    @Provides
    public String provideTest2() {
        return "test component create()";
    }

    @Provides
    public Test provideTest(){
        return new Test();
    }
}
