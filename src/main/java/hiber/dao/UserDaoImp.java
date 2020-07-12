package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.TypedQuery;

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
    String sqlCarQuery = "FROM Car WHERE name =: carName AND series =: carSeries";
    Query<Car> carQuery = sessionFactory.getCurrentSession().createQuery(sqlCarQuery);
    carQuery.setParameter("carName", car.getName());
    carQuery.setParameter("carSeries", car.getSeries());
    car = carQuery.getResultList().get(0);
    String sqlUserQuery = "FROM User WHERE car_series =: carSeries";
    Query<User> userQuery = sessionFactory.getCurrentSession().createQuery(sqlUserQuery);
    userQuery.setParameter("carSeries", car.getSeries());
    return userQuery.getResultList().get(0);
  }

}
