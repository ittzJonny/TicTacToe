package Strategies;

import Models.Board;
import Models.Cell;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{

    HashMap<Integer, HashMap<Symbol,Integer>> hp = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int r=move.getCell().getRow();
        Symbol sys=move.getCell().getSymbol();

        if (!hp.containsKey(r))
        {
            hp.put(r, new HashMap<>());
        }

        HashMap<Symbol,Integer> h=hp.get(r);
        if(!h.containsKey(sys)){
            h.put(sys, 0);
        }
        h.put(sys, h.get(sys)+1);

        if (h.get(sys)== board.getSize())
        {
            return true;
        }

        return false;
    }

    @Override
    public void handelUndo(Board board, Move move) {
        int r=move.getCell().getRow();
        Symbol sym=move.getPlayer().getSymbol();
        hp.get(r).put(sym,hp.get(r).get(sym)-1);

    }
}
