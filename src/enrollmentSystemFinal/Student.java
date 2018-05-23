package enrollmentSystemFinal;


public class Student {

	private String studIDNum,studFullName,
	studSection,studAge,studGender,studCourse;

	
	
	public String getStudCourse() {
		return studCourse;
	}

	public void setStudCourse(String studCourse) {
		this.studCourse = studCourse;
	}

	public String getStudSection() {
		return studSection;
	}

	public void setStudSection(String studSection) {
		this.studSection = studSection;
	}

	public String getStudAge() {
		return studAge;
	}

	public void setStudAge(String studAge) {
		this.studAge = studAge;
	}

	public String getStudGender() {
		return studGender;
	}

	public void setStudGender(String studGender) {
		this.studGender = studGender;
	}

	public String getStudIDNum() {
		return studIDNum;
	}

	public void setStudIDNum(String studIDNum) {
		this.studIDNum = studIDNum;
	}

	public String getStudFullName() {
		return studFullName.toUpperCase();
	}

	public void setStudFullName(String studFullName) {
		this.studFullName = studFullName;
	}
	
	
	public String toString() {
		return studIDNum+"		"+studFullName;
	}
	
	public static String getStudentsRecord(String idNum){
		
		String StudentsRecord="D:\\Java Eclipse Programs\\enrollmentSystemFinal\\db\\listofallstudent.txt";
		return StudentsRecord;
	}

}

