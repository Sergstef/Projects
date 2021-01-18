package lab6.studentsAndTeachers.modificationStudentAndTeacher;

import java.util.ArrayList;

public class MainTwo {

	public static void main(String[] args) {
		ArrayList <HumanImpl> studentsAndTeachers = new ArrayList<HumanImpl>();
		HumanImpl human1 = new HumanImpl("Сергей","Валов", 3, Constant.STUDENT);
		HumanImpl human2 = new HumanImpl("Иван","Коров",Constant.TEACHER);
		HumanImpl human3 = new HumanImpl("Алексей","Ледин", 4, Constant.STUDENT);
		HumanImpl human4 = new HumanImpl("Андрей","Курин",Constant.TEACHER);
		HumanImpl human5 = new HumanImpl("Семен","Сонов", 5, Constant.STUDENT);
		HumanImpl human6 = new HumanImpl("Денис","Варин", 5, Constant.BOTH);
		studentsAndTeachers.add( human1 );
		studentsAndTeachers.add( human2 );
		studentsAndTeachers.add( human3 );
		studentsAndTeachers.add( human4 );
		studentsAndTeachers.add( human5 );
		studentsAndTeachers.add( human6 );
		for(HumanImpl human:studentsAndTeachers ) {
			System.out.println(human.getSurname() + " - " + human.getAverageMark() + " - " + human.getType());
		}
		
		

	}

}
