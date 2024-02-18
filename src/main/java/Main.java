import java.io.FileNotFoundException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            Map<Integer, Integer> number = FileUtils.fillMap("liczby.txt");
            FileUtils.printInfo(number);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        }
    }
}
