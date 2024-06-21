package com.fotograbados.springv1.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileService {
    private final String UPLOAD_DIR = "src/main/resources/static/images/";

    public String saveImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // Generar un nombre único para la imagen
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filepath = Paths.get(UPLOAD_DIR, filename);

            // Crear el directorio si no existe
            Files.createDirectories(filepath.getParent());

            // Guardar el archivo en la ruta especificada
            Files.write(filepath, file.getBytes());
            return filename;
        }
        return "default.jpg";
    }

    public void deleteImage(String filename) {
        if (!"default.jpg".equals(filename)) {
            Path filepath = Paths.get(UPLOAD_DIR, filename);
            try {
                Files.deleteIfExists(filepath);
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar error de eliminación si es necesario
            }
        }
    }
}
