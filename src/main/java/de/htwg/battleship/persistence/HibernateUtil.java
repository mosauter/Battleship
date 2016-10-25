// HibernateUtil

package de.htwg.battleship.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil
 *
 * @author ms
 * @since 2016-03-30
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        final Configuration cfg = new Configuration();
        cfg.configure(HibernateUtil.class.getResource("hibernate.cfg.xml"));
        sessionFactory = cfg.buildSessionFactory();
    }

    private HibernateUtil() {
    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
