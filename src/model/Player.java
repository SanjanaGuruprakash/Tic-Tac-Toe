package model;

import java.util.Scanner;

public class Player {
    private char symbol;
    private  String name;
    private  int id;
    private PlayerType type;
    private Scanner scanner;


    public Player(char symbol, String name, int id, PlayerType type) {
        this.symbol = symbol;
        this.name = name;
        this.id = id;
        this.type = type;
        this.scanner=new Scanner(System.in);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Cell makeMove(Board board) {
        System.out.println(this.name + ", It's your turn. Enter the row and col");

        int row = scanner.nextInt();
        int col = scanner.nextInt();

        while(validateMove(row, col, board)==false){
            System.out.println("Invalid move, enter row and col again");

            row = scanner.nextInt();
            col = scanner.nextInt();
        }
        Cell cell=board.getBoard().get(row).get(col);
        cell.setState(CellState.FILLED);
        cell.setPlayer(this);

        return cell;

    }

    private boolean validateMove(int row, int col, Board board) {
        if(row<0 || row>= board.getSize())
            return false;
        if(col<0 || col>= board.getSize())
            return false;
        if(!CellState.EMPTY.equals(board.getBoard().get(row).get(col).getState()))
            return false;

        return true;
    }
}
