package bean;

public class Employee
{
	String name;
	String designation;
	int salary;
	String email;
	String technology;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
	
		// no argument constructor
		public Employee() {

		}

		// parameterize Constructor

		public Employee(String name, String designation, int salary, String email, String technology) {

			this.name = name;
			this.designation = designation;
			this.salary = salary;
			this.email = email;
			this.technology = technology;
		}

}
