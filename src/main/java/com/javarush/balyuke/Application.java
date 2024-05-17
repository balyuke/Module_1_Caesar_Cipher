package com.javarush.balyuke;

import com.javarush.balyuke.consoleui.BenDialogue;
import com.javarush.balyuke.consoleui.ConsoleDialogue;
import com.javarush.balyuke.consoleui.Dialogue;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.javarush.balyuke.demo.Utils.printMenu;
import static com.javarush.balyuke.consoleui.constants.ConstantsPrint.PRINT_UNRECOGNIZED_INPUT_OPTION;
import static java.lang.System.exit;

public class Application {

    // TODO Try implement swing interface
    private static Map<Integer, Dialogue> dialogs = new HashMap<>();
    static {
        dialogs.put(1, new ConsoleDialogue());
        dialogs.put(2, new BenDialogue());
    }

    protected static final String[] START_MENU = {
            "\nChoose mode working",
            "0 - Exit",
            "1 - Console Dialogue",
            "2 - Ben Dialog",
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = null;
        printMenu(START_MENU);
        while(scanner.hasNext() && !(input = scanner.next()).equals("0")) {

            int dialogNumber = Integer.parseInt(input);

            if (dialogs.containsKey(dialogNumber)) {
                Dialogue dialogue = dialogs.getOrDefault(dialogNumber, new BenDialogue());
                dialogue.start();
            } else if (dialogNumber == 0) {
                exit(0);
            } else {
                System.err.println (PRINT_UNRECOGNIZED_INPUT_OPTION + dialogNumber );
            }

            printMenu(START_MENU);
        }
    }
}
