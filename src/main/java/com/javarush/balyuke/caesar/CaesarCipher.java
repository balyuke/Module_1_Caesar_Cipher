package com.javarush.balyuke.caesar;

import static com.javarush.balyuke.caesar.CaesarAlphabet.ALPHABET;
import static com.javarush.balyuke.caesar.CaesarAlphabet.LEN_ALPH;

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
        return encoding(inputMessage, LEN_ALPH - (offset % LEN_ALPH));
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
