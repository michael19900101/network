package com.example.aotuman.network.dagger.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aotuman.network.R;
import com.example.aotuman.network.dagger.annotation.Computer;
import com.example.aotuman.network.dagger.annotation.Phone;
import com.example.aotuman.network.dagger.component.DaggerActivityComponent;
import com.example.aotuman.network.dagger.enity.TestSingleton;

import javax.inject.Inject;

public class DaggerSecondActivity extends AppCompatActivity {
    @Inject
    TestSingleton testSingleton1;
    @Inject
    TestSingleton testSingleton2;

    @Inject
    @Phone
    String phone;

    @Inject
    @Computer
    String computer;

    Button mBtnTestSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_sencond);

        mBtnTestSingleton = (Button) findViewById(R.id.btn_test_singleton);

        DaggerActivityComponent.builder()
                .build()
                .inject(this);

        mBtnTestSingleton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DaggerSecondActivity.this,"test1 hashcode:"+testSingleton1.toString()
                    +" test2 hashcode:"+testSingleton2.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
