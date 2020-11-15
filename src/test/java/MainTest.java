import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Timeout(5)
public class MainTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void shouldWorkForDescriptionExample() throws IOException {
        // given
        writeToFile(Arrays.asList("9", "2", "5", "2", "9", "6"), "liczby.txt");

        // when
        Main.main(new String[]{});

        // then
        assertThat(outContent.toString()).containsSubsequence("2 -", "5 -", "6 -", "9 -");
        assertThat(outContent.toString()).contains("2 - liczba wystąpień 2");
    }

    @Test
    void shouldOrderCorrectly() throws Exception {
        // given
        copyFileFromResources("example1.txt", "liczby.txt");

        // when
        Main.main(new String[]{});

        // then
        assertThat(outContent.toString()).containsSubsequence("1 -", "2 -", "3 -");
        assertThat(outContent.toString()).contains("1 - liczba wystąpień 1");
        assertThat(outContent.toString()).contains("2 - liczba wystąpień 1");
        assertThat(outContent.toString()).contains("3 - liczba wystąpień 1");
    }

    @Test
    void shouldWorkForBiggerNumbers() throws Exception {
        // given
        copyFileFromResources("example2.txt", "liczby.txt");

        // when
        Main.main(new String[]{});

        // then
        assertThat(outContent.toString()).containsSubsequence("512 -", "897 -", "12067 -");
        assertThat(outContent.toString()).contains("512 - liczba wystąpień 1");
        assertThat(outContent.toString()).contains("897 - liczba wystąpień 2");
        assertThat(outContent.toString()).contains("12067 - liczba wystąpień 1");
    }

    private void copyFileFromResources(String name, String output) throws Exception {
        URL resource = getClass().getResource(name);
        Files.copy(Path.of(resource.toURI()), new File(output).toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private void writeToFile(List<String> lines, String filename) throws IOException {
        File file = new File(filename);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(String.join("\n", lines));
        fileWriter.close();
    }

    @BeforeEach
    void init() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
    }

}
