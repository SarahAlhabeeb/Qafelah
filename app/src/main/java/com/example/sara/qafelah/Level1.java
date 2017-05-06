package com.example.sara.qafelah;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.riclage.boardview.BoardWord;
import com.riclage.boardview.WordSearchBoardView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Level1 extends AppCompatActivity {


    WordSearchBoardView boardView;
    TextView q1View, q2View, q3View, q4View, q5View, scoreView;
    int count = 0;
    EncouragingWords obj;
    Random rand;
    int randomNo1, randomNo2;
    String word;
    int score ;
    int level ;
    ImageView[] imageArray;
    final int NUMBER_OF_QUESTIONS = 5;
    ImageButton hintBtn;
    boolean isHintClick = false;
    boolean isAnswerCorrect = false;
    String randomQuestion;
    String[] choices;

    int correctAnswerIndex;
    String selection;
    String msg;

    String[] questions ;
    String[] answers ;
    String[] hint ;
    DBClass db ;
    SharedPreferences userData ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        userData = getSharedPreferences("UserAccount" , 0) ;
        final SharedPreferences.Editor editor = userData.edit() ;
        score = userData.getInt("score", 0);
        level = userData.getInt("level", 0) ;


        boardView = (WordSearchBoardView) findViewById(R.id.board_view);
        hintBtn = (ImageButton) findViewById(R.id.hintImgBtn);
        scoreView = (TextView) findViewById(R.id.scoreView);
        q1View = (TextView) findViewById(R.id.Q1View);
        q2View = (TextView) findViewById(R.id.Q2View);
        q3View = (TextView) findViewById(R.id.Q3View);
        q4View = (TextView) findViewById(R.id.Q4View);
        q5View = (TextView) findViewById(R.id.Q5View);
        imageArray = new ImageView[]{(ImageView) findViewById(R.id.imageViewQ1), (ImageView) findViewById(R.id.imageViewQ2),
                (ImageView) findViewById(R.id.imageViewQ3), (ImageView) findViewById(R.id.imageViewQ4), (ImageView) findViewById(R.id.imageViewQ5)};

        //Disappear imageView that appear when user answer question.
        for (int i = 0; i < imageArray.length; i++) {
            imageArray[i].setVisibility(View.INVISIBLE);
        }

        db = new DBClass(Level1.this) ;
        rand = new Random();
        obj = new EncouragingWords();
        //this code will change in higher levels
        randomQuestion = "آخر الأنبياء والرسل: ";
        choices = new String[]{"محمد صلى الله عليه وسلم", "عيسى ابن مريم عليه السلام", "موسى عليه السلام"};
        correctAnswerIndex = 0;

        questions = DBClass.questionsL1 ;
        answers = DBClass.answersL1 ;
        hint = DBClass.hintsL1 ;

        q1View.setText(questions[0]);
        q2View.setText(questions[1]);
        q3View.setText(questions[2]);
        q4View.setText(questions[3]);
        q5View.setText(questions[4]);

//Generates a random letter board for the given words and returns their locations on it
        final List<BoardWord> targetWords = boardView.generateRandomLetterBoard(Arrays.asList(answers[0], answers[1], answers[2], answers[3], answers[4]));


        //when user press hint Btn
        hintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show firs Dialog if user do not answer on hint or answer with incorrect answer
                if(!isHintClick) {
                    showFirstDialog();
                } else {

                    if(isAnswerCorrect){
                        showSecondDialog();
                    } else {
                        showFirstDialog();
                    }

                }
                isHintClick = true;

            }
        });


//Sets the listener for when a word is selected by the user
        boardView.setOnWordSelectedListener(new WordSearchBoardView.OnWordSelectedListener() {
            @Override
            public boolean onWordSelected(BoardWord selectedWord) {
                if (targetWords.contains(selectedWord)) {
                    int index = targetWords.indexOf(selectedWord);
                    imageArray[index].setVisibility(View.VISIBLE);

                    //TODO: Do something with the valid selected word
                    // استخراج جملة عشوائية من EncouragingWords class
                    randomNo1 = rand.nextInt(obj.words4EachAnswer.length);
                    word = obj.words4EachAnswer[randomNo1];

                    Toast.makeText(getApplicationContext(), word, Toast.LENGTH_LONG).show();
                    score++;
                    scoreView.setText(score + "");
                    count++;

                    //When user answer all question
                    if (count == targetWords.size()) {
                        randomNo2 = rand.nextInt(obj.wordsAfterLevelLength());
                        word = obj.wordsAfterLevel[randomNo2];

                        AlertDialog.Builder builder = new AlertDialog.Builder(Level1.this);
                        View DView = getLayoutInflater().inflate(R.layout.after_eachlevel_dialog, null);

                        TextView Dword = (TextView) DView.findViewById(R.id.DWordsView);
                        TextView Dscore = (TextView) DView.findViewById(R.id.DScoreView);
                        Dword.setText(word);


                        if (!isHintClick) {
                            score += 5;
                        } else {
                            if (isAnswerCorrect) {
                                score += 2;
                            }
                        }
                        isHintClick = false;
                        isAnswerCorrect = false;

                        Dscore.setText(score + "");
                        Button toMainBtnD = (Button) DView.findViewById(R.id.goToMainBtn);
                        Button toNextLevel = (Button) DView.findViewById(R.id.goToNextBtn);

                        editor.putInt("score" , score);
                        level++ ;
                        editor.putInt("level" , level);
                        editor.commit();


                        toNextLevel.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Level1.this, Level2.class));
                            }
                        });
                        toMainBtnD.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Level1.this, MainPageActivity.class));
                            }
                        });

                        builder.setView(DView);
                        AlertDialog dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                    } // end code that specify for ending game.

                    return true;
                }

                return false;
            }
        });
    }

    public void showFirstDialog() {
        final AlertDialog.Builder firstBuilder = new AlertDialog.Builder(Level1.this);

        firstBuilder.setTitle(randomQuestion)
                .setSingleChoiceItems(choices, -1 , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selection = choices[which];
                    }
                })
                .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (selection.equals(choices[correctAnswerIndex])) {
                            msg = "إجابتك صحيحة، أحسنت بارك الله فيك";
                            isAnswerCorrect = true ;

                        } else {
                            msg = "إجابتك خاطئة\n الإجابة الصحيحة هي " + choices[correctAnswerIndex];
                            isAnswerCorrect = false ;
                        }

                        Toast.makeText(Level1.this,msg,Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        showSecondDialog();

                    }
                })
                .setCancelable(false);

        AlertDialog firstDialog = firstBuilder.create();
        firstDialog.show();

    }


    public void showSecondDialog() {
        AlertDialog.Builder secondBuilder = new AlertDialog.Builder(Level1.this);

        secondBuilder.setTitle("تزود ... ")
                .setItems(questions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showThirdDialog(which);
                        dialog.dismiss();
                    }
                });

        AlertDialog secondDialog = secondBuilder.create();
        secondDialog.show();

    }

    public void showThirdDialog(int index) {
        AlertDialog.Builder thirdBuilder = new AlertDialog.Builder(Level1.this);

        thirdBuilder.setTitle(questions[index])
                .setMessage(hint[index]);

        thirdBuilder.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_BACK){
                    showSecondDialog();
                    dialog.dismiss();
                    return true;
                }
                return false;


            }
        });

        AlertDialog thirdDialog = thirdBuilder.create();
        thirdDialog.show();
    }

}
