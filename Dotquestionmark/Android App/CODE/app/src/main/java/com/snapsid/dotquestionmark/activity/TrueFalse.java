package com.snapsid.dotquestionmark.activity;

public class TrueFalse {

     int mQuestionid;
     boolean mAnswer;


    public TrueFalse(int questionId, boolean trueOrFalse){
        mQuestionid=questionId;
        mAnswer=trueOrFalse;
    }

    public int getQuestionid() {
        return mQuestionid;
    }

    public void setQuestionid(int questionid) {
        mQuestionid = questionid;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }


}