package com.javarush.balyuke.consoleui;

public enum Operation {
    EXIT(0, "exit", () -> System.out.println("Operation 1")),
    ENCRYPTION(1, "encrypt file", () -> System.out.println("Operation 2")),
    DECRYPTION(2, "decrypt file", () -> System.out.println("Operation 3")),
    BRUTOFORCE(3, "try to crack file with brutoforce", () -> System.out.println("Operation 4"));

    private final int number;
    private final String description;
    private final Runnable runnable;

    Operation(int number, String descrition, Runnable runnable) {
        this.number = number;
        this.description = descrition;
        this.runnable = runnable;
    }

    public int getNumber() {return number; }

    public String getDescription() { return description; }

    public void run() { this.runnable.run(); }

    public static Operation getByNumber(int number){
        for(Operation operation: values()) {
            if(operation.getNumber() == number){
                return operation;
            }
        }
        //return Operation.EXIT;
        throw new IllegalArgumentException("Wrong number for operation");
    }
}
