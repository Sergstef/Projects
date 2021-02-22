import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Exceptions.AverageMarkOutOfBoundException;
import Exceptions.NoFacultyException;
import Exceptions.NoGroupInFacultyException;
import Exceptions.NoStudentInFacultyException;

public class AverageMarkAccumulator {
	public double getAverageMark(ArrayList<Subject> listOfSubjects) throws AverageMarkOutOfBoundException{
		double result = listOfSubjects.stream()
				.mapToInt(e -> e.getMark())
				.average()
				.getAsDouble();
		if(result > 10 || result < 0) {
			throw new AverageMarkOutOfBoundException();
		}
		return result;
	}
	
	public double getAverageMarkByCurrentSubjectGroupFaculty(ArrayList<Student> listOfStudents,
			String nameOfFaculty, String nameOfGroup, String nameOfSubject) throws NoFacultyException,
	NoStudentInFacultyException, NoGroupInFacultyException, AverageMarkOutOfBoundException{
		
		if(!doesFacultyExist(nameOfFaculty)) {
			throw new NoFacultyException(); //есть ли факультет в университете
		}
		
		List<Student> sortedListByFaculty = getSortedListByFaculty(listOfStudents, nameOfFaculty);
		
		if(sortedListByFaculty.size() == 0) {
			throw new NoStudentInFacultyException(); //есть ли на факультете студенты
		}
		
		List<Student> sortedListByGroup = getSortedListByGroup(sortedListByFaculty, nameOfGroup);
		
		if(sortedListByGroup.size() == 0) {
			throw new NoGroupInFacultyException(); //есть ли конкретная группа в этом факультете
		}
		
		double result = getAverageMarkByCurrentSubjectInUniversity(sortedListByGroup, nameOfSubject);
		
		if(result > 10 || result < 0) {
			throw new AverageMarkOutOfBoundException();
		}
		
		return result;			
							
	}
	
	public static double getAverageMarkByCurrentSubjectInUniversity(List<Student> listOfStudents,
			String nameOfSubject) {
		double result = 0.0;
		int counter = 0;
		
		for(Student student: listOfStudents) {
			Subject curSub = student.getCurrentSubject(nameOfSubject);
			if (curSub != null) {
				result += curSub.getMark();
				counter++;
			}
			
		}
		return result / counter;
	}

	private static boolean doesFacultyExist(String nameOfFaculty) {
		boolean isFaculty = false;
		for(Faculty faculty: Faculty.values()) {
			if (faculty.equals(Faculty.valueOf(nameOfFaculty))) {
				isFaculty = true;
			}
		}
		return isFaculty;
	}

	private static List<Student> getSortedListByFaculty(ArrayList<Student> listOfStudents, String nameOfFaculty) {
		return listOfStudents.stream()
				.filter(e -> e.getFaculty().equals(Faculty.valueOf(nameOfFaculty)))
				.collect(Collectors.toList());
	}

	private static List<Student> getSortedListByGroup(List<Student> listOfStudents, String nameOfGroup) {
		return listOfStudents.stream()
				.filter(e -> e.getGroup().equals(Group.valueOf(nameOfGroup)))
				.collect(Collectors.toList());
	}

	
}
