// HibernateDAO

package de.htwg.battleship.dao.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.BattleshipModule;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.dao.IDAO;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.model.persistence.impl.GameSave;
import de.htwg.battleship.model.persistence.impl.HibernateGameSave;
import de.htwg.battleship.persistence.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.LinkedList;
import java.util.List;

/**
 * HibernateDAO
 *
 * @author ms
 * @since 2016-03-29
 */
public class HibernateDAO implements IDAO {

    private final SessionFactory sessionFactory;
    private final Injector injector;

    public HibernateDAO() {
        sessionFactory = HibernateUtil.getInstance();
        injector = Guice.createInjector(new BattleshipModule());
    }

    @Override
    public void saveOrUpdateGame(IMasterController masterController) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            IGameSave save = injector.getInstance(IGameSave.class);
            save.saveGame(masterController);
            session.save(save);
            tx.commit();
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
    }

    @Override
    public boolean isGameExisting(IPlayer player1, IPlayer player2) {
        Transaction tx = null;
        boolean contains = false;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(GameSave.class);
            List list = criteria.list();

            for (Object o : list) {
                GameSave gameSave = (GameSave) o;
                if (gameSave.getPlayer1Name().equals(player1.getName()) &&
                    gameSave.getPlayer2Name().equals(player2.getName()) ||
                    gameSave.getPlayer1Name().equals(player2.getName()) &&
                    gameSave.getPlayer2Name().equals(player1.getName())) {
                    contains = true;
                    break;
                }
            }
            tx.commit();
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return contains;
    }

    @Override
    public IMasterController loadGame(IPlayer player1, IPlayer player2) {
        // TODO: maybe include the date in the game
        IMasterController result = null;
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(GameSave.class);
            criteria.add(Restrictions.eq("player1ID", player1.getID()))
                    .add(Restrictions.eq("player2ID", player2.getID()));
            List list = criteria.list();
            if (!list.isEmpty()) {
                result =
                    ((HibernateGameSave) list.get(0)).restoreGame(injector);
            } else {
                criteria = session.createCriteria(GameSave.class);
                criteria.add(Restrictions.eq("player1ID", player2.getID()))
                        .add(Restrictions.eq("player2ID", player1.getID()));
                list = criteria.list();
                if (!list.isEmpty()) {
                    result =
                        ((HibernateGameSave) list.get(0)).restoreGame(injector);
                }
            }
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return result;
    }

    @Override
    public List<IMasterController> listAllGames(IPlayer player) {
        Transaction tx = null;
        List<IMasterController> list = new LinkedList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            int queryPlayer = player.getID();

            Criteria criteria = session.createCriteria(HibernateGameSave.class);
            List list1 = criteria.list();
            for (Object o : list1) {
                HibernateGameSave gs = (HibernateGameSave) o;
                if (gs.getPlayer1ID() == queryPlayer ||
                    gs.getPlayer2ID() == queryPlayer) {
                    list.add(gs.restoreGame(injector));
                }
            }
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return list;
    }

    /**
     * Utility Method to centralize the {@link HibernateException} ex in while
     * handling the {@link Transaction} tx.
     *
     * @param ex the {@link HibernateException} which occurred
     * @param tx the {@link Transaction} in which the exception occurred
     */
    private void handleHibernateException(HibernateException ex,
                                          Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(
                    "Exeption at Rollback:\n" + e.getMessage());
            }
            throw new RuntimeException(ex.getMessage());
        }
    }
}
