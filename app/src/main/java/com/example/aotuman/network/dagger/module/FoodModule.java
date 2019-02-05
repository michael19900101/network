package com.example.aotuman.network.dagger.module;

import com.example.aotuman.network.dagger.enity.Baozi;
import com.example.aotuman.network.dagger.enity.Kangshifu;
import com.example.aotuman.network.dagger.enity.Noodle;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Frank on 2017/7/19.
 */
@Module
public class FoodModule {

    @Provides
    public Baozi provideBaozi() {
        return new Baozi();
    }

    @Provides
    public Noodle provideNoodle() {
        return new Kangshifu();
    }
}
