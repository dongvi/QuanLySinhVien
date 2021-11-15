package vn.devpro.btck.update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.btck.studentsmanagement.StudentsManagementSystem;

public class SubjectListManagement {

	static Scanner sc =  new Scanner(System.in);
	
	static ArrayList<Subject> list = new ArrayList<Subject>();
	
	public static void update() {
		do{
			System.out.println("\n\t\t-------------| Update Subject List |-------------");
			System.out.println("\t\t1: Add a new subject.");
			System.out.println("\t\t2: Edit subject information.");
			System.out.println("\t\t3: Delete a subject.");
			System.out.println("\t\t4: Show subject list.");
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
		System.out.println("\n\t\t-------------| Add A New Subject |-------------");
		
		System.out.print("\t\t- Enter subject ID: ");
		String id = sc.nextLine();
		
		if(id == "") {
			System.out.println("\n\t-> You must enter subject ID!");
			StudentListManagement.enterC();
			return;
		}
		
		if(indexOfSubjectList(id) != -1) {
			System.out.println("\n\t-> This subject ID already exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		System.out.print("\t\t- Enter name: ");
		String name = sc.nextLine();
		
		double coe = -1;
		while(coe <= 0) {
			System.out.print("\t\t- Enter coefficient: ");
			try {
				coe = Double.parseDouble(sc.nextLine());
			}
			catch(NumberFormatException error) {
				error.getMessage();
			}
			
			if(coe <= 0) {
				System.out.println("\n\t-> The coefficient must be a positive number!");
				StudentListManagement.enterT();
			}
		}
		
		list.add(new Subject(id, name, coe));
		
		System.out.println("\n\t-> Successfully added!");
		StudentListManagement.enterC();
	}

	private static void edit() {
		if(list.size() == 0) {
			StudentListManagement.emptyList();
			return;
		}
		
		System.out.println("\n\t\t-------------| Edit Subject Information |-------------");
		
		System.out.print("\t\t- Enter subject id which you want to edit: ");
		String id = sc.nextLine();
		
		if(id == "") {
			System.out.println("\n\t-> You must enter subject ID!");
			StudentListManagement.enterC();
			return;
		}
		
		int index = indexOfSubjectList(id);
		
		if(index == -1) {
			System.out.println("\n\t-> This subject ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		do {
			System.out.println("\n\t\t- Select information that you want to edit.");
			System.out.println("\t\t1: Edit name.");
			System.out.println("\t\t2: Edit coefficient.");
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
				System.out.print("\t\t- Enter new name: ");
				String name = sc.nextLine();
				
				if(name == "") {
					System.out.println("\n\t-> You must enter name of the subject! Edit failed!");
					StudentListManagement.enterC();
				}
				else {
					list.get(index).setName(name);
					
					System.out.println("\n\t-> Successfully edited!");
					StudentListManagement.enterC();
				}
				break;
			case 2: 
				double coe = -1;
				System.out.print("\t\t- Enter new ceofficient: ");
				try {
					coe = Double.parseDouble(sc.nextLine());
				}
				catch(NumberFormatException error) {
					error.getMessage();
				}
				
				if(coe <= 0) {
					System.out.println("\n\t-> The coefficient must be a positive number! Edit failed!");
					StudentListManagement.enterC();
					break;
				}
				
				list.get(index).setCoefficient(coe);
				
				System.out.println("\n\t-> Successfully edited!");
				StudentListManagement.enterC();
				break;
			case 0: return;
			default:
				StudentsManagementSystem.wrongSelection();
			}
		} while(true);
	}

	private static void delete() {
		if(list.size() == 0) {
			StudentListManagement.emptyList();
			return;
		}
		
		System.out.println("\n\t\t-------------| Delete A Subject |-------------");
		
		System.out.print("\t\t- Enter subject ID which you want to delete: ");
		String id = sc.nextLine();
		
		if(id == "") {
			System.out.println("\n\t-> You must enter subject ID!");
			StudentListManagement.enterC();
			return;
		}
		int index = indexOfSubjectList(id);
		if(index == -1) {
			System.out.println("\n\t-> This subject ID does not exist! Please check and try againt!");
			StudentListManagement.enterC();
			return;
		}
		
		if(AcademicTranscript.indexSjID(id) == -1) {
			list.remove(index);
			System.out.println("\n\t-> Successfully deleted!");
			StudentListManagement.enterC();
		}
		else {
			System.out.println("\n\t-> This subject already has a mark for at least a student! Delete failed!");
			StudentListManagement.enterC();
		}
	}

	private static void display() {
		if(list.size() == 0) {
			StudentListManagement.emptyList();
			return;
		}
		
		sortByName();
		
		System.out.println("\n\t\t-------------| Subject List |-------------");
		
		int no = 1;
		System.out.printf("%-5s %-15s %-30s %15s \n", "N.O", "ID", "Name", "Coefficient");
		for(Subject i : list)
			i.display(no++);
		
		StudentListManagement.enterC();
	}
	
	public static int indexOfSubjectList(String id) {
		int i;
		for(i = 0; i < list.size(); i++)
			if(list.get(i).getId().compareToIgnoreCase(id) == 0)
				return i;
		return -1;
	}
	
	public static void sortByName() {
		Collections.sort(list, new Comparator<Subject>() {
			@Override
			public int compare(Subject s1, Subject s2) {
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
	}
	
	public static Subject subject(String sjID) {
		for(Subject i : list)
			if(i.getId().compareToIgnoreCase(sjID) == 0)
				return i;
		return null;
	}

	public static ArrayList<Subject> getList() {
		return list;
	}

	public static void setList(ArrayList<Subject> list) {
		SubjectListManagement.list = list;
	}
	
	public static int count() {
		int amount = 0;
		for(Subject i : list)
			amount++;
		return amount;
	}
}
