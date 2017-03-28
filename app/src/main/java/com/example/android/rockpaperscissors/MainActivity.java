package com.example.android.rockpaperscissors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //tracks score for player
    int playerScore = 0;
    //Tracks score for Computer
    int computerScore = 0;
    // Stores the user button choice
    int buttonChecked = 0;
    // Stores the random system choice
    int randomButton = 0;
    Button button1;
    Button button2;
    Button button3;
    ImageView image1;
    ImageView image2;
    ImageView image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associate images and buttons with the corresponding views
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);

        // The first button click listener
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the first button to appear checked and all the others appear unchecked
                button1.setBackgroundResource(R.drawable.rock2);
                button2.setBackgroundResource(R.drawable.paper);
                button3.setBackgroundResource(R.drawable.scissors);
                buttonChecked = 0;
                play();
            }
        });

        // The second button click listener
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the second button to appear checked and all the others appear unchecked
                button2.setBackgroundResource(R.drawable.paper2);
                button1.setBackgroundResource(R.drawable.rock);
                button3.setBackgroundResource(R.drawable.scissors);
                buttonChecked = 1;
                play();
            }
        });

        // The third button click listener
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the third button to appear checked and all the others appear unchecked
                button3.setBackgroundResource(R.drawable.scissors2);
                button1.setBackgroundResource(R.drawable.rock);
                button2.setBackgroundResource(R.drawable.paper);
                buttonChecked = 2;
                play();
            }
        });
    }

    /**
     * Returns a random number between 0..r
     */

    public int randomNumber(int r) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(r);
    }

    /**
     * is called when player makes his choice
     */
    public void play() {
        // assign a random value to the computer choice
        randomButton = randomNumber(3);

        // Computer is assigned the first image value
        if (randomButton == 0) {
            // Set the first image only checked and the others unchecked
            image1.setImageResource(R.drawable.paper2);
            image2.setImageResource(R.drawable.scissors);
            image3.setImageResource(R.drawable.rock);
            // compare computer value with the user choice and toast the messages in each case, also updating the score
            switch (buttonChecked) {
                case 0:
                    Toast.makeText(this, getString(R.string.paper_rock), Toast.LENGTH_SHORT).show();
                    setResultText(getString(R.string.lost));
                    computerScore++;
                    displayForComputer(computerScore);
                    break;
                case 1:
                    setResultText(getString(R.string.draw));
                    break;
                case 2:
                    Toast.makeText(this, getString(R.string.scissors_paper), Toast.LENGTH_SHORT).show();
                    setResultText(getString(R.string.win));
                    playerScore++;
                    displayForPlayer(playerScore);
                    break;
            }
            // Computer is assigned the second value
        } else if (randomButton == 1) {
            // Set the second image only checked and the others unchecked
            image2.setImageResource(R.drawable.scissors2);
            image3.setImageResource(R.drawable.rock);
            image1.setImageResource(R.drawable.paper);
            // compare computer value with the user choice and toast the messages in each case, also updating the score
            switch (buttonChecked) {
                case 0:
                    Toast.makeText(this, getString(R.string.rock_scissors), Toast.LENGTH_SHORT).show();
                    playerScore++;
                    displayForPlayer(playerScore);
                    setResultText(getString(R.string.win));
                    break;
                case 1:
                    Toast.makeText(this, getString(R.string.scissors_paper), Toast.LENGTH_SHORT).show();
                    computerScore++;
                    displayForComputer(computerScore);
                    setResultText(getString(R.string.lost));
                    break;
                case 2:
                    setResultText(getString(R.string.draw));
                    break;
            }
            // Computer is assigned the third value
        } else if (randomButton == 2) {
            // Set the third image only checked and the others unchecked
            image3.setImageResource(R.drawable.rock2);
            image1.setImageResource(R.drawable.paper);
            image2.setImageResource(R.drawable.scissors);
            // compare computer value with the user choice and toast the messages in each case, also updating the score
            switch (buttonChecked) {
                case 0:
                    setResultText(getString(R.string.draw));
                    break;
                case 1:
                    Toast.makeText(this, getString(R.string.paper_rock), Toast.LENGTH_SHORT).show();
                    playerScore++;
                    displayForPlayer(playerScore);
                    setResultText(getString(R.string.win));
                    break;
                case 2:
                    Toast.makeText(this, getString(R.string.rock_scissors), Toast.LENGTH_SHORT).show();
                    computerScore++;
                    displayForComputer(computerScore);
                    setResultText(getString(R.string.lost));
                    break;
            }
        }
    }


    /**
     * Displays the given score for player.
     */
    public void displayForPlayer(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Computer.
     */

    public void displayForComputer(int score) {
        TextView scoreView = (TextView) findViewById(R.id.computer_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Resets the score for both Teams and restores all selected images and button
     */
    public void resetScore(View view) {
        playerScore = 0;
        computerScore = 0;
        displayForPlayer(playerScore);
        displayForComputer(computerScore);
        buttonChecked = 0;
        randomButton = 0;
        button1.setBackgroundResource(R.drawable.rock);
        button2.setBackgroundResource(R.drawable.paper);
        button3.setBackgroundResource(R.drawable.scissors);
        image1.setImageResource(R.drawable.paper);
        image2.setImageResource(R.drawable.scissors);
        image3.setImageResource(R.drawable.rock);
        setResultText("");
    }

    /**
     * displays a text won, lost, draw
     */
    public void setResultText(String s) {
        TextView textView = (TextView) findViewById(R.id.result_text);
        textView.setText(s);
    }
}
