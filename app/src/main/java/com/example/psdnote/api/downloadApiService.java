package com.example.psdnote.api;

import com.example.psdnote.model.LoginData;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface downloadApiService {
    @POST("/login")
    Observable<LoginData> getLoginUser(@Query("email") String email, @Query("password") String password);
}
