package com.vintrace;

import com.vintrace.model.GrapeComponent;
import com.vintrace.model.Wine;
import com.google.gson.Gson;

import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class WineTest {

    public static void main(String[] args) {

        Gson gson = new Gson();

        try {
			Scanner scanner = new Scanner(System.in);  // Create a Scanner object
			System.out.println("Enter Json filename:(Eg: test.json):");

			String jsonFileName = scanner.nextLine();
			String filePath="src/main/resources/json/"+jsonFileName;
            Wine w = gson.fromJson(new FileReader(filePath), Wine.class);

            /*Wine w = new Wine("11YVCHAR001", 1000);
            w.setDescription("2011 Yarra Valley Chardonnay");
            w.setTankCode("T25-01");
            w.setProductState("Ready for bottling");
            w.setOwnerName("YV Wines Pty Ltd");

            w.getComponents().add(new GrapeComponent(80D, 2011, "Chardonnay", "Yarra Valley"));
            w.getComponents().add(new GrapeComponent(10D, 2010, "Chardonnay", "Macedon"));
            w.getComponents().add(new GrapeComponent(5D, 2011, "Pinot Noir", "Mornington"));
            w.getComponents().add(new GrapeComponent(5D, 2010, "Pinot Noir", "Macedon"));
            */
            System.out.println("*********  Printing Year Breakdown  *********");
            printYearBreakdown(w);
            System.out.println("*********  Printing Variety Breakdown  *********");
            printVarietyBreakdown(w);
            System.out.println("*********  Printing Region Breakdown  *********");
            printRegionBreakdown(w);
            System.out.println("*********  Printing Year and Variety Breakdown  *********");
            printYearAndVarietyBreakdown(w);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static void printVarietyBreakdown(Wine w) {
        Map<String, Double> components = new HashMap<String, Double>();

        for (GrapeComponent component : w.getComponents()) {
            components.put(component.getVariety(), getPercentageValueByVariety(components, component));
        }

        Stream<Map.Entry<String, Double>> componentsSorted =
                components.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));


        componentsSorted.forEach((k) -> System.out.println(k.getValue() + "% - " + k.getKey()));
    }

    private static void printYearBreakdown(Wine w) {
        Map<Integer, Double> components = new HashMap<Integer, Double>();

        for (GrapeComponent component : w.getComponents()) {
            components.put(component.getYear(), getPercentageValueByYear(components, component));
        }

        Stream<Map.Entry<Integer, Double>> componentsSorted =
                components.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));


        componentsSorted.forEach((k) -> System.out.println(k.getValue() + "% - " + k.getKey()));
    }


    private static void printRegionBreakdown(Wine w) {
        Map<String, Double> components = new HashMap<String, Double>();

        for (GrapeComponent component : w.getComponents()) {
            components.put(component.getRegion(), getPercentageValueByRegion(components, component));
        }

        Stream<Map.Entry<String, Double>> componentsSorted =
                components.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));


        componentsSorted.forEach((k) -> System.out.println(k.getValue() + "% - " + k.getKey()));
    }


    private static void printYearAndVarietyBreakdown(Wine w) {
        Map<String, Double> components = new HashMap<String, Double>();

        for (GrapeComponent component : w.getComponents()) {
            components.put(component.getYear() + "-" + component.getVariety(), getPercentageValueByYearAndVariety(components, component));
        }

        Stream<Map.Entry<String, Double>> componentsSorted =
                components.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));


        componentsSorted.forEach((k) -> System.out.println(k.getValue() + "% - " + k.getKey()));
    }

    private static Double getPercentageValueByYear(Map<Integer, Double> components, GrapeComponent component) {
        if (null != components.get(component.getYear()))
            return components.get(component.getYear()).doubleValue() + component.getPercentage();
        else {
            return component.getPercentage();
        }
    }

    private static Double getPercentageValueByVariety(Map<String, Double> components, GrapeComponent component) {
        if (null != components.get(component.getVariety()))
            return components.get(component.getVariety()).doubleValue() + component.getPercentage();
        else {
            return component.getPercentage();
        }
    }

    private static Double getPercentageValueByRegion(Map<String, Double> components, GrapeComponent component) {
        if (null != components.get(component.getRegion()))
            return components.get(component.getRegion()).doubleValue() + component.getPercentage();
        else {
            return component.getPercentage();
        }
    }

    private static Double getPercentageValueByYearAndVariety(Map<String, Double> components, GrapeComponent component) {
        if (null != components.get(component.getYear() + "-" + component.getVariety()))
            return components.get(component.getYear() + "-" + component.getVariety()).doubleValue() + component.getPercentage();
        else {
            return component.getPercentage();
        }
    }

}
