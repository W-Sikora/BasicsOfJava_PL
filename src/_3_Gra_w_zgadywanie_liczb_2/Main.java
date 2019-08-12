package src._3_Gra_w_zgadywanie_liczb_2;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int min = 0;
        int max = 1000;
        askUser("Pomyśl liczbę od", min, max);
        System.out.println("Zgadnę jaka to liczba maksymalnie w 10 ruchach.");
        int guess = guessNumber(min, max);
        int counter = 1;
        while (true) {
            System.out.println("\nPróba " + counter + ". Zgaduję: " + guess);
            System.out.println("----------------------------------------------\n" +
                    "\tCzy zgadłem?\n" +
                    "----------------------------------------------");
            System.out.println(menu());
            int response = askUser("Proszę wybrać opcję:", 0, 1);
            if (response == 1) {
                System.out.println("----------------------------------------------\n" +
                        "\tWygrałem, twoja liczba to: " + guess + "\n" +
                        "----------------------------------------------");
                break;
            } else {
                System.out.println("----------------------------------------------\n" +
                        "\tCzy liczba jest za duża?\n" +
                        "----------------------------------------------");
                System.out.println(menu());
                response = askUser("Proszę wybrać opcję:", 0, 1);
                if (response == 1) {
                    max = guess;
                } else {
                    System.out.println("----------------------------------------------\n" +
                            "\tCzy liczba jest za mała?\n" +
                            "----------------------------------------------");
                    System.out.println(menu());
                    response = askUser("Proszę wybrać opcję:", 0, 1);
                    if (response == 1) {
                        min = guess;
                    } else {
                        System.out.println("----------------------------------------------\n" +
                                "\tNie oszukuj!\n" +
                                "----------------------------------------------");
                    }
                }
            }
            guess = guessNumber(min, max);
            counter++;
        }
    }

    // pobiera od użytkownika liczbę całkowitą z przedziału od min do max
    static int askUser(String text, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(text + " " + min + " lub " + max + ": ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number >= min && number <= max) {
                    return number;
                }
            } else {
                scanner.nextLine();
            }
        }
    }

    // opcje do odpowiedzi
    static String menu() {
        return "Proszę wybrać odpowiedź:\n" +
                "  0 - nie;\n" +
                "  1 - tak;";
    }

    static int guessNumber(int min, int max) {
        return ((max - min) / 2) + min;
    }

}
