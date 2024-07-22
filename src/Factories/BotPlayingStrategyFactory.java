package Factories;

import Models.BotDifficultyLevel;
import Strategies.BotPlayingStrategy;
import Strategies.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy (BotDifficultyLevel botDifficultyLevel)
    {
        if (botDifficultyLevel.equals(BotDifficultyLevel.Easy))
        {
            return new EasyBotPlayingStrategy();
        }
        return null;
    }
}
