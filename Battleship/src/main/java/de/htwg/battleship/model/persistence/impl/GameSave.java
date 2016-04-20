// GameSave

package de.htwg.battleship.model.persistence.impl;

import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.State;

/**
 * GameSave
 *
 * @author ms
 * @since 2016-03-31
 */
public class GameSave implements IGameSave {

    private String player1Name;
    private int player1ID;
    private String player2Name;
    private int player2ID;
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
    public void saveGame(IMasterController masterController) {
        this.player1Name = masterController.getPlayer1().getName();
        this.player2Name = masterController.getPlayer2().getName();
        this.player1ID = masterController.getPlayer1().getID();
        this.player2ID = masterController.getPlayer2().getID();
        this.gameMode = masterController.getGameMode();
        this.currentState = masterController.getCurrentState();
        this.field1 = masterController.getPlayer1().getOwnBoard().getHitMap();
        this.field2 = masterController.getPlayer2().getOwnBoard().getHitMap();
        this.shipList1 =
            masterController.getPlayer1().getOwnBoard().getShipList();
        this.shipList2 =
            masterController.getPlayer2().getOwnBoard().getShipList();
        this.heightLength = masterController.getBoardSize();
        this.maxShipNumber = masterController.getShipNumber();
    }

    @Override
    public boolean validate() {
        return player1Name != null && player2Name != null && gameMode != null &&
               currentState != null && field1 != null && field2 != null &&
               shipList1 != null && shipList2 != null && heightLength != 0 &&
               maxShipNumber != 0 && player1ID != 0 && player2ID != 0;
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
    public int getPlayer1ID() {
        return player1ID;
    }

    @Override
    public void setPlayer1ID(int player1ID) {
        this.player1ID = player1ID;
    }

    @Override
    public int getPlayer2ID() {
        return player2ID;
    }

    @Override
    public void setPlayer2ID(int player2ID) {
        this.player2ID = player2ID;
    }
}
