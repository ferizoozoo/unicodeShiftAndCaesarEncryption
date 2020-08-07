package encryptdecrypt;

import java.io.FileDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;

interface Algorithm {
    String encrypt(String message, int key);
    String decrypt(String cypheredMessage, int key);
}

class ShiftAlgorithm implements Algorithm {
    @Override
    public String encrypt(String message, int key) {
        String cypheredMessage = "";
        for (int i = 0; i < message.length(); i++) {
            int characterCode = message.charAt(i);
            if (65 <= characterCode && characterCode <= 90) {
                cypheredMessage += characterCode + key < 90
                        ? (char) (characterCode + key)
                        : (char) (64 + (characterCode + key) % 90);
            } else if (97 <= characterCode && characterCode <= 122) {
                cypheredMessage += characterCode + key < 122
                        ? (char) (characterCode + key)
                        : (char) (96 + (characterCode + key) % 122);
            } else {
                cypheredMessage += (char) characterCode;
            }
        }
        return cypheredMessage;
    }

    @Override
    public String decrypt(String cypheredMessage, int key) {
        String message = "";
        for (int i = 0; i < cypheredMessage.length(); i++)
        {
            int characterCode = cypheredMessage.charAt(i);
            if (65 <= characterCode && characterCode <= 90) {
                message += characterCode - key >= 65
                        ? (char) (characterCode - key)
                        : (char) (26 + characterCode - key);
            } else if (97 <= characterCode && characterCode <= 122) {
                message += characterCode - key >= 97
                        ? (char) (characterCode - key)
                        : (char) (26 + characterCode - key);
            } else {
                message += (char) characterCode;
            }
        }
        return message;
    }
}

class UnicodeAlgorithm implements Algorithm {
    @Override
    public String encrypt(String message, int key) {
        String cypheredMessage = "";
        for (int i = 0; i < message.length(); i++) {
            int characterCode = message.charAt(i);
            cypheredMessage += (char) (characterCode + key);
        }
        return cypheredMessage;
    }

    @Override
    public String decrypt(String cypheredMessage, int key) {
        String message = "";
        for (int i = 0; i < cypheredMessage.length(); i++)
        {
            int characterCode = cypheredMessage.charAt(i);
            message += (char)(characterCode - key);
        }
        return message;
    }
}

class ChoosedAlgorithm {
    Algorithm algorithm;

    ChoosedAlgorithm(String algorithm) {
        switch (algorithm) {
            case "shift":
                this.algorithm = new ShiftAlgorithm();
                break;
            case " ":
                this.algorithm = new ShiftAlgorithm();
                break;
            case "unicode":
                this.algorithm = new UnicodeAlgorithm();
                break;
            default:
                break;
        }
    }

    String encrypt(String message, int key) {
        return this.algorithm.encrypt(message, key);
    }

    String decrypt(String cypheredMessage, int key) {
        return this.algorithm.decrypt(cypheredMessage, key);
    }
}

class CommandLineOptions {
    String mode = "";
    int key = 0;
    String data = "";
    String inFile = "";
    String outFile = "";
    String algorithm = "";
}

public class Main {

    public static void processCommandLineOptions(String[] args, CommandLineOptions options) {
        int counter = 0;
        while (counter < args.length) {
            switch (args[counter]) {
                case "-key":
                    options.key += Integer.parseInt(args[++counter]);
                    break;
                case "-data":
                    options.data += args[++counter];
                    break;
                case "-mode":
                    options.mode += args[++counter];
                    break;
                case "-in":
                    options.inFile += args[++counter];
                    break;
                case "-out":
                    options.outFile += args[++counter];
                    break;
                case "-alg":
                    options.algorithm += args[++counter];
                    break;
            }
            counter++;
        }
    }

    public static void readFileOrRaiseException(CommandLineOptions options) {
        File file = new File(options.inFile);
        if (options.data.equals("") && !options.inFile.equals("")) {
            try (Scanner scanner = new Scanner(file)) {
                options.data += new String(Files.readAllBytes(Paths.get(options.inFile)));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                //throw;
            }
        }
    }

    public static String getOutputForChosenAlgorithmAndMode(String mode, ChoosedAlgorithm alg, String data, int key) {
        String output = "";
        if (mode.equals("enc")) {
            output += alg.encrypt(data, key);
        } else if (mode.equals("dec")) {
            output += alg.decrypt(data, key);
        }
        return output;
    }

    public static void writeOutputToFileOrTerminal(String outFile, String output) {
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

    public static void main(String[] args) {
        CommandLineOptions options = new CommandLineOptions();
        processCommandLineOptions(args, options);

        readFileOrRaiseException(options);

        ChoosedAlgorithm alg = new ChoosedAlgorithm(options.algorithm);
        String output = getOutputForChosenAlgorithmAndMode(options.mode, alg, options.data, options.key);

        writeOutputToFileOrTerminal(options.outFile, output);
    }
}
