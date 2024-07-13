package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.addCar(new Car("Porsche", 12));
      userService.addCar(new Car("Mercedes", 3));
      userService.addCar(new Car("BMW", 6));

      List<Car> cars = userService.listCars();

      User user1 = new User("Илюха", "Макс", "milig@mail.ru");
      user1.setCar(cars.get(0));
      User user2 = new User("Жека", "Шафа", "zekya@mail.ru");
      user2.setCar(cars.get(1));
      User user3 = new User("Димон", "Тюва", "tutu@mail.ru");
      user3.setCar(cars.get(2));
      User user4 = new User("Санек", "Василек", "vaz14@mail.ru");

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();

      User userByCar = userService.getUserByCar("Mercedes", 3);
      System.out.println(userByCar);

      users.forEach(System.out::println);

      context.close();
   }
}
