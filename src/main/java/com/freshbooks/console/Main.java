package com.freshbooks.console;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.freshbooks.ApiConnection;

public class Main {
  
  public static void main(String[] args) throws IOException {
    Properties props = new Properties();
    
    String userAgent = "freshbooks-api-java-client-0.73.1";
    String systemUrl = null;
    String systemKey = null;
    
    ApiConnection con = null;
    
    try {
      props.load(new FileInputStream("/home/caco/workspace/freshbooks-console/src/main/resources/freshbooks.properties"));
      systemUrl  = props.getProperty("system.url");
      systemKey  = props.getProperty("system.key");
      System.out.println("Loaded configurations for system '" + systemUrl + "'");
    } catch (FileNotFoundException e) {
      System.out.println("File 'freshbooks.properties' not found, exiting.");
      return;
    } catch (IOException e) {
      System.out.println("IO error on file 'freshbooks.properties', exiting.");
      return;
    }
    
    
    if (systemUrl == null ) {
      System.out.println("system.url property not found a the 'freshbooks.properties' file, exiting.");
      return;
    }
    
    if (systemKey == null ) {
      System.out.println("system.key property not found a the 'freshbooks.properties' file, exiting.");
      return;
    }
    
    try {
      con = new ApiConnection(new URL(systemUrl), systemKey, userAgent);
    }
    catch (MalformedURLException e) {
      System.out.println("system.url is not a valid url.");
    }
    
    
    System.out.println("Welcome!");
    //System.out.println("Usage: method [[key:value]* | [subentity : {key : value}]*]");
    
    //easier to parse
    System.out.println("Usage: method [-key value]*");
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    CommandExecuter.setConnection(con);
    String line = null;
    
    while (!(line = reader.readLine()).equals("exit")) {
      try{
        CommandExecuter.execute(line);
      }
      catch(Exception e) {
        System.out.println(e.getMessage());
      }
    }
    
    System.out.println("Program terminated.");
    
    
    
    
  }

}
