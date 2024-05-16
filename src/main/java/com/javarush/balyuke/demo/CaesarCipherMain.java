package com.javarush.balyuke.demo;

import java.util.*;

import static com.javarush.balyuke.caesar.CaesarAlphabet.ALPHABET;
import static com.javarush.balyuke.caesar.CaesarAlphabet.LEN_ALPH;
import static com.javarush.balyuke.consoleui.constants.ConstantsPrint.*;

public class CaesarCipherMain {

//    public static String encoding(String inputMessage, int offset) {
//
//        char[] alphabet = ALPHABET.toCharArray();
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < inputMessage.length(); i++) {
//            for (int j = 0; j < LEN_ALPH; j++) {
//                if (inputMessage.charAt(i) == alphabet[j]){
//                    result.append(alphabet[(j+offset) % LEN_ALPH]);
//                        /*
//                        System.out.printf("Find symbol %s(%s), record to %s(%s)\n",
//                                alphabet[j], j,
//                                alphabet[(j + offset) % alphabet.length], (j + offset) % alphabet.length);
//                         */
//                }
//
//            }
//
//        }
//
//        return result.toString();
//    }

//    public static String decoding(String inputMessage, int offset){
//        return encoding(inputMessage, LEN_ALPH - (offset % LEN_ALPH));
//    }

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

    // если закрыть Scanner, то вылетаешь из верхнего меню

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