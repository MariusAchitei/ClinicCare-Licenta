package ro.uaic.clinic_care.controllers.tst;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.uaic.clinic_care.services.S3.FileService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = fileService.uploadFile(file);
            // Store the fileUrl in your database as needed
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        try (S3ObjectInputStream inputStream = fileService.downloadFile(fileName)) {
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam("fileName") String fileName) {
        fileService.deleteFile(fileName);
        return ResponseEntity.ok("File deleted successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles(@RequestParam(value = "prefix", required = false) String prefix) {
        List<String> files = fileService.listFiles(prefix);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> fileExists(@RequestParam("fileName") String fileName) {
        boolean exists = fileService.fileExists(fileName);
        return ResponseEntity.ok(exists);
    }
}
