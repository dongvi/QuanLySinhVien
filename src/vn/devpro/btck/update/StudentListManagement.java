package vn.devpro.btck.update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.btck.studentsmanagement.StudentsManagementSystem;

public class StudentListManagement {
	
	static Scanner sc =  new Scanner(System.in);
	
	static ArrayList<Student> list = new ArrayList<Student>();
	
	public static void update() {
		do{
			System.out.println("\n\t\t-------------| Update Student List |-------------");
			System.out.println("\t\t1: Add a new student.");
			System.out.println("\t\t2: Edit student information.");
			System.out.println("\t\t3: Delete a student.");
			System.out.println("\t\t4: Show student list.");
			System.out.println("\t\t0: Reback.");
			System.out.print("\t\t- Enter your selection: ");
			int chon = -1;
			try {
				chon = Integer.parseInt(sc.nextLine());
			}
			catch(NumberFormatException error) {
				error.getMessage();
			}
			
			switch(chon) {
			case 1: add(); break;
			case 2: edit(); break;
			case 3: delete(); break;
			case 4: display(); break;
			case 0: return;
			default:
				StudentsManagementSystem.wrongSelection();
			}
		} while(true);
	}

	private static void add() {
		System.out.println("\n\t\t-------------| Add A New Student |-------------");
		
		System.out.print("\t\t- Enter student ID: ");
		String id = sc.nextLine();
		
		if(id == "") {
			System.out.println("\n\t-> You must enter student ID!");
			enterC();
			return;
		}
		
		if(indexOfStudentList(id) != -1) {
			System.out.println("\n\t-> This student ID already exist! Please check and try againt!");
			enterC();
			return;
		}
		
		System.out.print("\t\t- Enter surname: ");
		String surname = sc.nextLine();
		System.out.print("\t\t- Enter name: ");
		String name = sc.nextLine();
		
		int year = -1;
		while(year <= 0) {
			System.out.print("\t\t- Enter year of birth: ");
			try {
				year = Integer.parseInt(sc.nextLine());
			}
			catch(NumberFormatException error) {
				error.getMessage();
			}
			if(year <= 0) {
				System.out.println("\n\t-> The year of birth must be a positive integer!");
				enterT();
			}
		}
		
		System.out.print("\t\t- Enter gender: ");
		String gender = sc.nextLine();
		
		list.add(new Student(id, surname, name, year, gender));
		
		System.out.println("\n\t-> Successfully added!");
		enterC();
	}

	private static void edit() {
		if(list.size() == 0) {
			emptyList();
			return;
		}
		
		System.out.println("\n\t\t-------------| Edit Student Information |-------------");
		
		System.out.print("\t\t- Enter student ID which you want to edit: ");
		String id = sc.nextLine();
		
		if(id == "") {
			System.out.println("\n\t-> You must enter student ID!");
			enterC();
			return;
		}
		
		int index = indexOfStudentList(id);
		
		if(index == -1) {
			System.out.println("\n\t-> This student ID does not exist! Please check and try againt!");
			enterC();
			return;
		}
		
		do {
			System.out.println("\n\t\t- Select information that you want to edit.");
			System.out.println("\t\t1: Edit surname.");
			System.out.println("\t\t2: Edit name.");
			System.out.println("\t\t3: Edit year of birth.");
			System.out.println("\t\t4: Edit gender.");
			System.out.println("\t\t0: Finish editing.");
			System.out.print("\t\t- Enter your selection: ");
			int chon = -1;
			try {
				chon = Integer.parseInt(sc.nextLine());
			}
			catch(NumberFormatException error) {
				error.getMessage();
			}
			
			switch(chon) {
			case 1: 
				System.out.print("\t\t- Enter new surname: ");
				String surname = sc.nextLine();
				
				if(surname == "") {
					System.out.println("\n\t-> You must enter surname of the student! Edit failed!");
					enterC();
				}
				else {
					list.get(index).setSurname(surname);
					
					System.out.println("\n\t-> Successfully edited!");
					enterC();
				}
				break;
			case 2: 
				System.out.print("\t\t- Enter new name: ");
				String name = sc.nextLine();
				
				if(name == "") {
					System.out.println("\n\t-> You must enter name of the student! Edit failed!");
					enterC();
				}
				else {
					list.get(index).setName(name);
					
					System.out.println("\n\t-> Successfully edited!");
					enterC();
				}
				break;
			case 3: 
				int year = -1;
				System.out.print("\t\t- Enter new year of birth: ");
				try {
					year = Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException error) {
					error.getMessage();
				}
				if(year <= 0) {
					System.out.println("\n\t-> The year of birth must be a positive integer! Edit failed!");
					enterC();
					break;
				}
				
				list.get(index).setYearOfBirth(year);
				
				System.out.println("\n\t-> Successfully edited!");
				enterC();
				break;
			case 4: 
				System.out.print("\t\t- Enter new gender: ");
				String gender = sc.nextLine();
				
				if(gender == "") {
					System.out.println("\n\t-> You must enter gender of the student! Edit failed!");
					enterC();
				}
				else {
					list.get(index).setGender(gender);
					
					System.out.println("\n\t-> Successfully edited!");
					enterC();
				}
				break;
			case 0: return;
			default:
				StudentsManagementSystem.wrongSelection();
			}
			
		} while(true);
	}

	private static void delete() {
		if(list.size() == 0) {
			emptyList();
			return;
		}
		
		System.out.println("\n\t\t-------------| Delete A Student |-------------");
		
		System.out.print("\t\t- Enter student ID which you want to delete: ");
		String id = sc.nextLine();
		
		if(id == "") {
			System.out.println("\n\t-> You must enter student ID!");
			enterC();
			return;
		}
		
		int index = indexOfStudentList(id);
		if(index == -1) {
			System.out.println("\n\t-> This student ID does not exist! Please check and try againt!");
			enterC();
			return;
		}
		
		if(AcademicTranscript.indexStID(id) == -1) {
			list.remove(index);
			System.out.println("\n\t-> Successfully deleted!");
			enterC();
		}
		else {
			System.out.println("\n\t-> This student already has a mark for at least a subject! Delete failed!");
			enterC();
		}
	}

	private static void display() {
		if(list.size() == 0) {
			emptyList();
			return;
		}
		
		sortByName();
		
		System.out.println("\n\t\t-------------| Student List |-------------");
		
		int no = 1;
		System.out.printf("%-5s %-15s %-30s %15s %10s \n", "N.O", "ID", "Full name", "Year of birth", "Gender");
		for(Student i : list)
			i.display(no++);
		
		enterC();
	}
	
	public static int indexOfStudentList(String id) {
		int i;
		for(i = 0; i < list.size(); i++)
			if(list.get(i).getID().compareToIgnoreCase(id) == 0)
				return i;
		return -1;
	}
	
	public static void sortByName() {
		Collections.sort(list, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
	}
	public static void sortByID() {
		Collections.sort(list, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s1.getID().compareToIgnoreCase(s2.getID());
			}
		});
	}
	
	public static void enterC() {
		System.out.println("\n\t- Enter to continue!");
		sc.nextLine();
	}
	
	public static void enterT() {
		System.out.println("\n\t- Enter to try againt!");
		sc.nextLine();
	}
	
	public static void emptyList() {
		System.out.println("\n\t-> The list is empty! Please add a new student to use this function!");
		enterC();
	}

	public static ArrayList<Student> getList() {
		return list;
	}

	public static void setList(ArrayList<Student> list) {
		StudentListManagement.list = list;
	}
	
	public static Student student(String ID) {
		for(Student i : list)
			if(i.getID().compareToIgnoreCase(ID) == 0)
				return i;
		return null;
	}
}
