package hfe.beans;

import org.springframework.context.annotation.Bean;

public class MuffenBean {

    @Bean(name= "wu")
    String name() {
        return "name";
    }
}
