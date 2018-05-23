package enrollmentSystemFinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Random;

	public class enrollmentMain {
		private static final String classSection [] = {"CSIT201","CSIT101","CSIT301"};
		private static final String subjSched[]= {"M-F 8:00AM-9:00:AM","M-F 10:00AM-11:00AM","M-F 11:00AM-12:00PM",
				"M-F 1:00PM-2:00PM","M-F 2:00PM-3:00PM","M-F 3:00PM-4:00PM","M-F 4:00PM-5:00PM","M-F 5:00PM-6:00PM"};
		private static final String subjRoom[]={"D-301","A-101","C-201","B-401","E-501","F-601","G-701","H-801",
				"I-901","J-102","K-202"};
		private static String studDetails[];
		private static String subjDetails[];
		
		public static void main (String [] args)throws IOException {
			showMenu();
			
		}
		
	public static void showMenu() throws IOException{
			
			BufferedReader in = Functions.bufferedReader();
			
			while(true) {
				
				System.out.println("**********ENROLLMENT SYSTEM************");
				System.out.println("*                                     *");
				System.out.println("* [A]. NEW STUDENT  [B]. VIEW STUDENT *");
				System.out.println("* [C]. ADD SUBJECT TO A STUDENT       *");
				System.out.println("* [D]. REMOVE SUBJECT TO A STUDENT    *");
				System.out.println("*                                     *");
				System.out.println("***************************************");
				String choices=in.readLine();
				
					if(choices.equalsIgnoreCase("a")) {
						addStudents();
					}else if(choices.equalsIgnoreCase("b")) {
						displayStudentFullDetails();
					}else if(choices.equalsIgnoreCase("c")){
						displayStudent();
					}else if(choices.equalsIgnoreCase("D")){
						removeSubject();
					}else {
						System.out.println("Invalid Input");
					}
			}
		}
	
	public static void showStudent(String idNum)throws IOException {
		Student stud = new Student();
		System.out.println("*************************");
		BufferedReader read=Functions.txtFileReader(stud.getStudentsRecord(idNum));
		String textLine;

		
			while((textLine=read.readLine())!=null) {
				studDetails = textLine.split("/");				
				stud.setStudIDNum(studDetails[0]);
				stud.setStudFullName(studDetails[1]);
				stud.setStudAge(studDetails[2]);
				stud.setStudGender(studDetails[3]);
				stud.setStudSection(studDetails[4]);
				stud.setStudCourse(studDetails[5]);
			
				if(stud.getStudIDNum().equalsIgnoreCase(idNum)) {
					displayStud(stud.getStudIDNum(), stud.getStudFullName(),stud.getStudAge(), 
							stud.getStudGender(), stud.getStudSection(),stud.getStudCourse());
				}
				
			}
		
		read.close();
	}
	
	public static void showStudentSubject(String idNum)throws IOException {
		
		Subject subj=new Subject();
		String subjRemove;
		String txtLine;
		
		BufferedReader read=Functions.txtFileReader(subj.getStudSubjects(idNum));
		System.out.println("************ENROLLED SUBJECT************");
		while((txtLine=read.readLine())!=null) {

			subjDetails=txtLine.split(",");
			subj.setSubjectCode(subjDetails[0].trim());
			subj.setSubjectName(subjDetails[1].trim());
			int numUnits = Integer.parseInt(subjDetails[2].trim());
			subj.setSubjectUnits(numUnits);;
			subj.setSubjectRoom(subjDetails[3]);
			subj.setSubjectSchedule(subjDetails[4]);
			
				System.out.println(subj.getSubjectCode()+" "+subj.getSubjectName()+" "+subj.getSubjectUnits()+" "
				+subj.getSubjectRoom()+" "+subj.getSubjectSchedule());
		}
		read.close();
		System.out.println("************************************\n");
}
	
	public static void addStudents() throws IOException{
		
		BufferedReader in = Functions.bufferedReader();
		Student stud=new Student();
		
		String firstName,middleName,lastName,gender,age,course,
		fullName="";
		
		do {
			
			System.out.println("*************************************");
			System.out.print("\nFirst Name : ");
			firstName = in.readLine();
			System.out.print("Middle Name : ");
			middleName = in.readLine();
			System.out.print("Last Name : ");
			lastName=in.readLine();
			System.out.print("Gender M/F : ");
			gender = in.readLine();
				if(gender.equalsIgnoreCase("m")) {
					gender="Male";
				}else if(gender.equalsIgnoreCase("f")) {
					gender="Female";
				}else {
					gender="undefined";
				}
			System.out.print("Age : ");
			age=in.readLine();
			System.out.print("Course : ");
			course=in.readLine();
			stud.setStudIDNum(generateIDNumber());
			stud.setStudSection(classSection[generateRandomIndex(classSection.length)]);
			
			fullName=lastName+", "+firstName+" "+middleName;
			
			displayStud(stud.getStudIDNum(), fullName,age,gender,
						stud.getStudSection(),course);
			
			System.out.println("\nDO YOU WANT TO EDIT ? [Y][N]");
			String choices = in.readLine();
			if(choices.equalsIgnoreCase("n")) {
				stud.setStudFullName(fullName);
				stud.setStudAge(age);
				stud.setStudGender(gender);
				stud.setStudCourse(course);
				System.out.println("****RECORD SAVED SUCCESSFULLY****");
				createAStudentFileData(stud.getStudIDNum(), stud.getStudFullName(),stud.getStudAge(),stud.getStudGender(),
						stud.getStudSection(),stud.getStudCourse());
				showMenu();
			}else if(choices.equalsIgnoreCase("y")) {
				
			}else {
				System.out.println("Invalid Input");
			}
		}while(true);
		
		
	}
	
	public static void addSubject(String idNum) throws IOException {
		System.out.println("PRESS X TO EXIT ADD SUBJECT");
		BufferedReader in = Functions.bufferedReader();
		
		PrintWriter print = null;
		
		String code;
		int tempUnits=0;
		displaySubjectTextFile();
		while(true) {
			System.out.print("\nENTER SUBJECT CODE : ");
			code = in.readLine();
			
			
				
			if(subjAlreadyTake(idNum, code)) {
			
				String textLine;
				Subject subj = new Subject();
				BufferedReader read=Functions.txtFileReader(subj.getAllSubj());
				
				
				subj.setSubjectRoom(subjRoom[generateRandomIndex(subjRoom.length)]);
				subj.setSubjectSchedule(subjSched[generateRandomIndex(subjSched.length)]);
				
					while((textLine=read.readLine())!=null) {
						subjDetails=textLine.split(",");
						subj.setSubjectCode(subjDetails[0].trim());
						subj.setSubjectName(subjDetails[1].trim());
						int numUnits = Integer.parseInt(subjDetails[2].trim());
						subj.setSubjectUnits(numUnits);;
						
						
						if(subj.getSubjectCode().equalsIgnoreCase(code)) {
							tempUnits=tempUnits+subj.getSubjectUnits();
							System.out.println(tempUnits);
							if(subj.checkIfMaxUnits(tempUnits)) {
								System.out.println("NUMBER OF UNITS IS MORE THE 25, FAILED TO ADD");
								showStudentSubject(idNum);
								showMenu();
							}else {
								
								try {
									
									
										 print=Functions.printWriter(Functions.file(subj.getStudSubjects(idNum)));
										
										print.println(subj.getSubjectCode()+","+subj.getSubjectName()+","
										+subj.getSubjectUnits()+","+subj.getSubjectRoom()+","+subj.getSubjectSchedule());
										
										

										
								}catch(Exception err){
									System.out.println("Create New File Error : "+err.getMessage());
								}finally {
									print.close();
								}
							}
							
						}
					}
					read.close();
					
			}else {
				System.out.println("SUBJECT IS ALREADY TAKEN");
			}
		
			if(code.trim().equalsIgnoreCase("x")) {
				System.out.println("STOP");
				System.exit(0);
			}
		}
	
		
	}
	
	public static void displayStudent()throws IOException {
		BufferedReader in = Functions.bufferedReader();
		

			String idNum;
			System.out.print("STUDENT NUMBER : ");
			idNum=in.readLine();
				if(ifStudentExist(idNum)) {
					showStudent(idNum);
					addSubject(idNum);
				}else {
					System.out.println("RECORD DOESN'T EXIST");
					showMenu();
				}
			
			
	}
	
	public static void displayStudentFullDetails()throws IOException {
		
		
		BufferedReader in = Functions.bufferedReader();
		Student stud = new Student();
		Subject subj=new Subject();
		String txtLine;
		
		int tempUnits=0;
		
			String idNum;
			System.out.print("STUDENT NUMBER : ");
			idNum=in.readLine();
			System.out.println("*************************");
			
			if(ifStudentExist(idNum.trim())) {
				showStudent(idNum.trim());
				BufferedReader read=Functions.txtFileReader(subj.getStudSubjects(idNum.trim()));
				showStudentSubject(idNum.trim());
			}else {
				System.out.println("NO RECORD FOR ID NUMBER "+idNum+" FOUND");
				showMenu();
			}
			
		
	}
	
	public static void displaySubjectTextFile()throws IOException {
			
			BufferedReader in = Functions.bufferedReader();
			Subject subj = new Subject();
				BufferedReader read=Functions.txtFileReader(subj.getAllSubj());
				String textLine;

			System.out.println("SUBJECT CODE	SUBJECT NAME	UNITS");
			System.out.println("-----------------------------------------------------");
					while((textLine=read.readLine())!=null) {
						subjDetails=textLine.split(",");
						subj.setSubjectCode(subjDetails[0].trim());
						subj.setSubjectName(subjDetails[1].trim());
						int numUnits = Integer.parseInt(subjDetails[2].trim());
						subj.setSubjectUnits(numUnits);;
							
							System.out.println(textLine);
				
					}
				
				read.close();
		}
	
	//for checking java is reading the file
	public static void displaySubj()throws IOException {
		String textLine;
		Subject subj = new Subject();
		BufferedReader read=Functions.txtFileReader(subj.getAllSubj());
		String subjDetails[];
			while((textLine=read.readLine())!=null) {
				subjDetails=textLine.split(",");
				subj.setSubjectCode(subjDetails[0].trim());
				subj.setSubjectName(subjDetails[1].trim());
				int numUnits = Integer.parseInt(subjDetails[2].trim());
				subj.setSubjectUnits(numUnits);;
				
				System.out.println(subj.getSubjectCode());
			}
			read.close();
	}
	
	public static boolean SubjExist(String Code) throws IOException {
		String textLine;
		Subject subj = new Subject();
		BufferedReader read=Functions.txtFileReader(subj.getAllSubj());
		String subjDetails[];
			while((textLine=read.readLine())!=null) {
				subjDetails=textLine.split(",");
				subj.setSubjectCode(subjDetails[0].trim());
				if(subj.getSubjectCode().equalsIgnoreCase(Code)) {
					return true;
				}else {
					return false;
				}
			}
			read.close();
			return false;
	}
	
	public static void removeSubject()throws IOException {
		
		BufferedReader in = Functions.bufferedReader();
		//Subject subj=new Subject();
		String idNum;
		String subjRemove;
//		String txtLine;
//		String subjDetails[];
//		
		System.out.print("ENTER STUDENT NUMBER : ");
		idNum=in.readLine();
//	
//		
		if(ifStudentExist(idNum)) {
			showStudent(idNum);
			showStudentSubject(idNum);
			
			System.out.print("ENTER SUBJECT TO DROP : ");
			subjRemove=in.readLine();
			removeLine(idNum, subjRemove);
			
			System.out.println("SUBJECT ALREADY REMOVE");
		}else {
			System.out.println("NO RECORD FOR ID NUMBER "+idNum+" FOUND");
			showMenu();
		}
		

	}
	
	public static void removeLine(String oldFileNum,String subjCode) throws IOException{
		
	    File oldFile = new File("D:\\Java Eclipse Programs\\enrollmentSystemFinal\\db\\studentFile\\"+oldFileNum+".txt");
	    	if(!oldFile.exists()) {
	    		System.out.println("File DOESN'T EXIST");
	    		showMenu();
	    	}
	  

	    File newFile = new File(oldFile.getAbsolutePath()+".txt");

	    BufferedReader br = new BufferedReader(new FileReader("D:\\Java Eclipse Programs\\enrollmentSystemFinal\\db\\studentFile\\"+oldFileNum+".txt"));
	    PrintWriter pw = new PrintWriter(new FileWriter(newFile));
	    String line = null;
	
	   
	    while ((line = br.readLine()) != null) {
	        if (!line.trim().contains(subjCode.toUpperCase())) {
	            pw.println(line);
	           
	        }
	    }
	    br.close();
	    pw.flush();
	    pw.close();
	    
	    
	    //Delete the original file
	    if(!oldFile.delete()) {
	    	System.out.println("FILE IS NOT DELETED");
	    	return;
	    }
	
	    //Rename the new file to the filename the original file had.
	  File dump = new File("D:\\Java Eclipse Programs\\enrollmentSystemFinal\\db\\studentFile\\"+oldFileNum+".txt");
	 
	  
		  if(!newFile.renameTo(dump)) {
			  System.out.println("FILE IS NOT RENAMED");
		    	return;
		  }
		
		}
	
	public static void displayStud(String studIDnum,String studName,String age,String gender,String section,String course) {
		System.out.println("**************************************************************************");
		System.out.println("STUDENT NUMBER : "+studIDnum+"          STUDENT NAME : "+studName);
		System.out.println("AGE : "+age+"                         GENDER : "+gender.toUpperCase());
		System.out.println("COURSE : "+course.toUpperCase()+"    	         SECTION : "+section.toUpperCase());
		System.out.println("**************************************************************************");
	}
	
	public static void createAStudentFileData(String studIDnum,String studName,String age,String gender,String section,String course){
		Student stud=new Student();
		Subject subj=new Subject();
	try {
		PrintWriter print=Functions.printWriter(Functions.file(stud.getStudentsRecord(studIDnum)));
		
		PrintWriter printID=Functions.printWriter(Functions.file(subj.getStudSubjects(studIDnum)));
		
	
		
		print.println(studIDnum+"/"+studName+"/"+age+"/"+gender+"/"+section+"/"+course);
		
		printID.close();
		print.close();
	}catch(Exception err){
		System.out.println("Create New File Error : "+err.getMessage());
	}
	
}
	
	public static int generateRandomIndex(int index) {
		  Random num = new Random();
	      int numberAsString = num.nextInt(index);
	      
	      return numberAsString;
	}
	
	public static String generateIDNumber() {
		
		  Random num = new Random();
		  DecimalFormat df = new DecimalFormat("000000");
	      String numberAsString = df.format(num.nextInt(99999));
	      
	      return numberAsString;
	}
	
	public static boolean ifStudentExist(String idNum) {
		Subject subj=new Subject();
		try {
			if(Functions.file(subj.getStudSubjects(idNum)).exists()) {
				return true;
			}else {
				return false;	
			}
	
		}catch(Exception err){
			System.out.println("File Exist : "+err.getMessage());
		}
		return false;
		
	}
	
	public static boolean subjAlreadyTake(String idNum,String subjCode)throws IOException {
		
			Subject subj = new Subject();
			BufferedReader read = Functions.txtFileReader(subj.getStudSubjects(idNum));
			String textLine;
				while((textLine=read.readLine())!=null) {
					subjDetails = textLine.split(",");
					subj.setSubjectCode(subjDetails[0]);		
				}
				
				if(!subjCode.equalsIgnoreCase(subj.getSubjectCode())) {
					return true;
				}else {
					return false;
				}
				
		}
	
	public static boolean conflictCheck(String idNum,String subjSched,String subjRoom)throws IOException {
		Subject subj = new Subject();
		BufferedReader read = Functions.txtFileReader(subj.getStudSubjects(idNum));
		String textLine;
			while((textLine=read.readLine())!=null) {
				subjDetails = textLine.split(",");
				subj.setSubjectSchedule(subjDetails[4]);
				subj.setSubjectRoom(subjDetails[3]);
			}
			read.close();
			if(!subjSched.equalsIgnoreCase(subj.getSubjectSchedule()) ||
					!subjRoom.equalsIgnoreCase(subj.getSubjectRoom())) {
				return true;
			}else {
				return false;
			}
			
			
	}
	
	}
