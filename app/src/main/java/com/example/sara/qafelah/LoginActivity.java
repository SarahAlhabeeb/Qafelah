package com.example.sara.qafelah;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText emailEd,passEd;
    String email,pass;
    DBClass appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btnLogin);
        emailEd = (EditText) findViewById(R.id.txtEmail);
        passEd = (EditText) findViewById(R.id.txtPass);

        appDB = new DBClass(this);

        passEd.setLongClickable(false);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEd.getText().toString();
                email = email.toLowerCase();
                pass = passEd.getText().toString();


                if (!appDB.isEmailExist(email)) {
                    Toast.makeText(getApplicationContext(), "البريد الإلكتروني غير موجود !", Toast.LENGTH_LONG).show();
                    emailEd.setText("");
                    passEd.setText("");
                    return;
                }
                if (appDB.isEmailExist(email)) {
                    if (appDB.emailCheck(email) && appDB.passCheck(pass)) {
                        Toast.makeText(getApplicationContext(), "تم تسجيل دخولك بنجاح", Toast.LENGTH_LONG).show();
                        //here we have to put the Activity we want to open after login :)
                    } else
                        Toast.makeText(getApplicationContext(), "كلمة المرور غير صحيحة", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}