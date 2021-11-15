package vn.devpro.btck.academictranscriptdisplay;

import java.util.Scanner;

import vn.devpro.btck.search.Search;
import vn.devpro.btck.studentsmanagement.StudentsManagementSystem;
import vn.devpro.btck.update.AcademicTranscript;
import vn.devpro.btck.update.Mark;
import vn.devpro.btck.update.Student;
import vn.devpro.btck.update.StudentListManagement;
import vn.devpro.btck.update.Subject;
import vn.devpro.btck.update.SubjectListManagement;

public class ATdisplay {

	static Scanner sc =  new Scanner(System.in);
	
	public static void display() {
		if(AcademicTranscript.getList().size() == 0) {
			System.out.println("\n\t-> The academic transcript is empty!");
			StudentListManagement.enterC();
			return;
		}
		
		do{
			System.out.println("\n\t\t-------------| Academic Transcript Display |-------------");
			System.out.println("\t\t1: Academic Transcript (displayed per student).");
			System.out.println("\t\t2: Academic Transcript (displayed per subject).");
			System.out.println("\t\t3: Academic Transcript (new).");
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
			case 1: display_1(); break;
			case 2: display_2(); break;
			case 3: display_new(); break;
			case 0: return;
			default:
				StudentsManagementSystem.wrongSelection();
			}
		} while(true);
	}

	private static void display_1() {
		System.out.println("\n\t\t\t-------------| Academic Transcript |-------------");
		
		StudentListManagement.sortByID();
		for(Student i : StudentListManagement.getList())
			if(AcademicTranscript.indexStID(i.getID()) != -1)
				Search.displaySt(i.getID());
						
		StudentListManagement.enterC();
	}

	private static void display_2() {
		System.out.println("\n\t\t\t-------------| Academic Transcript |-------------");

		for(Subject i : SubjectListManagement.getList()) 
			if(AcademicTranscript.indexSjID(i.getId()) != -1) 
				Search.displaySj(i.getId());
		
		StudentListManagement.enterC();
	}
	
	public static void display_new() {
		System.out.println("\n\t\t\t-------------| Information |-------------");

		int no = 1;
		System.out.printf("\n%-5s %-15s %-30s %30s \n", "N.O", "Student ID", "Full name", "Number of subjects studied");
		for(Student i : StudentListManagement.getList())
			System.out.printf("%-5d %-15s %-30s %30d \n", no++, i.getID(), i.getSurname() + " " + i.getName(), AcademicTranscript.countStID(i.getID()));
		
		Search.searchSt();
	}
}
