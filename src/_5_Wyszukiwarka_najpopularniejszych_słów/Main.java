package src._5_Wyszukiwarka_najpopularniejszych_słów;

import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        getWords("http://www.onet.pl/");
    }

    public static void getWords(String link) {
        Connection connection = Jsoup.connect(link);
        List<String> listOfWords = new ArrayList<>();
        Path path = Paths.get("popular_words.txt");
        try {
            Document document = connection.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                String[] tmpList = elem.text()
                        .replaceAll("\\.", "").replaceAll("'", "")
                        .replaceAll("!", "").replaceAll(",", "")
                        .replaceAll(":", "").replaceAll("\\?", "")
                        .replaceAll("\"", "").replaceAll("„", "")
                        .toLowerCase().split(" ");
                for (String str : tmpList) {
                    if (str.length() > 2) {
                        listOfWords.add(str);
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        try {
            Files.write(path, listOfWords);
        } catch (IOException e) {
            e.getMessage();
        }
        filterWords();
    }

    public static void filterWords() {
        // plik z zestawem pobranych słów
        Path pathLoad = Paths.get("popular_words.txt");
        // plik z przefiltrowanymi słowami
        Path pathSave = Paths.get("filtered_popular_words.txt");
        // lista wyjątków
        String[] filter = new String[]{"a także", "a w szczególności", "a zwłaszcza", "abo", "abowiem", "aby",
                "aczkolwiek", "albo", "albowiem", "ale", "ale również", "ale też", "ani", "ażeby", "bądź", "bowiem",
                "byle", "chociaż", "chyba że", "co gorsza", "co gorsze", "czy", "czyli", "dlatego", "dopóki", "dopóty",
                "dotąd", "dyby", "gdy", "gdyby", "gdyż", "i tak", "i lub", "ilekroć", "innymi słowy", "jak", "jakby",
                "jakkolwiek", "jako", "jako że", "jakoby", "jednak", "jednakowoż", "jednakże", "jesi", "jeśli",
                "jeżeli", "kiedy", "lebo", "lub", "mianowicie", "mimo to", "mimo że", "natomiast", "niby", "niczym",
                "niemniej jednak", "niż", "o ile", "oprócz tego", "oraz", "po czym", "podczas gdy", "póki", "ponieważ",
                "poza tym", "prawda", "razy", "równak", "skoro", "t.j.", "temu", "tj.", "to", "toteż", "tudzież",
                "tylko", "w sensie", "więc", "wprawdzie", "wszelako", "z tym że", "zaledwie", "zamiast", "zanim",
                "zarówno", "zatem", "żeby"};

        List<String> filteredWords = new ArrayList<>();
        try {
            for (String str : Files.readAllLines(pathLoad)) {
                filteredWords.add(str);
                for (String filterStr : filter) {
                    if (filterStr.equals(str)) {
                        filteredWords.remove(filteredWords.size() - 1);
                        break;
                    }
                }
            }
            Files.write(pathSave, filteredWords);
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
