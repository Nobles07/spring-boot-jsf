package com.vintrace.util;

import com.vintrace.model.GrapeComponent;
import com.vintrace.model.Wine;

import java.util.*;

public class CompositionUtil {

	public static String printYearBreakdown(Wine w) {
		Map<Integer, Double> components = new HashMap<Integer, Double>();
		String yearBreakdownComposition = "";
		for (GrapeComponent component : w.getComponents()) {
			components.put(component.getYear(), getPercentageValueByYear(components, component));
		}
		Map<Integer, Double> sortedMap = sortYearByValue(components);

		for (Map.Entry<Integer, Double> entry : sortedMap.entrySet()) {
			yearBreakdownComposition = yearBreakdownComposition + "\n" + entry.getValue() + "% - " + entry.getKey();
		}
		return yearBreakdownComposition;
	}
	public static String printVarietyBreakdown(Wine w) {
		Map<String, Double> components = new HashMap<String, Double>();
		String varietyBreakdownComposition = "";
		for (GrapeComponent component : w.getComponents()) {
			components.put(component.getVariety(), getPercentageValueByVariety(components, component));
		}
		Map<String, Double> sortedMap = sortByValue(components);

		for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
			varietyBreakdownComposition = varietyBreakdownComposition + "\n" + entry.getValue() + "% - " + entry.getKey();
		}
		return varietyBreakdownComposition;

	}
	public static String printRegionBreakdown(Wine w) {
		Map<String, Double> components = new HashMap<String, Double>();
		String regionBreakdownComposition = "";
		for (GrapeComponent component : w.getComponents()) {
			components.put(component.getRegion(), getPercentageValueByRegion(components, component));
		}

		Map<String, Double> sortedMap = sortByValue(components);

		for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
			regionBreakdownComposition = regionBreakdownComposition + "\n" + entry.getValue() + "% - " + entry.getKey();
		}
		return regionBreakdownComposition;
	}


	public static String printYearAndVarietyBreakdown(Wine w) {
		Map<String, Double> components = new HashMap<String, Double>();
		String yearAndVarietyBreakdownComposition="";
		for (GrapeComponent component : w.getComponents()) {
			components.put(component.getYear() + "-" + component.getVariety(), getPercentageValueByYearAndVariety(components, component));
		}

		Map<String, Double> sortedMap = sortByValue(components);

		for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
			yearAndVarietyBreakdownComposition = yearAndVarietyBreakdownComposition + "\n" + entry.getValue() + "% - " + entry.getKey();
		}
		return yearAndVarietyBreakdownComposition;
	}
	private static HashMap<Integer, Double> sortYearByValue(Map<Integer, Double> hm) {

		List<Map.Entry<Integer, Double>> list =
				new LinkedList<Map.Entry<Integer, Double>>(hm.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
			public int compare(Map.Entry<Integer, Double> o1,
							   Map.Entry<Integer, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		HashMap<Integer, Double> sortedMap = new LinkedHashMap<Integer, Double>();
		for (Map.Entry<Integer, Double> aa : list) {
			sortedMap.put(aa.getKey(), aa.getValue());
		}
		return sortedMap;
	}
	private static HashMap<String, Double> sortByValue(Map<String, Double> hm) {

		List<Map.Entry<String, Double>> list =
				new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
							   Map.Entry<String, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		HashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Double> aa : list) {
			sortedMap.put(aa.getKey(), aa.getValue());
		}
		return sortedMap;
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
