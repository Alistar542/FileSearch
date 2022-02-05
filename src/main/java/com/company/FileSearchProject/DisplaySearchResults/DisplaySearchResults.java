package com.company.FileSearchProject.DisplaySearchResults;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author Alistar
 *
 */
public class DisplaySearchResults {
	
	int printLimit = 10;

	/**
	 * @param filePercentMap
	 * Print the fileName with the percentage of the search result
	 */
	public void printTheFilePercentMap(Map<String, Double> filePercentMap) {
		Map<String,Double> sortedFilePercentMap =  sortFilePercentMap(filePercentMap);
		int limit = 0;
		for (Entry<String, Double> filePercentEntry : 
					sortedFilePercentMap.entrySet()) {
			if (limit >= printLimit) {
				break;
			}
			System.out.println(filePercentEntry.getKey() + " : "
					+ filePercentEntry.getValue() + "%");
			limit++;
		}
	}
	
	/**
	 * @param filePercentMap
	 * @return sortedFilePercentMap
	 */
	private static HashMap<String, Double> 
			sortFilePercentMap(Map<String, Double> filePercentMap) {
		return filePercentMap.entrySet()
				.stream()
				.sorted((i2, i1) -> i1.getValue().compareTo(i2.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}
