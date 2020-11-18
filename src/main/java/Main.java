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

            Response response = objectMapper.readValue(Paths.get("tickets.json").toFile(), Response.class);

            System.out.println(response.tickets.length);

            for (Ticket cat : response.tickets) {
                System.out.printf("%s %n", cat.getArrival_time());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
