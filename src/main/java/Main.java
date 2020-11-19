import com.fasterxml.jackson.databind.ObjectMapper;
import objectJSON.Response;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Response response = objectMapper.readValue(Paths.get("tickets.json").toFile(), Response.class);

            System.out.println("Среднее время полета между городами Владивосток и Тель-Авив:");
            Statistics.timeConverter(
                    Statistics.averageTime(response, "Владивосток", "Тель-Авив"));

            System.out.println("90-й процентиль времени полета между городами Владивосток и Тель-Авив:");
            Statistics.timeConverter(
                    Statistics.percentile(response, 90, "Владивосток", "Тель-Авив"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
