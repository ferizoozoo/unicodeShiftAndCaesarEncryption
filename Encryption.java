package encryptdecrypt;

import java.util.Scanner;

public class Encryption {
    public static void main(String[] args) {
        String message = "we found a treasure!";
        String cypheredMessage = "";
        for (int i = 0; i < message.length(); i++)
        {
            char character = message.charAt(i);
            if (!(character == ' ' || character == '!'))
                cypheredMessage += (char)(219 - character);
            else
                cypheredMessage += character;    
        }
        System.out.println(cypheredMessage);
    }
}
