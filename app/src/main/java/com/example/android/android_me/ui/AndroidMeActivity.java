package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null) {

            int mainHeadIndex = getIntent().getIntExtra(MainActivity.HEAD, 0);

            int mainBodyIndex = getIntent().getIntExtra(MainActivity.BODY, 0);

            int mainFootIndex = getIntent().getIntExtra(MainActivity.FOOT, 0);

            // ftiaxnw ena fragment kai tou ana8etw times.
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(mainHeadIndex);
            // ftiaxnw ena fragment kai tou ana8etw times.
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setmListIndex(mainBodyIndex);
            // ftiaxnw ena fragment kai tou ana8etw times.

            BodyPartFragment footFragment = new BodyPartFragment();
            footFragment.setmImageIds(AndroidImageAssets.getLegs());
            footFragment.setmListIndex(mainFootIndex);

            // ftiaxnw fragmentmanager poy kanei diaxeirisi twn fragment dynamika
            //use fragmentmanager and transaction to add a fragment to the screen.
            FragmentManager fragmentManager = getSupportFragmentManager();

            //mesw tou fragmentmanager kanw allages sto fragment moy dhladh allazw eikona
            //fragment transaction

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            //mesw tou fragmentmanager kanw allages sto fragment moy dhladh allazw eikona
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            //mesw tou fragmentmanager kanw allages sto fragment moy dhladh allazw eikona
            fragmentManager.beginTransaction()
                    .add(R.id.foot_container, footFragment)
                    .commit();
        }
    }
}
