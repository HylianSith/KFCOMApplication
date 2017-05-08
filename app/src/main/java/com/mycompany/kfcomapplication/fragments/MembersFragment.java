package com.mycompany.kfcomapplication.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;

import com.mycompany.kfcomapplication.R;
import com.mycompany.kfcomapplication.adapters.MembersAdapter;
import com.mycompany.kfcomapplication.models.Student;
import com.mycompany.kfcomapplication.utils.StudentApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jason_000 on 12/1/2016.
 */

public class MembersFragment extends ListFragment {

    private MembersAdapter mAdapter;
    public static MembersFragment getInstance(){
        MembersFragment fragment = new MembersFragment();

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListShown(false);
        getListView().setPadding(16, 16, 16, 16);
        getListView().setDivider(new ColorDrawable(Color.BLACK));
        getListView().setDividerHeight( 4 );
        getListView().setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        getListView().setClipToPadding(true);
        mAdapter = new MembersAdapter(getActivity(), 0 );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/anonymous/2abf90d4848026c16a8021b1553b385e/raw/2de546851b326f273e9600fe0e0f80bad6941201/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentApiInterface studentApiInterface = retrofit.create( StudentApiInterface.class);

        Call<List<Student>> myCall = studentApiInterface.getStreams();
        myCall.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> students, Response<List<Student>> response) {
                if(students == null || students.equals("") || !isAdded()){
                    return;
                }

                for( Student student: response.body() ) {
                    mAdapter.add (student);
                }

                mAdapter.notifyDataSetChanged();
                setListAdapter( mAdapter );
                setListShown(true);

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("KFCOM", "Retrofit error" + t.getMessage());
            }
        });
    }


}
