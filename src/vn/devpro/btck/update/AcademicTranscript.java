package vn.devpro.btck.update;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import vn.devpro.btck.studentsmanagement.StudentsManagementSystem;

public class AcademicTranscript {

	static Scanner sc =  new Scanner(System.in);
	
	static ArrayList<Mark> list = new ArrayList<Mark>();
	
	public static void update() {
		if(SubjectListManagement.getList().size() == 0 || StudentListManagement.getList().size() == 0) {
			System.out.println("\n\t-> The student list or the subject list is empty!");
			StudentListManagement.enterC();
			return;
		}
		
		do{
			System.out.println("\n\t\t-------------| Update Academic Transcript |-------------");
			System.out.println("\t\t1: Add a student's mark for a subject.");
			System.out.println("\t\t2: Edit a student's mark for a subject.");
			System.out.println("\t\t3: Delete a student's mark for a subject.");
			System.out.println("\t\t4: Show academic transcript ( simple ver ).");
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
		System.out.println("\n\t\t-------------| Add A Student's Mark For A Subject |-------------");
		
		int no = 1, size = 0;
		System.out.print("\t\t- The list of students you can select:\n");
		System.out.printf("\t\t%-5s %-15s %-30s \n", "N.O", "ID", "Full name");
		for(Student i : StudentListManagement.getList()) {
			int amount = countStID(i.getID());
			if(amount < SubjectListManagement.count()) {
				System.out.printf("\t\t%-5d %-15s %-30s \n", no++, i.getID(), i.getSurname() + " " + i.getName());
				size++;
			}
		}
		if(size == 0) {
			System.out.println("\n\t-> Empty! All students have enough marks already.");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.print("\n\t\t- Enter student ID: ");
		String stID = sc.nextLine();
		if(stID == "") {
			System.out.println("\n\t-> You must enter student ID!");
			StudentListManagement.enterC();
			return;
		}
		if(StudentListManagement.indexOfStudentList(stID) == -1) {
			System.out.println("\n\t-> This student ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		no = 1; size = 0;
		System.out.print("\n\t\t- The list of subjects you can select for this student:\n");
		System.out.printf("\t\t%-5s %-15s %-30s \n", "N.O", "ID", "Name");
		for(Subject i : SubjectListManagement.getList())
			if(index(stID, i.getId()) == -1) {
				System.out.printf("\t\t%-5d %-15s %-30s \n", no++, i.getId(), i.getName());
				size++;
			}
		if(size == 0) {
			System.out.println("\n\t-> Empty! This student have enough marks already.");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.print("\n\t\t- Enter subject ID: ");
		String sjID = sc.nextLine();
		if(sjID == "") {
			System.out.println("\n\t-> You must enter subject ID!");
			StudentListManagement.enterC();
			return;
		}
		if(SubjectListManagement.indexOfSubjectList(sjID) == -1) {
			System.out.println("\n\t-> This subject ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		
		if(index(stID, sjID) != -1) {
			System.out.println("\n\t-> This student already has a mark for this subject! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}

		double mark = -1;
		do {
			System.out.print("\t\t- Enter student's mark: ");
			try {
				mark = Double.parseDouble(sc.nextLine());
			}
			catch(NumberFormatException e) {
				e.getMessage();
			}	
					
			if(mark < 0 || mark > 10) {
				System.out.println("\n\t-> The student's mark for a subject must be between 0 and 10!");
				StudentListManagement.enterT();
			}
		} while(mark < 0 || mark > 10);
		
		list.add(new Mark(stID, sjID, mark));
		
		System.out.println("\n\t-> Successfully added!");
		StudentListManagement.enterC();
	}

	private static void edit() {
		if(list.size() == 0) {
			System.out.println("\n\t-> The academic transcript is empty!!");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.println("\n\t\t-------------| Edit A Student's Mark For A Subject |-------------");
		
		System.out.print("\t\t- Enter student ID which you want to edit: ");
		String stID = sc.nextLine();
		if(stID == "") {
			System.out.println("\n\t-> You must enter student ID!");
			StudentListManagement.enterC();
			return;
		}
		if(StudentListManagement.indexOfStudentList(stID) == -1) {
			System.out.println("\n\t-> This student ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.print("\t\t- Enter subject ID that you want to edit: ");
		String sjID = sc.nextLine();
		if(sjID == "") {
			System.out.println("\n\t-> You must enter subject ID!");
			StudentListManagement.enterC();
			return;
		}
		if(SubjectListManagement.indexOfSubjectList(sjID) == -1) {
			System.out.println("\n\t-> This subject ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		int index = index(stID, sjID);
		if(index == -1) {
			System.out.println("\n\t-> This student has not a mark for this subject! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}

		double mark = -1;
		do {
			System.out.print("\t\t- Enter student's mark: ");
			try {
				mark = Double.parseDouble(sc.nextLine());
			}
			catch(NumberFormatException e) {
				e.getMessage();
			}	
					
			if(mark < 0 || mark > 10) {
				System.out.println("\n\t-> The student's mark for a subject must be between 0 and 10!");
				StudentListManagement.enterT();
			}
		} while(mark < 0 || mark > 10);
		
		list.get(index).setMark(mark);
		
		System.out.println("\n\t-> Successfully edited!");
		StudentListManagement.enterC();
	}

	private static void delete() {
		if(list.size() == 0) {
			System.out.println("\n\t-> The academic transcript is empty!!");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.println("\n\t\t-------------| Delete A Student's Mark For A Subject |-------------");
		
		System.out.print("\t\t- Enter student ID which you want to delete: ");
		String stID = sc.nextLine();
		if(stID == "") {
			System.out.println("\n\t-> You must enter student ID!");
			StudentListManagement.enterC();
			return;
		}
		if(StudentListManagement.indexOfStudentList(stID) == -1) {
			System.out.println("\n\t-> This student ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.print("\t\t- Enter subject ID that you want to delete: ");
		String sjID = sc.nextLine();
		if(sjID == "") {
			System.out.println("\n\t-> You must enter subject ID!");
			StudentListManagement.enterC();
			return;
		}
		if(SubjectListManagement.indexOfSubjectList(sjID) == -1) {
			System.out.println("\n\t-> This subject ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		int index = index(stID, sjID);
		if(index == -1) {
			System.out.println("\n\t-> This student has not a mark for this subject! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}

		
		list.remove(index);
		
		System.out.println("\n\t-> Successfully Deleted!");
		StudentListManagement.enterC();
	}
	
	private static void display() {
		if(list.size() == 0) {
			System.out.println("\n\t-> The academic transcript is empty!!");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.println("\n\t\t\t-------------| Academic Transcript |-------------");
		
		int no = 1;
		System.out.printf("\n%-5s %15s %15s %15s \n","N.O", "Student ID", "Subject ID", "Student's mark");
		for(Student i : StudentListManagement.getList()) {
			int index = AcademicTranscript.indexStID(i.getID());
			if(index != -1)
				for(Mark j : list)
					if(j.getStudentID().compareToIgnoreCase(i.getID()) == 0)
						System.out.printf("%-5d %15s %15s %15.2f \n", no++, i.getID(), j.getSubjectID(), j.getMark());
		}
		
		StudentListManagement.enterC();
	}
	
	public static int index(String stID, String sjID) {
		int i;
		for(i = 0; i < list.size(); i++)
			if(list.get(i).getStudentID().compareToIgnoreCase(stID) == 0 
			&& list.get(i).getSubjectID().compareToIgnoreCase(sjID) == 0)
				return i;
		return -1;
	}
	
	public static int indexStID(String stID) {
		int i;
		for(i = 0; i < list.size(); i++)
			if(list.get(i).getStudentID().compareToIgnoreCase(stID) == 0)
				return i;
		return -1;
	}
	
	public static int indexSjID(String sjID) {
		int i;
		for(i = 0; i < list.size(); i++)
			if(list.get(i).getSubjectID().compareToIgnoreCase(sjID) == 0)
				return i;
		return -1;
	}
	
	public static double finalMark(String stID) {
		double ttM = 0, ttC = 0;
		for(Mark i : list)
			if(i.getStudentID().compareToIgnoreCase(stID) == 0) {
				ttM += i.getMark() * SubjectListManagement.subject(i.getSubjectID()).getCoefficient();
				ttC += SubjectListManagement.subject(i.getSubjectID()).getCoefficient();
			}
		return ttM / ttC;
	}

	public static double avengerMark(String sjID) {
		double ttM = 0;
		int amount = 0;
		for(Mark i : list)
			if(i.getSubjectID().compareToIgnoreCase(sjID) == 0) {
				ttM += i.getMark();
				amount++;
			}
		return ttM / amount;
	}
	
	public static ArrayList<Mark> getList() {
		return list;
	}

	public static void setList(ArrayList<Mark> list) {
		AcademicTranscript.list = list;
	}
	
	public static int countStID(String stID) {
		int amount = 0;
		for(Mark i : list)
			if(i.getStudentID().compareToIgnoreCase(stID) == 0)
				amount++;
		return amount;
	}
}
