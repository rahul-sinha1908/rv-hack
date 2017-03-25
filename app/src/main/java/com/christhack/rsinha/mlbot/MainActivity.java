package com.christhack.rsinha.mlbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference myDataRef;
    private ArrayList<Questions> myData;

    private TextView txt_quest;
    private Button answer1, answer2, answer3, answer4;

    private float currentQCode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        myDataRef = FirebaseDatabase.getInstance().getReference();

        txt_quest=(TextView)findViewById(R.id.txt_question);
        answer1=(Button)findViewById(R.id.answer_1);
        answer2=(Button)findViewById(R.id.answer_2);
        answer3=(Button)findViewById(R.id.answer_3);
        answer4=(Button)findViewById(R.id.answer_4);

        getCurrentQuestions();
    }
    public void getCurrentQuestions(){
        String department="General";
        if(currentQCode<1)
            department="General";
        else if(currentQCode<2)
            department="Surgeon";
        else if(currentQCode<3)
            department="Physician";
        else if(currentQCode<4)
            department="Cardio";
        else if(currentQCode<5)
            department="Medicine";

        Log.i("Check","It reached 1");
        myData=new ArrayList<>();
        myDataRef.child(department).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Check","Fetched the data");
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Questions q= data.getValue(Questions.class);
                    myData.add(q);
                }
                startAsking();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Check","Data failed");
            }
        });
    }

    private void startAsking(){
        int total = myData.size();
        int myChoice = (int)(Math.random()*total);
        displayQuestion(myChoice);
    }
    private void displayQuestion(int n){
        for(Questions q:myData){
            String qs = q.getQuestion();
            ArrayList<Questions.Answers> ans = q.getMyAns();
            txt_quest.setText(qs);
            if(ans.size()<4)
                return;

            answer1.setText(ans.get(0).answer);
            answer1.setTag(ans.get(0).value);
            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double d = (Double)v.getTag();
                    analyseValue(d);
                }
            });

            answer2.setText(ans.get(1).answer);
            answer2.setTag(ans.get(0).value);
            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double d = (Double)v.getTag();
                    analyseValue(d);
                }
            });

            answer3.setText(ans.get(2).answer);
            answer3.setTag(ans.get(2).value);
            answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double d = (Double)v.getTag();
                    analyseValue(d);
                }
            });

            answer4.setText(ans.get(3).answer);
            answer4.setTag(ans.get(3).value);
            answer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double d = (Double)v.getTag();
                    analyseValue(d);
                }
            });
        }
    }

    private void analyseValue(double d){

    }
}
