package botstrategies;

import model.Board;
import model.Cell;
import model.CellState;

import java.util.List;

public class EasyPlayingStrategy implements BotPlayingStrategy{


    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row: board.getBoard()){
            for(Cell cell: row){
                if(CellState.EMPTY.equals(cell.getState()))
                    return cell;
            }
        }
        return null;
    }
}
