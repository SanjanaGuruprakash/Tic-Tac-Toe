package botstrategies;

import model.Board;
import model.Cell;

public interface BotPlayingStrategy {

    Cell makeMove(Board board);
}
