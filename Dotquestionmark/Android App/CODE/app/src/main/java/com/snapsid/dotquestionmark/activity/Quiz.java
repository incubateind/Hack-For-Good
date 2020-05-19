package com.snapsid.dotquestionmark.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.snapsid.dotquestionmark.R;


public class Quiz extends Activity {



    // TODO: Declare member variables here:
    private Button trueButton;
    private Button falseButton;
    private TextView mTextView;
    private TextView score;
    private ProgressBar bar;

    private int mQuestion;
    private int mIndex;
    private int mScore;




    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_0, true),
            new TrueFalse(R.string.question_b, true),
            new TrueFalse(R.string.question_a, true),
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, true),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, true),
//            new TrueFalse(R.string.question_10, true),
//            new TrueFalse(R.string.question_11, false),
//            //new TrueFalse(R.string.question_12, false),
//            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here

    final int PROGRESS_BAR_INCREMENT=(int)Math.ceil(100.0/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if(savedInstanceState!=null){
            mScore=savedInstanceState.getInt("ScoreKey");
            mIndex=savedInstanceState.getInt("IndexKey");

        }

        else{
            mScore=0;
            mIndex=0;
        }

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        mTextView=findViewById(R.id.question_text_view);
        score=findViewById(R.id.score);
        bar=findViewById(R.id.progress_bar);

        score.setText("Score: "+mScore+ "/"+mQuestionBank.length);

        mQuestion=mQuestionBank[mIndex].getQuestionid();
        mTextView.setText(mQuestion);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(true);
                questionUpdate();
                score.setText("Score: "+mScore+ "/"+mQuestionBank.length);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "False Pressed!", Toast.LENGTH_SHORT).show();
                questionUpdate();
                checkAnswer(false);
            }
        });
    }

    private void questionUpdate(){
        String risk="";


        if(mScore>9)
        {
            risk="High";
        }
        else if(mScore>5)
        {
            risk="Medium";
        }
        else{
            risk="Low";
        }

        mIndex=(mIndex+1)%mQuestionBank.length;
        if(mIndex==0){
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle(risk+" Risk!!");
            alert.setCancelable(false);
            alert.setMessage("This is based on current understanding of your disease which subject to change.\n\nPlease do concern your doctor in case you are not feeling well.");
            alert.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }


        mQuestion=mQuestionBank[mIndex].getQuestionid();
        Log.d("que", String.valueOf(mIndex));
        mTextView.setText(mQuestion);
        bar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
    }

    private void checkAnswer(Boolean userSelection){
        Boolean correctAns=mQuestionBank[mIndex].isAnswer();
        if(userSelection==correctAns)
        {
            if(mIndex==0)
            {
                // mScore=mScore;
                trueButton.setText("YES");
                falseButton.setText("NO");
            }
            else if(mIndex==1)
            {
                mScore=mScore+1;
            }
            else if(mIndex==2||mIndex==3||mIndex==4)
            {
                mScore=mScore+10;
                //Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            }
            else
            {
                mScore=mScore+1;
            }


        }
        else{
//            if(mIndex==0)
//            {
                trueButton.setText("YES");
                falseButton.setText("NO");
//            }
            //Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);

    }
}
