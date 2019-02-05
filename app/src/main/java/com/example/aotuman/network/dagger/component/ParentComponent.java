package com.example.aotuman.network.dagger.component;


import com.example.aotuman.network.dagger.module.XiaoChiModule;

import dagger.Component;

/**
 * Created by Frank on 2017/7/20.
 */
@Component(modules = XiaoChiModule.class)
public interface ParentComponent {
    SubComponent provideSubComponent();
}
