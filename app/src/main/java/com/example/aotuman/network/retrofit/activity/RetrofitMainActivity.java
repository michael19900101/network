package com.example.aotuman.network.retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aotuman.network.R;
import com.example.aotuman.network.retrofit.MovieService;
import com.example.aotuman.network.retrofit.bean.NowPlayingMovie;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMainActivity extends AppCompatActivity {

    @BindView(R.id.btn_request)
    Button btnRequest;

    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });


    }

    private void request(){
        // 步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.douban.com/") //设置最基本url,也就是http请求的url前缀,可以把项目中重复的前缀用这个来设置
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        MovieService service = retrofit.create(MovieService.class);

        // 对 发送请求 进行封装
        Call<NowPlayingMovie> call = service.listNowPlayingMovies();

        // 步骤6:发送网络请求(异步)
        call.enqueue(new Callback<NowPlayingMovie>() {
            @Override
            public void onResponse(Call<NowPlayingMovie> call, Response<NowPlayingMovie> response) {
                NowPlayingMovie nowPlayingMovie = response.body();
                if(nowPlayingMovie != null){
                    tvResult.setText("正在热映："+nowPlayingMovie.getTotal());
                }else {
                    tvResult.setText("正在热映：0");
                }
            }

            @Override
            public void onFailure(Call<NowPlayingMovie> call, Throwable t) {
                System.out.println("连接失败");
            }
        });

        // 发送网络请求（同步）
//                try {
//                    Response<NowPlayingMovie> response = call.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

    }
}
