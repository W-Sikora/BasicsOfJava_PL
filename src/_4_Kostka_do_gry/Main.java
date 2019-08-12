package src._4_Kostka_do_gry;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String dice = askUser();
        String type = typeOfDice(dice);
        int numberOfThrows = howManyThrows(dice);
        int bonusSign = bonus(dice);
        int bonusValue = bonusValue(dice, bonusSign);
        System.out.println(" - liczba rzutów: " + numberOfThrows +
                "\n - typ kostki: " + type +
                "\n - wartość bonusa: " + bonusValue +
                "\n------------------------------------------------------" +
                "\nWynik to: " + simulationOfThrows(type, numberOfThrows, bonusValue));
    }

    // pobiera od użytkownika parametry kostki
    static String askUser() {
        Scanner scanner = new Scanner(System.in);
        String result = "";
        boolean end = true;
        while (end) {
            System.out.print("\nProszę podać parametry kostki wg wzoru:\n" +
                    "------------------------------------------------------\n" +
                    "                       xDy+z\n" +
                    "------------------------------------------------------\n" +
                    " y – rodzaj kostek, których należy użyć (np. D6, D10),\n" +
                    " x – liczba rzutów kośćmi (jeśli rzucamy raz, ten parametr jest pomijalny),\n" +
                    " z – (opcjonalnie) liczba, którą należy dodać (lub odjąć) do wyniku rzutów).\n" +
                    " typy kostek: D3, D4, D6, D8, D10, D12, D20, D100.\n" +
                    "------------------------------------------------------\n" +
                    "Twoje parametry: ");
            String dice = scanner.nextLine();
            if (validateDice(dice)) {
                result = dice;
                end = false;
            } else {
                System.out.println("=======================================\n" +
                        " Parametry kostki nie zgodne z wzorem!" +
                        "\n=======================================");
            }
        }
        return result;
    }

    // sprawdza czy kostka spełnia wymagane parametry
    static boolean validateDice(String dice) {
        String regex = "\\d*(D3|D4|D6|D8|D10|D12|D20|D100)(\\+|\\-)*\\d*";
        return dice.matches(regex);
    }

    // zwraca pozycję na której występuje podany symbol
    static int findChar(String text, char sign) {
        return text.indexOf(sign);
    }

    // zwraca jakiego typu jest bonus
    static int bonus(String dice) {
        int result;
        if (findChar(dice, '+') != -1) {
            result = 1;
        } else if (findChar(dice, '-') != -1) {
            result = -1;
        } else {
            result = 0;
        }
        return result;
    }

    // zwraca wartość bonusa
    static int bonusValue(String dice, int bonusSign) {
        int result;
        if (bonusSign == 1) {
            String[] diceArray = dice.split("\\+");
            result = Integer.parseInt(diceArray[1]);
        } else if (bonusSign == -1) {
            String[] diceArray = dice.split("-");
            result = -Integer.parseInt(diceArray[1]);
        } else {
            result = 0;
        }
        return result;
    }

    // zwraca ile razy trzeba rzucić kostką
    static int howManyThrows(String dice) {
        int result;
        int indexDSign = findChar(dice, 'D');
        if (indexDSign == 0) {
            result = 1;
        } else {
            String[] diceArray = dice.split("D");
            result = Integer.parseInt(diceArray[0]);
        }
        return result;
    }

    // zwraca typ kostki
    static String typeOfDice(String dice) {
        String diceType = "";
        char[] diceArray = dice.toCharArray();
        int indexDSign = findChar(dice, 'D');
        int bonusSign = bonus(dice);
        int indexBonusSign;
        if (bonusSign == 1) {
            indexBonusSign = findChar(dice, '+');
        } else if (bonusSign == -1) {
            indexBonusSign = findChar(dice, '-');
        } else {
            indexBonusSign = diceArray.length;
        }
        for (int i = indexDSign; i < indexBonusSign; i++) {
            diceType += diceArray[i];
        }
        return diceType;
    }

    // zwraca losową liczbę z zakresu od from do to
    static int randomNumber(int from, int to) {
        Random random = new Random();
        boolean end = true;
        int number = 0;
        while (end) {
            number = random.nextInt(to + 1);
            if (number >= from) {
                end = false;
            }
        }
        return number;
    }

    // zwraca wynik symulacji rzutów określonym typem kostki określoną ilość razy
    static int drawLots (String diceType, int numberOfDraws) {
        int result = 0;
        int from = 1;
        String[] diceArray = diceType.split("D");
        int to = Integer.parseInt(diceArray[1]);
        for (int i = 0; i < numberOfDraws ; i++) {
            result += randomNumber(from, to);
        }
        return result;
    }

    // zwraca ostateczny wynik
    static int simulationOfThrows(String diceType, int numberOfThrows, int bonusValue) {
        return drawLots(diceType, numberOfThrows) + bonusValue;
    }

}
