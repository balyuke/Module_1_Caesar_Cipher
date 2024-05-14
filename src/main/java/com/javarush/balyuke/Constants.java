package com.javarush.balyuke;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
    //public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\"\':-! ?";
    public static final String ALPHABET_RU_LARGE = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final String ALPHABET_RU_SMALL = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String ALPHABET_DIGITS = "0123456789";
    public static final String ALPHABET_PUNCTUATION_MARKS = ".,\"\':-! ?";
    public static final String ALPHABET = ALPHABET_RU_LARGE + ALPHABET_RU_SMALL + ALPHABET_PUNCTUATION_MARKS;
    public static final String ALPHABET_BRUTE = ALPHABET_RU_SMALL + ALPHABET_PUNCTUATION_MARKS;
    public static final String PRINT_BRUTE_FORCE = "\n\n=== bruteForce ===";
    public static final String PRINT_COUNT_OFFSET = "\ncount offset=";
    public static final String PRINT_TRY_OFFSET = "\t try offset = ";
    public static int LEN_ALPH = 75;

    public static final String PRINT_WORK = "WORK()!";
    public static final String PRINT_DEMO = "DEMO()!";
    public static final String PRINT_CHECK_FILE = "Check file ";
    public static final String PRINT_ENTER_OFFSET_VALUE = "\nEnter Offset value: ";
    public static final String PRINT_MY_EXCEPTION = "My Exception: ";
    public static final String PRINT_ENTER_THE_TEXT_MESSAGE_TO_BE_ENCRYPTED = "Enter the text Message to be encrypted: ";

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
}
