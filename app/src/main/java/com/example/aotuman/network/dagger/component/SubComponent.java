package com.example.aotuman.network.dagger.component;


import com.example.aotuman.network.dagger.activity.DaggerThirdActivity;
import com.example.aotuman.network.dagger.module.FoodModule;

import dagger.Subcomponent;

/**
 * Created by Frank on 2017/7/20.
 */
@Subcomponent(modules = FoodModule.class)
public interface SubComponent {
    void inject(DaggerThirdActivity activity);
}
