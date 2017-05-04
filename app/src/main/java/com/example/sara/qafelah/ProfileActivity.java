package com.example.sara.qafelah;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    TextView score, level;
    EditText nameEdit, emailEdit;
    Button signoutBtn, deleteBtn;
    SharedPreferences userAccount;
    String oldName, oldEmail, newName, newEmail;
    int UPDATE_NAME = 0, UPDATE_EMAIL = 1, SIGNOUT = 2, DELETE_ACCOUNT = 3;
    DBClass appDB = new DBClass(ProfileActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        nameEdit = (EditText) findViewById(R.id.PnameEdit);
        emailEdit = (EditText) findViewById(R.id.PemailEdit);
        score = (TextView) findViewById(R.id.PscoreValue);
        level = (TextView) findViewById(R.id.PlevelValue);

        signoutBtn = (Button) findViewById(R.id.PsignOutBtn);
        deleteBtn = (Button) findViewById(R.id.PdeleteBtn);


        userAccount = getSharedPreferences("UserAccount", 0);

        nameEdit.setText(userAccount.getString("name", null));
        emailEdit.setText(userAccount.getString("email", null));
        score.setText(String.valueOf(userAccount.getInt("score", 0)));
        level.setText(String.valueOf(userAccount.getInt("level", 0)));

        oldName = nameEdit.getText().toString();
        oldEmail = emailEdit.getText().toString();

        nameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                newName = nameEdit.getText().toString();

                if (!(newName.equals(oldName))) {

                    showCostumeDialog("هل تريد تحديث بياناتك ؟", "تحديث", "إلغاء", UPDATE_NAME);
//                  nameEdit.setText(oldName);

                }

                return false;
            }
        });

        emailEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                newEmail = emailEdit.getText().toString();

                if (!(newEmail.equals(oldEmail))) {
                    showCostumeDialog("هل تريد تحديث بياناتك ؟", "تحديث", "إلغاء", UPDATE_EMAIL);
                    // emailEdit.setText(oldEmail);

                }

                return false;
            }
        });

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCostumeDialog("أ تريد تسجيل خروجك ؟", "تسجيل الخروج", "إلغاء", SIGNOUT);
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCostumeDialog("أ تريد مغادرة عالم قافلة :( ؟", "حذف حسابي", "إلغاء", DELETE_ACCOUNT);

            }
        });


    }


    public void showCostumeDialog(String msg, String yesMsg, String noMsg, final int flag) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        View DView = getLayoutInflater().inflate(R.layout.warning_dialog_layout, null);


        builder.setView(DView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView msgView = (TextView) DView.findViewById(R.id.msg);
        Button yesBtn = (Button) DView.findViewById(R.id.yesBtn);
        Button noBtn = (Button) DView.findViewById(R.id.noBtn);

        msgView.setText(msg);
        yesBtn.setText(yesMsg);
        noBtn.setText(noMsg);

        //when user clicks ok button, ensure if Dialog is for delete account or update or signout
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (flag) {
                    case 0:
                        updateEditorName();
                        break;
                    case 1:
                        updateEditorEmail();
                        break;
                    case 2:
                        signout();
                        break;
                    case 3:
                        deleteAccount();
                        break;

                }

                dialog.dismiss();

            }

        });

        //when user clicks cancle button
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag) {
                    case 0:
                        nameEdit.setText(oldName);
                        break;
                    case 1:
                        emailEdit.setText(oldEmail);
                        break;

                }
                dialog.dismiss();

            }

        });

    }

    public void updateEditorName() {

        userAccount = getSharedPreferences("UserAccount", 0);
        SharedPreferences.Editor editor = userAccount.edit();

        Toast.makeText(ProfileActivity.this, "1 \n" + appDB.showDB(), Toast.LENGTH_LONG).show();

        //call update name method
        if (appDB.updateName(newName, emailEdit.getText().toString()) > 0) {
            editor.putString("name", newName);
            editor.commit();
            Toast.makeText(ProfileActivity.this, "2 \n" + appDB.showDB(), Toast.LENGTH_LONG).show();
            Toast.makeText(ProfileActivity.this, "تم التحديث بنجاح", Toast.LENGTH_LONG).show();

        }

    }

    public void updateEditorEmail() {

        userAccount = getSharedPreferences("UserAccount", 0);
        SharedPreferences.Editor editor = userAccount.edit();

        if (!appDB.isEmailExist(newEmail)) {
            if (appDB.updateEmail(newEmail, oldEmail) > 0) {
                editor.putString("email", newEmail);
                editor.commit();
                Toast.makeText(ProfileActivity.this, "تم التحديث بنجاح", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void signout() {

        userAccount = getSharedPreferences("UserAccount", 0);
        SharedPreferences.Editor editor = userAccount.edit();

        editor.putString("name", "");
        editor.putString("email", "");
        editor.putString("password", "");
        editor.putInt("score", 0);
        editor.putInt("level", 1);
        editor.putBoolean("hasLoggedIn", false);
        editor.commit();
        Toast.makeText(ProfileActivity.this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_LONG).show();
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        finish();

    }

    public void deleteAccount() {

        userAccount = getSharedPreferences("UserAccount", 0);
        SharedPreferences.Editor editor = userAccount.edit();

        //call delete row method
        if (appDB.deleteRow(emailEdit.getText().toString()) > 0) {
            editor.putString("name", "");
            editor.putString("email", "");
            editor.putString("password", "");
            editor.putInt("score", 0);
            editor.putInt("level", 1);
            editor.putBoolean("hasLoggedIn", false);
            editor.commit();
            Toast.makeText(ProfileActivity.this, "تم حذف الحساب", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ProfileActivity.this, SignUpActivity.class));
            finish();

        }

    }
}





