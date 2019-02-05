package com.example.aotuman.network.dagger.component;


import com.example.aotuman.network.dagger.activity.DaggerThirdActivity;
import com.example.aotuman.network.dagger.module.XiaoChiModule;

import dagger.Component;

/**
 * Created by Frank on 2017/7/19.
 */
@Component(modules = XiaoChiModule.class
        ,dependencies = XiaoChiComponent.class)
public interface FoodComponent {
    void inject(DaggerThirdActivity activity);
}
