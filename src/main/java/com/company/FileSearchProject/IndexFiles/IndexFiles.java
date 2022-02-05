package com.company.FileSearchProject.IndexFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IndexFiles {
	
	/**
	 * Returns map to store with the indexed file name as the key
	 * and the indexed file content as the value
	 * @param directory
	 * @param indexableDirectory
	 * @return indexedFileMap
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Map<String,String> indexTheFilesFound(String directory, final File indexableDirectory) throws FileNotFoundException, IOException {
		Map<String, String> indexedFileMap = new HashMap<String,String>();
		for (String fileName : indexableDirectory.list()) {
			FileInputStream fI = new FileInputStream(directory+fileName);
			int i = 0;
			String fileContent = "";
			while ((i = fI.read()) != -1) {
				fileContent = fileContent + String.valueOf((char) i);
			}
			indexedFileMap.put(fileName,fileContent);
			fI.close();
		}
		return indexedFileMap;
	}

}
