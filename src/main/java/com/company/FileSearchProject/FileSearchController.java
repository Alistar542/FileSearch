package com.company.FileSearchProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.company.FileSearchProject.DisplaySearchResults.DisplaySearchResults;
import com.company.FileSearchProject.IndexFiles.IndexFiles;
import com.company.FileSearchProject.SearchFiles.SearchFiles;

/**
 * @author Alistar
 *
 */
public class FileSearchController {
	
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void fileSearch(String args[]) throws FileNotFoundException, IOException {
		String directory = "";
		if (args.length == 0) {
			throw new IllegalArgumentException("No directory given to index."); 
		}else {
			directory = args[0];
		}
		directory = directory+"\\";
		
		final File indexableDirectory = new File(directory);
		int numberOfFiles = indexableDirectory.list().length;
		System.out.println(numberOfFiles+" files read in the directory "+directory);
		Map<String, String> indexedFileMap = new IndexFiles().indexTheFilesFound(directory
				,indexableDirectory);

		while (true) {
			final String line = getInputFromUser();
			Map<String, Double> filePercentMap = new SearchFiles()
					.searchForTheTerm(indexedFileMap, line);
			new DisplaySearchResults().printTheFilePercentMap(filePercentMap);
			// Uncomment the break statement when running the test cases.
			//break;
		}

	}

	/**
	 * @return
	 * @throws IOException
	 */
	public String getInputFromUser() throws IOException {
		System.out.print("search> ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

	
}
