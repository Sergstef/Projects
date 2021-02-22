import java.util.ArrayList;

import Exceptions.AverageMarkOutOfBoundException;
import Exceptions.NoFacultyException;
import Exceptions.NoGroupInFacultyException;
import Exceptions.NoStudentInFacultyException;


public class Main {

	public static void main(String[] args) {
		ArrayList<Subject> l = new ArrayList<>();
		l.add(new Subject("math", 5));
		l.add(new Subject("english", 10));
		ArrayList<Subject> l1 = new ArrayList<>();
		l1.add(new Subject("math", 9));
		l1.add(new Subject("english", 2));
		
		Student student1 = new Student("sergey", l, Faculty.IMIKN, Group.BI2);
		Student student2 = new Student("ivan", l1, Faculty.IMIKN, Group.BI2);
		
		ArrayList<Student> s1 = new ArrayList<>();
		s1.add(student1);
		s1.add(student2);
		
		AverageMarkAccumulator mar = new AverageMarkAccumulator();
		System.out.println(mar.getAverageMarkByCurrentSubjectInUniversity(s1, "math"));
		try {
			System.out.println(mar.getAverageMarkByCurrentSubjectGroupFaculty(s1, "IMIKN", "BI2", "english"));
		} catch (NoFacultyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoStudentInFacultyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoGroupInFacultyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AverageMarkOutOfBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
