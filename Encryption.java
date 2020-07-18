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
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        String output = "";
        if (command.equals("enc")) {
            output += encrypt(message, key);
        } else if (command.equals("dec")) {
            output += decrypt(message, key);
        }
        System.out.println(output);
    }
}

