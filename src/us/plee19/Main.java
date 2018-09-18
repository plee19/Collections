package us.plee19;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * Main class to demonstrate Collections and Maps.
 * @author plee19
 * @version 1
 */
public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) {
        String line;
        String[] words;
        Object wordFound;
        //   String[] fields;


        //Remove punctuation from words for entry into a Map
        while ((line = indata.fileReadLine()) != null) {
            line = line.replace(",", "").replace(".", "")
                    .replace(";", "").replace(":", "")
                    .replace("'", "").replace("\"", "")
                    .replace("-", "").replace("!", "")
                    .replace("#", "").replace("(", "")
                    .replace(")", "").replace("?", "")
                    .replace("_", " ").replace("?", "")
                    .toLowerCase().trim();
            words = line.split(" ");
            for (String s : words) {
                wordFound = map.get(s);
                if (wordFound == null) {
                    map.put(s, new Integer(1));
                } else {
                    map.put(s, map.get(s) + 1);
                }

            }

        }
        System.out.println("Words used only once:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            // Print the words used only once
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey());
            }

        }

        Map<String, Integer> sorted = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        System.out.println("\nTop 20 words: ");
        int i = 0;
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            // Counts first 20, ignoring the empty space with value of 3241
            if (i < 20 && entry.getValue() != 3241) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                i++;
            }
        }

    }
}