/*
package frc.robot.util;

import java.util.Map;
import java.util.TreeMap;

public class LookupTable {

    private TreeMap<Double, Integer> values = new TreeMap<>();

    public double getValue(Double lookupKey) {
        Map.Entry<Double, Integer> higherEntry = values.higherEntry(lookupKey);
        Map.Entry<Double, Integer> lowerEntry = values.lowerEntry(lookupKey);
        if (higherEntry == null) {
            return (double) values.lastEntry().getValue();
        } else if (lowerEntry == null) {
            return (double) values.firstEntry().getValue();
        }
        double distance = (lookupKey - lowerEntry.getKey()) / (higherEntry.getKey() - lowerEntry.getKey());
        return ((higherEntry.getValue() - lowerEntry.getValue()) * distance) + lowerEntry.getValue();
    }

    public void addValue(double tx, int speed) {
        this.values.put(tx, speed);
    }

    
} */