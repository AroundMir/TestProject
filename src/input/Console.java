package input;

import entity.Person;

import java.util.Scanner;

public class Console {
     public static String read() {
         Scanner scanner = new Scanner(System.in);
         String str = scanner.nextLine();
         return str;
    }

}

