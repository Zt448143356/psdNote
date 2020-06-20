package com.example.psdnote.api;


import com.example.psdnote.model.LoginData;
import com.example.psdnote.model.LoginUserUp;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDataApiManger {
    private static final String ENDPOINT = "http://www.zt448143356.com:8001";

    private static final Retrofit sRetrofit = new Retrofit .Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    private static final downloadApiService apiManager = sRetrofit.create(downloadApiService.class);

    public static Observable<LoginData> getUserData(LoginUserUp loginUserUp) {
        return apiManager.getLoginUser(loginUserUp.getEmail(),loginUserUp.getPassword());
    }
}
