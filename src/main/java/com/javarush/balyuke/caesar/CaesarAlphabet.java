package com.javarush.balyuke.caesar;

import com.javarush.balyuke.caesar.exception.CaesarAlphabetException;

import java.util.*;

public class CaesarAlphabet {

    private static final String ALPHABET_RU_LARGE = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    private static final String ALPHABET_RU_SMALL = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private static final String ALPHABET_DIGITS = "0123456789";

    public static final String ALPHABET_PUNCTUATION_MARKS = ".,\"\':-! ?";

    public static final String ALPHABET = ALPHABET_RU_LARGE + ALPHABET_RU_SMALL + ALPHABET_PUNCTUATION_MARKS;
    public static int LEN_ALPH = 75;

    public static final String ALPHABET_BRUTE = ALPHABET_RU_SMALL + ALPHABET_PUNCTUATION_MARKS;


    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";

    // Declaring the color
    // Custom declaration
    public static final String ANSI_GREEN = "\u001B[32m";


    private static final Character[] RU_ALPHABET_LARGE = {
            'А','Б','В','Г','Д','Е','Ж','З','И','Й',
            'К','Л','М','Н','О','П','Р','С','Т','У',
            'Ф','х','ц','ч','ш','щ','ъ','ы','ь','Э',
            'Ю','Я'
    };

    private static final Character[] RU_ALPHABET = {
            'а','б','в','г','д','е','ж','з','и','й',
            'к','л','м','н','о','п','р','с','т','у',
            'ф','х','ц','ч','ш','щ','ъ','ы','ь','э',
            'ю','я'
    };

    private static final Character[] EN_ALPHABET = {
            'a','b','c','d','e','f','g','h','i','j',
            'k','l','m','n','o','p','q','r','s','t',
            'u','v','w','x','y','z'
    };

    private static final Character[] SYMBOLS_ALPHABET = {
            '1','2','3','4','5','6','7','8','9','0',
            ' ',',','.','\'','!',':',';','*','(',')',
            '<','>','@','$','%','&','?'
    };

    private final List<Character> alphabet;

    private final Map<Character, Integer> charIndex;

    public CaesarAlphabet(){
        List<Character> temporaryAlphabet = new ArrayList<>();

//        temporaryAlphabet.addAll(Arrays.asList(RU_ALPHABET_LARGE));
//        temporaryAlphabet.addAll(Arrays.asList(RU_ALPHABET));
//        temporaryAlphabet.addAll(Arrays.asList(SYMBOLS_ALPHABET));

        for (char c : ALPHABET.toCharArray()) {
            temporaryAlphabet.add(c);
        }

        alphabet = List.copyOf(temporaryAlphabet);

        charIndex = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++){
            charIndex.put(alphabet.get(i), i);
        }
    }

    public Character getCharByIndex(int index) {
        if (index < 0 || index > alphabet.size()){
            throw new CaesarAlphabetException("Invalid index. Index: " + index + ". Valid is from 0 to " + alphabet.size());
        }

        return alphabet.get(index);
    }

    public int getCharIndex(Character character){
        if (!charIndex.containsKey(character)) {
            throw new CaesarAlphabetException("Invalid character. Char: " + character + ". ");
        }

        return charIndex.get(character);
    }

    public int getSize(){ return alphabet.size(); }

}
