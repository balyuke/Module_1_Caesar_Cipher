package com.javarush.balyuke.consoleui;

import com.javarush.balyuke.caesar.CaeserCoder;
import com.javarush.balyuke.caesar.exception.CaesarCodingException;
import com.javarush.balyuke.consoleui.constants.ConstantsPrint;
import com.javarush.balyuke.file.exception.FileProcessingException;
import com.javarush.balyuke.consoleui.exception.InvalidUserInputException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.javarush.balyuke.caesar.CaesarAlphabet.*;
import static com.javarush.balyuke.consoleui.constants.ConstantsPrint.*;
import static com.javarush.balyuke.demo.Utils.addSpaceAfterChar;
import static com.javarush.balyuke.file.constants.ConstantsFiles.DIR_WORK;

// Уровень представления - Общение с пользователем
public class ConsoleDialogue implements Dialogue {

    private static final String WELCOME_MESSAGE =
            """
                        
            Start CAESAR CIPHER...
            """;


    private static final String PRINT_STOP_APP = "Stop CAESAR CIPHER...\n";

    public static final String OPERATION_PATTERN = "%d - %s;";

    public static final String TRY_AGAIN_COMMAND = "again";

    private static final String DEMO_INPUT = "demo-input.txt";
    private static final String DEMO_ENCRYPT = "demo-encrypt.txt";
    private static final String DEMO_DECRYPT = "demo-decrypt.txt";

    private static final String DEMO_ENCRYPT_BRUTOFORCE = "demo-encrypt-brutoforce.txt";
    private static final String DEMO_DECRYPT_BRUTOFORCE = "demo-decrypt-brutoforce.txt";

    private final Scanner in;

    private final CaeserCoder caeserCoder;

    public ConsoleDialogue() {
        in = new Scanner(System.in);
        caeserCoder = new CaeserCoder();
    }

    @Override
    public void start() {
        showMenu();
        Operation operation = readOperation();
        processOperation(operation);
    }

    private void showMenu() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PRINT_CHARS_ALPHABET);
        System.out.println(addSpaceAfterChar(ALPHABET));
        System.out.println(PRINT_HINT_OFFSET + (LEN_ALPH -1));
        System.out.println("\nChoose next option to continue:");
        for (Operation operation : Operation.values()){
            String message = String.format(OPERATION_PATTERN, operation.getNumber(), operation.getDescription());
            System.out.println(message);
        }
    }

    private Operation readOperation() {
        boolean shouldTryAgain = false;
        do {
            try {
                int option = readInt();
                return Operation.getByNumber(option);
            } catch (IllegalArgumentException | InvalidUserInputException ex) {
                System.out.println("Operation number is wrong");
                System.out.println("Reason: " + ex.getMessage());
                System.out.println("Enter 'again' for trying again and something other for exit.");

                String input = readString();
                if (TRY_AGAIN_COMMAND.equalsIgnoreCase(input)) {
                    shouldTryAgain = true;
                }

            }
        } while (shouldTryAgain);

        return Operation.EXIT;
    }

    private void processOperation(Operation operation){
        switch (operation){
            case EXIT -> processExit();
            case ENCRYPTION -> processEncryptionOperation();
            case DECRYPTION -> processDecryptionOperation();
            case BRUTOFORCE -> processBrutoForceOperation();
        }
    }

    private void processBrutoForceOperation() {
        System.out.println("Current folder is " + DIR_WORK.toString());
        System.out.println("Enter filename (or press key " + PRESS_DEFAULT.ENTER + " for demo file: " + DEMO_ENCRYPT_BRUTOFORCE + "), which contains encrypted text : ");
        String tmp = readString();
        String inputFilename = (tmp.equalsIgnoreCase("")) ? Paths.get(DIR_WORK.toString(), DEMO_ENCRYPT_BRUTOFORCE).toString() : Paths.get(DIR_WORK.toString(), tmp).toString();
        System.out.println("Input file: " + ANSI_GREEN + inputFilename + ANSI_RESET);

        System.out.println("Enter filename (or press key " + PRESS_DEFAULT.ENTER + " for demo file: " + DEMO_DECRYPT_BRUTOFORCE + "), which will be user for result saving : ");
        tmp = readString();
        String outputFilename = (tmp.equalsIgnoreCase("")) ? Paths.get(DIR_WORK.toString(), DEMO_DECRYPT_BRUTOFORCE).toString() : Paths.get(DIR_WORK.toString(), tmp).toString();
        System.out.println("Output file: " + ANSI_GREEN + outputFilename + ANSI_RESET);

        try{
            caeserCoder.decryptBrutoforce(inputFilename, outputFilename);
            System.out.println("Done!");
        } catch (FileProcessingException | CaesarCodingException ex) {
            System.err.println("Error happened. Reason: " + ex.getMessage());
            //ex.PrintStackTrace();
        }
    }

    private void processEncryptionOperation(){
        System.out.println("Current folder is " + DIR_WORK.toString());
        System.out.println("Enter filename (or press <" + PRESS_DEFAULT.ENTER + "> for demo file: " + DEMO_INPUT + "), which contains original text : ");
        String tmp = readString();
        String inputFilename = (tmp.equalsIgnoreCase("")) ? Paths.get(DIR_WORK.toString(), DEMO_INPUT).toString() : Paths.get(DIR_WORK.toString(), tmp).toString();
        System.out.println("Input file: " + ANSI_GREEN + inputFilename + ANSI_RESET);

        System.out.println("Enter filename (or press <" + PRESS_DEFAULT.ENTER + "> for demo file: " + DEMO_ENCRYPT + "), which will be user for result saving : ");
        tmp = readString();
        String outputFilename = (tmp.equalsIgnoreCase("")) ? Paths.get(DIR_WORK.toString(), DEMO_ENCRYPT).toString() : Paths.get(DIR_WORK.toString(), tmp).toString();
        System.out.println("Output file: " + ANSI_GREEN + outputFilename + ANSI_RESET);

        System.out.println("Enter key:");
        int key = readInt();

        try{
            caeserCoder.encrypt(inputFilename, outputFilename, key);
            System.out.println("Done!");
        } catch (FileProcessingException | CaesarCodingException ex) {
            System.err.println("Error happened. Reason: " + ex.getMessage());
            //ex.PrintStackTrace();
        }
    }

    private void processDecryptionOperation(){
        System.out.println("Current folder is " + DIR_WORK.toString());
        System.out.println("Enter filename (or press key " + PRESS_DEFAULT.ENTER + " for demo file: " + DEMO_ENCRYPT + "), which contains encrypted text : ");
        String tmp = readString();
        String inputFilename = (tmp.equalsIgnoreCase("")) ? Paths.get(DIR_WORK.toString(), DEMO_ENCRYPT).toString() : Paths.get(DIR_WORK.toString(), tmp).toString();
        System.out.println("Input file: " + ANSI_GREEN + inputFilename + ANSI_RESET);

        System.out.println("Enter filename (or press key " + PRESS_DEFAULT.ENTER + " for demo file: " + DEMO_DECRYPT + "), which will be user for result saving : ");
        tmp = readString();
        String outputFilename = (tmp.equalsIgnoreCase("")) ? Paths.get(DIR_WORK.toString(), DEMO_DECRYPT).toString() : Paths.get(DIR_WORK.toString(), tmp).toString();
        System.out.println("Output file: " + ANSI_GREEN + outputFilename + ANSI_RESET);

        System.out.println("Enter key:");
        int key = readInt();

        try{
            caeserCoder.decrypt(inputFilename, outputFilename, key);
            System.out.println("Done!");
        } catch (FileProcessingException | CaesarCodingException ex) {
            System.err.println("Error happened. Reason: " + ex.getMessage());
            //ex.PrintStackTrace();
        }
    }

    private void processExit(){
        System.out.println(PRINT_STOP_APP);
    }

    private int readInt() {
        String input = in.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new InvalidUserInputException("Integer value is wrong", ex);
        }
    }

    private String readString() {
        return in.nextLine();
    }
}
