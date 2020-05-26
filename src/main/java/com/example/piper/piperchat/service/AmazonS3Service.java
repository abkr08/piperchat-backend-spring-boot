package com.example.piper.piperchat.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class AmazonS3Service {

    private static S3Client s3;
    private Region region = Region.EU_WEST_2;
    private String bucketName = "piperchat-storage";
    private String endpointUrl = "https://piperchat-storage.s3.eu-west-2.amazonaws.com/";


    @Bean
    public void initializeS3Client(){
        s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(SystemPropertyCredentialsProvider.create())
                .build();

    }
    public String uploadImage(MultipartFile image){
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(image);
            String fileName = generateFileName(image);
            fileUrl = endpointUrl + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
    private void uploadFileTos3bucket(String fileName, File file) {
        s3.putObject(PutObjectRequest.builder().bucket(bucketName).key(fileName)
                        .build(),
                RequestBody.fromFile(file));
    }
}
