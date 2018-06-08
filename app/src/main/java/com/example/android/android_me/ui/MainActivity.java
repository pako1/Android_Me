package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int footIndex;
    public static final String HEAD = "head_index";
    public static final String BODY = "body_index";
    public static final String FOOT = "foot_index";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
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
