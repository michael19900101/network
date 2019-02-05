package com.example.aotuman.network.dagger.enity;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;

/**
 * Created by Frank on 2017/7/19.
 */

public class TestLazy {

    @Inject
    @Named("TestLazy")
    Lazy<String> name;

    public String getName() {
        return name.get();
    }

//    String name;
//
//    public String getName() {
//        if ( name == null ) {
//            name = "TestLazy";
//        }
//
//        return name;
//    }
}
