package com.javarush.balyuke;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.javarush.balyuke.Utils.*;
import static java.lang.System.*;

public class CaesarCipherMain {

    public static final String ALPHABET_RU_LARGE = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final String ALPHABET_RU_SMALL = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String ALPHABET_DIGITS = "0123456789";
    public static final String ALPHABET_PUNCTUATION_MARKS = ".,\"\':-! ?";
    //public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\"\':-! ?";
    public static final String ALPHABET = ALPHABET_RU_LARGE + ALPHABET_RU_SMALL + ALPHABET_PUNCTUATION_MARKS;
    public static final String PRINT_WORK = "WORK()!";
    public static final String PRINT_DEMO = "DEMO()!";
    public static final String PRINT_CHECK_FILE = "Check file ";
    public static final String PRINT_ENTER_OFFSET_VALUE = "\nEnter Offset value: ";
    public static final String PRINT_MY_EXCEPTION = "My Exception: ";
    public static final String PRINT_ENTER_THE_TEXT_MESSAGE_TO_BE_ENCRYPTED = "Enter the text Message to be encrypted: ";
    //public static final String alph = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\"\':-! ?";
    public static int LEN_ALPH = 75;


    public static String START_PATH_PROJECT = new File("").getAbsolutePath();
    public static String DEMO_IN_FILE = START_PATH_PROJECT.concat( "\\src\\main\\resources\\files\\demoInFile.txt");
    public static String DEMO_OUT_FILE = START_PATH_PROJECT.concat( "\\src\\main\\resources\\files\\demoOutFile.txt");
    public static String FREQUENCY_FILE = START_PATH_PROJECT.concat( "\\src\\main\\resources\\files\\freq.txt");

    public static final String[] MENU_OPTIONS = {
            "\nChoose mode working",
            "1- Manual",
            "2- File",
            "3- Exit",
    };

    public static final String PRINT_START_APP = "\nStart CAESER CIPHER...";
    public static final String PRINT_STOP_APP = "Stop CAESER CIPHER...\n";

    public static final String PRINT_ENCRYPTED_TEXT = "\nEncrypted text : ";
    public static final String PRINT_DECRYPTED_TEXT = "\nDecrypted text : ";

    public static final String PRINT_CHARS_ALPHABET = "Use char from alphabet: ";
    public static final String PRINT_HINT_OFFSET = "\nValue offset must between 1 and ";

    public static final String PRINT_CHOOSE_OPTION = "Choose your option : ";

    public static final String PRINT_UNRECOGNIZED_INPUT_OPTION = "\nUnrecognized input option: ";

    private static final Map<String, Double> EXPECTED_MAP_DISCARD = Stream.of(new String[][]{
            {"А", String.valueOf(7.64)},
            {"Б", String.valueOf(2.01)},
            {"В", String.valueOf(4.38)}
    }).collect(Collectors.toMap(data -> data[0], data -> Double.valueOf(data[1])));


    // static because all other functions are static, and we can't reference from a non-static
    public static String encoding(String message, int offset) {

        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'а';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % LEN_ALPH;
                char newCharacter = (char) ('а' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }


    // following same algorithm but in reverse way, plaintext becomes ciphertext and vice versa
    public static String decoding(String message, int offset) {
        return encoding(message, LEN_ALPH - (offset % LEN_ALPH));
    }

    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print(PRINT_CHOOSE_OPTION);
    }


    public static void main(String[] args)  {
        LEN_ALPH = ALPHABET.length();
        headerMenu();


        Scanner sc = new Scanner(System.in);
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
                        exit(0);
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
        sc.close(); // Scanner is closed
    }

    // если закрыть Scanner, то вылетаешь из верхнего меню
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
            String encryptMessage = encoding(message, offset);
            System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage);
            System.out.print(PRINT_DECRYPTED_TEXT + decoding(encryptMessage, offset));
            System.out.println();
        }
        catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
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
            String encryptMessage = encoding(message, offset);
            stringsOutFile.add(encryptMessage);
            System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage);
            System.out.print(PRINT_DECRYPTED_TEXT);
            System.out.print(decoding(encryptMessage, offset));
            System.out.println();
        }

        clearFileContent(DEMO_OUT_FILE);
        writeFileFromArrayList(stringsOutFile, DEMO_OUT_FILE, false);

    }



    private static void headerMenu() {
        System.out.println(PRINT_START_APP);
        System.out.println(PRINT_CHARS_ALPHABET);
        System.out.println(addSpaceAfterChar(ALPHABET));
        System.out.println(PRINT_HINT_OFFSET + (LEN_ALPH -1));
    }

    private static void demo(){
        System.out.println(PRINT_DEMO);
        System.out.println(ALPHABET);
        LEN_ALPH = ALPHABET.length();
        System.out.println(LEN_ALPH);

        //printFile(fileFreq);
        // загрука
        //Map<String, Double> mapFreq = byBufferedReader(fileFreq, "\t", DupKeyOption.OVERWRITE);
        //mapFreq.forEach((key, value) -> System.out.println(key + " " + value));

        //String msg = "мама?";
        //String s = "momй";
        //System.out.println(msg);
        //String msg1 = verifyText(s) ? s : s.replaceAll("[^а-яА-Я.,'! ?:-]", "");
        //System.out.println("msg1=" + msg1);
    }

    private static void work(){
        System.out.println(PRINT_WORK);
    }

}