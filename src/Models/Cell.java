package Models;

public class Cell {
    private int row;
    private int col;
    private Symbol symbol;
    private CellState cellState;

    public Cell(int row, int col)
    {
        this.row=row;
        this.col=col;
        this.symbol=null;
        this.cellState=CellState.EMPTY;
    }

    public void occupy()
    {
        this.cellState=CellState.FILLED;

    }

    public void renew()
    {
        symbol=null;
        cellState=CellState.EMPTY;
    }

    public void displayCell()
    {
        if (cellState==CellState.EMPTY)
        {
            System.out.print("|-| ");
        }
        else {
            System.out.print("| "+symbol.getSym()+" | " );
        }
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}