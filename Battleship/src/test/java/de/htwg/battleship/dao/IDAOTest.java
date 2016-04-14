package de.htwg.battleship.dao;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.BattleshipModule;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.util.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * IDAOTest
 *
 * @author ms
 * @since 2016-03-30
 */
@Ignore
public class IDAOTest {

    private static final String player1 = "PLAYER_ONE";
    private static final String player2 = "PLAYER_TWO";

    private IDAO idao;
    private IMasterController iMasterController;
    private State savedState;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new BattleshipModule());
        idao = injector.getInstance(IDAO.class);
        iMasterController = injector.getInstance(IMasterController.class);
        iMasterController.getPlayer1().setName(player1);
        iMasterController.getPlayer2().setName(player2);
        savedState = iMasterController.getCurrentState();
        idao.saveOrUpdateGame(iMasterController);
    }

    @Test
    public void isGameExisting() {
        assertTrue(idao.isGameExisting(iMasterController.getPlayer1(),
                                       iMasterController.getPlayer2()));
        assertTrue(idao.isGameExisting(iMasterController.getPlayer2(),
                                       iMasterController.getPlayer1()));
    }

    @Test
    public void listAllGames() {
        List<IMasterController> list =
            idao.listAllGames(iMasterController.getPlayer1());
        assertEquals(1, list.size());
        IMasterController tmp1 = list.get(list.size() - 1);
        assertEquals(savedState, tmp1.getCurrentState());
        list = idao.listAllGames(iMasterController.getPlayer2());
        assertEquals(1, list.size());
        IMasterController tmp2 = list.get(list.size() - 1);
        assertEquals(savedState, tmp2.getCurrentState());
        assertEquals(tmp1.getPlayer1(), tmp2.getPlayer1());
        assertEquals(tmp1.getPlayer2(), tmp2.getPlayer2());
        assertEquals(tmp1.getCurrentState(), tmp2.getCurrentState());
    }

    @Test
    public void loadGameRight() {
        IMasterController mc = idao.loadGame(iMasterController.getPlayer1(),
                                             iMasterController.getPlayer2());
        assertEquals(iMasterController.getPlayer1(), mc.getPlayer1());
        assertEquals(iMasterController.getPlayer2(), mc.getPlayer2());
    }

    @Test
    public void loadGameReverse() {
        IMasterController mc = idao.loadGame(iMasterController.getPlayer2(),
                                             iMasterController.getPlayer1());
        assertEquals(iMasterController.getPlayer1(), mc.getPlayer1());
        assertEquals(iMasterController.getPlayer2(), mc.getPlayer2());
    }

    @After
    public void tearDown() throws Exception {


    }
}
