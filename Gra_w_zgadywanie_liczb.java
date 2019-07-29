package src;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Gra_w_zgadywanie_liczb {

    public static void main(String[] args) {
        System.out.println("Wymyśliłem sobie liczbę całkowitą od 0 do 100 - zgadnij jaką?");
        Random generator = new Random();
        int randomNumber = generator.nextInt(101);

        boolean end = true;
        while (end) {
            try {
                int number = askUser();
                if (number == randomNumber) {
                    System.out.println("-----------------------------\n Gratulacje odgadłeś liczbę \n-----------------------------");
                    end = false;
                } else if (number < randomNumber) {
                    System.out.println("Podana liczba jest za mała.");
                } else {
                    System.out.println("Podana liczba jest za duża.");
                }
            } catch (InputMismatchException e) {
                System.out.println("To nie jest liczba całkowita z zakresu od 0 do 100.");
            }
        }
    }

    static int askUser() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.print(" - Proszę podać liczbę: ");
        int number = scan.nextInt();
        return number;
    }

}