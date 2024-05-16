package com.javarush.balyuke.consoleui;

import com.javarush.balyuke.caesar.CaeserCoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.javarush.balyuke.caesar.CaesarAlphabet.*;
import static com.javarush.balyuke.caesar.CaesarCipher.*;
import static com.javarush.balyuke.demo.CaesarCipherMain.*;
import static com.javarush.balyuke.demo.Utils.addSpaceAfterChar;
import static com.javarush.balyuke.demo.Utils.*;
import static com.javarush.balyuke.consoleui.constants.ConstantsPrint.*;
import static com.javarush.balyuke.file.constants.ConstantsFiles.*;
import static java.lang.System.out;

public class BenDialogue implements Dialogue {

    private static final Scanner sc = new Scanner(System.in);

    private final CaeserCoder caeserCoder;

    public BenDialogue() {
        //sc = new Scanner(System.in);
        this.caeserCoder = new CaeserCoder();
    }

    @Override
    public void start() {
        mainMethod();
    }

    private static void headerMenu() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PRINT_CHARS_ALPHABET);
        System.out.println(addSpaceAfterChar(ALPHABET));
        System.out.println(PRINT_HINT_OFFSET + (LEN_ALPH -1));
    }

    private static void inputFile() throws IOException {
        System.out.println("\nWorking method inputFile");

        ArrayList<String> stringsInFile  = readFileToArrayList(DEMO_IN_FILE);
        if (stringsInFile.isEmpty() || stringsInFile == null || stringsInFile.size() < 2) {
            System.out.println(PRINT_CHECK_FILE + DEMO_IN_FILE);
            return;
        }
        String input = null;
        //System.out.println("print stringsFile: " + stringsFile);

        int offset;
        if (!stringsInFile.get(0).isEmpty() && stringsInFile.get(0) != null)
            input = stringsInFile.get(0);

//        try {
//            //System.out.println(input);
//            offset = Integer.valueOf(input);
//            if (offset >= 0 && offset < lenAlph)
//                System.out.println("\nInput Offset value: " + offset);
//            else {
//                System.out.println("\nValue Offset must between 1 and " + (lenAlph - 1) + "\n");
//                System.out.println();
//                return;
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("\nmethod inputFile Unrecognized Offset option: " + input + "\n");
//            return;
//        }

        //заглушка
        offset = 3;

        stringsInFile.remove(input);
        System.out.println(stringsInFile);

        ArrayList<String> stringsOutFile  = readFileToArrayList(DEMO_OUT_FILE);
        for (String str : stringsInFile){
            String message = str;
            System.out.print("\nInput the text message to be encrypted: "+message);
            //String encryptMessage = encoding(message, offset);
            String encryptMessage = encrypt(message, offset);
            stringsOutFile.add(encryptMessage);
            System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage);
            System.out.print(PRINT_DECRYPTED_TEXT);
            //System.out.print(decoding(encryptMessage, offset));
            System.out.print(decrypt(encryptMessage, offset));
            System.out.println();
        }

        clearFileContent(DEMO_OUT_FILE);
        writeFileFromArrayList(stringsOutFile, DEMO_OUT_FILE, false);

    }

    private static void inputManual() {
        System.out.println("\nWorking method inputManual");
        Scanner scanner = new Scanner(System.in);
        String input = null;
        try  {
            int offset = 0;
            System.out.print(PRINT_ENTER_OFFSET_VALUE);
            while(scanner.hasNext()) {
                input = scanner.nextLine();
                if (isInt(input)) {
                    offset = Integer.parseInt(input);
                    if (offset >= 0 && offset < LEN_ALPH) break;
                    else System.err.println (PRINT_HINT_OFFSET + (LEN_ALPH -1)+ "\n");
                }
                else
                    System.err.println ( PRINT_UNRECOGNIZED_INPUT_OPTION + input + "\n");
                System.out.print(PRINT_ENTER_OFFSET_VALUE);
            }
            System.out.print(PRINT_ENTER_THE_TEXT_MESSAGE_TO_BE_ENCRYPTED);
            String message = scanner.nextLine();
            // Crypt
            //String encryptMessage = encoding(message, offset);
            String encryptMessage = encrypt(message, offset);
            System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage);
            // Decrypt
            System.out.print(PRINT_DECRYPTED_TEXT + decrypt(encryptMessage, offset));
            // Brute Force for Console
            System.out.println(PRINT_BRUTE_FORCE);
            List<String> listBruteForceConsole =  bruteForceConsole(encryptMessage, ALPHABET_BRUTE);
            System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage + PRINT_COUNT_OFFSET + ALPHABET_BRUTE.length());
            for(int i = 0; i < listBruteForceConsole.size(); i++) {
                System.out.println(PRINT_TRY_OFFSET + (listBruteForceConsole.size() - i - 1) + PRINT_DECRYPTED_TEXT + listBruteForceConsole.get(i) + "   ...i=" + i);
                //if (i == 38) {
                String s = replaceSymbol(listBruteForceConsole.get(i), ALPHABET_PUNCTUATION_MARKS);
                List<String> words = readFileToListString(DICTIONARY_FILE);
                //boolean isNormalMessage = containsWordsJava8(s, words);
                int countWords = countsWords(s, words);
                out.println("... " + s + " ...countWords=" + countWords);
                //}
            }

            System.out.println();
        }
        catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }


    public static void mainMethod()  {
        LEN_ALPH = ALPHABET.length();
        headerMenu();


        //Scanner sc = new Scanner(System.in);
        String input = null;

        int option = 1;
        printMenu(MENU_OPTIONS);
        while(sc.hasNext() && !(input = sc.next()).equals("3")) {

            if (isInt(input)) {
                option = Integer.parseInt(input);
                switch (option){
                    case 1:
                        inputManual();
                        break;
                    case 2:
                        try {
                            inputFile();
                        }
                        catch (IOException e) {
                            System.out.println(PRINT_MY_EXCEPTION + e.getMessage());
                        }
                        break;
                    case 3:
                        //exit(0);
                        return;
                    default:
                        System.err.println (PRINT_UNRECOGNIZED_INPUT_OPTION + option );
                        break;
                }
            } else  {
                System.err.println (PRINT_UNRECOGNIZED_INPUT_OPTION +  input );
            }
            printMenu(MENU_OPTIONS);
        }


        System.out.println(PRINT_STOP_APP);
        //sc.close(); // Scanner is closed
    }

}
