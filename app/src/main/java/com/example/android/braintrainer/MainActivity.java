package com.example.android.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView addTextView;
    TextView timerTextView;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int logicalCorrectAnswer;
    TextView resultTextview;
    TextView scoreTextView;
    int score=0;
    int numberofquestions=0;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    public void playAgain(View view)
    {
        score=0;
        numberofquestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestions));
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextview.setText("");
        newQuestions();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                resultTextview.setText("Done!!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);//done for all buttons so that when time gets out button should get freeze and after playing again they all get activated.
            }
        }.start();


    }
    public void chooseAnswer(View view)
    {
        if(Integer.toString(logicalCorrectAnswer).equals(view.getTag().toString())){
                resultTextview.setText("Correct!!");
                score++;
        }
        else
        {
            resultTextview.setText("Wrong :(");

        }
        numberofquestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestions));
        newQuestions();
    }

    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));

    }

    public void newQuestions(){

        Random rand=new Random();
        int a=rand.nextInt(21);//bound is set like for range from 0-20
        int b=rand.nextInt(21);

        addTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        logicalCorrectAnswer=rand.nextInt(4);

        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==logicalCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==a+b)
                {
                    wrongAnswer=rand.nextInt(41);//if bychance number gets same
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton=findViewById(R.id.goButton);
        addTextView=findViewById(R.id.addTextView);
        resultTextview=findViewById(R.id.resultTextview);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        gameLayout=findViewById(R.id.gameLayout);


        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
