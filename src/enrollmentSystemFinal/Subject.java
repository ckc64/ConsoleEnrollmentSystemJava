package enrollmentSystemFinal;


public class Subject {
		
	private String subjectName,
	subjectCode,
	subjectSchedule,
	subjectRoom,
	roomNumber;
	int subjectUnits;
	
//	{"M-F 8:00AM-10:00:AM","M-F 1:00PM-3:00PM","M-F 4:00PM-6:00PM"}
//	{"D-301","A-101","C-201","B-401"}
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}




	public String getSubjectSchedule() {
		return subjectSchedule;
	}




	public void setSubjectSchedule(String subjectSchedule) {
		this.subjectSchedule = subjectSchedule;
	}




	public String getSubjectRoom() {
		return subjectRoom;
	}




	public void setSubjectRoom(String subjectRoom) {
		this.subjectRoom = subjectRoom;
	}
	

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getSubjectUnits() {
		return subjectUnits;
	}

	public void setSubjectUnits(int subjectUnits) {
		this.subjectUnits = subjectUnits;
	}

	public boolean checkIfMaxUnits(int units) {
		if(units>25) {
			return true;
		}else {
			return false;
		}
	}

	public static String getStudSubjects(String idNum){
		
		String studentSubjectRec="D:\\Java Eclipse Programs\\enrollmentSystemFinal\\db\\studentFile\\"+idNum+".txt";
		return studentSubjectRec;
	}
	
public static String getAllSubj(){
		
		String listOfSubj="D:\\Java Eclipse Programs\\enrollmentSystemFinal\\db\\listofsubject.txt";
		return listOfSubj;
	}
	
//	public String toString() {
//		return getSubjectCode()+" "+getSubjectName()+" "+getSubjectUnits();
//	}
	
}