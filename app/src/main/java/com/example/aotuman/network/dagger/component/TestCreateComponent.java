package com.example.aotuman.network.dagger.component;


import com.example.aotuman.network.dagger.enity.Test;
import com.example.aotuman.network.dagger.module.TestCreate;

import dagger.Component;

/**
 * Created by Frank on 2017/7/20.
 */
@Component(modules = TestCreate.class)
public interface TestCreateComponent {
    Test ceshi();
}
