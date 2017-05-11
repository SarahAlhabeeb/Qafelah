package com.example.sara.qafelah;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity {
        TextView about;
        TextView copyRights;
        Button menuBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about= (TextView) findViewById(R.id.txtAbout);
        copyRights= (TextView) findViewById(R.id.txtCopyRights);
        menuBtn= (Button) findViewById(R.id.btnmenu);

        about.setText("تطبيق قافلة يأخذ المستخدم في رحلة ممتعة \n" +
                "مع سيرة النبي محمد ﷺ، حيث تتوقف القافلة \n" +
                "في كل محطة من محطاتها مع مرحلة من مراحل \n" +
                "حياة النبي ﷺ، ومن خلال الأسئلة والتلميحات\n" +
                " التي يقدمها التطبيق ستزداد معرفة المستخدم\n" +
                " بسيرة نبيه، وسيصبح أكثر ارتباطا وحبا بها حيث \n" +
                "يقوم التطبيق بتذكيره بدخول التطبيق لحل مرحلة يوميا. \n");

        //To change the font
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/AlNile.ttc");
        about.setTypeface(type);

        copyRights.setText("- الخلفية صممت بواسطة frepik.com\n" +
                "- الأيقونات صممت بواسطة flaticon.com");

        copyRights.setTypeface(type);
        menuBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Int = new Intent(getApplicationContext(),Menu.class);
                startActivity(Int);
                finish();

            }

        });

    }
}
