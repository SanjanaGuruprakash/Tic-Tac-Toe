package apprunner;

import controller.GameController;
import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneBotException;
import exceptions.PlayerCountMismatchException;
import model.*;
import winningstartegies.ColumnWinningStrategy;
import winningstartegies.RowWinningStrategy;
import winningstartegies.WinningStrategy;
import winningstartegies.diagonalWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class app {
    public static void main(String[] args) throws DuplicateSymbolException, PlayerCountMismatchException, MoreThanOneBotException {
        GameController gameController= new GameController();
        Scanner scanner = new Scanner(System.in);

        int size = 3;
        List<Player> playerList=new ArrayList<>();
        List<WinningStrategy> winningStrategiesList=new ArrayList<>();

        playerList.add(new Player('X',"Sanjana", 1, PlayerType.HUMAN));
        playerList.add(new Player('O', "Vinay", 2, PlayerType.HUMAN));

        winningStrategiesList.add(new RowWinningStrategy());
        winningStrategiesList.add(new ColumnWinningStrategy());
        winningStrategiesList.add(new diagonalWinningStrategy());

        Game game=gameController.startGame(size,playerList,winningStrategiesList);
        while (game.getState()==GameStatus.IN_PROG)
        {
            /*
            1. we want to print board
            2. we want to ask for undo
            3. make move
             */
            game.printBoard();

            System.out.println("Do you want to undo? Enter y/n");
            String undoRequired = scanner.next();
            if(undoRequired.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }

        if(GameStatus.SUCCESS.equals(game.getState())){
            System.out.println(game.getWinningPlayer().getName() + ", Congrats you WON :) Check your board");
            game.printBoard();
        }
        if(GameStatus.DRAW.equals(game.getState())){
            System.out.println(" Match tied, try another game!");
            game.printBoard();
        }

    }
}
