import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<Ticket> books = Arrays.asList(objectMapper.readValue(Paths.get("tickets.json").toFile(), Ticket[].class));

            // print books
            books.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
