package com.example.sara.qafelah;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;

public class MainPageActivity extends AppCompatActivity {
    Button startBtn;
    Button menuBtn, game1;
    SharedPreferences userAccount;
    int level ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        menuBtn= (Button) findViewById(R.id.btnmenu);
        startBtn= (Button) findViewById(R.id.btnstart);
        game1= (Button) findViewById(R.id.game1);

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

                if(level >= 1){
                    startActivity(new Intent(MainPageActivity.this , Level1.class));
                }
                else {
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

        });

    }


}


