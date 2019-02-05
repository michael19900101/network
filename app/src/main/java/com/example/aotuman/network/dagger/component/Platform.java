package com.example.aotuman.network.dagger.component;


import com.example.aotuman.network.dagger.enity.ZhaiNan;
import com.example.aotuman.network.dagger.module.ShangjiaAModule;

import dagger.Component;

/**
 * Created by Frank on 2017/7/11.
 */
@Component(modules = ShangjiaAModule.class)
public interface Platform {
    ZhaiNan waimai();
}
