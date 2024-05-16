package com.javarush.balyuke.file;

import com.javarush.balyuke.file.exception.FileProcessingException;

import java.io.IOException;
import java.nio.file.*;
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
            Files.writeString(filePath, content, FILE_WRITE_OPTIONS);
        } catch (IOException | InvalidPathException ex) {
            throw new FileProcessingException(ex.getMessage(), ex);
        }
    }
}
