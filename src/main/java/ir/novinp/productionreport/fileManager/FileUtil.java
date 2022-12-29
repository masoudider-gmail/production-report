package ir.novinp.productionreport.fileManager;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtil {

    public static void saveFile(String fileName, MultipartFile multipartFile) {

        Path uploadDirectory = Paths.get("fileUpload");

        try (InputStream inputStream = multipartFile.getInputStream()) {

            Path filePath = uploadDirectory.resolve(fileName);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path foundFile;

    public static Resource getFile(String fileName) throws IOException {


        Path uploadDirectory = Paths.get("fileUpload");

        Files.list(uploadDirectory).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileName)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;

    }

}
