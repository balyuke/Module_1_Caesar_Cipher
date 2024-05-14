package com.javarush.balyuke;

import java.io.IOException;
import java.util.*;

import static com.javarush.balyuke.Utils.*;
import static com.javarush.balyuke.Constants.*;

import static java.lang.System.*;

public class CaesarCipherMain {

    public static String encoding(String inputMessage, int offset) {

        char[] alphabet = ALPHABET.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputMessage.length(); i++) {
            for (int j = 0; j < LEN_ALPH; j++) {
                if (inputMessage.charAt(i) == alphabet[j]){
                    result.append(alphabet[(j+offset) % LEN_ALPH]);
                        /*
                        System.out.printf("Find symbol %s(%s), record to %s(%s)\n",
                                alphabet[j], j,
                                alphabet[(j + offset) % alphabet.length], (j + offset) % alphabet.length);
                         */
                }

            }
            
        }

        return result.toString();
    }

    public static String decoding(String inputMessage, int offset){
        return encoding(inputMessage, LEN_ALPH - (offset % LEN_ALPH));
    }

    public static List<String> bruteForceConsole(String inputMessage, String inputAlphabet){
        char[] alphabet = inputAlphabet.toCharArray();
        List<String> res = new ArrayList<>();
        String message = inputMessage.toLowerCase();
        //int offset = inputAlphabet.length();
        int offset = 0;
        while (offset < inputAlphabet.length()) {
            StringBuilder result = new StringBuilder();
            offset++;
            for (int i = 0; i < message.length(); i++) {
                for (int j = 0; j < alphabet.length; j++) {
                    if (message.charAt(i) == alphabet[j]) {
                        result.append(alphabet[(j + offset) % alphabet.length]);
                    }
                }
            }
            res.add(result.toString());
            //System.out.println("offset="+ (alphabet.length - offset) + ": result=" + res.get(offset-1));
        }
        return res;

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
            // Crypt
            String encryptMessage = encoding(message, offset);
            System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage);
            // Decrypt
            System.out.print(PRINT_DECRYPTED_TEXT + decoding(encryptMessage, offset));
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

    public static String replaceSymbol(String str, String alphabetMarks) {
        //out.println("start: "+str);
//        for (int i = 0; i < alphabetMarks.length(); i++) {
//            if (alphabetMarks.charAt(i) == '?')
//                result = result.replaceAll(String.valueOf(alphabetMarks.charAt(i)), " ");
//            else
//                result = result.replaceAll(""+String.valueOf("\\"+alphabetMarks.charAt(i)), " ");
//            result = result.replaceAll("  ", " ");
//        }
        String s1 = str.replace('?',' ');
        String s2 = s1.replaceAll("  "," ");
        String s3 = s2.replace(',',' ');
        String s4 = s3.replaceAll("  "," ");
        String s5 = s4.replace('.',' ');
        String s6 = s5.replaceAll("  "," ");
        String s7 = s6.replace(':',' ');
        String s8 = s7.replaceAll("  "," ");
        String s9 = s8.replace('!',' ');
        String s10 = s9.replaceAll("  "," ");
        String s11 = s10.replace('-',' ');
        String s12 = s11.replaceAll("  "," ");

        String result= s11;
        //out.println("end: "+result);
        return result;
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
            String encryptMessage = encoding(message, offset);
            stringsOutFile.add(encryptMessage);
            System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage);
            System.out.print(PRINT_DECRYPTED_TEXT);
            //System.out.print(decoding(encryptMessage, offset));
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