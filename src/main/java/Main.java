import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Response response = objectMapper.readValue(Paths.get("tickets.json").toFile(), Response.class);

            /*System.out.println(response.tickets.length);

            for (Ticket ticket : response.tickets) {
                System.out.printf("%s %n", ticket.getArrival_time());
            }*/

            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            Date departure_time = null;
            Date arrival_time = null;

            for (Ticket ticket : response.tickets){
                departure_time = format.parse(ticket.getDeparture_time());
                arrival_time = format.parse(ticket.getArrival_time());
                //in milliseconds
                long diff = arrival_time.getTime() - departure_time.getTime();
                long diffHours = diff/(60 *  60 *  1000) % 24;
                System.out.println(diffHours);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
