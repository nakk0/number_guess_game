package com.example;
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class App 
{
    public static void main( String[] args )
    {
        Random rand = new Random();
        int randomNumber = rand.nextInt(101);
        System.out.println("Random Number: " + randomNumber);
        try{
            System.out.println("server started and executing");
            ServerSocket server = new ServerSocket(3000);
            Socket client = server.accept();
            server.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());   
            
            System.out.println("client connected");
            int clientGuess = -1;
            do{
                clientGuess = Integer.parseInt(in.readLine());
                System.out.println("number received from client: " + clientGuess);
                out.writeBytes((clientGuess < randomNumber ? 
                                "1" : 
                                clientGuess > randomNumber ?
                                "2" :
                                "3") + '\n');
            }while(clientGuess != randomNumber);
            client.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
    }
}