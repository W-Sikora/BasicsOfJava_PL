package src;

import java.util.Random;
import java.util.Scanner;

public class Gra_w_zgadywanie_liczb {

    public static void main(String[] args) {

        int maxNumber = 100;
        System.out.println("\n Wymyśliłem sobie liczbę całkowitą od 0 do " + maxNumber +
                           " - zgadnij jaką? \n" + "---------------------------------------------------------------");
        int randNumber = drawNumber(maxNumber);
        compareNumbers(randNumber, maxNumber);
    }

    // generuje liczbę losową z zakresu od 0 do maxNumber
    static int drawNumber(int maxNumber) {
        Random generator = new Random();
        return generator.nextInt(maxNumber + 1);
    }

    // pobiera od użytkownika liczbę całkowitą z przedziału od 0 do maxNumber
    static int askUser(int maxNumber) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(" - Proszę podać liczbę całkowitą z zakresu od 0 do " +
                    maxNumber + ": ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.nextLine();
            }
        }
    }

    // porównuje liczbę zebraną od użytkownika z liczbą wygenerowaną losową
    static void compareNumbers(int randomNumber, int maxNumber) {
        boolean end = true;
        while (end) {
            int number = askUser(maxNumber);
            if (number == randomNumber) {
                System.out.println("-----------------------------\n " +
                        "Gratulacje odgadłeś liczbę" +
                        " \n-----------------------------");
                end = false;
            } else if (number < randomNumber) {
                System.out.println("Podana liczba jest za mała.");
            } else {
                System.out.println("Podana liczba jest za duża.");
            }
        }
    }

}
