package lab6.studentsAndTeachers.sortByAverageMark;

import java.util.ArrayList;
import java.util.Arrays;

import lab6.studentsAndTeachers.modificationStudentAndTeacher.Constant;

import lab6.studentsAndTeachers.modificationStudentAndTeacher.HumanImpl;

public class MainFour {

	public static void main(String[] args) {
		ArrayList<HumanImpl> students = new ArrayList<HumanImpl>();
		HumanImpl human1 = new HumanImpl("������","�����", 3, Constant.STUDENT);
		HumanImpl human2 = new HumanImpl("����","�����",Constant.TEACHER);
		HumanImpl human3 = new HumanImpl("�������","�����", 4, Constant.STUDENT);
		HumanImpl human4 = new HumanImpl("������","�����",Constant.TEACHER);
		HumanImpl human5 = new HumanImpl("�����","�����", 5, Constant.STUDENT);
		HumanImpl human6 = new HumanImpl("�����","�����", 5, Constant.BOTH);
		students.add(human1);
		students.add(human2);
		students.add(human3);
		students.add(human4);
		students.add(human5);
		students.add(human6);
		HumanImpl [] secondListOfStudents = students.toArray(new HumanImpl[0]);
		Arrays.sort(secondListOfStudents, HumanImpl.Mark);
		System.out.println("��������, ��������������� �� ������:");
		for(HumanImpl human:secondListOfStudents ) {
			if(human.getType()=="STUDENT" | human.getType()=="BOTH") {
			System.out.println(human.getName() + " " + human.getSurname() + " - " + human.getAverageMark());
			}
		}
	

}
}
