package com.example.sara.qafelah;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainPageActivity extends AppCompatActivity {
    Button startBtn;
    Button menuBtn, game1, game2,game3,game4,game5,game6,game7,game8;
    SharedPreferences userAccount;
    int level ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        menuBtn= (Button) findViewById(R.id.btnmenu);
        startBtn= (Button) findViewById(R.id.btnstart);
        game1= (Button) findViewById(R.id.game1);
        game2= (Button) findViewById(R.id.game2);
        game3= (Button) findViewById(R.id.game3);
        game4= (Button) findViewById(R.id.game4);
        game5= (Button) findViewById(R.id.game5);
        game6= (Button) findViewById(R.id.game6);
        game7= (Button) findViewById(R.id.game7);
        game8= (Button) findViewById(R.id.game8);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/ithra-light-webfont.ttf");
        startBtn.setTypeface(type);
        userAccount = getSharedPreferences("UserAccount" , 0) ;

//        Toast.makeText(getApplicationContext(), userAccount.getString("email" ,null),Toast.LENGTH_LONG).show();

      //  Toast.makeText(getApplicationContext(), new DBClass(MainPageActivity.this).showQuestionTable(),Toast.LENGTH_LONG).show();


        level = getSharedPreferences("UserAccount" , 0).getInt("level" , 0);

        menuBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Int = new Intent(getApplicationContext(), Menu.class);
                startActivity(Int);
                finish();

            }

        });

        game1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(level == 1){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
                    warningMessage();

                }
            }

        });

        game2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(level == 2){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
                    warningMessage();

                }
            }

        });

        game3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(level == 3){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
                    warningMessage();

                }
            }

        });

        game4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(level == 4){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
                    warningMessage();

                }
            }

        });

        game5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(level == 5){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
                    warningMessage();

                }
            }

        });

        game6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(level == 6){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
                    warningMessage();

                }
            }

        });

        game7.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(level == 7){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
                    warningMessage();

                }
            }

        });

        game8.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(level == 8){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
                    warningMessage();

                }
            }

        });


    }

    public void warningMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainPageActivity.this);
        builder.setMessage("عليك إكمال المراحل المسبقة أولًا")
                .setCancelable(false)
                .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


}


