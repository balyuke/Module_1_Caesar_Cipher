package com.javarush.balyuke.file;

import com.javarush.balyuke.file.exception.FileProcessingException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    private static final StandardOpenOption[] FILE_WRITE_OPTIONS =
            { StandardOpenOption.CREATE, StandardOpenOption.APPEND };


    // работает для небольшого размера файлах 5-10 Мб.

    /**
     *
      * @param fileName
     * @return
     * throws FileProcessingException
     */
    public List<String> readFile (String fileName) {
        try {
            Path filePath = Path.of(fileName);
            return Files.readAllLines(filePath);
        } catch (IOException | InvalidPathException ex){
            //System.err.println("Error occured during reading file! Reason: " + ex.getMessage());
            throw new FileProcessingException(ex.getMessage(), ex);
        }
        ////return Collections.EMPTY_LIST;  // Так никогда НЕ ДЕЛАНЕМ, потому что в GUI сообщение об ошибке не отобразится!
    }

    /**
     *
     * @param fileName
     * @param content
     * throws FileProcessingException
     */
    public void  appendToFile(String fileName, String content) {
        try {
            Path filePath = Path.of(fileName);
            //System.out.println("Debug...method FileProcessor.appendToFile:  " + content);
            Files.writeString(filePath, content,/* StandardCharsets.UTF_8,*/ FILE_WRITE_OPTIONS);
        } catch (IOException | InvalidPathException ex) {
            throw new FileProcessingException(ex.getMessage(), ex);
        }
    }


    public static void writeFileFromArrayList(String fileName, ArrayList<String> content, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append)))
        {
            for (String str : content) {
                writer.write(str);
                writer.newLine();
            }
            System.out.println("ArrayList written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
