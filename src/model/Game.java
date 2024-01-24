package model;

import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneBotException;
import exceptions.PlayerCountMismatchException;
import winningstartegies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winningPlayer;
    private GameStatus state;
    private int nextPlayerIndex;
    private List<WinningStrategy> winingStrategy;

    public List<WinningStrategy> getWiningStrategy() {
        return winingStrategy;
    }

    public void setWiningStrategy(List<WinningStrategy> winingStrategy) {
        this.winingStrategy = winingStrategy;
    }

    public Game(int size, List<Player> players, List<WinningStrategy> winingStrategy) {
        this.board=new Board(size);
        this.players = players;
        this.winingStrategy = winingStrategy;
        this.state=GameStatus.IN_PROG;
        this.moves= new ArrayList<>();

    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public void printBoard(){
        board.printBoard();
    }

    public void makeMove() {
        Player p=players.get(getNextPlayerIndex());
        Cell cell = p.makeMove(board);

        Move currentMove=new Move(cell,p);
        moves.add(currentMove);

        if(checkWinner(board,currentMove)){
            state=GameStatus.SUCCESS;
            winningPlayer=p;
            return;
        }
        if(moves.size()== board.getSize()* board.getSize()){
            state=GameStatus.DRAW;
            return;
        }
        nextPlayerIndex++;
        nextPlayerIndex=nextPlayerIndex% players.size();

    }

    private boolean checkWinner(Board board, Move currentMove) {
        for(WinningStrategy ws: winingStrategy){
            if(ws.checkWinner(board,currentMove)){
                return true;
            }
        }
        return false;
    }

    public void undo() {
        if(moves.isEmpty()){
            System.out.println("No moves to undo");
            return;
        }
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(lastMove);
        Cell cell= lastMove.getCell();
        cell.setPlayer(null);
        cell.setState(CellState.EMPTY);
        for(WinningStrategy ws: winingStrategy){
            ws.undo(board,lastMove);
        }
        if(nextPlayerIndex!=0)
            nextPlayerIndex--;
        else
            nextPlayerIndex=players.size()-1;
    }

    public static class Builder{
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private Builder(){
            this.size=0;
            this.players=new ArrayList<>();
            this.winningStrategies=new ArrayList<>();

        }
        public Game build() throws MoreThanOneBotException, DuplicateSymbolException, PlayerCountMismatchException {
            validateBotCount();
            validateUniqueSymbolForPlayer();
            validateDimensionAndPlayerCount();
            return new Game(size, players,winningStrategies);


        }

        private void validateDimensionAndPlayerCount() throws PlayerCountMismatchException {
            if(players.size()!=(size-1))
                throw new PlayerCountMismatchException();

        }

        private void validateUniqueSymbolForPlayer() throws DuplicateSymbolException {
            Set<Character> symbolSet=new HashSet<>();
            for(Player p:players){
                if(symbolSet.contains(p.getSymbol())){
                    throw new DuplicateSymbolException();
                }
                else
                    symbolSet.add(p.getSymbol());
            }
        }

        private void validateBotCount() throws MoreThanOneBotException {
            int botCount=0;
            for(Player p:players){
                if(p.getType().equals(PlayerType.BOT))
                    botCount++;
            }
            if(botCount>1){
                throw new MoreThanOneBotException();
            }


        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMove() {
        return moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setMove(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public GameStatus getState() {
        return state;
    }

    public void setState(GameStatus state) {
        this.state = state;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
}
