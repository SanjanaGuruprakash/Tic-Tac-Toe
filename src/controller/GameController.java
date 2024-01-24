package controller;

import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneBotException;
import exceptions.PlayerCountMismatchException;
import model.Game;
import model.Player;
import winningstartegies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int size, List<Player> players, List<WinningStrategy> strategies) throws DuplicateSymbolException, PlayerCountMismatchException, MoreThanOneBotException {
        return Game.getBuilder()
                .setSize(size)
                .setPlayers(players)
                .setWinningStrategies(strategies)
                .build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }

    public  void undo(Game game){
        game.undo();
    }
}
