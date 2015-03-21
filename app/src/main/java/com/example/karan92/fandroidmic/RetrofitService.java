
package com.example.karan92.fandroidmic;

import java.util.List;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Karan92 on 3/15/2015.
 */
public interface RetrofitService {

    @FormUrlEncoded
    @POST("/api/v1/subscribe")
    Registration signUp(@Field("email") String email);

    @GET("/api/v1/kingdoms")
    List<Kingdoms> getKingdoms();

    @GET("/api/v1/kingdoms/{id}")
    Kingdoms getQuests(@Path("id") String kingdomID);

}
