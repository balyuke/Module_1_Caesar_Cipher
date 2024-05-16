package com.javarush.balyuke.file;

import com.javarush.balyuke.file.exception.FileProcessingException;

import java.nio.file.*;
import java.util.List;

public class FilenameValidator {

    public static final List<String> FORBIDDEN_DIRS_FILES =
            List.of("System", "Program Files");

    public static final String SYSTEM_SEPARATOR = "\\\\";   // System.getProperty("file.separator");
    public static final String FILE_IS_DIRECTORY = "File is directory";
    public static final String FILE_IS_NOT_ACCESSIBLE_FOR_WRITING = "File is not accessible for writing";
    public static final String FILE_DOES_NOT_EXIST = "File doesn't exist";
    public static final String DON_T_HAVE_RIGHT_TO_READ_FROM_THE_FILE = "You don't have right to read from the file";
    public static final String PATH_CONTAINS_FORBIDDEN_PART = "Path contains forbidden part: ";
    public static final String INVALID_PATH = "Invalid path. Reason: ";

    public void validateForWriting(String filename){
        Path path = validatePath(filename);

        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new FileProcessingException(FILE_IS_DIRECTORY + path);
            }

            if (!Files.isWritable(path)){
                throw new FileProcessingException(FILE_IS_NOT_ACCESSIBLE_FOR_WRITING + path);
            }
        }
    }

    public void validateForReading(String filename){
        Path path = validatePath(filename);

        if (Files.exists(path)) {
            if (Files.notExists(path)) {
                throw new FileProcessingException(FILE_DOES_NOT_EXIST + path);
            }

            if (Files.isDirectory(path)) {
                throw new FileProcessingException(FILE_IS_DIRECTORY + path);
            }

            if (!Files.isReadable(path)){
                throw new FileProcessingException(DON_T_HAVE_RIGHT_TO_READ_FROM_THE_FILE + path);
            }
        }
    }

    private Path validatePath(String filename) {

        for (String pathPart : filename.split(SYSTEM_SEPARATOR)) {
            if (FORBIDDEN_DIRS_FILES.contains(pathPart)) {
                throw new FileProcessingException(PATH_CONTAINS_FORBIDDEN_PART + pathPart);
            }
        }

        try {
            Path path = Path.of(filename);
            return path;
        } catch (InvalidPathException ex) {
            throw new FileProcessingException(INVALID_PATH +ex.getMessage(), ex);
        }
    }

}
