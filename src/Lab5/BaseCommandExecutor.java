package Lab5;

import java.io.*;
import java.util.List;

public abstract class BaseCommandExecutor implements CommandExecutor {
    protected abstract String[] buildCommand(String command);

    @Override
    public void executeCommands(List<String> commands) {
        for (String command : commands) {
            try {
                System.out.println("Execut comanda: " + command);

                ProcessBuilder processBuilder = new ProcessBuilder(buildCommand(command));
                processBuilder.redirectErrorStream(true); // Combine stdout și stderr
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    output.append(line).append(System.lineSeparator());
                }

                int exitCode = process.waitFor();
                System.out.println("Comanda a terminat cu codul de ieșire: " + exitCode);

                saveOutputToFile(command, output.toString(), this.getClass().getSimpleName());

            } catch (IOException | InterruptedException e) {
                System.err.println("Eroare la executarea comenzii: " + command);
                e.printStackTrace();
            }
        }
    }

    private void saveOutputToFile(String command, String output, String executorType) {
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
