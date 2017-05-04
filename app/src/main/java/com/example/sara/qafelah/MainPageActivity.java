package com.example.sara.qafelah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainPageActivity extends AppCompatActivity {
    TextView startTxt;
    Button menuBtn;
    SharedPreferences userAccount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        startTxt = (TextView) findViewById(R.id.start);
        menuBtn = (Button) findViewById(R.id.btnmenu);

        userAccount = getSharedPreferences("UserAccount" , 0) ;

        Toast.makeText(getApplicationContext(), userAccount.getString("email" ,null),Toast.LENGTH_LONG).show();
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/ithra-light-webfont.ttf");
        startTxt.setTypeface(type);

        menuBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Int = new Intent(getApplicationContext(), Menu.class);
                startActivity(Int);
                finish();

            }

        });

    }

}
