package src;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Symulator_LOTTO {

    public static void main(String[] args) {

        System.out.println("Symulator LOTTO");

        int[] userNumbers = getUserNumbers();
        userNumbers = sortArray(userNumbers);

        System.out.print("\nTwoje liczby to: ");
        for (int i = 0; i < userNumbers.length; i++) {
            if (i < userNumbers.length - 1) {
                System.out.print(userNumbers[i] + ", ");
            } else {
                System.out.println(userNumbers[i]);
            }
        }

        System.out.print("\nWylosowane liczby to: ");
        int[] array = randNumbers();
        array = sortArray(array);
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                System.out.print(array[i] + ", ");
            } else {
                System.out.println(array[i]);
            }

        }

        System.out.println("\n" + compare(userNumbers, array));

    }

    // wypełnia tablicę liczbami całkowitymi bez powtórzeń pobranymi od użytkownika jeśli > 0 && < 50
    static int[] getUserNumbers() {
        int[] userNumbers = new int[6];
        int i = 0;
        while (i < 6) {
            int number = askUser();
            if (number > 0 && number < 50 && include(userNumbers, number)) {
                userNumbers[i] = number;
                i++;
            }
        }
        return userNumbers;
    }

    // pobiera od użytkownika liczbę całkowitą
    static int askUser() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(" - Proszę podać liczbę całkowitą z zakresu od 0 do 49: ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.nextLine();
            }
        }
    }

    // zwraca tablicę losowych liczb > 0 && < 50 bez powtórzeń
    static int[] randNumbers() {
        Random generator = new Random();
        int[] randomNumbersArray = new int[6];

        int i = 0;
        while (i < 6) {
            int randomNumber = generator.nextInt(50);
            if (randomNumber > 0 && include(randomNumbersArray, randomNumber)) {
                randomNumbersArray[i] = randomNumber;
                i++;
            }
        }
        return randomNumbersArray;
    }

    // sprawdza powtarzalność elementów w tablicy
    static boolean include(int[] array, int number) {
        boolean result;
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                sum++;
            }
        }
        if (sum > 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    // zwraca posortowaną rosnąco tablicę
    static int[] sortArray(int[] array) {
        Arrays.sort(array);
        return array;
    }

    //
    static String compare(int[] userNumbers, int[] randNumbers) {
        String result = "";
        int repeat = 0;
        for (int i = 0; i < userNumbers.length; i++) {
            if (userNumbers[i] == randNumbers[i]) {
                repeat++;
            }
        }
        if (repeat > 5) {
            result += "Gratulacje trafiłeś 6!";
        } else if (repeat > 4) {
            result += "Gratulacje trafiłeś 5!";
        } else if (repeat > 3) {
            result += "Gratulacje trafiłeś 4!";
        } else if (repeat > 2) {
            result += "Gratulacje trafiłeś 3!";
        } else {
            result += "Spróbuj następnym razem";
        }
        return result;
    }

}
