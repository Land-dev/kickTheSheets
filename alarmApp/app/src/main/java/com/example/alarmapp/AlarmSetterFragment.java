package com.example.alarmapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AlarmSetterFragment extends Fragment {


    View setterView;
    public AlarmSetterFragment() {
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
            setterView = inflater.inflate(R.layout.fragment_alarm_setter, container, false);

            Button scheduler = setterView.findViewById(R.id.sAlarm);
            scheduler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(new HomeFragment());

                }
            });
            return setterView;

        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}