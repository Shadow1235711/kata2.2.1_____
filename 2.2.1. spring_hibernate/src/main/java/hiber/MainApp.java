package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
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
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        }

        System.out.println("\n_____ Добавляем пользователей с машинами в БД");
        userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("Jeap", 5)));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("Tank", 6)));
        userService.add(new User("User7", "Lastname7", "user7@mail.ru", new Car("Jele", 2)));
        userService.add(new User("User8", "Lastname8", "user8@mail.ru", new Car("Lamborginy", 1)));

        System.out.println("\n_____ получаем  пользователя с машиной");
        User user = userService.getCar("Lamborginy", 1);
        if (user == null) {
            System.out.println("Пользователь с таким автомобилем не найден");
        } else {
            System.out.println(user);
        }


        context.close();
    }
}
