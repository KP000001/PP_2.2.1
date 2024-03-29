package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Model1", 1)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Model2", 2)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Model3", 3)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Model4", 4)));
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user + " " + user.getCar());
        }
        System.out.println(userService.getUserByCarModelSeries("Model1", 1).getFirstName());
        System.out.println(userService.getUserByCarModelSeries("Model3", 3).getFirstName());
        context.close();
    }
}
