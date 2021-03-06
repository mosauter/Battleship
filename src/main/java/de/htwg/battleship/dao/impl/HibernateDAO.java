// HibernateDAO

package de.htwg.battleship.dao.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.dao.IDAO;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.model.persistence.impl.HibernateGameSave;
import de.htwg.battleship.modules.BattleshipHibernateModule;
import de.htwg.battleship.persistence.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * HibernateDAO
 *
 * @author ms
 * @since 2016-03-29
 */
public class HibernateDAO implements IDAO {

    private static final String PLAYER_1_COLUMN_ID = "player1ID";
    private static final String PLAYER_2_COLUMN_ID = "player2ID";
    private static final Logger LOGGER = LogManager.getLogger();

    private final SessionFactory sessionFactory;
    private final Injector injector;

    public HibernateDAO() {
        sessionFactory = HibernateUtil.getInstance();
        this.injector = Guice.createInjector(new BattleshipHibernateModule());
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

            contains = load(player1, player2, session) != null;
            if (!contains) {
                contains = load(player2, player1, session) != null;
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
        Transaction tx = null;
        IGameSave gameSave = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            gameSave = load(player1, player2, session);

            if (gameSave == null) {
                gameSave = load(player2, player1, session);
            }

        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return gameSave == null ? null : gameSave.restoreGame(injector);
    }

    private IGameSave load(final IPlayer player1, final IPlayer player2, final Session session) {
        Criteria criteria = session.createCriteria(injector.getInstance(IGameSave.class).getClass());
        criteria.add(Restrictions.eq(PLAYER_1_COLUMN_ID, player1.getID()))
                .add(Restrictions.eq(PLAYER_2_COLUMN_ID, player2.getID()));
        List list = criteria.list();
        return list.isEmpty() ? null : (IGameSave) list.get(0);
    }

    @Override
    public List<IGameSave> listAllGames(final IPlayer player) {
        Transaction tx = null;
        List<IGameSave> list = new LinkedList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            String queryPlayer = player.getID();

            Criteria criteria = session.createCriteria(injector.getInstance(IGameSave.class).getClass());
            List list1 = criteria.list();
            for (Object o : list1) {
                HibernateGameSave gs = (HibernateGameSave) o;
                if (Objects.equals(gs.getPlayer1ID(), queryPlayer) || Objects.equals(gs.getPlayer2ID(), queryPlayer)) {
                    list.add(gs);
                }
            }
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return list;
    }

    /**
     * Utility Method to centralize the {@link HibernateException} ex in while handling the {@link Transaction} tx.
     *
     * @param ex the {@link HibernateException} which occurred
     * @param tx the {@link Transaction} in which the exception occurred
     */
    private void handleHibernateException(final HibernateException ex, final Transaction tx) {
        if (tx != null) {
            LOGGER.error("HibernateException occured, tryin to" +
                         " rollback the last transaction.\n" + ex.getMessage());
            try {
                tx.rollback();
            } catch (HibernateException e) {
                LOGGER.error("HibernateException occured at rollback.\n" + e.getMessage());
            }
        }
    }
}
