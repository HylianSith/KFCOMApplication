package com.mycompany.kfcomapplication.utils;

import com.mycompany.kfcomapplication.models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by jason_000 on 12/3/2016.
 */

public interface StudentApiInterface {
    @GET( "Students.json" )
    Call<List<Student>> getStreams();
}
