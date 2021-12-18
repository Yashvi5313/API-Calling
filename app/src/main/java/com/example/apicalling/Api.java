package com.example.apicalling;

import com.example.apicalling.Pojo.Root;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //static String BASE_URL = "http://invicainfotech.com/apicall/mydata";

    @GET("mydata")
    Call<Root> getcontact();
}
