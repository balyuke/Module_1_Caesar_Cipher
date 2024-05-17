package com.javarush.balyuke.caesar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.javarush.balyuke.caesar.CaesarAlphabet.*;
//import static com.javarush.balyuke.demo.CaesarCipherMain.bruteForceConsole;
import static com.javarush.balyuke.demo.Utils.replaceSymbol;
import static com.javarush.balyuke.demo.Utils.*;
import static com.javarush.balyuke.file.constants.ConstantsFiles.DICTIONARY_FILE;

public class CaesarCipher {

    private final CaesarAlphabet alphabet;

    public CaesarCipher(CaesarAlphabet alphabet){ this.alphabet = alphabet; }

    public static String encrypt (String inputMessage, int offset) {
        //return process(inputMessage, offset);
        return encoding(inputMessage, offset);
    }

    private static String encoding(String inputMessage, int offset) {
        char[] alphabet = ALPHABET.toCharArray();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputMessage.length(); i++) {
            for (int j = 0; j < LEN_ALPH; j++) {
                if (inputMessage.charAt(i) == alphabet[j]){
                    result.append(alphabet[(j+offset) % LEN_ALPH]);
                        // System.out.printf("Find symbol %s(%s), record to %s(%s)\n", alphabet[j], j, alphabet[(j + offset) % alphabet.length], (j + offset) % alphabet.length);
                }
            }
        }
        return result.toString();
    }

    public static String decrypt (String inputMessage, int offset) {
        //return process(inputMessage, -offset);
        String result = encoding(inputMessage, LEN_ALPH - (offset % LEN_ALPH));
        System.out.println("Debug...method CaesarCipher.decrypt:  " + result);
        return result;
    }


    public static String decryptBrutoforce (String inputMessage) {
        //return process(inputMessage, -offset);
        //return encoding(inputMessage, LEN_ALPH - (offset % LEN_ALPH));

        List<String> listBruteForceConsole =  getListAllDecrypt(inputMessage, ALPHABET_BRUTE);
        Set<String> setWordsDictFile = readFileToSetString(DICTIONARY_FILE);
        //System.out.println(PRINT_ENCRYPTED_TEXT + inputMessage + PRINT_COUNT_OFFSET + ALPHABET_BRUTE.length());
        int correctOffset = 0;
        int countWordMatches = 0;
        for(int i = 0; i < listBruteForceConsole.size(); i++) {
            //System.out.println(PRINT_TRY_OFFSET + (listBruteForceConsole.size() - i - 1) + PRINT_DECRYPTED_TEXT + listBruteForceConsole.get(i) + "   ...i=" + i);
            //if (i == 38) {
            String s = replaceSymbol(listBruteForceConsole.get(i), ALPHABET_PUNCTUATION_MARKS);
            Set<String> setInputWords = getSetString(s);

            int countWords = countsWordsSet(setInputWords, setWordsDictFile);
            if (countWords > countWordMatches) {
                countWordMatches = countWords;
                correctOffset = i;
            }
            //}
        }
        System.out.println("Debug...method CaesarCipher.decryptBrutoforce:  " + listBruteForceConsole.get(correctOffset) + " ... correctOffset="+correctOffset);
        return listBruteForceConsole.get(correctOffset);

    }



    public static List<String> getListAllDecrypt (String inputMessage, String inputAlphabet) {
        char[] alphabet = inputAlphabet.toCharArray();
        List<String> res = new ArrayList<>();
        String message = inputMessage.toLowerCase();
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
        }
        return res;
    }


    private String process(String originalText, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < originalText.length(); i++) {
            Character originalChar = Character.toLowerCase(originalText.charAt(i));
            int originalCharIndex = alphabet.getCharIndex(originalChar);
            int newCharIndex = (alphabet.getSize() + (originalCharIndex + key)) % alphabet.getSize();

            result.append(alphabet.getCharByIndex(newCharIndex));
        }

        return result.toString();
    }

    // так как я знаю, что при вызове метода строка состоит из одного символа, обращаемся к ней через индекс 0
    private Character toLowerCase(Character character){
        return (character + "").toLowerCase().charAt(0);
    }

}
