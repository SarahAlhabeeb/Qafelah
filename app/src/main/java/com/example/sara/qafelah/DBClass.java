package com.example.sara.qafelah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by SARA on 28 أبر، 2017 م.
 */

public class DBClass extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AppDB.db";

    //Define Tables
    public static final String USER_TBL = "User";
    public static final String QUESTION_TBL = "Question"; // contain questions , answer , hint , GridID and Difficultly Level
    public static final String GRID_TBL = "Grid";

    //Define Columns of User Table
    public static final String ID_COL = "ID";
    public static final String NAME_COL = "Name";
    public static final String EMAIL_COL = "Email";
    public static final String PASS_COL = "Password";
    public static final String SCORE_COL = "Score";
    public static final String LEVEL_COL = "Level";

    // Columns of Questions Table
    public static final String Q_NO = "Question_No";
    public static final String Q_COL = "QuestionName";
    public static final String ANS_COL = "Answer";
    public static final String HINT_COL = "Hint";
    public static final String GRID_ID_COL = "GridID";
    public static final String DIFF_LVL_COL = "Difficultly_Lvl";

    //Define Columns of Grid Table
    public static final String CHARS_COL = "GridChars";

    /* TODO هنا يا بنات انسخوا الكلام علشان يسهل عليكن تعبئة البيانات
    //Initialize questions, answers, hints, GridID and Difficultly Level for level1
    public static final String[] questionsL1 = new String[] {"" , "" , "" , "" , ""};
    public static final String[] answersL1 = new String[] {"" , "" , "" , "" , ""} ;
    public static final String[] hintsL1 = new String[]{"" , "" , "" , "" , ""};
    public static final int[] diffLvl = new int[]{};
    */

    //Initialize questions, answers, hints, GridID and Difficultly Level for level1


    public static final String[] questionsL1 = new String[] {"اسم والد الرسول ﷺ" , "اسم والدة الرسول ﷺ" , "اسم جد الرسول ﷺ" ,
            "قال الرسول ﷺ: (أنا ابن ….). يعني إسماعيل عليه السلام وأباه عبدالله." , "توفي والد النبي ﷺ  عن عمر خمس و .... سنة."};
    public static final String[] answersL1 = new String[] {"عبدالله", "آمنة", "عبدالمطلب", "الذبيحين", "عشرين"} ;

    public static final String[] hintsL1 = new String[]{"عبد الله والد رسول الله ﷺ:" +
            " \n أمـه فاطمة بنت عمرو بن عائذ بن عمران بن مخزوم بن يـَقظَة بـن مـرة،" +
            " وكـان عبد الله أحسن أولاد عبد المطلب وأعفهم وأحبهم إليه، وهو الذبيح." ,
            "آمنة بنت وهب والدة رسول الله ﷺ: \n" +
                    "اختار عبد المطلب لولده عبد الله آمنة بنت وهب بن عبد مناف بن زهرة بن كلاب،" +
                    " وهي يومئذ تعد أفضل امرأة في قريش نسبًا وموضًعا، وأبوها سيد بني زهرة نسبًا وشرفًا، فزوجه بها." ,
            "عبـد المطلب جد رسول الله ﷺ: /n" +
                    "خرج هاشم والد عبدالمطلب إلى الشام تاجًرا، فلما قدم المدينة تزوج سلمى بنت عمرو أحد بني عدى بن النجار وأقام عندها،" +
                    " ثم خرج إلى الشام، وهي عند أهلها قد حملت بعبد المطلب. فمات هاشم بغزة من أرض \n" +
                    "فلسطين، وولدت امرأته سلمى عبد المطلب وسمته شيبة؛ لشيبة كانت في رأسه،" +
                    " وجعلت تربيه في بيت أبيها في يثرب، ولم يشعر به أحد من أسرتـه بمكـة. لما صار شيبة ـ عبد المطلب ـ ابن سبع سنين أو ثماني سنين سمع به المطلب." +
                    " فرحل في طلبه، فلما رآه فاضت عيناه، وضمه، وأردفه على راحلته فامتنع حتى تأذن له أمه، فسألها المطلب أن ترسله معه،" +
                    " فامتنعت، فقال : إنما يمضى إلى ملك أبيه وإلى حرم االله فأذنت له، فقدم به مكة مردفه على بعيره،" +
                    " فقال الناس: هذا عبد المطلب، فقال: ويحكم، إنما هو ابن أخى هاشم، فأقام عنده حتى ترعرع،" +
                    " ثم إن المطلب هلك بـ [دمان] من أرض اليمن، فولى بعده عبد المطلب، فأقام لقومه ما كان آباؤه يقيمون لقومهم، " +
                    "وشرف في قومه شرفًا لم يبلغه أحد من آبائه، وأحبه قومه وعظم خطره فيهم. " ,
            "سمي والد الرسول ﷺ عبدالله بالذبيح؛ وذلك أن عبد المطلب لمـا تم أبناؤه عشرة، وعرف أنهم \n" +
                    "يمنعونه أخبرهم بنذره فأطاعوه، فقيل : إنه أقـرع بينهم أيهم ينحر ؟ فطـارت القرعـة على عـبدالله ، وكــان أحـب النـاس إليه. فقال: اللهم هو أو مائة من الإبل." +
                    " ثم أقرع بينه وبين الإبل فطارت القرعة على المائة من الإبل، وقيل:إنه كتب أسماءهم في القداح،وأعطاها قيم هبل، فضرب القداح فخرج القدح على عبد االله ، فأخذه عبد المطلب، وأخذ الشفرة،ثم أقبل به إلى الكعبة ليذبحه،فمنعته قريش،" +
                    "ولاسيما أخواله من بني مخزوم وأخوه أبو طالب. فقال عبد المطلب : فكيف أصنع بنذري؟ فأشاروا عليه أن يأتى عرافة فيستأمرها، فأتاها، فأمرت أن يضرب القداح على عبد االله وعلى عشر من الإبل،" +
                    " فإن خرجت على عبد االله يزيد عشًرا من الإبل حتى يرضى ربه، فإن خرجت على الإبل نحرها، فرجع وأقرع بين عبد االله وبين عشر من الإبل، فوقعت القرعة على عبد االله ،" +
                    " فلم يزل يزيد من الإبل عشًرا عشًرا ولا تقع القرعة إلا عليه إلى أن بلغت الإبل مائة فوقعت القرعة عليها، فنحرت ثم تركت، لا يرد عنها إنسان ولا سبع،" +
                    " وكانت الدية في قريش وفي العرب عشًرا من الإبل، فجرت بعد هذه الوقعة مائة من الإبل، وأقرها الإسلام،" +
                    " وروى عن النبي ﷺ أنه قال: [أنا ابن الذبيحين] يعنى إسماعيل، وأباه عبد االله . \n"
            , "بعدما تزوج عبدالله بآمنة، أرسله عبد المطلب إلى المدينة يمتار لهم تمًرا، فمات بها،" +
            " وقيل : بل خرج تاجًرا إلى الشام،" +
            " فأقبل في عير قريش، فنزل بالمدينة وهو مريض فتوفي بها، ودفن في دار النابغة الجعدى، وله إذ ذاك خمس وعشرون سنة، وكانت وفاته قبل أن يولد رسول الله ﷺ ." +
            " وجميع ما خلفه عبد االله خمسة أجمال، وقطعة غنم، وجارية حبشية اسمها بركة وكنيتها أم أيمن،" +
            " وهي حاضنـة رسول االله صلى االله عليه وسلم. \n"};

    public static final int[] diffLvlL1 = new int[]{1 , 1 , 1 , 5 , 5 };

    public static final String[] questionsL2 = new String[] {"ينتمي رسول الله ﷺ إلى قبيلة ..." , "ولد رسول الله ﷺ في عام ..." , "اسم مرضعة الرسول ﷺ" ,
            "اصطحبه عمه أبو طالب في رحلة إلى ... للتجارة." , "اسم الراهب الذي قابله النبي في رحلته وأخبر عمه بنبوته"};
    public static final String[] answersL2 = new String[] {"قريش", "الفيل", "حليمة", "الشام", "بحيرا"} ;
    public static final String[] hintsL2 = new String[]{"قريش" ,
            "الفيل" ,
            "حليمة السعدية" ,
            "الشام" ,
            "بحيرا الراهب: \n" +
                    "لما بلغ رسول الله صلى الله عليه وسلم اثنتي عشرة سنة- قيل وشهرين وعشرة أيام، " +
                    " ارتحل به أبو طالب تاجرا إلى الشام، حتى وصل إلى بصرى، " +
                    "وكان في هذا البلد راهب عرف ببحيرا واسمه جرجيس فلما نزل الركب خرج إليهم، " +
                    "وأكرمهم بالضيافة، وكان لا يخرج إليهم قبل ذلك وعرف رسول الله صلى الله عليه وسلم بصفته، " +
                    "فقال وهو آخذ بيده: هذا سيد العالمين، هذا يبعثه الله رحمة للعالمين. " +
                    "فقال أبو طالب: وما علمك بذلك؟ فقال: إنكم حين أشرفتم من العقبة لم يبق حجر ولا شجر إلا وخرّ ساجدا،" +
                    " ولا تسجد إلا لنبي، وإني أعرفه بخاتم النبوة في أسفل غضروف كتفه مثل التفاحة،وإنا نجده في كتبنا، " +
                    "وإنا نجده في كتبنا، وسأل أبا طالب أن يرده، ولا يقدم به إلى الشام، خوفا عليه من اليهود، فبعثه عمه مع بعض غلمانه إلى مكة."};
    public static final int[] diffLvlL2 = new int[]{1 , 1 , 1 , 5 , 5};



    //Initialize questions, answers, hints, GridID and Difficultly Level for level1
    public static final String[] questionsL3 = new String[] {"الحرب التي حدثت في مطلع شباب النبي ﷺ" , "الحلف الذي شارك فيه النبي لدفع الظلم" ,"أول زوجة للنبي ﷺ", "قبل زواجهما ذهب في تجارة لها مع غلامها ..." , "اسم الغار الذي كان يتعبد به النبي ﷺ قبل البعثة"};
    public static final String[] answersL3 = new String[] {"الفجار" , "الفضول" , "خديجة" , "ميسرة" , "حراء"} ;
    public static final String[] hintsL3 = new String[]{"حرب الفجار: \n" +
            "ولخمس عشرة من عمره صلى الله عليه وسلم كانت حرب الفجار،" +
            " بين قريش ومن معهم من كنانة وبين قيس عيلان،" +
            " وكان قائد قريش وكنانة كلها حرب بن أمية لمكانته فيهم سنا وشرفا" +
            " وكان الظفر في أول النهار لقيس على كنانة،" +
            "حتى إذا كان في وسط النهار كان الظفر لكنانة على قيس." +
            "وسميت بحرب الفجار لانتهاك حرمات الحرم والأشهر الحرم فيها،" +
            "وقد حضر هذه الحرب رسول الله صلى الله عليه وسلم،وكان ينبل على عمومته، أي يجهز لهم النبل للرمي." ,
            "حلف الفضول:\n" +
                    "وعلى أثر هذه الحرب وقع حلف الفضول في ذي القعدة في شهر حرام،" +
                    "تداعت إليه قبائل من قريش: بنو هاشم، وبنو المطلب، وأسد بن عبد العزى، " +
                    "وزهرة بن كلاب، وتيم بن مرة، فاجتمعوا في دار عبد الله بن جدعان التيمي لسنه وشرفه، " +
                    "فتعاقدوا وتعاهدوا على ألايجدوا بمكة مظلوما من أهلها وغيرهم من سائر الناس إلا قاموا معه،" +
                    " وكانوا على من ظلمه حتى ترد عليه مظلمته، وشهد هذا الحلف رسول الله صلى الله عليه وسلم،" +
                    " وقال بعد أن أكرمه الله بالرسالة: لقد شهدت في دار عبد الله بن جدعان حلفا" +
                    "ما أحب أن لي به حمر النعم، ولو أدعي به في الإسلام لأجبت» ." ,
            "خديجة رضي الله عنها" ,
            "غلامها ميسرة:\n" +
                    "كانت خديجة بنت خويلد امرأة تاجرة ذات شرف ومال،" +
                    " تستأجر الرجال في مالها، وتضاربهم إياه بشيء تجعله لهم،" +
                    " وكانت قريش قوما تجارا فلما بلغها عن رسول الله صلى الله عليه وسلم " +
                    "ما بلغها من صدق حديثه، وعظم أمانته وكرم أخلاقه بعثت إليه، " +
                    "فعرضت عليه أن يخرج في مال لها إلى الشام تاجرا، وتعطيه أفضل " +
                    "ما كانت تعطي غيره من التجار، مع غلام لها يقال لها ميسرة، " +
                    "فقبله رسول الله صلى الله عليه وسلم منها، وخرج في" +
                    " مالها ذلك، وخرج معه غلامها ميسرة حتى قدم الشام." ,
            "في غار حراء\n" +
                    "ولما تقاربت سنه صلى الله عليه وسلم الأربعين،" +
                    "وكانت تأملاته الماضية قد وسعت الشقة العقلية بينه وبين قومه،" +
                    "حبب إليه الخلاء، فكان يأخذ السويق والماء ويذهب إلى غار حراء في جبل النور،" +
                    "فيقيم فيه شهر رمضان، يطعم من جاءه من المساكين، ويقضي وقته في العبادة" +
                    "والتفكير فيما حوله من مشاهد الكون، وفيما وراءها من قدرة مبدعة،" +
                    "وهو غير مطمئن لما عليه قومه من عقائد الشرك المهلهلة، وتصوراتها الواهية،" +
                    "ولكن ليس بين يديه طريق واضح، ولا منهج محدد، ولا طريق قاصد يطمئن إليه ويرضاه."};
    public static final int[] diffLvlL3 = new int[]{1 , 1 , 1 , 5 , 5};




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
                LEVEL_COL + " INTEGER );");

        db.execSQL("CREATE TABLE " + QUESTION_TBL + "(" +
                Q_NO + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                Q_COL + " TEXT , " +
                ANS_COL + " TEXT , " +
                HINT_COL + " TEXT , " +
                GRID_ID_COL + " INTEGER , " +
                DIFF_LVL_COL + " INTEGER );");

        db.execSQL("CREATE TABLE " + GRID_TBL + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                CHARS_COL + " TEXT );");

        ContentValues gameRow = new ContentValues();

        //TODO هنا تغير اسم المتغيرات
        for (int i = 0 ; i < 5 ; i++) {
            gameRow.put(Q_COL, questionsL1[i]);
            gameRow.put(ANS_COL, answersL1[i]);
            gameRow.put(HINT_COL, hintsL1[1]);
            gameRow.put(GRID_ID_COL, 1); //level 1
            gameRow.put(DIFF_LVL_COL, diffLvlL1[i]);

            long test = db.insert(QUESTION_TBL, null, gameRow);

        }

        gameRow.clear();
        for (int i = 0 ; i < 5 ; i++) {
            gameRow.put(Q_COL, questionsL2[i]);
            gameRow.put(ANS_COL, answersL2[i]);
            gameRow.put(HINT_COL, hintsL2[1]);
            gameRow.put(GRID_ID_COL, 2); //level 2
            gameRow.put(DIFF_LVL_COL, diffLvlL2[i]);

            long test = db.insert(QUESTION_TBL, null, gameRow);

        }

        gameRow.clear();
        for (int i = 0 ; i < 5 ; i++) {
            gameRow.put(Q_COL, questionsL3[i]);
            gameRow.put(ANS_COL, answersL3[i]);
            gameRow.put(HINT_COL, hintsL3[1]);
            gameRow.put(GRID_ID_COL, 3); //level 3
            gameRow.put(DIFF_LVL_COL, diffLvlL3[i]);

            long test = db.insert(QUESTION_TBL, null, gameRow);

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + USER_TBL);
        db.execSQL("DROP TABLE IF EXIST " + QUESTION_TBL);
        db.execSQL("DROP TABLE IF EXIST " + GRID_TBL);
        Log.d("DB", "The table has been removed!");
        onCreate(db);

    }

    public void addUserRecord(String name, String email, String pass, int score, int level) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues userData = new ContentValues();
        userData.put(NAME_COL, name);
        userData.put(EMAIL_COL, email);
        userData.put(PASS_COL, pass);
        userData.put(SCORE_COL, score);
        userData.put(LEVEL_COL, level);

        db.insert(USER_TBL, null, userData);

        db.close();
    }

    private Cursor emailRow(String email ,  SQLiteDatabase db ){



        Cursor cr = db.rawQuery("SELECT * FROM " + USER_TBL + " WHERE " + EMAIL_COL + " = '" + email + "' ;", null);

        return cr ;

    }

    //return true if email exist.
    public boolean isEmailExist(String email) {

        SQLiteDatabase db = getWritableDatabase();
        Cursor cr = emailRow(email , db);

        if (cr.getCount() == 0) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }

    }

    public String showUserTable() {
        String msg = "";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cr = db.rawQuery("SELECT * FROM " + USER_TBL, null);

        cr.moveToPosition(-1);

        while (cr.moveToNext()) {
            msg += cr.getInt(0) + "|";
            msg += cr.getString(1) + "|";
            msg += cr.getString(2) + "|";
            msg += cr.getString(3) + "|";
            msg += cr.getInt(4) + "|";
            msg += cr.getInt(5) + "|\n";
        }

        db.close();

        return msg;
    }

    public String showQuestionTable() {
        String msg = "";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cr = db.rawQuery("SELECT * FROM " + QUESTION_TBL, null);

        cr.moveToPosition(-1);

        while (cr.moveToNext()) {
            msg += cr.getInt(0) + "|";
            msg += cr.getString(1) + "|";
            msg += cr.getString(2) + "|";
            msg += cr.getString(3) + "|";
            msg += cr.getInt(4) + "|";
            msg += cr.getInt(5) + "|\n";
        }

        db.close();

        return msg;
    }




    public String passCheck(String email) {

        String pass = "";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cr = emailRow(email, db);

        cr.moveToPosition(-1);

        if (cr.moveToNext()) {

            pass = cr.getString(cr.getColumnIndex(PASS_COL));
            db.close();

        }

        return pass;
    }

    public String[] userData(String email){

        String[] row = new String[5] ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cr = emailRow(email , db);

        cr.moveToPosition(-1);

        if (cr.moveToNext()) {
            row[0] = cr.getString(cr.getColumnIndex(NAME_COL));
            row[1] = cr.getString(cr.getColumnIndex(EMAIL_COL));
            row[2] = cr.getString(cr.getColumnIndex(PASS_COL));
            row[3] = cr.getString(cr.getColumnIndex(SCORE_COL));
            row[4] = cr.getString(cr.getColumnIndex(LEVEL_COL));

        }
        db.close();
        return row;

    }

    public int deleteRow(String email){

        SQLiteDatabase db = getWritableDatabase();

        int number = db.delete(USER_TBL,EMAIL_COL + " = '" + email + "'", null);
        db.close();

        return number ;
    }

    public int updateName(String name , String email){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues updValues = new ContentValues();
        updValues.put(NAME_COL,name);
        int number = db.update(USER_TBL,updValues , EMAIL_COL + " = '" + email + "'" , null);
        db.close();

        return number ;
    }

    public int updateEmail(String newEmail , String oldEmail){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues updValues = new ContentValues();
        updValues.put(EMAIL_COL, newEmail);
        int number = db.update(USER_TBL,updValues , EMAIL_COL + " = '" + oldEmail + "'" , null);
        db.close();

        return number ;
    }





}

