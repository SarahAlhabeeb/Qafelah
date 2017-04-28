package com.example.sara.qafelah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by SARA on 28 أبر، 2017 م.
 */

public class DBClass extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AppDB.db";

    //Define Tables
    public static final String USER_TBL = "User" ;
    public static final String QUESTION_TBL = "Question" ; // contain question , answer , hint , GridID and Difficultly Level
    public static final String GRID_TBL = "Grid" ;

    //Define Columns of User Table
    public static final String ID_COL = "ID" ;
    public static final String NAME_COL = "Name" ;
    public static final String EMAIL_COL = "Email" ;
    public static final String PASS_COL = "Password";
    public static final String SCORE_COL = "Score" ;
    public static final String LEVEL_COL = "Level" ;

    // Columns of Questions Table
    public static final String Q_NO = "Question_No" ;
    public static final String Q_COL = "QuestionName" ;
    public static final String ANS_COL = "Answer" ;
    public static final String HINT_COL = "Hint" ;
    public static final String GRID_ID_COL = "GridID" ;
    public static final String DIFF_LVL_COL = "Difficultly_Lvl" ;

    //Define Columns of Grid Table
    public static final String CHARS_COL = "GridChars" ;



    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + USER_TBL + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                NAME_COL + " TEXT , " +
                EMAIL_COL + " TEXT , " +
                PASS_COL + " TEXT , " +
                SCORE_COL + " INTEGER , " +
                LEVEL_COL + " INTEGER );" );

        db.execSQL("CREATE TABLE " + QUESTION_TBL + "(" +
                Q_NO + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                Q_COL + " TEXT , " +
                ANS_COL + " TEXT , " +
                HINT_COL + " TEXT , " +
                GRID_ID_COL + " INTEGER , " +
                DIFF_LVL_COL + " INTEGER );" );

        db.execSQL("CREATE TABLE " + GRID_TBL + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                CHARS_COL + " TEXT );" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + USER_TBL);
        db.execSQL("DROP TABLE IF EXIST " + QUESTION_TBL);
        db.execSQL("DROP TABLE IF EXIST " + GRID_TBL);
        Log.d("DB", "The table has been removed!");
        onCreate(db);

    }

    public void addUserRecord(String name , String email , String pass , int score , int level ){
        SQLiteDatabase db = getWritableDatabase() ;

        ContentValues userData = new ContentValues();
        userData.put(NAME_COL, name);
        userData.put(EMAIL_COL , email);
        userData.put(PASS_COL,pass);
        userData.put(SCORE_COL , score);
        userData.put(LEVEL_COL , level);

        db.insert(USER_TBL, null , userData);

        db.close();
    }

    //return true if email exit.
    public boolean isEmailExit(String email){

        SQLiteDatabase db =  getWritableDatabase();

        Cursor cr = db.rawQuery("SELECT * FROM " + USER_TBL + " WHERE " + EMAIL_COL + " = '" + email +"' ;" , null ) ;


        if(cr.getCount() == 0 ){
            db.close();
            return false ;
        } else {
            db.close();
            return true ;
        }

    }

    public String showDB(){
        String msg = "" ;
        SQLiteDatabase db =  getWritableDatabase();

        Cursor cr = db.rawQuery("SELECT * FROM " + USER_TBL , null) ;

        cr.moveToPosition(-1);

        while(cr.moveToNext()){
            msg += cr.getInt(0) + "|";
            msg += cr.getString(1) + "|";
            msg += cr.getString(2) + "|" ;
            msg += cr.getString(3) + "|";
            msg += cr.getInt(4) + "|" ;
            msg += cr.getInt(5) + "|\n" ;
        }

        db.close();

        return msg ;
    }


}
