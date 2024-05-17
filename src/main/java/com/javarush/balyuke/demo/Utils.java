package com.javarush.balyuke.demo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.javarush.balyuke.consoleui.constants.ConstantsPrint.PRINT_CHOOSE_OPTION;

public class Utils {
    public static Map toMap(Object[] keys, Object[] values) {
        int keysSize = (keys != null) ? keys.length : 0;
        int valuesSize = (values != null) ? values.length : 0;

        if (keysSize == 0 && valuesSize == 0) {
            // return mutable map
            return new HashMap();
        }

        if (keysSize != valuesSize) {
            throw new IllegalArgumentException(
                    "The number of keys doesn't match the number of values.");
        }

        Map map = new HashMap();
        for (int i = 0; i < keysSize; i++) {
            map.put(keys[i], values[i]);
        }

        return map;
    }

    public static void printFile(String fileName){
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verifyText(String text)
    {
        text = text.trim();

        if(text == null || text.equals(""))
            return false;

        if(!text.matches("[а-яА-Я.,'! ?:\\-]"))
            return false;

        return true;
    }


    public static String addSpaceAfterChar(String msg) {
        char[] msgCharArray = msg.toCharArray();
        char[] resCharArray = new char[msg.length()*2];
        char space = ' ';
        StringBuilder result = new StringBuilder();

        for (char msgChar : msgCharArray) {
            result.append(msgChar);
            if (msgChar != space)
                result.append(space);
        }

        return result.toString();
    }

    public static String addSpaceAfterCharShort (String msg){
        return  msg.replaceAll("[а-яА-Я.,'! ?:-а-яА-Я.,'! ?:-]", " $0 ").replace("  ", " ").trim();
    }

    public static boolean isInt(String string) {

        //System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
            //System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            //System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    public static ArrayList<String> readFileToArrayList(String filename){
        ArrayList<String> result = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(filename))) {
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearFileContent(String filePath) throws IOException {
        // Open the file for writing (this will create the file if it doesn't exist)
        try(FileWriter fileWriter = new FileWriter(filePath)) {

            // Truncate the file to zero length
            fileWriter.write("");

            // Close the file writer
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFileFromArrayList(String outFile, ArrayList<String> stringsOutFile, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile, append)))
        {
            for (String str : stringsOutFile) {
                writer.write(str);
                writer.newLine();
            }
            System.out.println("ArrayList written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFileToListString(String fileName){
        List<String> words = null;
        try {
            words = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }

    public static Set<String> readFileToSetString(String fileName){
        List<String> words = null;
        try {
            words = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new HashSet<String>(words);
    }

    public static Set<String> getSetString(String s){
        return new HashSet<String>(Arrays.asList(s.split(" ")));
    }

    public static Set<String> getSetString (List<String> list){
        return new HashSet<String>(list);
    }

    public static int countsWordsSet(Set<String> inputList, Set<String> wordsList) {
        int sizeInputList = inputList.size();
        int countEqualMatches = 0;
        for(String a : inputList){
            for(String b : wordsList){
                if(a.equals(b))
                    countEqualMatches++;
                if (countEqualMatches == sizeInputList)
                    break;
            }
        }
        //System.out.println(countEqualMatches);
        return countEqualMatches;
    }


    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print(PRINT_CHOOSE_OPTION);
    }

    // TODO Rewrite method replaceSymbol(String str, String alphabetMarks)
    public static String replaceSymbol(String str, String alphabetMarks) {
        //out.println("start: "+str);
//        for (int i = 0; i < alphabetMarks.length(); i++) {
//            if (alphabetMarks.charAt(i) == '?')
//                result = result.replaceAll(String.valueOf(alphabetMarks.charAt(i)), " ");
//            else
//                result = result.replaceAll(""+String.valueOf("\\"+alphabetMarks.charAt(i)), " ");
//            result = result.replaceAll("  ", " ");
//        }
        String s1 = str.replace(".","");
        String s2 = s1.replaceAll(",","");
        String s3 = s2.replaceAll("\"","");
        String s4 = s3.replaceAll("'"," ");
        String s5 = s4.replace(":","");
        String s6 = s5.replace("-","");
        String s7 = s6.replace("!","");
        String s8 = s7.replaceAll("  "," ");
        String s9 = s8.replaceAll("\\?","");

        String s10 = s9.replaceAll("\\s+", " ");

        String result= s10;
        //out.println("end: "+result);
        return result;
    }
}

