package Models;

import Exceptions.InvalidInputEcxeption;

import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(Long id, String name, Symbol symbol) {
        super(id, name, symbol);
        this.setPlayerType(PlayerType.Human);
    }

    public void validateRow(int row, int size) throws InvalidInputEcxeption {
        if (row>=size || row<0)
        {
            throw new InvalidInputEcxeption("Invalid in put for row: Board Size: " +size+" Row Input: "+row+".");
        }
    }

    public void validateCol(int col, int size) throws InvalidInputEcxeption {
        if (col>=size || col<0)
        {
            throw new InvalidInputEcxeption("Invalid in put for col: Board Size: " +size+" Col Input: "+col+".");
        }
    }

    public void validateCell(int row, int col, Board board) throws InvalidInputEcxeption {
        if(!board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY))
        {
            throw new InvalidInputEcxeption("Cell is already occupied at row: "+row+" col: "+col+".");
        }
    }

    public void validate(int row, int col, Board board) throws InvalidInputEcxeption {
        validateRow(row, board.getSize());
        validateCol(col, board.getSize());
        validateCell(row,col, board);
    }

    public Move makeMove(Board board) throws InvalidInputEcxeption {
        Scanner sc= new Scanner(System.in);

        System.out.println("Please enter the row in which you would like to play");
        int r= sc.nextInt();

        System.out.println("Please enter the column in which you would like to play");
        int c= sc.nextInt();

        validate(r,c,board);


        return new Move(new Cell(r,c),this);


    }
}
