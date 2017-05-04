package com.example.sara.qafelah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText emailEd,passEd;
    String email,pass;
    DBClass appDB;
    TextView txtV;
    SharedPreferences userAccount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btnLogin);
        emailEd = (EditText) findViewById(R.id.txtEmail);
        passEd = (EditText) findViewById(R.id.txtPass);
        txtV= (TextView) findViewById(R.id.signUpTxt);

        //To change the font
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/ithra-light-webfont.ttf");
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/AlNile.ttc");
        login.setTypeface(type);
        emailEd.setTypeface(type);
        passEd.setTypeface(type);
        txtV.setTypeface(type2);

        login.animate();


        appDB = new DBClass(this);

        passEd.setLongClickable(false);

        txtV.setOnClickListener(new View.OnClickListener(){


            public void onClick(View V){

                Intent Int = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(Int);
                finish();
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEd.getText().toString();
                email = email.toLowerCase();
                pass = passEd.getText().toString();
                String[] userData ;

                userAccount = getSharedPreferences("UserAccount" , 0) ;
                SharedPreferences.Editor editor = userAccount.edit() ;

                if(!email.isEmpty() && !pass.isEmpty()) {

                    if (!appDB.isEmailExist(email)) {
                        Toast.makeText(getApplicationContext(), "البريد الإلكتروني غير موجود !", Toast.LENGTH_SHORT).show();
                        emailEd.setText("");
                        passEd.setText("");
                        return;
                    }
                    if (appDB.isEmailExist(email)) {
                        if (pass.equals(appDB.passCheck(email))) {
                            Toast.makeText(getApplicationContext(), "تم تسجيل دخولك بنجاح", Toast.LENGTH_SHORT).show();
                            //here we have to put the Activity we want to open after login :)
                            Intent Int = new Intent(getApplicationContext(), MainPageActivity.class);

                            //call method userData witch returns user's information as string Array then stored it in sharedPreference
                            userData = appDB.userData(email);
                            editor.putString("name" , userData[0]);
                            editor.putString("email" , userData[1]);
                            editor.putString("password" , userData[2]);
                            editor.putInt("score" , Integer.parseInt(userData[3]));
                            editor.putInt("level" , Integer.parseInt(userData[4]));
                            editor.putBoolean("hasLoggedIn" , true);

                            editor.commit();

                           // Toast.makeText(getApplicationContext(),appDB.showDB(),Toast.LENGTH_LONG).show();
                            startActivity(Int);
                            finish();
                        } else
                            Toast.makeText(getApplicationContext(), "كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext() ,"بياناتك ناقصة :(" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}