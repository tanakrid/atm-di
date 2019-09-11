package atm_database_reader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config-database.xml");
        ATMSimulator atmSimulator = context.getBean("simulator", ATMSimulator.class);
        atmSimulator.run();
    }
}
