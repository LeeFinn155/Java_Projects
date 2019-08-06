/*
COS 161 Fall 2018
Date: December 1, 2018
Author: Omar, Matt, Lee
Assignment: Program 5 - Recursive
*/



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Recursive {
	
	public static void main(String args[]) {
		
		String dir = System.getProperty("user.dir") + 
				System.getProperty("file.separator") + "src" + 
				System.getProperty("file.separator") + "dir";
		
		
		replaceWord(new File("***someFileThatDoesn'tExist***"), "I", "you");
		replaceWord(new File(dir + System.getProperty("file.separator") + "file1.txt"), "I", "you");
		replaceWord(new File(dir), "I", "you");
		
	}
	
	
	
	static String directory = " directory";
	static String numberFiles = " file.";
	static int [] dirFileChangedFinal;
	
	public static void replaceWord(File dir, String oldString, String newString){
	
		int [] array1 = new int[3];
		replaceWord(dir, oldString, newString, array1);
		System.out.println("Checked " + dirFileChangedFinal[0] + directory + ", and updated " + dirFileChangedFinal[1] + " of " + dirFileChangedFinal[2] + numberFiles );
	}
	
	//Method that actually does the work. It is recursively going through the directories, reading files and making changes
	//The part that isn't working right is the numbers for directories and files changed
	public static void replaceWord(File dir, String oldString, String newString, int[] dirFileChanged) {

		
		
		//dirFileChanged 0 = Directories and subs, 1 = Files that were checked, 2 = files that were updated
		
		//Single File
		try {
		if(dir.isFile() == true) {
			
			
				StringBuilder fileBeingRead2 = new StringBuilder();
				
			    try (BufferedReader buffReader2 = new BufferedReader(new FileReader(dir.getCanonicalPath())))
			    {
			        String currentLine2;
			        while ((currentLine2 = buffReader2.readLine()) != null)
			        {
			        	fileBeingRead2.append(currentLine2).append(System.lineSeparator());
			        }
			    }
			    
			    catch (FileNotFoundException | NullPointerException ex)
			    {
			        ex.printStackTrace();
			    }
			    
			    dirFileChanged[2] +=1;
			   String replacedText2 = (fileBeingRead2.toString()).replaceAll(oldString, newString);
			    	
			   		boolean compareText2 = fileBeingRead2.toString().equals(replacedText2.toString());
			   		if (compareText2 == false) {
			    		dirFileChanged[1] += 1;
			    		Files.write(Paths.get(dir.getCanonicalPath()), replacedText2.getBytes());
			    	}	
				}
				
		}
		catch (IOException | NullPointerException ex) {
			ex.printStackTrace();
		}
		
		
		
		//directories and files
		File[] files = null;
		try {

			files = dir.listFiles();
			if (files != null){
			for (File file : files) {
				if (file.isDirectory() && file.exists()) {
					replaceWord(file, oldString, newString, dirFileChanged);
				//	dirFileChanged[1] += 1;
				
				} 
			
				
				else if (file.isFile() && file.exists()) {
					StringBuilder fileBeingRead = new StringBuilder();
					
				    try (BufferedReader buffReader = new BufferedReader(new FileReader(file.getCanonicalPath())))
				    {
				        String currentLine;
				        while ((currentLine = buffReader.readLine()) != null)
				        {
				        	fileBeingRead.append(currentLine).append(System.lineSeparator());
				        }
				  
				    }
				    
				    catch (FileNotFoundException | NullPointerException ex)
				    {
				        ex.printStackTrace();
				    }
				    
				    dirFileChanged[2] +=1;
				   String replacedText = (fileBeingRead.toString()).replaceAll(oldString, newString);
				  
				
				   		boolean compareText = fileBeingRead.toString().equals(replacedText.toString());
				   		if (compareText == false) {
				    		
				    		Files.write(Paths.get(file.getCanonicalPath()), replacedText.getBytes());
				    		dirFileChanged[1] +=1;
				    	}		
				   		
				   		//Directory Counter
				   		File folder = new File(dir.getCanonicalPath());
						File[] folders = folder.listFiles(new FileFilter() {
						    @Override
						    public boolean accept(File f) {
						        return f.isDirectory();
						    }
						});
						dirFileChanged[0] = dirFileChanged[0] + folders.length;
				   		
						
				}
					else {
					System.out.println("File not found.");
					}
				}
			} 
		}
		catch (IOException | NullPointerException ex) {
			ex.printStackTrace();
		}
		
		

	
	if (dirFileChanged[0] > 1 ) {
		directory = " directories";
	}
	if (dirFileChanged[2] > 1 ) {
		numberFiles = " files.";
	}
	
	dirFileChangedFinal = dirFileChanged;
	
	

	}
}

