package com.javarush.baliuk;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.*;

public class CaesarCipherMain {

    public static final String alph = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\"\':-! ?";
    //public static final String alph = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\"\':-! ?";
    public static int lenAlph = 75;


    public static String startPath = new File("").getAbsolutePath();
    public static String inFile = startPath.concat( "\\src\\main\\resources\\files\\inFile.txt");
    public static String outFile = startPath.concat( "\\src\\main\\resources\\files\\outFile.txt");
    public static String freqFile = startPath.concat( "\\src\\main\\resources\\files\\freq.txt");

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
                int newAlphabetPosition = (originalAlphabetPosition + offset) % lenAlph;
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
        return encoding(message, lenAlph - (offset % lenAlph));
    }

    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }


    public static void main(String[] args)  {
        lenAlph = alph.length();
        headerMenu();

        String[] options = {"\nChoose mode working",
                "1- Manual",
                "2- File",
                "3- Exit",
        };

        Scanner sc = new Scanner(System.in);
        String input = null;

        int option = 1;
        printMenu(options);
        while(sc.hasNext() && !(input = sc.next()).equals("3")) {

            if (Utils.isInt(input)) {
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
                            System.out.println("My Exception: "+ e.getMessage());
                        }
                        break;
                    case 3:
                        exit(0);
                    default:
                        System.err.println ( "method main: Unrecognized input option: " + option );
                        break;
                }
            } else  {
                System.err.println ( "Unrecognized input option: " + input );
            }
            printMenu(options);
        }

        System.out.println("Stop CAESER CIPHER...\n");
        sc.close(); // Scanner is closed
    }

    // если закрыть Scanner, то вылетаешь из верхнего меню
    private static void inputManual() {
        System.out.println("\nWorking method inputManual");
        Scanner scanner = new Scanner(System.in);
        String input = null;
        try  {
            int offset = 0;
            System.out.print("\nEnter Offset value: ");
            while(scanner.hasNext()) {
                input = scanner.nextLine();
                if (Utils.isInt(input)) {
                    offset = Integer.parseInt(input);
                    if (offset >= 0 && offset < lenAlph) break;
                    else System.err.println ("\nValue Offset must between 1 and " + (lenAlph-1)+ "\n");
                }
                else
                    System.err.println ( "\nmethod inputManual Unrecognized Offset option: " + input + "\n");
                System.out.print("\nEnter Offset value: ");
            }
            System.out.print("Enter the text Message to be encrypted: ");
            String message = scanner.nextLine();
            String encryptMessage = encoding(message, offset);
            System.out.println("Encrypted Text : " + encryptMessage/*encoding(message, offset)*/);
            System.out.print("Decryptd Text : ");
            System.out.print(decoding(encryptMessage/*encoding(message, offset)*/, offset));
            System.out.println();
        }
        catch (NoSuchElementException e){
            System.out.println("method inputManual " + e.getMessage());
        }
    }

    private static void inputFile() throws IOException {
        System.out.println("\nWorking method inputFile");



        ArrayList<String> stringsInFile  = Utils.readFileToArrayList(inFile);
        if (stringsInFile.isEmpty() || stringsInFile == null || stringsInFile.size() < 2) {
            System.out.println("Check file "+inFile);
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

        ArrayList<String> stringsOutFile  = Utils.readFileToArrayList(outFile);
        for (String str : stringsInFile){
            String message = str;
            System.out.print("\nInput the text message to be encrypted: "+message);
            String encryptMessage = encoding(message, offset);
            stringsOutFile.add(encryptMessage);
            System.out.println("\nEncrypted Text : " + encryptMessage);
            System.out.print("\nDecryptd Text : ");
            System.out.print(decoding(encryptMessage, offset));
            System.out.println();
        }

        Utils.clearFileContent(outFile);
        Utils.writeFileFromArrayList(stringsOutFile, outFile, false);

    }



    private static void headerMenu() {
        System.out.println("\nStart CAESER CIPHER...");
        System.out.println("Use char from alphabet: ");
        System.out.println(Utils.addSpaceAfterChar(alph));
        System.out.println("Value offset must between 1 and " + (lenAlph-1));
    }

    private static void demo(){
        System.out.println("DEMO()!");
        System.out.println(alph);
        lenAlph = alph.length();
        System.out.println(lenAlph);

        //Utils.printFile(fileFreq);
        // загрука
        //Map<String, Double> mapFreq = Utils.byBufferedReader(fileFreq, "\t", DupKeyOption.OVERWRITE);
        //mapFreq.forEach((key, value) -> System.out.println(key + " " + value));

        //String msg = "мама?";
        //String s = "momй";
        //System.out.println(msg);
        //String msg1 = Utils.verifyText(s) ? s : s.replaceAll("[^а-яА-Я.,'! ?:-]", "");
        //System.out.println("msg1=" + msg1);
    }

    private static void work(){
        System.out.println("WORK()!");
    }

}