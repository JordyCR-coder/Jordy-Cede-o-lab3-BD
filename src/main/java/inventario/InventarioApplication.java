package inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Esta anotación le dice a Java que esto es un proyecto Spring Boot
public class InventarioApplication {

    public static void main(String[] args) {
        // Esta línea es la que inicia la conexión a la base de datos y levanta el servidor
        SpringApplication.run(InventarioApplication.class, args);
    }
}
