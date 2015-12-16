package io.kry.adventofenterprise.days.twelve;

import com.google.gson.*;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTwelve implements Day {

    public static final int ID = 12;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("12.input"))) {
            String input = scanner.nextLine();
            Pattern pattern = Pattern.compile("-?\\d+");
            Matcher matcher = pattern.matcher(input);
            List<Integer> nums = new ArrayList<>();
            while (matcher.find()) nums.add(Integer.parseInt(matcher.group()));
            //List<Integer> numsP2 = new ArrayList<>();
            //input = input.replaceAll("\\{.*:\"red\"(.*?)\\}", "");
            //input = input.replaceAll("\\{[a-z0-9:\"\\[\\]\\s,]*:\"red\"[a-z0-9:\"\\[\\]\\s,]*\\}", "");
            //matcher = pattern.matcher(input);
            //while (matcher.find()) numsP2.add(Integer.parseInt(matcher.group()));
            JsonArray arr = new Gson().fromJson(input, JsonArray.class);

            return new TaskAnswer(nums.stream().mapToInt(Integer::intValue).sum(), getNumbers(arr));
        }
    }

    public int getNumbers(JsonElement jsonElement) {

        int sum = 0;
        if (jsonElement.isJsonArray()) {
            sum = getNumbers(jsonElement.getAsJsonArray());
        } else if (jsonElement.isJsonPrimitive()) {
            try {
                sum = jsonElement.getAsInt();
            } catch (Exception ex) {
                return sum;
            }
        } else {
            JsonObject object = jsonElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                JsonElement element = entry.getValue();
                if (element.isJsonPrimitive()) {
                    if (element.getAsString().contains("red")) {
                        return 0;
                    }
                }
            }
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                JsonElement element = entry.getValue();
                sum += getNumbers(element);
            }
        }
        return sum;
    }

    public int getNumbers(JsonArray jsonArray) {
        int sum = 0;
        for (JsonElement jsonElement : jsonArray) {
            sum += getNumbers(jsonElement);
        }
        return sum;
    }
}

