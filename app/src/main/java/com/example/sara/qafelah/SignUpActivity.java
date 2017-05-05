package com.example.sara.qafelah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText nameEdit , emailEdit , passEdit , confirmPassEdit ;
    Button registerBtn ;
    TextView logView , passError , emailError ;
    String name , email , pass , confirmPass ;
    DBClass appDB ;
    SharedPreferences userAccount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        nameEdit = (EditText) findViewById(R.id.PnameEdit);
        emailEdit = (EditText) findViewById(R.id.PemailEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);
        confirmPassEdit = (EditText) findViewById(R.id.confirmPassEdit);

        registerBtn = (Button) findViewById(R.id.RegisterBtn);
        logView = (TextView) findViewById(R.id.loginView);
        emailError = (TextView) findViewById(R.id.emailError) ;
        passError = (TextView) findViewById(R.id.passError);

        //To add animation
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);
        alphaAnimation.setRepeatCount(0);
        nameEdit.findViewById(R.id.PnameEdit).startAnimation(alphaAnimation);
        emailEdit.findViewById(R.id.PemailEdit).startAnimation(alphaAnimation);
        passEdit.findViewById(R.id.passEdit).startAnimation(alphaAnimation);
        confirmPassEdit.findViewById(R.id.confirmPassEdit).startAnimation(alphaAnimation);
        registerBtn.findViewById(R.id.RegisterBtn).startAnimation(alphaAnimation);
        logView.findViewById(R.id.loginView).startAnimation(alphaAnimation);


        //To change the font
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/ithra-light-webfont.ttf");
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/Farah.ttc");
        nameEdit.setTypeface(type);
        emailEdit.setTypeface(type);
        passEdit.setTypeface(type);
        confirmPassEdit.setTypeface(type);
        registerBtn.setTypeface(type);
        logView.setTypeface(type2);



        appDB = new DBClass(this);

        //to disable copy/paste in password fields :)
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
                userAccount = getSharedPreferences("UserAccount" , 0) ;
                SharedPreferences.Editor editor = userAccount.edit() ;

                if(name.isEmpty() || email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext() ,"بياناتك ناقصة :(" , Toast.LENGTH_LONG).show();
                } else {
                emailError.setText("");
                passError.setText("");
                passEdit.setText("");
                confirmPassEdit.setText("");

                if(!appDB.isEmailExist(email)) {

                    if (pass.equals(confirmPass)) {
                        appDB.addUserRecord(name,email,pass, 0 , 1 );
                        //Go to Main Page
                         startActivity(new Intent(getApplicationContext() , MainPageActivity.class));
                        //Store date of user in shared Preference
                        editor.putString("name" , name);
                        editor.putString("email" , email);
                        editor.putString("password" , pass);
                        editor.putInt("score" , 0);
                        editor.putInt("level" , 1);
                        editor.putBoolean("hasLoggedIn" , true);
                        editor.commit();

                    } else {
                        passError.setText("كلمات السر غير متطابقة!");
                    }

                } else {
                    emailError.setText("البريد الإلكتروني مسجل مسبقا");
                }

//                Toast.makeText(getApplicationContext() , appDB.showDB() + userAccount.getString("name",null) +
//                        userAccount.getString("email",null) + userAccount.getString("password",null) +
//                        userAccount.getInt("score", 0) + userAccount.getInt("level" , 0) , Toast.LENGTH_LONG).show();

                }
            }


            //go to login Activity
            if(v.getId() == R.id.loginView){
                Intent in = new Intent(getApplicationContext(),LoginActivity.class);
               startActivity(in);

                }
            }
        }
    }



