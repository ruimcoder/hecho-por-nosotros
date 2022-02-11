package com.folcamp.hechopornosotros.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${amazonProperties.region}")
    private String defaultRegion;

    @Value("${amazonProperties.accessKey}")
    private String accessKeyId;

    @Value("${amazonProperties.secretKey}")
    private String accessSecretKey;


    @Bean
    public AmazonS3 getS3Client() {

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, accessSecretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(defaultRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}