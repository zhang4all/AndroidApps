package com.example.android.project4;

/**
 * Created by zhang4all on 4/6/2017.
 */

public class GameState {
    public Coordinate[] player1Coordinates;
    public Coordinate[] player2Coordinates;
    public int[][] board;
    public int player1PiecesSet;
    public int player2PiecesSet;


    /**
     * default constructor
     */
    public GameState() {
        player1Coordinates = new Coordinate[3];
        player2Coordinates = new Coordinate[3];

        for (int i=0; i<3; i++) {
            player1Coordinates[i] = new Coordinate(-1,-1);
            player2Coordinates[i] = new Coordinate(-1,-1);
        }

        player1PiecesSet=0;
        player2PiecesSet=0;
        board = new int[3][3];
    }


    /**
     * copy constructor
     * @param gameState
     */
    public GameState(GameState gameState) {
        player1Coordinates = gameState.player1Coordinates;
        player2Coordinates = gameState.player2Coordinates;
        board = gameState.board;
        player1PiecesSet = gameState.player1PiecesSet;
        player2PiecesSet = gameState.player2PiecesSet;
    }


    public void movePiece(int player, int curRow, int curCol, int newRow, int newCol) {
        board[curRow][curCol] = 0;
        board[newRow][newCol] = player;
    }


    public void setPiece(int player, int row, int col) {
        board[row][col] = player;
        if (player == 1) {
            player1PiecesSet++;
        }
        else if (player == 2) {
            player2PiecesSet++;
        }
    }
}
