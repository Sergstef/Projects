package lab6.studentsAndTeachers.simpleOutOfTeacherAndStudent;


import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList people = new ArrayList();
		Student student1 = new Student("������", "��������");
		people.add(student1);
		Teacher teacher1 = new Teacher("�������", "��������");
		people.add(teacher1);
		System.out.println("������ ��������� � ��������������");
		for(Object a: people) {
			if(a instanceof Student) {
				System.out.println("������� ��������: " + ((Student) a).getSurname());
			}
			if(a instanceof Teacher) {
				System.out.println("������� �������������: " + ((Teacher) a).getSurname());
			}
		}

	}

}
