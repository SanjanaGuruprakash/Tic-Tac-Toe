package winningstartegies;

import model.Board;
import model.Move;

import java.util.HashMap;
import java.util.Map;

public class diagonalWinningStrategy implements WinningStrategy{
    Map<Character,Integer> rightDia = new HashMap<>();
    Map<Character,Integer> leftDia=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

         // check for right diagonal

        if(row==col){
            if(!leftDia.containsKey(symbol)){
                leftDia.put(symbol,0);
            }
            leftDia.put(symbol,leftDia.get(symbol)+1);
            if(leftDia.get(symbol)== board.getSize())
                return true;
        }

        // check for left diagonal

        if(row+col==board.getSize()-1){
            if(!rightDia.containsKey(symbol)){
                rightDia.put(symbol,0);
            }
            rightDia.put(symbol,rightDia.get(symbol)+1);
            if (rightDia.get(symbol) == board.getSize()-1)
                return true;
        }
        return false;
    }

    @Override
    public void undo(Board board, Move lastMove) {

        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        char symbol = lastMove.getPlayer().getSymbol();

        if(row==col){
            leftDia.put(symbol,rightDia.get(symbol)-1);
        }

        else
            rightDia.put(symbol,leftDia.get(symbol)-1);
    }
}
