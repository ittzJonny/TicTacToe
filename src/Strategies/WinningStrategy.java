package Strategies;

import Models.Board;
import Models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void handelUndo(Board board, Move move);
}
