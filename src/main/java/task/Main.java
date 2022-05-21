package task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import task.config.HibernateConfig;
import task.services.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        UserService userService = context.getBean(UserService.class);

        System.out.println(userService.read(1L));
    }
}
