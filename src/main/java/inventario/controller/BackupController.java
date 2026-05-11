package inventario.controller;

import inventario.service.BackupService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/backups")
@CrossOrigin
public class BackupController {

    private final BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @PostMapping("/productos")
    public Map<String, String> generarRespaldo() {
        String ruta = backupService.exportarProductos();
        return Map.of(
                "mensaje", "Respaldo generado correctamente",
                "archivo", ruta
        );
    }
}