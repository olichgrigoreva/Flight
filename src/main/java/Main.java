import com.fasterxml.jackson.databind.ObjectMapper;
import objectJSON.Response;
import objectJSON.Ticket;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Response response = objectMapper.readValue(Paths.get("tickets.json").toFile(), Response.class);

            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            Date departure_time = null;
            Date arrival_time = null;

            List<Long> timeDiffList = new ArrayList<>();

            for (Ticket ticket : response.tickets) {
                departure_time = format.parse(ticket.getDeparture_time());
                arrival_time = format.parse(ticket.getArrival_time());
                //in milliseconds
                long diff = arrival_time.getTime() - departure_time.getTime();
                timeDiffList.add(diff);
            }

            System.out.println("Среднее время полета между городами Владивосток и Тель-Авив:");
            System.out.println(Statistics.timeConverter(Statistics.averageTime(timeDiffList)));


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
