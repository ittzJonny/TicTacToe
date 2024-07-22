package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> grid;

    public Board(int size)
    {
        this.size=size;
        this.grid=new ArrayList<>();
        for (int i=0;i<size;i++)
        {
            grid.add(new ArrayList<>());
            for (int j=0;j<size;j++)
            {
                grid.get(i).add(new Cell(i,j));
            }
        }
    }

    public void display()
    {
        for (List<Cell> row:grid)
        {
            for (Cell cell:row)
            {
                cell.displayCell();
            }
            System.out.println();
        }
    }

    public void move(int row, int col)
    {
        Cell currentCell= grid.get(row).get(col);

    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }
}
