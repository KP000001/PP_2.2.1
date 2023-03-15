package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().
                createQuery("from User", User.class);
        return typedQuery.getResultList();
    }

    @Override
    public User getUserByCarModelSeries(String model, int series) {
        TypedQuery<User> tq = sessionFactory.getCurrentSession()
                .createQuery("from User user where user.car.model = :model and user.car.series = :series")
                .setParameter("model", model)
                .setParameter("series", series);
        return tq.getSingleResult();
    }
}
