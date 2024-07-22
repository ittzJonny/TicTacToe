package Models;

import Exceptions.InvalidInputEcxeption;
import Factories.BotPlayingStrategyFactory;

public class BotPlayer extends Player{

    private BotDifficultyLevel botDifficultyLevel;

    public BotPlayer(Long id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.setPlayerType(PlayerType.Bot);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    Move makeMove(Board board) throws InvalidInputEcxeption {
        Move m=BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel).makeMove(board);
         m.setPlayer(this);
        return m;

    }
}
