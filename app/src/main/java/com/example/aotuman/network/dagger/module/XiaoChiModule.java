package com.example.aotuman.network.dagger.module;


import com.example.aotuman.network.dagger.enity.Guazi;
import com.example.aotuman.network.dagger.enity.Huotuichang;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Frank on 2017/7/19.
 */
@Module
public class XiaoChiModule {

    @Provides
    public Guazi provideGuazi() {
        return new Guazi();
    }

    @Provides
    public Huotuichang provideHuotuichang() {
        return new Huotuichang();
    }
}
