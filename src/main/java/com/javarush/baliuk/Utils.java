package com.javarush.baliuk;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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


    public static Map<String, Double> byBufferedReader(String filePath, String delimeter, DupKeyOption dupKeyOption) {
        HashMap<String, Double> map = new HashMap<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(delimeter, 2);
                if (keyValuePair.length > 1) {
                    String key = keyValuePair[0];
                    Double value = Double.valueOf(keyValuePair[1]);
                    if (DupKeyOption.OVERWRITE == dupKeyOption) {
                        map.put(key, value);
                    } else if (DupKeyOption.DISCARD == dupKeyOption) {
                        map.putIfAbsent(key, value);
                    }
                } else {
                    System.out.println("No Key:Value found in line, ignoring: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void writeFileFromArrayList(ArrayList<String> stringsOutFile, String outFile, boolean append) {
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


}

