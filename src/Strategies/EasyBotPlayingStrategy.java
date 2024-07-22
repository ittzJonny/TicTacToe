package Strategies;

import Models.Board;
import Models.Cell;
import Models.CellState;
import Models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for (List<Cell> row: board.getGrid())
        {
            for (Cell c: row)
            {
                if (c.getCellState().equals(CellState.EMPTY)){
                    return new Move(c,null);
                }
            }
        }
        return null;
    }
}
