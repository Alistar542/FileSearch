package com.company.FileSearchProject;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

/**
 * @author Alistar
 * When running the test cases uncomment the break statement in the file
 * FileSearchController.java in the line no. 46
 *
 */
class FileSearchControllerTest {

	String[] args = new String[10];
	
	@Spy
	FileSearchController controller;
	
	
	@BeforeEach
	public void setup() {
		controller = Mockito.spy(FileSearchController.class);
		args[0] = "C:\\Users\\Alistar\\eclipse-workspace\\FileSearchProject\\src\\main\\resources";
	}
	
	/**
	 * Test case to determining the rank for files based on the individual terms
	 * found on searching, but only individualy and not as a whole.
	 * For Example : "risus" and "maximus" are found in a file seperately, 
	 * but the term "risus maximus" as a whole is not found. Thus a lower rank 
	 * is assigned to the file than a file where the whole term is found.
	 */
	@Test
	public void determineRankWhenIndividualTermsAreFound() throws Exception {
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		doReturn("risus maximus").when(controller).getInputFromUser();
		controller.fileSearch(args);
		assertTrue(Arrays.asList(outputStreamCaptor.toString()
			      .trim().split("\\r?\\n|\\r|\\s+|:")).contains("66.66666666666667%"));
		System.setOut(standardOut);
	}
	
	/**
	 * When individual terms are found separately, attempt is made to search the
	 * term as whole.
	 */
	@Test
	public void attemptToFindWholeTermWhenIndividualTermsAreFound() 
			throws IOException {
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		doReturn("risus maximus magna").when(controller).getInputFromUser();
		controller.fileSearch(args);
		assertTrue(!Arrays.asList(outputStreamCaptor.toString()
			      .trim().split("\\r?\\n|\\r|\\s+|:")).contains("100.0%"));
		System.setOut(standardOut);
	}
	
	/**
	 * Attempt is not made to search for the whole term, when individual words
	 * are not found seperately.
	 */
	@Test
	public void doNotAttemptToSearchForWholeTermWhenIndividualTermsAreNotFound() 
			throws IOException {
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		doReturn("string not present").when(controller).getInputFromUser();
		controller.fileSearch(args);
		assertTrue(!Arrays.asList(outputStreamCaptor.toString()
			      .trim().split("\\r?\\n|\\r|\\s+|:")).contains("100.0%"));
		System.setOut(standardOut);
	}
	
	/**
	 * The rank is assigned as 100 for files, where the whole term is found 
	 * @throws IOException
	 */
	@Test
	public void calculateRankAs100WhenTheWholeTermIsFound() 
			throws IOException {
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		doReturn("imperdiet fermentum").when(controller).getInputFromUser();
		controller.fileSearch(args);
		assertTrue(Arrays.asList(outputStreamCaptor.toString()
			      .trim().split("\\r?\\n|\\r|\\s+|:")).contains("100.0%"));
		System.setOut(standardOut);
	}
	
	/**
	 * Should throw an illegalArgumentException when argument is empty.
	 * @throws Exception
	 */
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenArgsIsEmpty()
			throws Exception{
		args = new String[0];
		assertThrows(IllegalArgumentException.class, () -> { controller.fileSearch(args);});
	}
	
}
