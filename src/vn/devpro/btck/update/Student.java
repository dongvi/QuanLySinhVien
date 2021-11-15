package vn.devpro.btck.update;

public class Student {
	
	private String id, surname, name, gender;
	private int yearOfBirth;
	
	public void display(int no) {
		System.out.printf("%-5d %-15s %-30s %15d %10s \n", no, id, surname + " " + name, yearOfBirth, gender);
	}
	
	public Student() {}
	public Student(String id, String surname, String name, int yearOfBirth, String gender) {
		this.id = id;
		this.yearOfBirth = yearOfBirth;
		this.surname = surname;
		this.name = name;
		this.gender = gender;
	}
	
	
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
