// GameSave

package de.htwg.battleship.model.persistence.impl;

import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.StatCollection;
import de.htwg.battleship.util.State;

/**
 * GameSave
 *
 * @author ms
 * @since 2016-03-31
 */
public class GameSave implements IGameSave {

    private String player1;
    private String player2;
    private GameMode gameMode;
    private State currentState;
    private boolean[][] field1;
    private boolean[][] field2;
    private IShip[] shipList1;
    private IShip[] shipList2;
    private int heightLength;
    private int maxShipNumber;

    public GameSave() {
    }

    public GameSave(IMasterController masterController) {
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

    @Override
    public String getPlayer1() {
        return player1;
    }

    @Override
    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    @Override
    public String getPlayer2() {
        return player2;
    }

    @Override
    public void setPlayer2(String player2) {
        this.player2 = player2;
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
        return field1;
    }

    @Override
    public void setField1(boolean[][] field1) {
        this.field1 = field1;
    }

    @Override
    public boolean[][] getField2() {
        return field2;
    }

    @Override
    public void setField2(boolean[][] field2) {
        this.field2 = field2;
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
        IMasterController masterController =
            injector.getInstance(IMasterController.class);
        masterController.restoreGame(this);
        return masterController;
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
}
