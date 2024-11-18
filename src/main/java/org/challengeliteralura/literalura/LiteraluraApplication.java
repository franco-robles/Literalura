package org.challengeliteralura.literalura;

import org.challengeliteralura.literalura.principal.Principal;
import org.challengeliteralura.literalura.repository.EscritorRepository;
import org.challengeliteralura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner  {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private EscritorRepository escritorRepo;
    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(libroRepository, escritorRepo);
        principal.mostrarMenu();

    }
}
