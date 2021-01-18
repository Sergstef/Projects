package lab6.studentsAndTeachers.modificationStudentAndTeacher;

import java.util.Comparator;

import simpleOutOfTeacherAndStudent.Student;

public class HumanImpl extends Humon {
	String name;
	String surname;
	int averageMark;
	
	
	public HumanImpl(String name, String surname, int averageMark, String typeOfHuman) {
		this.setType(typeOfHuman);
		this.name = name;
		this.surname = surname;
		this.averageMark = averageMark;
	}

	public HumanImpl(String name, String surname, String typeOfHuman) {
		this.setType(typeOfHuman);
		this.name = name;
		this.surname = surname;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	@Override
	public boolean equals(Object human) {
		if(this.name.equals(((HumanImpl) human).getName())){
			return true;
		}
		else {return false;
				}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public static Comparator<HumanImpl> Mark = new Comparator<HumanImpl>() {
		public int compare(HumanImpl a, HumanImpl b) {
			if(a.averageMark > b.getAverageMark()) {
				return 1;
			}
			else if(a.averageMark < b.getAverageMark()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	};
	
	public static Comparator<HumanImpl> Name = new Comparator<HumanImpl>() {
		public int compare(HumanImpl a, HumanImpl b) {
			int result = a.getName().compareTo(b.getName());
			return result;
		}
	};
	
	public static Comparator<HumanImpl> Surname = new Comparator<HumanImpl>() {
		public int compare(HumanImpl a, HumanImpl b) {
			int result = a.getSurname().compareTo(b.getSurname());
			return result;
		}
	};
	

 	
	





	public int getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(int averageMark) {
		this.averageMark = averageMark;
	}}
