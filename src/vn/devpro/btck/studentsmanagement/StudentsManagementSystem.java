package vn.devpro.btck.studentsmanagement;

import java.util.Scanner;

import vn.devpro.btck.academictranscriptdisplay.ATdisplay;
import vn.devpro.btck.search.Search;
import vn.devpro.btck.update.DataUpdateCenter;



public class StudentsManagementSystem {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		do{
			System.out.println("\n======================={ WELCOME TO THE STUDENTS MANAGEMENT SYSTEM PROGRAM!!! }=======================");
			System.out.println("\n\t\t-------------| Main Menu |-------------");
			System.out.println("\t\t1: Data update center.");
			System.out.println("\t\t2: Show academic transcript.");
			System.out.println("\t\t3: Search.");
			System.out.println("\t\t4: About us.");
			System.out.println("\t\t0: Exit.");
			System.out.print("\t\t- Enter your selection: ");
			int chon = -1;
			try {
				chon = Integer.parseInt(sc.nextLine());
			}
			catch(NumberFormatException error) {
				error.getMessage();
			}
			
			switch(chon) {
			case 1: DataUpdateCenter.update(); break;
			case 2: ATdisplay.display(); break;
			case 3: Search.search(); break;
			case 4: author(); break;
			case 0: 
				System.out.println("\n\t-> Thank you for using our product! See you later.");
				System.exit(0);
			default:
				wrongSelection();
			}
		} while(true);
	}

	public static void wrongSelection() {
		System.out.println("\n\t-> Your selection is wrong! Please check and try againt.");
		System.out.println("\n\t- Enter to continue!");
		sc.nextLine();
	}
	
	private static void author() {
		System.out.println("\n\t\t**********************************| Author |**********************************");
		System.out.println("\t\t- Author name: Vi Ngoc Dong ( VND coporation :) ).");
		System.out.println("\t\t- Engsub by VND.");
		System.out.println("\t\t- If you have any problems while using, please contact me with hotline: 0338987383.");
		System.out.println("\t\t- Thank you for reading. ^^");
		System.out.println("-------------------------------- +++++++++++++++++++++++++++++ --------------------------------");
		System.out.println("\n\t- Enter to continue!");
		sc.nextLine();
	}
}
