package Lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selectați sistemul de operare: ");
        System.out.println("1. Windows");
        System.out.println("2. Linux");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consumă linia rămasă

        CommandExecutor executor;

        if (choice == 1) {
            executor = new WindowsCommandExecutor();
        } else if (choice == 2) {
            executor = new LinuxCommandExecutor();
        } else {
            System.out.println("Opțiune invalidă!");
            return;
        }

        List<String> commands = new ArrayList<>();
        commands.add(choice == 1 ? "ping -n 4 google.com" : "ping -c 4 google.com");
        commands.add(choice == 1 ? "tracert google.com" : "traceroute google.com");

        executor.executeCommands(commands);
    }
}
