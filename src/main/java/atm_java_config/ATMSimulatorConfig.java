package atm_java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ATMSimulatorConfig {

    @Bean
    public DataSource getDataSource(){return new DataSource("customers.txt");}

    @Bean
    public Bank getBank(){return new Bank(getDataSource());}

    @Bean
    public ATM getATM(){return new ATM(getBank());}

    @Bean
    public ATMSimulator getATMSimulator(){return new ATMSimulator(getATM());}
}
