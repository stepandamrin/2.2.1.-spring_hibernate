package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(User user) {
    sessionFactory.getCurrentSession().save(user);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> listUsers() {
    TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
    return query.getResultList();
  }

  @Override
  public User getUserByCar(Car car) {
    TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where car_id = :car_id");
    query.setParameter("car_id", (Long) car.getId());
    return query.getSingleResult();
  }

}
