package com.example.aotuman.network.okhttp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.aotuman.network.R;
import com.example.aotuman.network.okhttp.bean.NowPlayingMovie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpMainActivity extends AppCompatActivity {

    @BindView(R.id.btn_request)
    Button btnRequest;

    @BindView(R.id.tv_result)
    TextView tvResult;

    private String url = "http://api.douban.com/v2/movie/nowplaying?apikey=0df993c66c0c636e29ecbb5344252a4a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_main);
        ButterKnife.bind(this);

        btnRequest.setOnClickListener(v -> request());


    }

    private void request(){
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url(url).method("GET",null).build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    final String jsonData = response.body().string();
                    Gson gson = new Gson();
                    final NowPlayingMovie nowPlayingMovie = gson.fromJson(jsonData, new TypeToken<NowPlayingMovie>() {}.getType());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(nowPlayingMovie != null){
                                tvResult.setText("正在热映："+nowPlayingMovie.getTotal());
                            }else {
                                tvResult.setText("正在热映：0");
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("连接失败");
            }
        });

    }
}
