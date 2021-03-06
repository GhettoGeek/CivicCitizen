package com.example.votingapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.votingapp.Network;
import com.example.votingapp.activities.MainActivity;
import com.example.votingapp.adapters.ElectionsAdapter;
import com.example.votingapp.models.Election;
import com.example.votingapp.R;

import java.util.ArrayList;
import java.util.List;

public class ElectionFragment extends Fragment {

    private static final String TAG = "ElectionFragment";
    private NotificationManagerCompat notificationManager;
    static List<Election> elections;
    static RecyclerView rvElections;
    static ElectionsAdapter adapter;
    static Button btnGone;
    static TextView tvGone;

    public ElectionFragment() {
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
        return inflater.inflate(R.layout.fragment_election, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Upcoming Elections");
        notificationManager = NotificationManagerCompat.from(getContext());

        elections = new ArrayList<>();
        rvElections = view.findViewById(R.id.rvElections);
        tvGone = view.findViewById(R.id.tvNone);
        btnGone = view.findViewById(R.id.btnNone);
        adapter = new ElectionsAdapter(getContext(), elections);
        rvElections.setLayoutManager(new LinearLayoutManager(getContext()));
        rvElections.setAdapter(adapter);
        rvElections.setVisibility(View.GONE);
        btnGone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.goInfo();
            }
        });
        Network.getElections();
    }



    public static void hasElections() {
        btnGone.setVisibility(View.GONE);
        tvGone.setVisibility(View.GONE);
        rvElections.setVisibility(View.VISIBLE);
    }

    public static void addUserElection(Election election) {
        Log.i(TAG, "contests is not null!");
        elections.add(election);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "election: " + election.toString());
        hasElections();
    }
}