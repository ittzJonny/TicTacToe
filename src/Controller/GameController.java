package Controller;

import Models.Game;
import Models.GameState;
import Models.Player;
import Strategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        //Validations
//        1.count of players = dimension-1;
//        2.BotCount<=1;
//        3.EveryPlayer has a diff symbol
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public GameState checkState(Game game){

        return game.getGameState();
    }

    public void displayBoard(Game game)
    {
        game.display();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public Player getWinner(Game game)
    {
        return game.getWinner();
    }

    public void undo(Game game){
        game.undo();
    }
}


//1.Start the game: create object of game, taking in the size, creating the board, getting the players
//2. display the board
//3. make a move
//while() gameState is in_progress 2-3
//4. based on gameState show output
//5.undo