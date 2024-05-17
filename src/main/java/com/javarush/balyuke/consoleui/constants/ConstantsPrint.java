package com.javarush.balyuke.consoleui.constants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstantsPrint {

    public static final String PRINT_BRUTE_FORCE = "\n\n=== bruteForce ===";
    public static final String PRINT_COUNT_OFFSET = "\ncount offset=";
    public static final String PRINT_TRY_OFFSET = "\t try offset = ";
    //public static int LEN_ALPH = 75;

    public static final String PRINT_WORK = "WORK()!";
    public static final String PRINT_DEMO = "DEMO()!";
    public static final String PRINT_CHECK_FILE = "Check file ";
    public static final String PRINT_ENTER_OFFSET_VALUE = "\nEnter Offset value: ";
    public static final String PRINT_MY_EXCEPTION = "My Exception: ";
    public static final String PRINT_ENTER_THE_TEXT_MESSAGE_TO_BE_ENCRYPTED = "Enter the text Message to be encrypted: ";

    public static final String[] MENU_OPTIONS = {
            "\nChoose mode working",
            "1 - Manual",
            "2 - File",
            "3 - Exit",
    };

    public static final String WELCOME_MESSAGE =
            """
                        
            Start DEMO CAESAR CIPHER...
            
            """;

    public static final String PRINT_STOP_APP = "Stop DEMO CAESAR CIPHER...\n";

    public static final String PRINT_ENCRYPTED_TEXT = "\nEncrypted text : ";
    public static final String PRINT_DECRYPTED_TEXT = "\nDecrypted text : ";
    public static final String PRINT_DECRYPTED_BRUTOFORCE_TEXT = "\nDecrypted text Brutoforce : ";

    public static final String PRINT_CHARS_ALPHABET = "Use char from alphabet: ";
    public static final String PRINT_HINT_OFFSET = "\nValue offset must between 1 and ";

    public static final String PRINT_CHOOSE_OPTION = "Choose your option : ";

    public static final String PRINT_UNRECOGNIZED_INPUT_OPTION = "\nUnrecognized input option: ";

    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
    // Declaring the color
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    private static final Map<String, Double> EXPECTED_MAP_DISCARD = Stream.of(new String[][]{
            {"А", String.valueOf(7.64)},
            {"Б", String.valueOf(2.01)},
            {"В", String.valueOf(4.38)}
    }).collect(Collectors.toMap(data -> data[0], data -> Double.valueOf(data[1])));

    public enum PRESS_DEFAULT {
        ENTER
    }




}
