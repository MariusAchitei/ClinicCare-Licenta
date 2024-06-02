package ro.uaic.clinic_care.utils;

import java.util.Map;
import java.util.stream.Collectors;

public class ConvertUtils {

    public static String convertMapToString(Map<String, String> map) {
        return map.entrySet().stream()
            .map(entry -> entry.getKey() + "=" + entry.getValue())
            .collect(Collectors.joining("&"));
    }
}
