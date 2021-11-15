package vn.devpro.btck.search;

import java.util.Scanner;

import vn.devpro.btck.studentsmanagement.StudentsManagementSystem;
import vn.devpro.btck.update.AcademicTranscript;
import vn.devpro.btck.update.Mark;
import vn.devpro.btck.update.Student;
import vn.devpro.btck.update.StudentListManagement;
import vn.devpro.btck.update.SubjectListManagement;

public class Search {
	
	static Scanner sc =  new Scanner(System.in);

	public static void search() {
		if(SubjectListManagement.getList().size() == 0 && StudentListManagement.getList().size() == 0) {
			System.out.println("\n\t-> No data!");
			StudentListManagement.enterC();
			return;
		}
		
		do{
			System.out.println("\n\t\t-------------| Search |-------------");
			System.out.println("\t\t1: Search information about a student.");
			System.out.println("\t\t2: Search information about a subject.");
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
			case 1:
				System.out.println("\n\t\t-------------| Search Information About A Student |-------------");
				searchSt();
				break;
			case 2: 
				System.out.println("\n\t\t-------------| Search Information About A Subject |-------------");
				searchSj(); 
				break;
			case 0: return;
			default:
				StudentsManagementSystem.wrongSelection();
			}
		} while(true);
	}

	public static void searchSt() {
		System.out.print("\n\t\t- Enter student ID you want to know details about: ");
		String id = sc.nextLine();
		
		if(id == "") {
			System.out.println("\n\t-> You must enter student ID!");
			StudentListManagement.enterC();
			return;
		}
		if(StudentListManagement.indexOfStudentList(id) == -1) {
			System.out.println("\n\t-> This student ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.println("\n\t\t\t-------------| Result |-------------");
		displaySt(id);
		
		StudentListManagement.enterC();
	}
	
	public static void displaySt(String id) {
		System.out.printf("\n\tStudent ID : %s. \n\tFull name: %s.",id, StudentListManagement.student(id).getSurname() + " " + StudentListManagement.student(id).getName());
		if(AcademicTranscript.indexStID(id) == -1) {
			System.out.println("\n\t-> This student has not stydied any subject!");
			return;
		}
		
		int no = 1;
		System.out.println("\n\t- Subject list of this student:");
		System.out.printf("\t%-5s %-15s %-30s %15s \n","N.O", "ID", "Subject name", "Student's mark");
		for(Mark j : AcademicTranscript.getList())
			if(j.getStudentID().compareToIgnoreCase(id) == 0)
				System.out.printf("\t%-5d %-15s %-30s %15.2f \n",
				no++, j.getSubjectID(), SubjectListManagement.subject(j.getSubjectID()).getName(), j.getMark(), " ");
		System.out.printf("\t- Final mark: %.2f.\n", AcademicTranscript.finalMark(id));				
	}

	public static void searchSj() {
		System.out.print("\n\t\t- Enter subject ID you want to know details about: ");
		String id = sc.nextLine();
		
		if(id == "") {
			System.out.println("\n\t-> You must enter subject ID!");
			StudentListManagement.enterC();
			return;
		}
		if(SubjectListManagement.indexOfSubjectList(id) == -1) {
			System.out.println("\n\t-> This subject ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.println("\n\t\t\t-------------| Result |-------------");
		displaySj(id);
		
		StudentListManagement.enterC();
	}

	public static void displaySj(String id) {
		System.out.printf("\n\tSubject ID : %s. \n\tName: %s.", id, SubjectListManagement.subject(id).getName());
		
		if(AcademicTranscript.indexSjID(id) == -1) {
			System.out.println("\n\t-> No students have taken this subject yet!");
			StudentListManagement.enterC();
			return;
		}
		
		int no = 1;
		System.out.println("\n\t- Student list of this subject:");
		System.out.printf("\t%-5s %-15s %-30s %15s \n","N.O", "ID", "Full name", "Student's mark");
		for(Mark j : AcademicTranscript.getList())
			if(j.getSubjectID().compareToIgnoreCase(id) == 0)
				System.out.printf("\t%-5d %-15s %-30s %15.2f \n", no++,j.getStudentID(), 
				StudentListManagement.student(j.getStudentID()).getSurname() + " " + 
				StudentListManagement.student(j.getStudentID()).getName(), j.getMark());
		
		System.out.printf("\t- Average marks of this subject: %.2f.\n", AcademicTranscript.avengerMark(id));
	}
}
