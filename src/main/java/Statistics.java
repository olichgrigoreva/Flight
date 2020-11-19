import objectJSON.Response;
import objectJSON.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Statistics {
    public static long averageTime(Response response, String departureCity, String arrivalCity) {
        try {
            List<Long> timeDiffList = listOfTimeDifference(response, departureCity, arrivalCity);

            long sum = 0;
            for (Long item : timeDiffList) {
                sum += item;
            }
            long averageTimeMs = sum / timeDiffList.size();
            System.out.println(timeConverter(averageTimeMs));
            return averageTimeMs;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Long> listOfTimeDifference(Response response, String departureCity, String arrivalCity) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Date departure_time = null;
        Date arrival_time = null;

        List<Long> timeDiffList = new ArrayList<>();

        for (Ticket ticket : response.tickets) {
            if (ticket.getOrigin_name().equals(departureCity) &
                    ticket.getDestination_name().equals(arrivalCity)) {
                departure_time = format.parse(ticket.getDeparture_time());
                arrival_time = format.parse(ticket.getArrival_time());
                //in milliseconds
                long diff = arrival_time.getTime() - departure_time.getTime();
                timeDiffList.add(diff);
            }
        }
        return timeDiffList;
    }

    public static long percentile(Response response, int percentile, String departureCity, String arrivalCity) {
        try {
            List<Long> timeDiffList = listOfTimeDifference(response, departureCity, arrivalCity);
            Collections.sort(timeDiffList);
            int index = (int) Math.ceil((double) percentile / 100 * timeDiffList.size()) - 1;
            return timeDiffList.get(index);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Map<String, Long> timeConverter(long averageTimeMs) {
        long averageTimeSeconds = averageTimeMs / 1000 % 60;
        long averageTimeMinutes = averageTimeMs / (60 * 1000) % 60;
        long averageTimeHours = averageTimeMs / (60 * 60 * 1000) % 24;
        long averageTimeDays = averageTimeMs / (24 * 60 * 60 * 1000);

        Map<String, Long> map = new LinkedHashMap<>();
        map.put("days", averageTimeDays);
        map.put("hours", averageTimeHours);
        map.put("minutes", averageTimeMinutes);
        map.put("seconds", averageTimeSeconds);
        return map;
    }
}
