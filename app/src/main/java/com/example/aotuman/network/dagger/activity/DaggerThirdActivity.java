package com.example.aotuman.network.dagger.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aotuman.network.R;
import com.example.aotuman.network.dagger.component.DaggerFoodComponent;
import com.example.aotuman.network.dagger.component.DaggerParentComponent;
import com.example.aotuman.network.dagger.component.DaggerXiaoChiComponent;
import com.example.aotuman.network.dagger.component.XiaoChiComponent;
import com.example.aotuman.network.dagger.enity.Baozi;
import com.example.aotuman.network.dagger.enity.Guazi;
import com.example.aotuman.network.dagger.enity.Huotuichang;
import com.example.aotuman.network.dagger.enity.Noodle;

import javax.inject.Inject;

public class DaggerThirdActivity extends AppCompatActivity {

    Button mBtnTest;

    @Inject
    Guazi guazi;
    @Inject
    Huotuichang huotuichang;
    @Inject
    Baozi baozi;
    @Inject
    Noodle noodle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_third);

        mBtnTest = (Button) findViewById(R.id.test_dependency);
        XiaoChiComponent xiaoChiComponent = DaggerXiaoChiComponent.builder()
                .build();

        DaggerFoodComponent.builder()
                .xiaoChiComponent(xiaoChiComponent)
                .build()
                .inject(this);

        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DaggerThirdActivity.this,
                        baozi+" "+
                        noodle+" "
                        +guazi+""+huotuichang,Toast.LENGTH_LONG).show();
            }
        });

        DaggerParentComponent.builder().build()
                .provideSubComponent().inject(this);

    }
}
