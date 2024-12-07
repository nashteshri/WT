package in.kitcoek.crud.model;

public class Student {
	private int prn;
	private String name;
	private String branch;
	public Student(int prn, String name, String branch) {
		this.prn = prn;
		this.name = name;
		this.branch = branch;
	}
	public int getPrn() {
		return prn;
	}
	public void setPrn(int prn) {
		this.prn = prn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
}
