package lab6.studentsAndTeachers.uniqueStudentsAndTeachers;

import java.util.HashSet;
import java.util.Set;

import lab6.studentsAndTeachers.modificationStudentAndTeacher.Constant;

import lab6.studentsAndTeachers.modificationStudentAndTeacher.HumanImpl;

public class MainThree {

	public static void main(String[] args) {
		Set <HumanImpl> people = new HashSet<HumanImpl>();
		HumanImpl human1 = new HumanImpl("as", "dsg", Constant.STUDENT);
		HumanImpl human2 = new HumanImpl("as", "dsg", Constant.TEACHER);
		HumanImpl human3 = new HumanImpl("assds", "dsg", Constant.TEACHER);

		people.add(human1);
		people.add(human2);
		people.add(human3);
		for(HumanImpl a: people) {
			System.out.println(a.getName());
		}

	}

}
