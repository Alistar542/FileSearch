# File Search
A simple java application that can search for terms in all the files in a given directory

## Instructions to run the code
* Compile the all the java files in the src folder.Java version used is 1.8.
* Now run the command `java -cp . com.company.FileSearchProject.App <path_to_directory>`
* The above command will run the program with the path to the directory as given
* There are already 11 files present in the directory "FileSearchProject\src\main\resources" for ease.
* The user can then enter the search term once the `search>` appears in the command prompt
* The program searches for the presence of the words seperately and as a whole
* The First search is done on the words individually.
* Once Individual words are found seperately, the search now happens for the search term
  as a whole automatically.
* If the whole term is found, then the rank "100%" is shown against the file name.
* If the whole term is not found, then appropriate rank is shown.
* The formulae used for calculating rank is as given:
	- Weightage is distributed among the individual words and the whole term.
	- So if the search term contains 4 words, then weightage is distributed among the 
	  four words and the whole term equally. The weightage is calculated by
	  100/(no.of.words + 1). So for the given example of 4 words, the individual weightage
	  would be 100/5 = 20 among the 4 words and 20 for the whole term.

## Example:
```
      search term = "Aelius Maximus Decimus Meridius"
      So the weightage individually would be 100/(4+1) = 20
	If just "Aelius" is found:
		then the rank is 20%
	If "Aelius" and "Maximus" is found:
		then the rank is 50%
	If all the words "Aelius","Maximus","Decimus","Meridius" are found,
	but not as a whole
		then the rank is 75%
	If the term "Aelius Maximus Decimus Meridius" is found as a whole
		then the rank is 100%
```
    
## Few assumptions :
```
	* The search is case insensitive
	* Input handling is not done to handle all the cases(wildcards)
	* The directory to be searched must only contain files and not directories.
	* A break statement is to be added in the while(true){} loop when running testcases
	  else the test cases wont stop their execution.
```
