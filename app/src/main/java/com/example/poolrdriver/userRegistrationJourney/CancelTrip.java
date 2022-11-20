package com.example.poolrdriver.userRegistrationJourney;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.poolrdriver.R;
import com.example.poolrdriver.adapters.IntroViewPagerAdapter;
import com.example.poolrdriver.classes.screenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CancelTrip extends AppCompatActivity {

    private ViewPager viewPager2;
    private Button next,getStarted;
    private TabLayout indicator;
    private Animation btn_animation;
    private TextView skip;
    private Context mContext;
    private List<screenItem> screenItemsList;
    private List<String> Titles;
    private List<String> Descriptions;
    private List<Integer> pictures;

    private final String[] titles=new String[]{
            "Welcome to poolr",
            "What is poolr?",
            "Our goal?"};

    private final String[] descriptions=new String[]{
            "car pooling on a whole new level",
            "poolr is a rideHailing app which lets you connect with friends to ofset costs without loosing comfort",
            "To offer a platform where passengers get a cheaper, comfortable and effective way to travel"};

    private final int[] pictureResources=new int[]{
            R.drawable.undraw_fast_car_,
            R.drawable.undraw_social,
            R.drawable.undraw_shared_goals
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough_screen);

        initializeVariables();


        //Main workflow
        setListeners();
        initializeSliders();
        setUpAdapter(screenItemsList);
        setUpAnimations();
        setUpPager();


    }

    //initializations
    private void initializeVariables() {
        //set up viewpager
        viewPager2=findViewById(R.id.viewPager);

        //set up button
        next= findViewById(R.id.btn_next_viewPager);
        getStarted= findViewById(R.id.btn_getStarted);

        //textView
        skip= findViewById(R.id.textViewbtn_skipToSignUp);

        //tab
        indicator=findViewById(R.id.tabIndicator);

        mContext=getApplicationContext();

        //initialize ArrayList
        screenItemsList=new ArrayList<>();
        Titles=new ArrayList<>();
        Descriptions=new ArrayList<>();
        pictures=new ArrayList<>();

        //initializeSliders();

    }

    private void initializeSliders() {
        for (int index=0;index<titles.length;index++) {
            CreateScreenItem(titles[index], descriptions[index], pictureResources[index]);

            screenItemsList.add(AddScreenItem(index));
        }

    }

    //listeners
    private void setListeners() {

        getStarted.setOnClickListener(view -> viewCancelDialog());

        //skip screens
        skip.setOnClickListener(view -> viewCancelDialog());

        next.setOnClickListener(view -> displayNextSlide());

        indicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==screenItemsList.size()-1){
                    loadLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void viewCancelDialog() {
        new AlertDialog.Builder(CancelTrip.this)
                .setTitle("Confirmation")
                .setIcon(R.drawable.icons8_bus_ticket_20px)
                .setMessage("Are you sure you would like to cancel this ride?")
                .setPositiveButton("yes", (dialog1, which) -> {
                   //cancel trip
                })
                .setNegativeButton("no",(dialog1,which)->{

                })
                .show();
    }

    //user interface
    private void displayNextSlide() {
        int position = viewPager2.getCurrentItem();
        if (position <screenItemsList.size()){ position++; viewPager2.setCurrentItem(position); }
        if(position ==screenItemsList.size()) loadLastScreen();

    }

    private void loadLastScreen() {
        //views visibility
        getStarted.setVisibility(View.VISIBLE);
        next.setVisibility(View.INVISIBLE);
        indicator.setVisibility(View.INVISIBLE);

        //animation
        getStarted.setAnimation(btn_animation);

    }
    private void setUpPager() {
        //set up with pager
        indicator.setupWithViewPager(viewPager2);
    }

    private void setUpAnimations() {
        btn_animation= AnimationUtils.loadAnimation(mContext,R.anim.btn_getstarted);
    }


    //screen item
    private screenItem AddScreenItem(int index){
        return new screenItem(getTitle(index),getDescription(index),getPicture(index));
    }
    private void CreateScreenItem(String title, String description, int picture){
        addTitle(title); addDescription(description); addPicture(picture);
    }

    private void addTitle(String title){ Titles.add(title);}

    private void addDescription(String description){ Descriptions.add(description); }

    private void addPicture(int picture){ pictures.add(picture);}

    private String getTitle(int index){ return Titles.get(index);}

    private String getDescription(int index){ return Descriptions.get(index); }

    private int getPicture(int index){ return pictures.get(index);}




    //adapters

    private void setUpAdapter(List<screenItem> mList) {

        IntroViewPagerAdapter adapter=new IntroViewPagerAdapter(mContext,mList);
        viewPager2.setAdapter(adapter);

    }



}