package proj.lockedme.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;   // Import the FileWriter class

@SuppressWarnings("unused")
public class LockedMeMain {
	private static LockedMeMain mainObj = new LockedMeMain();
	private static Scanner input = new Scanner(System.in);
	private String userName;
	private String userPwd;
	private String userEmail;
	private static String filePath;
	private static String folderPath;
	
	public LockedMeMain() {
		userName = "";
		userEmail= ""; 
		userPwd = "";
		filePath = "/Applications/MAMP/htdocs/PGPFullStack/proj1/proj_rw/credentials.csv";
	}
	
	private Object readFromFile_Login() {
		Boolean loggedIn = LockedMeMain.userInput_Reg(false); 
		if(loggedIn && validateUserDetails (false)) {
			System.out.println("Logged in");
			showUserMainOptions ();
		}
		else {
			System.out.println("\n\nError in login. Try Again\n\n");
			showUserLoginOptions ();
		}
		return null;
	}
	

	private static void deleteFileModule () {
		boolean bError = true;
		try {
			while (bError) {
				File dirPath = new File(folderPath);
			   	 System.out.println("\n\n");

			   	 System.out.println(" Enter Filename with extn (type):");
			   	 System.out.println(" to delete filename is case sensitive:");
			   	 
			   	 String fileName = input.next();

			   	 
			   	 System.out.println(" Enter content of the file:");
			   	 
			   	 String fPath = folderPath + fileName;
			   	 Boolean fileExists = checkIfFileNameExists (fPath);

			   	 System.out.println(" Confirm Delete permanently. y/n");
			   	 
			   	 char answer = input.next().charAt(0);
			   	 
	
				 if(fileExists && answer == 'y') {

					 File fileObj = new File(fPath);
				   	 if(fileObj.delete()) {
				   		 System.out.println("File: " + fileObj.getName() + " deleted Successfully");
				   	 } else {
						 throw new Exception ("Error in deleting File.");
					 }
				 }

			     bError = false;
			     doMoreWithFolderContents();
			}
		} catch (Exception e) {

			  System.out.println("An error occurred.");
		      //e.printStackTrace();
		      //input.reset();
		      input.next();
		      doMoreWithFolderContents();
        }
	}
	
	private static void getNewFileCreateInput () {
		boolean bError = true;
		try {
			while (bError) {
				File dirPath = new File(folderPath);
			   	 System.out.println("\n\n");
			   	 
			   	 System.out.println(" Enter Filename with extn (type):");
			   	 
			   	 String fileName = input.next();

			   	 
			   	 System.out.println(" Enter content of the file:");
			   	 
			   	 String fileContent = input.next();
			   	 String fPath = folderPath + fileName;
			   	 
			   	File fileObj = new File(fPath);
				Boolean fileExists = CreateFile (fileObj);
	
				 if(fileExists) {
					 FileWriter myWriter = new FileWriter(fPath);
				     myWriter.write(fileContent);
				     myWriter.close();
				     System.out.println();
				     System.out.println("\nNew File created Successfully. Path:\n");
				     System.out.println(fPath);
				     doMoreWithFolderContents();
				 }else {
					 throw new Exception ("Error in creating File.");
				 }

			     bError = false;
			}
		} catch (Exception e) {

			  System.out.println("An error occurred.");
		      //e.printStackTrace();
		      //input.reset();
		      input.next();
		      doMoreWithFolderContents();
        }
		
	}
	
	private static void listFolderContents () {
		File dirPath = new File (folderPath);
		//List of all files and directories
	      File contentList[] = dirPath.listFiles();
	      System.out.println("Content of directory :");
	      System.out.println();
	      Arrays.sort(contentList);
	      String content = "File name:\t\t";
	      content += "File Path:\t\t\t\t\t\t";
	      content += "Size:\n";
	      for(File file : contentList) {
	    	  content += file.getName() +"\t\t";
		      content += file.getAbsolutePath() + "\t\t";
		      content += file.getTotalSpace() + "\n";
	      }
	      System.out.println(content);
	      showUserMainOptions ();
	}
	
	private static Boolean checkIfFileNameExists (String fPath) {
		File file = new File(fPath);
		try {
			if(file.exists() && file.getCanonicalPath().equals(fPath)){
			    // file exists
			    return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return false;
	}
	
	private static void doMoreWithFolderContents () {
		boolean bError = true;
		try {
			while (bError) {
				File dirPath = new File(folderPath);
			   	 System.out.println("\n\n");
			   	 
			   	 System.out.println(" 1. Add New File to Directory");
			   	 System.out.println(" 2. Delete File from Directory");
			   	 System.out.println(" 3. Search for a File in Directory");
			   	 System.out.println(" 4. Go Back to previous Options.");
			   	 System.out.println(" 5. Exit Application.");
			   	 System.out.println();
			        
			   	 System.out.println("Enter Your Choice:");
			   	 int num = input.nextInt();   
			         
			   	 switch(num){    
		        	case 1: 

		        		getNewFileCreateInput(); 
		                break;  
		        	case 2: System.out.println("Login");  
		        		doMoreWithFolderContents();
		                break;  
		        	case 3: System.out.println("Exit Complete"); 
		                break;  
		        	case 4: System.out.println("Login");  
		        		doMoreWithFolderContents();
		                break;  
		        	case 5: System.out.println("Exit Complete"); 
	                	break;  
		        	default: System.out.println("invalid option");  
		        		LockedMeMain.showUserLoginOptions();
			   	 }

			     bError = false;
			}
		} catch (Exception e) {

			  System.out.println("An error occurred.");
		      //e.printStackTrace();
		      //input.reset();
		      input.next();
		      LockedMeMain.showUserLoginOptions();
        }
	}
	
	private static void showUserMainOptions () {
		//Take user Option from the below mentioned Menu
	    boolean bError = true;
		try {
			while (bError) {
				File dirPath = new File(folderPath);
				Boolean dirExists = (dirPath.exists() && dirPath.isDirectory());
	
				 if(dirExists) {
				   	
				   	 System.out.println("\n\n");
				   	 
				   	 System.out.println(" 1. View Folder contents.");
				   	 System.out.println(" 2. Work With Folder (Add/ Delete/Search");
				   	 System.out.println(" 3. Exit Application.");
				   	 System.out.println();
				        
				   	 System.out.println("Enter Your Choice:");
				   	 int num = input.nextInt();   
				         
			        switch(num){    
			        	case 1: System.out.println("Listing"); 
			        		listFolderContents(); 
			                break;  
			        	case 2: System.out.println("Login");  
			        		doMoreWithFolderContents();
			                break;  
			        	case 3: System.out.println("Exit Complete"); 
			                break;  
			        	default: System.out.println("invalid option");  
			        		LockedMeMain.showUserLoginOptions();
			        }
				 }else if(folderPath.length() > 0){
					 dirPath.mkdir();
					 System.out.println("Folder did not exists, its been created for you.");
					 System.out.println("Path of folder is " + folderPath);
				 }else {
					System.out.println("Enter Path of folder");
					System.out.println("Example = /Applications/MAMP/htdocs/PGPFullStack/proj1/proj_rw/");
					 folderPath = input.nextLine();
				 }
			     bError = false;
			}
		} catch (Exception e) {

			  System.out.println("An error occurred.");
		      //e.printStackTrace();
		      //input.reset();
		      input.next();
		      LockedMeMain.showUserLoginOptions();
        }
		 
	}

	
	private static boolean validateUserDetails (Boolean registration) {
		
		//this module checks for user already present.
		try {
		      File myObj = new File(filePath);
		      if(myObj.length() <= 0) {
				    return true;
		      }
		      Scanner myReader = new Scanner(myObj);
		      Boolean userExists = false;
		      while (myReader.hasNextLine() && !userExists) {
		    	  
		        
		        String row = myReader.nextLine();
		        String[] userDetails = row.split(",");
		        printArray(userDetails);

        		userExists = true;
		        if(userDetails[0] == mainObj.userName && userDetails[1] == mainObj.userPwd) {
		        	if(registration && userDetails[2] == (mainObj.userEmail + "\n")) {
	        			userExists = true;
	        			printArray(userDetails);
		        	}else {
		        		userExists = true;
		        	}
		        }
		      }
		      myReader.close();
		      return userExists;
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return false;
	}

	private static boolean CreateFile (Object fileObj) {
	    try {
		     
			  if (((File) fileObj).createNewFile()) {
			    System.out.println("File created: " + ((File) fileObj).getName());
			  } else {
			    System.out.println("File already exists.");
			  }
			  return true;
		} catch (IOException e) {
			  System.out.println("An error occurred.");
		      e.printStackTrace();
		      return false;
	    }
	}
	
	private static boolean WriteToFile () {
		  
		    try {
		    	FileOutputStream myWriter = new FileOutputStream(filePath,true);

				File fileObj = new File(filePath);
				String csvRow = "";
		    	if(fileObj.length() > 0 && !validateUserDetails(true)) {
		    		csvRow += "\n";
		    	}else {
				    System.out.println("User Name Already exists. please try logging in.");
			        myWriter.close();
			        mainObj.readFromFile_Login();
		    		return false;
		    	}
		      csvRow += mainObj.userName + ",";
		      csvRow += mainObj.userPwd + ",";
		      csvRow += mainObj.userEmail;
		      myWriter.write(csvRow.getBytes());
		      myWriter.flush();
		      myWriter.close();
		      fileObj = null;
		      return true;
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      return false;
		    }
	}
	
	private static boolean userNameInput () throws IOException {
		 System.out.println();
	   	 System.out.println(" Enter a User Name");
	      
	   	mainObj.userName = input.next();
	   	if(mainObj.userName.length() <= 0) {
	   		throw new IOException ("User Name cannot be empty.");
	   	}
		return true;
	}
	
	private static boolean userPasswordInput () throws IOException {
		 System.out.println();
	   	 System.out.println(" Enter a Password for User Name : " + mainObj.userName);
	      
	   	mainObj.userPwd = input.next();
	   	if(mainObj.userPwd.length() <= 0) {
	   		throw new IOException ("Password Name cannot be empty.");
	   	}
		return true;
	}
	
	private static boolean userEmailInput () throws IOException {
		 System.out.println();
	   	 System.out.println(" Enter a Email for User Name : " + mainObj.userName);
	      
	   	mainObj.userEmail = input.next();
		 System.out.println();
	   	if(mainObj.userEmail.length() <= 0) {
	   		throw new IOException ("Email cannot be empty.");
	   	}
		return true;
	}

	private static boolean userInput_Reg(Boolean registerUser) {
		
		Boolean userInputSuccess = false;
	    try {

				 userInputSuccess = LockedMeMain.userNameInput ();
				 if(userInputSuccess) {
					 userInputSuccess = false;
					 userInputSuccess = userPasswordInput ();

					 if(userInputSuccess && registerUser) {
						 userInputSuccess = false;
						 userInputSuccess = userEmailInput ();
					 }
				 }
				 if(userInputSuccess && registerUser && LockedMeMain.WriteToFile() ) {
					 System.out.println(" User Registration Successfull");
					 mainObj.readFromFile_Login();
					 return true;
				 }else {
					 return true;
				 }
		} catch (IOException e) {
			  System.out.println("An error occurred.");
		      e.printStackTrace();
		      LockedMeMain.userInput_Reg(registerUser); 
		      return false;
	    }
		
	}


	/* A utility function to print array of size n */
	static void printArray(String[] userDetails) 
	{ 
		int n = userDetails.length; 
		for (int i=0; i<n; ++i) 
			System.out.print(userDetails[i]+" "); 
		System.out.println(); 
	} 
	
	private static void showUserLoginOptions () {
		//Take user Option from the below mentioned Menu
	    boolean bError = true;
		try {
			while (bError) {
				File fileObj = new File(filePath);
				Boolean fileExists = CreateFile (fileObj);
	
				 if(fileExists) {
				   	 System.out.println("****************************************************************");
				   	 System.out.println("*********************  LOCKED ME. COM  ********************");
				   	 System.out.println("****************************************************************");
				   	 System.out.println();
				   	 
				   	 System.out.println(" 1. New User - Register");
				   	 System.out.println(" 2. Already User - Login");
				   	 System.out.println();
				        
				   	 System.out.println("Enter Your Choice:");
				   	 int num = input.nextInt();   
				         
			        switch(num){    
			        	case 1: System.out.println("Register"); 
			        		LockedMeMain.userInput_Reg(true); 
			                break;  
			        	case 2: System.out.println("Login");  
			        		mainObj.readFromFile_Login();
			                break;  
			        	default: System.out.println("invalid option");  
			        		LockedMeMain.showUserLoginOptions();
			        }
				 }
			     bError = false;
			}
		} catch (Exception e) {

			  System.out.println("An error occurred.");
		      //e.printStackTrace();
		      //input.reset();
		      input.next();
		      LockedMeMain.showUserLoginOptions();
        }
		 
	}

	public static void main(String[] args) {
		Scanner lm = new Scanner(System.in);
		// TODO Auto-generated method stub
		System.out.println("Welcome User!");
		System.out.println("Project: LockedMe.com");
		System.out.println("Developed By: Shubham Raj");
		System.out.println("Email: raiandbala@outlook.com");
		System.out.println("Github Repo: https://github.com/raiandbala/test4");
		System.out.println("Enter Path of folder");
		System.out.println("Example = /Applications/MAMP/htdocs/PGPFullStack/proj1/proj_rw/");
		LockedMeMain.folderPath = lm.nextLine();
		showUserLoginOptions();
		lm.close();
	}

}
