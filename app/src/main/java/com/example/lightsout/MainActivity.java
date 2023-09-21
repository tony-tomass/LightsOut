package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    private boolean cellState [][];

    Button random_bt;
    Button reset_bt;
    TextView score_tv;
    int clicks;
    View.OnClickListener butt_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int button_index = grid.indexOfChild(view);
            int row = button_index / GRID_SIZE;
            int col = button_index % GRID_SIZE;

            cellState[row][col] = !cellState[row][col];
            if (cellState[row][col] == true) {
                view.setBackgroundColor(getColor(R.color.blue_500));
            }
            else {
                view.setBackgroundColor(getColor(R.color.black));
            }
            updateScore();

            clicks++;
            if (countLights() == 9) {
                //Start new activity
                //Needs where you're going from and where you are going
                Intent winner = new Intent(getApplicationContext(), WinActivity.class);
                winner.putExtra("TAPS",clicks);
                winner.putExtra("Fun","Yippie!!!!");
                startActivity(winner);
            }
        }
    };
    View.OnClickListener random_bt_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clicks = 0;
            randomize();
            recolor();
            updateScore();
        }
    };
    View.OnClickListener reset_bt_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            turnOffLights();
            recolor();
            updateScore();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);

        for (int i = 0; i < grid.getChildCount(); i++) {
            grid.getChildAt(i).setOnClickListener(butt_listener);
        }
        randomize();

        random_bt = findViewById(R.id.random_BT);
        random_bt.setOnClickListener(random_bt_listener);
        reset_bt = findViewById(R.id.reset_BT);
        reset_bt.setOnClickListener(reset_bt_listener);


        score_tv = findViewById(R.id.score_TV);

        updateScore();
        recolor();
    }

    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                gridButton.setBackgroundColor(getColor(R.color.blue_500));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));
            }
        }
    }

    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
    }

    public int countLights() {
        int count = 0;
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                if (cellState[i][j] == true) {
                    count++;
                }
            }
        }
        return count;
    }
    public void updateScore() {
        String text = "Score: " + countLights();
        score_tv.setText(text);
    }

    public void turnOffLights() {
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                if (cellState[i][j] == true) {
                    cellState[i][j] = false;
                }
            }
        }
    }
}