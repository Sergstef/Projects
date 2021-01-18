package lab6.studentsAndTeachers.simpleOutOfTeacherAndStudent;


import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList people = new ArrayList();
		Student student1 = new Student("Сергей", "Стефанов");
		people.add(student1);
		Teacher teacher1 = new Teacher("Арсений", "Визгунов");
		people.add(teacher1);
		System.out.println("Список студентов и преподавателей");
		for(Object a: people) {
			if(a instanceof Student) {
				System.out.println("Фамилия студента: " + ((Student) a).getSurname());
			}
			if(a instanceof Teacher) {
				System.out.println("Фамилия преподавателя: " + ((Teacher) a).getSurname());
			}
		}

	}

}
