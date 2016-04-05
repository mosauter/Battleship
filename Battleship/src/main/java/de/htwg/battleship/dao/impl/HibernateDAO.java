// HibernateDAO

package de.htwg.battleship.dao.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.dao.IDAO;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.persistence.impl.HibernateGameSave;
import de.htwg.battleship.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * HibernateDAO
 *
 * @author ms
 * @since 2016-03-29
 */
public class HibernateDAO implements IDAO {

    private final SessionFactory sessionFactory;

    public HibernateDAO() {
        sessionFactory = HibernateUtil.getInstance();
    }

    @Override
    public void saveOrUpdateGame(IMasterController masterController) {
        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tx = session.beginTransaction();

            session.save(new HibernateGameSave());
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e) {
                    // ignore
                }
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    @Override
    public boolean isGameExisting(IPlayer player1, IPlayer player2) {
        return false;
    }

    @Override
    public IMasterController loadGame(IPlayer player1, IPlayer player2) {
        return null;
    }

    @Override
    public List<IMasterController> listAllGames(IPlayer player) {
        return null;
    }
}
