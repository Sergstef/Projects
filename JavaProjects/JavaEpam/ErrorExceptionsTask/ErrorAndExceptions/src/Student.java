import java.util.ArrayList;
import java.util.Arrays;

public class Student {
	
	private String name;
	private ArrayList<Subject> listOfSubjects;
	private Faculty faculty;
	private Group group;

	public Student(String name, ArrayList<Subject> listOfSubjects, Faculty faculty, Group group) {
		super();
		this.name = name;
		this.listOfSubjects = listOfSubjects;
		this.faculty = faculty;
		this.group = group;
	}

	public String getName() {
		return name;
	}



	public ArrayList<Subject> getListOfSubjects() {
		return listOfSubjects;
	}
	
	public Subject getCurrentSubject(String nameOfSubject) {
		return listOfSubjects.stream()
				.filter(e -> e.getName().equals(nameOfSubject))
				.findFirst()
				.get();
	}


	public Faculty getFaculty() {
		return faculty;
	}



	public Group getGroup() {
		return group;
	}

}
