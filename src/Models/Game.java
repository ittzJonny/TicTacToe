package Models;

import Exceptions.InvalidInputEcxeption;
import Strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Player winner;
    private int turn;
    private GameState gameState;
    private List<Move> moveList;
    private List<WinningStrategy> winningStrategies;

    private Game(GameBuilder builder)
    {
        board= new Board(builder.getDimension());
        this.players=builder.getPlayers();
        this.winner=null;
        this.turn=0;
        this.moveList=new LinkedList<>();
        this.gameState=GameState.IN_PROGRESS;
        this.winningStrategies=builder.getWinningStrategies();
    }

    public void undo()
    {

        if (moveList.isEmpty())
        {
            System.out.println("Nothing to undo");
            return ;
        }

        // remove move form move list
        Move removedMove=moveList.remove(moveList.size()-1);

        // go back on player index
        turn = (turn-1 + players.size())%players.size();

        // set cell to empty
        removedMove.getCell().renew();


        //reset the moves set in winning strategies
        for (WinningStrategy winningStrategy: winningStrategies)
        {
            winningStrategy.handelUndo(board, removedMove);
        }



    }

    public void makeMove()
    {
        Player currentPlayer=players.get(turn);

        Move m=null;
        try {
            System.out.println("It's "+currentPlayer.getName()+" turn. Please make your move");
            m = currentPlayer.makeMove(board);

        } catch (InvalidInputEcxeption e) {
            System.out.println(e.getMessage());
            return ;
        }


        int r=m.getCell().getRow();
        int c=m.getCell().getCol();

        Cell cellToChange=board.getGrid().get(r).get(c);
        cellToChange.setSymbol(currentPlayer.getSymbol());
        cellToChange.setCellState(CellState.FILLED);

        m.setCell(cellToChange);
        moveList.add(m);

        turn++;
        turn %= players.size();

        // we need to check for winner
        if (checkWinner(m)){
            setWinner(currentPlayer);
            setGameState(GameState.SUCCESS);
        } else if (moveList.size()==board.getSize()* board.getSize()) {
            setWinner(null);
            setGameState(GameState.DRAW);
        }

    }

    public boolean checkWinner(Move move){
        for (WinningStrategy winningStrategy: winningStrategies)
        {
            if (winningStrategy.checkWinner(board,move))
            {
                return true;
            }
        }

        return false;
    }

    public void display()
    {
        board.display();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }



    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public static class GameBuilder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public int getDimension() {
            return dimension;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void gridPlayerValidation()
        {
            if (players.size()!= dimension-1)
            {
                throw new RuntimeException("Incorrect number of players.");
            }
        }

        private void botCountValidation()
        {
            int botCount=0;
            for (Player p: players)
            {
                if (p.getPlayerType().equals(PlayerType.Bot)) {
                    botCount++;
                }
            }
            if (botCount>1)
            {
                throw new RuntimeException("Too Many Bot players");
            }
        }

        private void validate()
        {
            gridPlayerValidation();
            botCountValidation();
            //every player has different symbol             -- Pending
        }

        public Game build()
        {
            validate();
            return new Game(this);
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                ", players=" + players +
                ", winner=" + winner +
                ", turn=" + turn +
                ", gameState=" + gameState +
                ", moveList=" + moveList +
                ", winningStrategies=" + winningStrategies +
                '}';
    }
}
