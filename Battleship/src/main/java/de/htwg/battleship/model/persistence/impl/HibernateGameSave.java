// GameSave

package de.htwg.battleship.model.persistence.impl;

import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.StatCollection;
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
@Table(name = "HibernateGameSave")
public class HibernateGameSave implements IGameSave, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "player1")
    private String player1;
    @Column(name = "player2")
    private String player2;
    @Column(name = "gameMode")
    private GameMode gameMode;
    @Column(name = "currentState")
    private State currentState;

    // TODO: make them not transient but real columns in the database
    @Transient
    private boolean[][] field1;
    // TODO: make them not transient but real columns in the database
    @Transient
    private boolean[][] field2;
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

    public HibernateGameSave(IMasterController masterController) {
        this.player1 = masterController.getPlayer1().getName();
        this.player2 = masterController.getPlayer2().getName();
        this.gameMode = masterController.getGameMode();
        this.currentState = masterController.getCurrentState();
        this.field1 = masterController.getPlayer1().getOwnBoard().getHitMap();
        this.field2 = masterController.getPlayer2().getOwnBoard().getHitMap();
        this.shipList1 =
            masterController.getPlayer1().getOwnBoard().getShipList();
        this.shipList2 =
            masterController.getPlayer2().getOwnBoard().getShipList();
        heightLength = StatCollection.heightLenght;
        maxShipNumber = StatCollection.shipNumberMax;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public boolean[][] getField1() {
        return field1;
    }

    public void setField1(boolean[][] field1) {
        this.field1 = field1;
    }

    public boolean[][] getField2() {
        return field2;
    }

    public void setField2(boolean[][] field2) {
        this.field2 = field2;
    }

    public IShip[] getShipList1() {
        return shipList1;
    }

    public void setShipList1(IShip[] shipList1) {
        this.shipList1 = shipList1;
    }

    public IShip[] getShipList2() {
        return shipList2;
    }

    public void setShipList2(IShip[] shipList2) {
        this.shipList2 = shipList2;
    }

    public IMasterController restoreGame(Injector injector) {
        IMasterController masterController =
            injector.getInstance(IMasterController.class);
        masterController.getPlayer1().setName(this.getPlayer1());
        masterController.getPlayer2().setName(this.getPlayer2());
        masterController.setCurrentState(this.currentState);
        return masterController;
    }

    public int getHeightLength() {
        return heightLength;
    }

    public void setHeightLength(int heightLength) {
        this.heightLength = heightLength;
    }

    public int getMaxShipNumber() {
        return maxShipNumber;
    }

    public void setMaxShipNumber(int maxShipNumber) {
        this.maxShipNumber = maxShipNumber;
    }
}
