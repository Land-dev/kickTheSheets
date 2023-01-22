package com.example.alarmapp;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment {
    View view;
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    RecyclerView recyclerView;
    List<MyModel> myModelList;
    CustomAdapter customAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        {
            view = inflater.inflate(R.layout.fragment_home, container, false);

            Button adder = view.findViewById(R.id.addAlarm);
            displayItems();
            adder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(new AlarmSetterFragment());

                }
            });
            return view;

        }

    }

    private void displayItems() {
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        myModelList = new ArrayList<>();
        myModelList.add(new MyModel("School Time", "10:30 AM"));
        myModelList.add(new MyModel("Soccer Practice", "7:00 AM"));
        myModelList.add(new MyModel("Band Audition", "10:00 AM"));
        myModelList.add(new MyModel("Badminton Tryouts", "7:00 AM"));
        myModelList.add(new MyModel("Homework Due", "6:32 AM"));
        myModelList.add(new MyModel("Meditation Time", "11:00 AM"));
        myModelList.add(new MyModel("Go to the Gym", "8:30 AM"));
        myModelList.add(new MyModel("Vaccumn the floors", "11:00AM"));
        myModelList.add(new MyModel("Soccer Practice", "7:00 AM"));
        myModelList.add(new MyModel("Soccer Practice", "7:00 AM"));
        myModelList.add(new MyModel("Soccer Practice", "7:00 AM"));
        customAdapter = new CustomAdapter(getActivity(), myModelList);
        recyclerView.setAdapter(customAdapter);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}