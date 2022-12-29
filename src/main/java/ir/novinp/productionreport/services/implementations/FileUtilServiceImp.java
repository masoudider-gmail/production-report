package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.services.FileUtilService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUtilServiceImp implements FileUtilService {

    @Value("${pdf.file.location}")
    private String fileLocation;

    @Override
    public void saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadDirectory = Paths.get(fileLocation);

        try (InputStream inputStream = multipartFile.getInputStream()) {

            Path filePath = uploadDirectory.resolve(fileName);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public Resource downloadFile(String fileName) throws IOException {

        Path uploadDirectory = Paths.get(fileLocation);

        URI uri = Files.list(uploadDirectory)
                .filter(file -> file.getFileName().toString().startsWith(fileName))
                .findFirst()
                .orElseThrow()
                .toUri();


        return new UrlResource(uri);

    }
}
