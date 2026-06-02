import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReading {
    public static void main(String[] args) {

        try {
            Files.lines(Paths.get("output.txt"))
                 .forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}