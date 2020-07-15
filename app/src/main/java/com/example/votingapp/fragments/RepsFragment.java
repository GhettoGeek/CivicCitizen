package com.example.votingapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.votingapp.Network;
import com.example.votingapp.R;
import com.example.votingapp.adapters.RepAdapter;
import com.example.votingapp.models.Candidate;
import com.example.votingapp.models.Rep;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RepsFragment extends Fragment {

    private static final String TAG = "RepsFragment";
    public static List<Rep> reps;
    public static RepAdapter adapter;
    public static RecyclerView rvReps;

    public RepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reps = new ArrayList<>();
        rvReps = view.findViewById(R.id.rvReps);
        adapter = new RepAdapter(getContext(), reps);
        rvReps.setAdapter(adapter);
        rvReps.setLayoutManager(new LinearLayoutManager(getContext()));

        Network.getReps();

    }

    public static void parseNetworkRequest(JSONArray offices, JSONArray people) {
        try {
            reps.addAll(Rep.fromJSON(offices, people));
            adapter.notifyDataSetChanged();
            Log.i(TAG, "retrieved reps: " + reps);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}