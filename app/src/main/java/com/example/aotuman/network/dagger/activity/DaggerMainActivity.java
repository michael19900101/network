package com.example.aotuman.network.dagger.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aotuman.network.R;
import com.example.aotuman.network.dagger.component.DaggerPlatform;
import com.example.aotuman.network.dagger.component.DaggerTestCreateComponent;
import com.example.aotuman.network.dagger.component.DaggerWaimaiPingTai;
import com.example.aotuman.network.dagger.component.TestCreateComponent;
import com.example.aotuman.network.dagger.component.WaimaiPingTai;
import com.example.aotuman.network.dagger.enity.Test;
import com.example.aotuman.network.dagger.enity.TestSingleton;
import com.example.aotuman.network.dagger.enity.ZhaiNan;
import com.example.aotuman.network.dagger.module.ShangjiaAModule;

import javax.inject.Inject;


public class DaggerMainActivity extends AppCompatActivity {

    @Inject
    public TestSingleton testSingleton;

    Button mBtnTestInject;
    Button mBtnTestModule;
    Button mBtnTestZhuru;
    Button mBtnTestActivity;
    Button mBtnJumpToSecond;
    Button mBtnJumpToThird;



    @Inject
    int testvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_main);
        mBtnTestInject = findViewById(R.id.btn_test_inject);

        final ZhaiNan zainan = DaggerPlatform.builder()
                .shangjiaAModule(new ShangjiaAModule("王小二包子店"))
                .build()
                .waimai();

        mBtnTestInject.setOnClickListener(v ->
                Toast.makeText(DaggerMainActivity.this,zainan.eat(),Toast.LENGTH_LONG).show());

        mBtnTestModule = findViewById(R.id.btn_test_module);

        final ZhaiNan zainan1 = DaggerWaimaiPingTai.builder()
                .shangjiaAModule(new ShangjiaAModule("衡阳鱼粉店"))
                .build()
                .waimai();

        mBtnTestModule.setOnClickListener(v ->
                Toast.makeText(DaggerMainActivity.this,zainan1.eat(),Toast.LENGTH_LONG).show());

        mBtnTestZhuru = findViewById(R.id.btn_test_zhuru);
        mBtnTestActivity = findViewById(R.id.btn_test_inject_act);
        final ZhaiNan zhaiNan = new ZhaiNan();
        WaimaiPingTai daggerWaimaiPingTai = DaggerWaimaiPingTai.builder()
                .shangjiaAModule(new ShangjiaAModule("常德津市牛肉粉"))
                .build();
        daggerWaimaiPingTai.zhuru(zhaiNan);

        mBtnTestZhuru.setOnClickListener(v ->
                Toast.makeText(DaggerMainActivity.this,zhaiNan.eat(),Toast.LENGTH_LONG).show());

        daggerWaimaiPingTai.inject(this);
        mBtnTestActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DaggerMainActivity.this,"testvalue is "+ testvalue,Toast.LENGTH_LONG).show();
            }
        });

        mBtnJumpToSecond = findViewById(R.id.btn_jump_second);
        mBtnJumpToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaggerMainActivity.this,DaggerSecondActivity.class);
                startActivity(intent);

                Toast.makeText(DaggerMainActivity.this,"testsingleton is "+ testSingleton,Toast.LENGTH_LONG).show();
            }
        });
        mBtnJumpToThird = findViewById(R.id.btn_jump_third);
        mBtnJumpToThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaggerMainActivity.this,DaggerThirdActivity.class);
                startActivity(intent);
            }
        });

        TestCreateComponent testCreateComponent = DaggerTestCreateComponent.create();
        Test test = testCreateComponent.ceshi();

    }
}
