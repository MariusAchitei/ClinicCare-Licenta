package ro.uaic.clinic_care.services.S3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private AmazonS3 amazonS3;

    private String bucketName = "your-bucket-name";

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    public S3ObjectInputStream downloadFile(String fileName) {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        return s3Object.getObjectContent();
    }

    public void deleteFile(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
    }

    public List<String> listFiles(String prefix) {
        return amazonS3.listObjectsV2(bucketName, prefix).getObjectSummaries()
                .stream()
                .map(s -> amazonS3.getUrl(bucketName, s.getKey()).toString())
                .collect(Collectors.toList());
    }

    public boolean fileExists(String fileName) {
        return amazonS3.doesObjectExist(bucketName, fileName);
    }
}

