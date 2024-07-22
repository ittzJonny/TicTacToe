import Controller.GameController;
import Models.*;
import Strategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//
// git config --global user.name "ittzJonny"
//git config --global user.email "your.email@example.com"

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

//2. display the board
//3. make a move
//while() gameState is in_progress 2-3
//4. based on gameState show output
//5.undo
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController= new GameController();

        List<Player> players= new ArrayList<>();
        players.add(new HumanPlayer(1L,"player 1",new Symbol('0')));
        players.add(new BotPlayer(2L,"Bot 1", new Symbol('X'),BotDifficultyLevel.Easy));

        Game game= gameController.startGame(3,players,List.of(new RowWinningStrategy()));

        System.out.println(game);

        gameController.displayBoard(game);
        while (gameController.checkState(game).equals(GameState.IN_PROGRESS))
        {
            gameController.makeMove(game);
            gameController.displayBoard(game);

            System.out.println("Do you want to undo?[Y/N]");
            String undoAns= sc.nextLine();
            if (undoAns.equalsIgnoreCase("Y"))
            {
                gameController.undo(game);
                System.out.println("Undo successful");
                gameController.displayBoard(game);
            }



        }

        if(gameController.checkState(game).equals(GameState.SUCCESS))
        {
            System.out.println(gameController.getWinner(game).getName()+" You Win!!");
        }
        else if (gameController.checkState(game).equals(GameState.DRAW))
        {
            System.out.println("Nobody Wins----------");
        }

    }
}