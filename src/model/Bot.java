package model;

import botstrategies.BotPlayingStrategy;
import botstrategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    private DifficultyLevel level;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(char symbol, String name, int id, PlayerType type, DifficultyLevel level) {
        super(symbol, name, id, type);
        this.level=level;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(level);
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.println("Bot's move");
        Cell cell= botPlayingStrategy.makeMove(board);

        cell.setState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }

    public DifficultyLevel getLevel() {
        return level;
    }

    public void setLevel(DifficultyLevel level) {
        this.level = level;
    }
}
