package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
//import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println("---------------------------------------------------------------------");

      Car tesla = new Car("Tesla", 1);
      User user1 = new User("John", "Gold", "johngold@yandex.ru");
      user1.setUsersCar(tesla);
      userService.add(user1);

      Car mustang = new Car("Mustang", 2);
      User user2 = new User("PlayBoyzTV", "Youtube", "playboyztv@youtube.com");
      user2.setUsersCar(mustang);
      userService.add(user2);

      List<User> secondList = userService.listUsers();
      for (User user : secondList) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println("Tesla owner is: " + userService.getUserByCarModelAndSeries("Tesla", 1));
      System.out.println("Mustang owner is: " + userService.getUserByCarModelAndSeries("Mustang", 2));

      context.close();
   }
}
