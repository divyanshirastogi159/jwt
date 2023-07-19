package com.aws;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;

import java.util.Date;

public class JWT_AWS {
    
    
    public static void main() {
                


        // Get the AWS Secret Access Key
//        String secretAccessKey = default_credentialsProvider.getCredentials().getAWSSecretKey();
//        System.out.println("AWS Secret Access Key: " + secretAccessKey);

        // AWS Region and Secret Manager ARN for the JWT secret
        String awsRegion = "us-east-1";
        String secretArn = "arn:aws:secretsmanager:us-east-1:874104759934:secret:mydemosecret-EEIffs";
        // Generate and print the JWT token
        String jwtToken = generateJwtToken(awsRegion, secretArn);
        System.out.println("Generated JWT Token: " + jwtToken);
        
    }
    
    
    
    public static AWSCredentials credentials(){
        String access_key = System.getenv("AWS_ACCESS_KEY_ID");//null
        String secret_key = System.getenv("AWS_SECRET_ACCESS_KEY");//null
        
        System.out.println("AWS_ACCESS_KEY_ID: " +access_key);
        System.out.println("AWS_SECRET_KEY_ID: " +secret_key);

//        DefaultAWSCredentialsProviderChain default_credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();       
//        String defaccessKeyId = default_credentialsProvider.getCredentials().getAWSSecretKey();
//        System.out.println("defAWS Access Key ID: " + defaccessKeyId);
        
//        EnvironmentVariableCredentialsProvider credentialsProvider = new EnvironmentVariableCredentialsProvider();
//        AWSCredentials awsCredentials = credentialsProvider.getCredentials();
//        String accessKeyId = awsCredentials.getAWSAccessKeyId();
//        String secretAccessKey = awsCredentials.getAWSSecretKey();
//
//        System.out.println("AWS Access Key ID hiiii: " + accessKeyId);
//        System.out.println("AWS Secret Access Key hiiii: " + secretAccessKey);
//        
//        
        
        AWSCredentials credentials = new BasicAWSCredentials("AKIA4XBFUEZ7LNXSGWMV","lqJLVbf8h2m5hir88DQKa+KBhUCL7C4XpSHrCwJp");
        return credentials;
    }
    
    
    private static String getJwtSecretKeyFromSecretManager(String awsRegion, String secretArn) {
        AWSSecretsManager secretsManager = AWSSecretsManagerClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withRegion(awsRegion)
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretArn);

        GetSecretValueResult getSecretValueResult = secretsManager.getSecretValue(getSecretValueRequest);
        return getSecretValueResult.getSecretString();
    }

    private static String generateJwtToken(String awsRegion, String secretArn) {
        String jwtSecretKey = getJwtSecretKeyFromSecretManager(awsRegion, secretArn);

        // Use the retrieved jwtSecretKey to sign the JWT token
        Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);

        // Customize your payload here (e.g., add claims)
        String issuer = "divyanshirastogi";
        String subject = "science";
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 3600000); // 1 hour

        String jwtToken = JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return jwtToken;
    }
}



