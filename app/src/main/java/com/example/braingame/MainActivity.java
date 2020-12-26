package com.example.braingame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.text.Format;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button go;
    ConstraintLayout main;

    CountDownTimer countDownTimer;
    TextView timer;
    Button playagain;

    TextView question;
    int result;
    Random random;

    TextView grid1;
    TextView grid2;
    TextView grid3;
    TextView grid4;

    TextView correct;
    TextView score;
    int winnings=0;
    int playing=0;

    ArrayList<Integer> storeGridValue;
    int answer;

    public void setGridValue() {
        storeGridValue=new ArrayList<Integer>();
        answer=random.nextInt(4);

        for(int i=0;i<=3;i++) {
            if (answer == i) {
                storeGridValue.add(result);
            } else {
                int samevalue=random.nextInt(201);
                if(samevalue==result) {
                    while (samevalue != result) {
                        samevalue = random.nextInt(201);
                    }storeGridValue.add(samevalue);
                }
                 else{
                    storeGridValue.add(samevalue);
                }
            }
        }
        grid1.setText(storeGridValue.get(0).toString());
        grid2.setText(storeGridValue.get(1).toString());
        grid3.setText(storeGridValue.get(2).toString());
        grid4.setText(storeGridValue.get(3).toString());
    }


    public void questionGenerator(){

        ArrayList<String> operators=new ArrayList<String>();
        operators.add("+");
        operators.add("-");
        operators.add("/");
        operators.add("*");

        int firstValue=random.nextInt(100);
        int secondValue=random.nextInt(100);
        String operator = operators.get(random.nextInt(operators.size()));
        result=0;

        if(operator=="+"){
            result=firstValue+secondValue;
        }
        else if(operator=="-"){
            result=firstValue-secondValue;
        }
        else if(operator=="/"){
            result=(int)(firstValue/secondValue);
        }
        else if(operator=="*"){
            result=firstValue*secondValue;
        }
        question.setText(Integer.toString(firstValue)+operator+Integer.toString(secondValue));
    }


    public void startPlaying(View V){
        go.setVisibility(View.INVISIBLE);

        main.setVisibility(View.VISIBLE);
        playagain.setVisibility(View.INVISIBLE);
        playing=0;

        countDownTimer=new CountDownTimer(30*1000+300,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0");
                correct.setVisibility(View.INVISIBLE);
                main.setVisibility(View.INVISIBLE);
                playagain.setVisibility(View.VISIBLE);
                go.setText(winnings+"/16");
                go.setVisibility(View.VISIBLE);
                go.setEnabled(false);
                winnings=0;
                score.setText(winnings+"/16");
                Log.i("tag",winnings+"");
            }
        }.start();
    }

    public void onClick(View V){
        playing++;
     if(V.getTag().toString().equals(Integer.toString(answer))) {
         Log.i("tag",V.getTag().toString());
         if (playing == 1) {
             correct.setVisibility(View.VISIBLE);
         }
         winnings++;
         correct.setText("Correct!");
         score.setText(winnings + "/16");
         questionGenerator();
         setGridValue();
     }
     else{
         correct.setText("Wrong!");
         if (playing == 1){
             correct.setVisibility(View.VISIBLE);
         }
         questionGenerator();
         setGridValue();
     }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = findViewById(R.id.go);
        main = findViewById(R.id.main);

        timer = findViewById(R.id.timer);
        playagain=findViewById(R.id.playAgain);

        grid1=findViewById(R.id.gridtext1);
        grid2=findViewById(R.id.gridtext2);
        grid3=findViewById(R.id.gridtext3);
        grid4=findViewById(R.id.gridtext4);
        random =new Random();
        question=findViewById(R.id.question);
        score=findViewById(R.id.score);
        correct=findViewById(R.id.correct);
        questionGenerator();
        setGridValue();
    }

}