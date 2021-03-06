// GameSave

package de.htwg.battleship.model.persistence.impl;

import com.google.gson.Gson;
import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.State;

import javax.persistence.*;
import java.io.Serializable;

/**
 * GameSave
 *
 * @author ms
 * @since 2016-03-31
 */
@Entity
@Table(name = "HibernateGameSave4")
public class HibernateGameSave implements IGameSave, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "player1Name")
    private String player1Name;
    @Column(name = "player1ID")
    private String player1ID;
    @Column(name = "player2Name")
    private String player2Name;
    @Column(name = "player2ID")
    private String player2ID;
    @Column(name = "gameMode")
    private GameMode gameMode;
    @Column(name = "currentState")
    private State currentState;
    @Column(name = "field1", length = 660)
    private String field1;
    @Column(name = "field2", length = 660)
    private String field2;
    @Column(name = "shipList1")
    private IShip[] shipList1;
    @Column(name = "shipList2")
    private IShip[] shipList2;
    @Column(name = "heightLength")
    private int heightLength;
    @Column(name = "maxShipNumber")
    private int maxShipNumber;

    public HibernateGameSave() {
    }

    @Override
    public String getPlayer1Name() {
        return player1Name;
    }

    @Override
    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    @Override
    public String getPlayer2Name() {
        return player2Name;
    }

    @Override
    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    @Override
    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public boolean[][] getField1() {
        return new Gson().fromJson(field1, boolean[][].class);
    }

    @Override
    public void setField1(boolean[][] field1) {
        this.field1 = new Gson().toJson(field1);
    }

    @Override
    public boolean[][] getField2() {
        return new Gson().fromJson(field2, boolean[][].class);
    }

    @Override
    public void setField2(boolean[][] field2) {
        this.field2 = new Gson().toJson(field2);
    }

    @Override
    public IShip[] getShipList1() {
        return shipList1;
    }

    @Override
    public void setShipList1(IShip[] shipList1) {
        this.shipList1 = shipList1;
    }

    @Override
    public IShip[] getShipList2() {
        return shipList2;
    }

    @Override
    public void setShipList2(IShip[] shipList2) {
        this.shipList2 = shipList2;
    }

    @Override
    public IMasterController restoreGame(Injector injector) {
        IMasterController masterController = injector.getInstance(IMasterController.class);
        masterController.restoreGame(this);
        return masterController;
    }

    @Override
    public IGameSave saveGame(IMasterController masterController) {
        this.player1Name = masterController.getPlayer1().getName();
        this.player2Name = masterController.getPlayer2().getName();
        this.player1ID = masterController.getPlayer1().getID();
        this.player2ID = masterController.getPlayer2().getID();
        this.gameMode = masterController.getGameMode();
        this.currentState = masterController.getCurrentState();
        this.setField1(masterController.getPlayer1().getOwnBoard().getHitMap());
        this.setField2(masterController.getPlayer2().getOwnBoard().getHitMap());
        this.shipList1 = masterController.getPlayer1().getOwnBoard().getShipList();
        this.shipList2 = masterController.getPlayer2().getOwnBoard().getShipList();
        this.heightLength = masterController.getBoardSize();
        this.maxShipNumber = masterController.getShipNumber();
        return this;
    }

    @Override
    public boolean validate() {
        return player1Name != null && player2Name != null && gameMode != null &&
               currentState != null && field1 != null && field2 != null &&
               shipList1 != null && shipList2 != null && heightLength != 0 &&
               maxShipNumber != 0 && player1ID != null && player2ID != null;
    }

    @Override
    public int getHeightLength() {
        return heightLength;
    }

    @Override
    public void setHeightLength(int heightLength) {
        this.heightLength = heightLength;
    }

    @Override
    public int getMaxShipNumber() {
        return maxShipNumber;
    }

    @Override
    public void setMaxShipNumber(int maxShipNumber) {
        this.maxShipNumber = maxShipNumber;
    }

    @Override
    public String getPlayer1ID() {
        return player1ID;
    }

    @Override
    public void setPlayer1ID(String player1ID) {
        this.player1ID = player1ID;
    }

    @Override
    public String getPlayer2ID() {
        return player2ID;
    }

    @Override
    public void setPlayer2ID(String player2ID) {
        this.player2ID = player2ID;
    }
}
