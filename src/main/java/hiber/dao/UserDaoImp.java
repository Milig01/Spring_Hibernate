package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void addCar(Car car) {sessionFactory.getCurrentSession().save(car);}

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where model = :model and series = :series", Car.class);
      Car car = query.setParameter("model", model).setParameter("series", series).getSingleResult();
      TypedQuery<User> query1 = sessionFactory.getCurrentSession().createQuery("from User where car.id = :car_id", User.class);
      return query1.setParameter("car_id", car.getId()).getSingleResult();
   }
}
