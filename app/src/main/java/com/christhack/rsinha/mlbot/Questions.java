package com.christhack.rsinha.mlbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rsinha on 3/26/17.
 */

public class Questions {
    public class Answers{
        public String answer;
        public double value;

        public Answers(String s, double val){
            answer=s;
            value=val;
        }
    }
    private String question;
    private Map<String, String> answers;
    private ArrayList<Answers> myAns;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
        myAns=new ArrayList<>();
        for(Map.Entry<String, String> ent : answers.entrySet() ){
            String s=ent.getValue();
            String ans = s.substring(0, s.indexOf('`'));
            double val = Double.parseDouble(s.substring(s.indexOf('`')+1, s.lastIndexOf('`')));
            myAns.add(new Answers(ans, val));
        }
    }

    public ArrayList<Answers> getMyAns(){
        return myAns;
    }
}
