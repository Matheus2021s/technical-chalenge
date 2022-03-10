package br.com.sicredi.techinicalchalenge;

import br.com.sicredi.techinicalchalenge.service.VotoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class TechinicalChalengeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TechinicalChalengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
