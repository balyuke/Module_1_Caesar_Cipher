package com.javarush.balyuke.file.constants;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConstantsFiles {

    public static String START_PATH_PROJECT = new File("").getAbsolutePath();

    public static String DEMO_IN_FILE = START_PATH_PROJECT.concat( "\\src\\main\\resources\\files\\demoInFile.txt");

    public static String DEMO_OUT_FILE = START_PATH_PROJECT.concat( "\\src\\main\\resources\\files\\demoOutFile.txt");

    public static String FREQUENCY_FILE = START_PATH_PROJECT.concat( "\\src\\main\\resources\\files\\freq.txt");

    public static String DICTIONARY_FILE = START_PATH_PROJECT.concat( "\\src\\main\\resources\\files\\dict.txt");

    public static Path DIR_WORK = Paths.get("D:\\projects\\java\\jr\\Module_1_Caesar_Cipher\\src\\main\\resources\\files\\");
}
