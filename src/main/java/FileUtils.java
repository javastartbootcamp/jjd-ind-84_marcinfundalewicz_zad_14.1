import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FileUtils {
    public static Map<Integer, Integer> fillMap(String fileName) throws FileNotFoundException {
        Map<Integer, Integer> numbers = new TreeMap<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (numbers.containsKey(number)) {
                    numbers.put(number, numbers.get(number) + 1);
                } else {
                    numbers.put(number, 1);
                }
            }
        }
        return numbers;
    }

    public static void printInfo(Map<Integer, Integer> numbers) {
        for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
            System.out.println(entry.getKey() + " - liczba wystąpień " + entry.getValue());
        }
    }
}
