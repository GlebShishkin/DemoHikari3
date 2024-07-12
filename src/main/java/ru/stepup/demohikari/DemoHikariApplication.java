package ru.stepup.demohikari;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import java.util.List;

@ComponentScan("ru.stepup.demohikari")
public class DemoHikariApplication {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {

        applicationContext =
                new AnnotationConfigApplicationContext(DemoHikariApplication.class);

//        for (String beanName : applicationContext.getBeanDefinitionNames()) {
//            System.out.println(beanName);
//        }

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.deleteAll();

        User user = new User("Ivan Ivanov");
        int user_id = userService.save(user);

        User user2 = new User("Petr Petrov");
        int user2_id = userService.save(user2);

        System.out.println("user_id = " + user_id + "; user2_id = " + user2_id);

        List<User> userList = userService.getUsers();
        System.out.println("User list:");
        for (User usr:userList) {
            System.out.println(usr.getUsername());
        }

        userService.updateUser(user2_id, "Sergey Sergeyev");
        System.out.println(userService.getUser(user2_id).getUsername());
    }
}
