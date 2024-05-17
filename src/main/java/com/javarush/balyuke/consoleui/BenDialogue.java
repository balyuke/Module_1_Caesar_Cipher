package com.javarush.balyuke.consoleui;

import com.javarush.balyuke.demo.CaesarCipherMain;

import java.io.IOException;
import java.util.*;

import static com.javarush.balyuke.caesar.CaesarAlphabet.*;
import static com.javarush.balyuke.demo.Utils.addSpaceAfterChar;
import static com.javarush.balyuke.demo.Utils.*;
import static com.javarush.balyuke.consoleui.constants.ConstantsPrint.*;

public class BenDialogue implements Dialogue {

    private static final Scanner sc = new Scanner(System.in);

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


    public static void mainMethod()  {
        LEN_ALPH = ALPHABET.length();
        headerMenu();

        String input ;

        int option;
        printMenu(MENU_OPTIONS);
        while(sc.hasNext() && !(input = sc.next()).equals("3")) {

            if (isInt(input)) {
                option = Integer.parseInt(input);
                switch (option){
                    case 1:
                        CaesarCipherMain.inputManual();
                        break;
                    case 2:
                        try {
                            CaesarCipherMain.inputFile();
                        }
                        catch (IOException e) {
                            System.out.println(PRINT_MY_EXCEPTION + e.getMessage());
                        }
                        break;
                    case 3:
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
