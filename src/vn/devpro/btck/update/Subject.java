package vn.devpro.btck.update;

public class Subject {
	
	private String id, name;
	private double coefficient;
	
	public void display(int no) {
		System.out.printf("%-5d %-15s %-30s %15.2f \n", no, id, name, coefficient);
	}
	
	public Subject() {}
	public Subject(String id, String name, double coefficient) {
		this.id = id;
		this.name = name;
		this.coefficient = coefficient;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
}
