package com.example.sara.qafelah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit , emailEdit , passEdit , confirmPassEdit ;
    Button registerBtn ;
    TextView logView , passError , emailError ;
    String name , email , pass , confirmPass ;
    DBClass appDB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameEdit = (EditText) findViewById(R.id.nameEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);
        confirmPassEdit = (EditText) findViewById(R.id.confirmPassEdit);

        registerBtn = (Button) findViewById(R.id.RegisterBtn);
        logView = (TextView) findViewById(R.id.loginView);
        emailError = (TextView) findViewById(R.id.emailError) ;
        passError = (TextView) findViewById(R.id.passError) ;

        appDB = new DBClass(this);

        //to disable copy/past in password fields :)
        passEdit.setLongClickable(false);
        confirmPassEdit.setLongClickable(false);

        MyListener handler = new MyListener();
        registerBtn.setOnClickListener(handler);
        logView.setOnClickListener(handler);

    }

    //I use event handle method like java :) I don't like repeat code *.*

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.RegisterBtn){
                name = nameEdit.getText().toString();
                email = emailEdit.getText().toString();
                pass = passEdit.getText().toString();
                confirmPass = confirmPassEdit.getText().toString();
                email = email.toLowerCase();

                emailError.setText("");
                passError.setText("");
                passEdit.setText("");
                confirmPassEdit.setText("");

                if(!appDB.isEmailExit(email)) {

                    if (pass.equals(confirmPass)) {
                        appDB.addUserRecord(name,email,pass, 0 , 1 );

                    } else {
                        passError.setText("كلمات السر غير متطابقة :(");
                    }

                } else {
                    emailError.setText("البريد الإلكتروني موجود");
                }

                Toast.makeText(getApplicationContext() , appDB.showDB() , Toast.LENGTH_LONG).show();

            }


            //go to login Activity
            if(v.getId() == R.id.loginView){

//                Intent in = new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(in);

                }
            }
        }
    }



