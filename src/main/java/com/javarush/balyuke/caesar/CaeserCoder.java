package com.javarush.balyuke.caesar;

import com.javarush.balyuke.file.FileProcessor;
import com.javarush.balyuke.file.FilenameValidator;

import java.util.List;

// средний уровень - бизнес-логика
public class CaeserCoder{

    // самый низкий уровень. автономный класс CaeserCipher
    private CaesarCipher caeserCipher;
    // самый низкий уровень. автономный класс FilenameValidator
    private FilenameValidator validator;
    // самый низкий уровень. автономный класс FileProcessor
    private FileProcessor fileProcessor;

    public CaeserCoder(){
        this.caeserCipher = new CaesarCipher(new CaesarAlphabet());
        this.validator = new FilenameValidator();
        this.fileProcessor = new FileProcessor();
    }

    // Метод encrypt предоставляет бизнес-логику
    public void encrypt(String inputFilename, String outputFilename, int key){
        validator.validateForReading(inputFilename);
        validator.validateForWriting(outputFilename);

        List<String> sourceLines = fileProcessor.readFile(inputFilename);
        for (String sourceLine : sourceLines){
            String encryptLine = caeserCipher.encrypt(sourceLine, key);
            fileProcessor.appendToFile(outputFilename, encryptLine);
            //ArrayList<String> content = new ArrayList<>(Arrays.asList(encryptLine.split("")));
            //fileProcessor.writeFileFromArrayList(outputFilename, content, false);
        }
    }

    // Метод decrypt предоставляет бизнес-логику
    // decrypt похож на encrypt. Разница лишь в одной строке. можно их переписать через лямбда-выражение
    public void decrypt(String inputFilename, String outputFilename, int key){
        validator.validateForReading(inputFilename);
        validator.validateForWriting(outputFilename);

        List<String> sourceLines = fileProcessor.readFile(inputFilename);
        for (String sourceLine : sourceLines){
            String decryptLine = caeserCipher.decrypt(sourceLine, key);
            fileProcessor.appendToFile(outputFilename, decryptLine);
            //ArrayList<String> content = new ArrayList<>(Arrays.asList(decryptLine.split("")));
            //fileProcessor.writeFileFromArrayList(outputFilename, content, false);
        }
    }


    public void decryptBrutoforce(String inputFilename, String outputFilename){
        validator.validateForReading(inputFilename);
        validator.validateForWriting(outputFilename);

        List<String> sourceLines = fileProcessor.readFile(inputFilename);
        for (String sourceLine : sourceLines){
            String decryptLine = caeserCipher.decryptBrutoforce(sourceLine);
            fileProcessor.appendToFile(outputFilename, decryptLine);
            //System.out.println("Debug...method CaesarCoder.decryptBrutoforce:  " + decryptLine);
            //ArrayList<String> content = new ArrayList<>(Arrays.asList(decryptLine.split("")));
            //fileProcessor.writeFileFromArrayList(outputFilename, content, false);
        }

    }


}
