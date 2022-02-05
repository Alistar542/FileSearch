package com.company.FileSearchProject.SearchFiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alistar
 *
 */
public class SearchFiles {
	
	private static final String regexForSplitingIntoWords = "\\r?\\n|\\r|\\s+";

	/**
	 * @param indexedFileMap
	 * @param line
	 * 
	 */
	public Map<String,Double> searchForTheTerm(Map<String, String> indexedFileMap, final String line) {
		List<String> searchStringList = Arrays.asList(line.toLowerCase().split(regexForSplitingIntoWords));
		Set<String> uniqueSearchStringSet = new HashSet<String>(searchStringList);
		int totalSearchStringSize = searchStringList.size();
		
		/*
		 * Calculate the percentage division by dividing 100 
		 * with totalSearchStringSize+1. 1 is added to compensate
		 * the search percentage if the whole term is found in the 
		 * file content.
		 */
		double percentDivision = 100.0/(totalSearchStringSize+1);
		Map<String, Double> filePercentMap = new HashMap<String,Double>();
		for (Entry<String, String> indexedFileEntry : indexedFileMap.entrySet()) {
			double totalPercent = 0;
			int finalMatchingCount = 0;
			String fileContent = indexedFileEntry.getValue();
			for(String uniqueSearchWord : uniqueSearchStringSet) {
				/*
				 * Calculate the frequency of the word to be 
				 * searched in the search term provided by the user
				 */
				int searchWordFrequency = Collections.frequency(searchStringList, uniqueSearchWord);
				int matcherCount = findNumberOfPatternMatches(fileContent, uniqueSearchWord);
			    if(matcherCount >= searchWordFrequency ) {
			    	/*
				     * If there are same or more number of words 
				     * found in the file content
				     */
			    	totalPercent += percentDivision*searchWordFrequency;
			    	finalMatchingCount += searchWordFrequency; 
			    }else {
			    	/*
			    	 * If there are less number of words than the 
			    	 * words present in the search term
			    	 */
			    	totalPercent += percentDivision*matcherCount;
			    	finalMatchingCount += matcherCount;
			    }
					
			}
			totalPercent = calculatePercentWhenTheWholeTermIsFound(line, totalSearchStringSize, totalPercent,
					finalMatchingCount, fileContent);
			filePercentMap.put(indexedFileEntry.getKey(), totalPercent);
		}
		return filePercentMap;
	}
	

	/**
	 * Method to match and find the number of occurrences of 
	 * the search term in the pattern
	 * @param fileContent
	 * @param patternString
	 * @return matcherCount
	 */
	private static int findNumberOfPatternMatches(String fileContent, String patternString) {
		Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(fileContent);
		int matcherCount=0;
		while(matcher.find()) {
			matcherCount++;
		}
		return matcherCount;
	}

	/**
	 * If the same number of words of search term are present in the file content then search for the term
	 * as a whole and return the final search percentage
	 * @param line
	 * @param totalSearchStringSize
	 * @param totalPercent
	 * @param finalMatchingCount
	 * @param fileContent
	 * @return percentage
	 */
	private static double calculatePercentWhenTheWholeTermIsFound(final String line, int totalSearchStringSize,
			double totalPercent, int finalMatchingCount, String fileContent) {
		if(finalMatchingCount == totalSearchStringSize) {
		    if(findNumberOfPatternMatches(fileContent, line) > 0) {
		    	/*
		    	 * If the whole term is found then the totalPercent
		    	 * is set to 100
		    	 */
		    	totalPercent = 100;
		    }
		}
		return totalPercent;
	}
}
