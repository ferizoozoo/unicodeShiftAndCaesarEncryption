package encryptdecrypt;

import java.io.FileDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Main {    
    // TODO: make the encrypt and decrypt methods, one method which receives an extra parameter named "mode"
    public static String encrypt(String message, int key) {
        String cypheredMessage = "";
        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            cypheredMessage += (char) ((int) character + key);
        }
        return cypheredMessage;
    }
    
    public static String decrypt(String cypheredMessage, int key) {
        String message = "";
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
        String inFile = "";
        String outFile = "";
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
                case "-in":
                    inFile += args[++counter];
                    break;
                case "-out":
                    outFile += args[++counter];
                    break;
            }
            counter++;
        }

        File file = new File(inFile);
        String output = "";

        if (data.equals("") && !inFile.equals("")) {
            try (Scanner scanner = new Scanner(file)) {
                data += new String(Files.readAllBytes(Paths.get(inFile)));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        if (mode.equals("enc")) {
            output += encrypt(data, key);
        } else if (mode.equals("dec")) {
            output += decrypt(data, key);
        }

        if (outFile.equals("")) {
            System.out.println(output);
        } else {
            try (FileWriter fileWriter = new FileWriter(outFile)) {
                fileWriter.write(output);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

