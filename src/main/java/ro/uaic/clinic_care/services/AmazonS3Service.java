package ro.uaic.clinic_care.services;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AmazonS3Service {

    private final AmazonS3 amazonS3;

    private final String bucketName = "clinic-care-resources";

    public String uploadClinicFile(Long id, MultipartFile file) {
        String filename = String.format("clinics/%d/gallery/%d_%s", id, System.currentTimeMillis(), file.getOriginalFilename());
        return uploadFile(file, filename);
    }

    public String uploadClinicMainPhoto(Long id, MultipartFile file) {
        String extension = file.getOriginalFilename().split("\\.")[file.getOriginalFilename().split("\\.").length - 1];
        String filename = String.format("clinics/%d/%s", id, "main_photo." + extension);
        return uploadFile(file, filename);
    }

    public String uploadFile(MultipartFile file, String fileName) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error uploading file", e)
                    ;
        }
    }
}
