import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    public static long averageTime(List<Long> list) {

        long sum = 0;
        for (Long item : list) {
            sum += item;
        }
        long averageTimeMs = sum / list.size();
        return averageTimeMs;
    }

    public static void percentile(List<Long> list) {

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
