package encryptdecrypt;

import java.util.Scanner;

public class Main {    
    public static String encrypt(String message, int key) {
        String cypheredMessage = "";
        for (int i = 0; i < message.length(); i++)
        {
            char character = message.charAt(i);
            cypheredMessage += (char)((int)character + key);    
        }
        return cypheredMessage;
    }
    
    public static String decrypt(String cypheredMessage, int key) {
        String message = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < cypheredMessage.length(); i++)
        {
            char character = cypheredMessage.charAt(i);
            message += (char)((int)character - key);  
        }
        return message;
    }
    
    public static void main(String[] args) {
        String mode = "";
        String data = "";
        int key = 0;
        int counter = 0;
        while (counter < args.length) {
            switch (args[counter]) {
                case "-key":
                    key = Integer.parseInt(args[++counter]);
                    break;
                case "-data":
                    data += args[++counter];
                    break;
                case "-mode":
                    mode += args[++counter];
                    break;            
            }
            counter++;
        }
        String output = "";
        if (mode.equals("enc")) {
            output += encrypt(data, key);
        } else if (mode.equals("dec")) {
            output += decrypt(data, key);
        }
        System.out.println(output);
    }
}

