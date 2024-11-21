package Lab5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    public static void saveOutputToFile(String command, String output, String executorType) {
        try {
            String sanitizedCommand = command.replaceAll("[^a-zA-Z0-9]", "_");
            String fileName = executorType + "_" + sanitizedCommand + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(output);
            }

            System.out.println("Rezultatul a fost salvat în fișierul: " + fileName);

        } catch (IOException e) {
            System.err.println("Eroare la salvarea fișierului!");
            e.printStackTrace();
        }
    }
}