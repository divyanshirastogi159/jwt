/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
import com.aws.JWT_AWS;
import org.testng.annotations.Test;

/**
 *
 * @author Divyanshi
 */
public class test {

   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    @Test
    public void test() {
//        String access_key = System.getenv("AWS_ACCESS_KEY_ID");
//        String secret_key = System.getenv("AWS_SECRET_ACCESS_KEY");
//        String [] params = new String[]{access_key,secret_key};
        JWT_AWS.main();

//        JWT_AWS.main(params);
    }
   
}

