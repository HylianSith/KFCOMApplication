package com.mycompany.kfcomapplication.utils;

import com.mycompany.kfcomapplication.models.Pin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jason_000 on 11/30/2016.
 */

public interface PinsApiInterface {
    @GET( "Pins.json" )
    Call<List<Pin>> getStreams();
}
