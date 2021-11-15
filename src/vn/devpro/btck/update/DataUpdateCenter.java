package vn.devpro.btck.update;

import java.util.Scanner;

import vn.devpro.btck.studentsmanagement.StudentsManagementSystem;

public class DataUpdateCenter {
	
	public static void update() {
		do{
			System.out.println("\n\t\t-------------| Data Update Center |-------------");
			System.out.println("\t\t1: Update student list.");
			System.out.println("\t\t2: Update subject list.");
			System.out.println("\t\t3: Update academic transcript.");
			System.out.println("\t\t0: Reback.");
			System.out.print("\t\t- Enter your selection: ");
			int chon = -1;
			try {
				chon = Integer.parseInt(new Scanner(System.in).nextLine());
			}
			catch(NumberFormatException error) {
				error.getMessage();
			}
			
			switch(chon) {
			case 1: StudentListManagement.update(); break;
			case 2: SubjectListManagement.update(); break;
			case 3: AcademicTranscript.update(); break;
			case 0: return;
			default:
				StudentsManagementSystem.wrongSelection();
			}
		} while(true);
	}
}
