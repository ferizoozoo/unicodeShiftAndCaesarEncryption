package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int shift = scanner.nextInt();
        String cypheredMessage = "";
        for (int i = 0; i < message.length(); i++)
        {
            char character = message.charAt(i);
            if (!(character == ' ' || character == '!'))
                cypheredMessage += alphabet.charAt((shift + (int)character - 97) % 26);
            else
                cypheredMessage += character;    
        }
        System.out.println(cypheredMessage);
    }
}

