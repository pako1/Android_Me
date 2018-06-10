package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int footIndex;
    public static final String HEAD = "head_index";
    public static final String BODY = "body_index";
    public static final String FOOT = "foot_index";
    private boolean twoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button button = findViewById(R.id.next_button);
        button.setVisibility(View.GONE);

        GridView gridView = findViewById(R.id.grid_view);
        gridView.setNumColumns(2);

        //if we are on a single screen phone this layout doesn't exists since its in the second activity.
        //but on a tablet it is side by side with the 1st acitivity.
        if (findViewById(R.id.android_me_linear_layout) != null) {
            twoPane = true;
            if (savedInstanceState == null) {
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();
                // ftiaxnw ena fragment kai tou ana8etw times.
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
                FragmentManager Manager = getSupportFragmentManager();
                Manager.beginTransaction()
                        .add(R.id.body_container, headFragment)
                        .commit();
                // ftiaxnw ena fragment kai tou ana8etw times.

                BodyPartFragment footFragment = new BodyPartFragment();
                footFragment.setmImageIds(AndroidImageAssets.getLegs());
                FragmentManager foot = getSupportFragmentManager();
                foot.beginTransaction()
                        .add(R.id.foot_container, headFragment)
                        .commit();
            }
        } else {
            twoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "you clicked the " + position, Toast.LENGTH_LONG).show();
        // an einai 5/12 auto kanei 0
        // an einai 19/12 auto kanei 1
        // an einai 30/21 auto kanei 3
        //sto 0 eimaste stin 1h lista, sto 1 sth 2h lista kai sto 2 eimaste stin 3h lista

        int bodyPartNumber = position / 12;

        int listIndex = position - 12 * bodyPartNumber;

        if (twoPane) {
            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, bodyPartFragment)
                            .commit();
                    break;
                case 1:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, bodyPartFragment)
                            .commit();
                    break;
                case 2:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.foot_container, bodyPartFragment)
                            .commit();
                    break;
            }

        } else {

            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    footIndex = listIndex;
                    break;
                default:
                    break;
            }

            final Bundle bundle = new Bundle();
            bundle.putInt(HEAD, headIndex);
            bundle.putInt(BODY, bodyIndex);
            bundle.putInt(FOOT, footIndex);

            final Intent androidMeIntent = new Intent(this, AndroidMeActivity.class);
            androidMeIntent.putExtras(bundle);
            Button nextButton = findViewById(R.id.next_button);

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(androidMeIntent);
                }
            });
        }
    }
}
