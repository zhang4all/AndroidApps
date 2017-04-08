package com.example.android.project4;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    Handler UIHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start the player threads
        Player1Thread player1Thread = new Player1Thread();
        player1Thread.start();
        Player2Thread player2Thread = new Player2Thread();
        player2Thread.start();


        GameState gameState = new GameState();
        gameState.setPiece(1, 0, 0);
        gameState.setPiece(2, 1, 0);

        createBoard(gameState.board);


        // to send runnables to player threads
        // player1Thread.player1Handler.post();
        // player2Thread.player2Handler.post();
        // to send message to UIThread
        // UIHandler.sendMessage();

        // handles messages from player threads
        UIHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

            }
        };
    }


    void createBoard(int[][] board) {

    }


    class Player1Thread extends Thread {
        Handler player1Handler;

        @Override
        public void run() {
            Looper.prepare();

            player1Handler = new Handler();

            Looper.loop();
        }
    }


    class Player2Thread extends Thread {
        Handler player2Handler;

        @Override
        public void run() {
            Looper.prepare();

            player2Handler = new Handler();

            Looper.loop();
        }
    }
}
