package com.app.intrinsic.intrinsicapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {
    Random rand = new Random();
    int vanillacheck = 0, chocolatecheck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button secondpbutton = (Button) findViewById(R.id.secondpbutton);
        Button rollgo = (Button) findViewById(R.id.roll);

        //flavorbuttons
        Button vanilla = (Button) findViewById(R.id.vanillabutton);
        Button chocolate = (Button) findViewById(R.id.chocolatebutton);

        //flavor arraylist
        ArrayList flavors = new ArrayList();
        flavors.add("vanilla");
        flavors.add("chocolate");
        flavors.add("taro");

        //conditions
        vanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vanilla.setBackgroundColor(Color.DKGRAY);
                if(vanillacheck == 1){
                    vanilla.setBackgroundResource(R.drawable.button);
                    flavors.add("vanilla");
                    vanillacheck = 0;
                    return;
                }
                flavors.remove("vanilla");
                vanillacheck = 1;
            }
        });

        chocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chocolate.setBackgroundColor(Color.DKGRAY);
                if(chocolatecheck == 1){
                    chocolate.setBackgroundResource(R.drawable.button);
                    flavors.add("chocolate");
                    chocolatecheck = 0;
                    return;
                }
                flavors.remove("chocolate");
                chocolatecheck = 1;
            }
        });

        secondpbutton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                goToMainActivity();

            }

        });

        rollgo.setOnClickListener(new View.OnClickListener() {

            @Override
            //concatenate food arrays
            public void onClick(View v) {

                TextView yolo = (TextView) findViewById(R.id.text);

                int random = rand.nextInt(flavors.size());
                yolo.setText(String.valueOf(flavors.get(random)));


            }

        });

    }


    private void goToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }

    private void goToSecondActivity() {

        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);

    }
}

