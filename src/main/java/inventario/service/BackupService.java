package inventario.service;

import inventario.dto.ProductoBackupDTO;
import inventario.model.Producto;
import inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class BackupService {

    private final ProductoRepository productoRepository;

    public BackupService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public String exportarProductos() {
        try {
            List<Producto> productos = productoRepository.findAll();

            List<ProductoBackupDTO> respaldo = productos.stream()
                    .map(p -> new ProductoBackupDTO(
                            p.getId(),
                            p.getNombre(),
                            p.getPrecio(),
                            p.getStock(),
                            p.getCategoria().getNombre()
                    ))
                    .toList();

            Path carpeta = Path.of("backups");
            Files.createDirectories(carpeta);

            String ruta = carpeta.resolve("productos.dat").toString();

            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {
                salida.writeObject(respaldo);
            }

            return ruta;
        } catch (Exception e) {
            throw new RuntimeException("Error al generar respaldo binario", e);
        }
    }
}