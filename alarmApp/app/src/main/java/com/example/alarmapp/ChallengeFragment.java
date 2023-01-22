package com.example.alarmapp;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class ChallengeFragment extends Fragment {

    View challengeView;
    private ProgressBar progressBar;
    private int currentProgress = 0;
    private Handler hdlr = new Handler();

    private TextView congratsText;

    public ChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        challengeView = inflater.inflate(R.layout.fragment_challenge, container, false);
        Button generator = challengeView.findViewById(R.id.genButton);
        TextView challengeText = challengeView.findViewById(R.id.challengeText);
        congratsText = challengeView.findViewById(R.id.congratsText);
        progressBar = challengeView.findViewById(R.id.progressBar);
        progressBar.setMax(100);
        ImageView motivationalImage = challengeView.findViewById(R.id.motivationalImage);

        generator.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                generator.setVisibility(View.GONE);
                generator.setClickable(false);

                Random random = new Random();
// you have also handle min to max index
                String challenges[] = {"Do 20 push ups!","Solve a Rubixs Cube!","Walk 100 steps!","Complete a crossword puzzle!","Do 50 sit ups!","Cook breakfast"};
                int motivational[] = {R.drawable.inthesky,R.drawable.teamwork,R.drawable.fistup,R.drawable.jumping,R.drawable.trophy};
                int index = random.nextInt(challenges.length - 0) + 0;
                int index2 = random.nextInt(motivational.length - 0) + 0;
                challengeText.setText(challenges[index]);
                motivationalImage.setImageResource(motivational[index2]);

                startProgress();
            }
        });

        // Inflate the layout for this fragment
        return challengeView;
    }

    public void startProgress() {
        {
            currentProgress = progressBar.getProgress();
            new Thread(new Runnable() {
                public void run() {
                    while (currentProgress < 100) {
                        currentProgress += 1;
                        if(currentProgress >= 98) {
                            congratsText.setText("LETS GO!!! You earned 100 points!");

                        }
                        // Update the progress bar and display the current value in text view
                        hdlr.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(currentProgress);
                            }
                        });
                        try {
                            // Sleep for 100 milliseconds to show the progress slowly.
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }



}