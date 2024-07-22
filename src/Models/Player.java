package Models;

import Exceptions.InvalidInputEcxeption;

import java.util.Scanner;

public abstract class Player {
    private Long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    public Player(Long id, String name, Symbol symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    abstract Move makeMove(Board board) throws InvalidInputEcxeption;

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
