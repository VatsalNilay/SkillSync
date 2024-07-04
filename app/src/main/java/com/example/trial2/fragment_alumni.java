package com.example.trial2;

import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trial2.data.DBHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_alumni#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_alumni extends Fragment {


    public fragment_alumni() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alumni, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.recycler_alumni_name);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);

        DBHelper forAlumni = new DBHelper(getContext());
        ArrayList<AlumniModel> alumniList = forAlumni.getAlumni();
        Log.d("jabaj","pp");

        RecyclerAlumniDetailAdapter adapter = new RecyclerAlumniDetailAdapter(getContext(), alumniList);
        rv.setAdapter(adapter);
    }


}