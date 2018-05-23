package enrollmentSystemFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

public class Functions {
	
	public static BufferedReader bufferedReader()throws IOException {
		return new BufferedReader(new InputStreamReader(System.in));
	}
	public static PrintWriter printWriter(File fileName)throws IOException {
		return new PrintWriter(new FileWriter(fileName,true));
	}
	public static File file(String fileDir)throws IOException {
		return new File(fileDir);
	}
	public static Date dateTime() {
		return new Date();
	}

	public static BufferedWriter bufferedWriter(File fileName)throws IOException {
		return new BufferedWriter(new FileWriter(fileName,true));
	}
	public static BufferedReader txtFileReader(String fileName)throws IOException {
		return new BufferedReader(new FileReader(fileName));
	}
	
	
	

}
