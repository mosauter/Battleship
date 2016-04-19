package de.htwg.battleship.dao;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.BattleshipModule;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.persistence.HibernateUtil;
import de.htwg.battleship.util.State;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
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
public class IDAOTest {

    private static final String PLAYER_1 = "PLAYER_ONE";
    private static final String PLAYER_2 = "PLAYER_TWO";

    private static final int PLAYER_1_ID = 111111;
    private static final int PLAYER_2_ID = 222222;

    private IDAO idao;
    private IMasterController iMasterController;
    private State savedState;
    private Injector injector;

    @Before
    public void setUp() {
        injector = Guice.createInjector(new BattleshipModule());
        idao = injector.getInstance(IDAO.class);
        iMasterController = injector.getInstance(IMasterController.class);
        iMasterController.getPlayer1().setProfile(PLAYER_1, PLAYER_1_ID);
        iMasterController.getPlayer2().setProfile(PLAYER_2, PLAYER_2_ID);
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
    public void loadGameRightOrder() {
        IMasterController mc = idao.loadGame(iMasterController.getPlayer1(),
                                             iMasterController.getPlayer2());
        assertEquals(iMasterController.getPlayer1(), mc.getPlayer1());
        assertEquals(iMasterController.getPlayer2(), mc.getPlayer2());
    }

    @Test
    public void loadGameReverseOrder() {
        IMasterController mc = idao.loadGame(iMasterController.getPlayer2(),
                                             iMasterController.getPlayer1());
        assertEquals(iMasterController.getPlayer1(), mc.getPlayer1());
        assertEquals(iMasterController.getPlayer2(), mc.getPlayer2());
    }

    @After
    public void tearDown() throws Exception {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getInstance().getCurrentSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(
                injector.getInstance(IGameSave.class).getClass());
            for (Object o : criteria.list()) {
                session.delete(o);
            }
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e) {
                    throw new RuntimeException(
                        "Exception at Rollback:\n" + e.getMessage());
                }
                throw new RuntimeException(ex.getMessage());
            }
        }
    }
}
