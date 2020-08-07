package encryptdecrypt;

import java.io.FileDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;

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
