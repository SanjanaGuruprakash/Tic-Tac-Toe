package botstrategies;

import model.DifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(DifficultyLevel level) {
        return new EasyPlayingStrategy();
    }
}
