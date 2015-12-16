package io.kry.adventofenterprise.days.three;

import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.days.three.object.Coordinate;
import io.kry.adventofenterprise.days.three.object.GPS;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class DayThree implements Day {

    public static final int ID = 3;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("3.input")) {
            String data = new Scanner(is).nextLine();
            List<Coordinate> visitedLocations = new ArrayList<>(Arrays.asList(new Coordinate[] {new Coordinate(0, 0)}));
            List<Coordinate> visitedLocations2 = new ArrayList<>(Arrays.asList(new Coordinate[] {new Coordinate(0, 0)}));
            //Task One
            GPS[] gps = {new GPS(), new GPS(), new GPS()};
            int i = 0;
            for (char c : data.toCharArray()) {
                if (i % 2 == 0) {
                    gps[1].processInstruction(c);
                } else {
                    gps[2].processInstruction(c);
                }
                gps[0].processInstruction(c);
                if (!visitedLocations.contains(gps[0].getCoordinate())) visitedLocations.add(gps[0].getCoordinate().clone());
                if (!visitedLocations2.contains(gps[1].getCoordinate())) visitedLocations2.add(gps[1].getCoordinate().clone());
                if (!visitedLocations2.contains(gps[2].getCoordinate())) visitedLocations2.add(gps[2].getCoordinate().clone());
                i++;
            }
            return new TaskAnswer(visitedLocations.size(), visitedLocations2.size());
        } catch (IOException e) {
            throw new TaskException("Could not load file for Day Three");
        } catch (CloneNotSupportedException e) {
            throw new TaskException("Could not clone a thing");
        }
    }
}
