package bean;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.lang.Thread.State;
import java.sql.*;

public class TestEmployee
{
	public static void main(String[] args) 
	{
		
		System.out.println("----------------Company----------------");
		Scanner sc1= new Scanner(System.in);
		
		System.out.println();
		System.out.println("----------------Employee----------------");
		System.out.println("Enter 1=Add Employee 2=Show all Id 3=Show all Employee Data");
		int num=sc1.nextInt();
		
		switch (num) {
		case 1:
			
				TestEmployee te1=new TestEmployee();
				te1.addEmployee();
			
					
			break;
		case 2:
			TestEmployee te2=new TestEmployee();
			te2.showById();
			break;
		case 3:
			try 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/company","root","root");
				
				Statement st = conn.createStatement();
				String query = "select * from employee";
				ResultSet rs = st.executeQuery(query);
				
				
				HashMap<Integer, Employee> map = new HashMap<>();
				
				while(rs.next())
				{
					int id= rs.getInt("id");
					String name=rs.getString("name");
					String desig = rs.getString("designation");
					int salary = rs.getInt("salary");
					String email =rs.getString("email");
					String tech =rs.getString("technology");
					
					Employee emp=new Employee();
					
					emp.setName(name);
					emp.setDesignation(desig);
					emp.setSalary(salary);
					emp.setEmail(email);
					emp.setTechnology(tech);

					//employee Added to Map
					
					map.put(id, emp);
					
				}
				
				Set<Integer> keysOfMap = map.keySet();
				
				//Fetch Data using foreach loop--------
				for(Integer key : keysOfMap)
				{
					
					Employee value = map.get(key);
					System.out.println(" ID : "+key+" Name: "+value.getName()+" Designation: "+value.getDesignation()+"\n"+" Salary: "+value.getSalary()+" Email: "
							+value.getEmail()+"\n"+" Technology: "+value.getTechnology()+" Anual Salary: "+value.getSalary()*12);
					System.out.println();
					
				}
	
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
			break;
			

		default:
			break;
		}
		
	}
	
	public void addEmployee() {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Employee Id:");
		int emp_id = sc.nextInt();
		System.out.print("Enter Employee Name:");
		String emp_name =sc.next();
		System.out.print("Enter Employee Designation:");
		String emp_desig = sc.next();
		System.out.print("Enter Employee Salary:");
		int emp_sal = sc.nextInt();
		System.out.print("Enter Employee Email:");
		String emp_email= sc.next();
		System.out.print("Enter Employee Technology:");
		String emp_tech = sc.next();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//create database company;
			//use company;
			//create table employee(id int(100) primary key,name varchar(100),designation varchar(100),salary int(100),email varchar(100) unique key,technology varchar(100));
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/company","root","root");
			
			PreparedStatement pts = conn.prepareStatement("insert into employee values(?,?,?,?,?,?)");
			
			pts.setInt(1, emp_id);
			pts.setString(2, emp_name);
			pts.setString(3, emp_desig);
			pts.setInt(4, emp_sal);
			pts.setString(5, emp_email);
			pts.setString(6, emp_tech);
			
			int s =pts.executeUpdate();
			
			if(s!=0) {
				System.out.println("-------------------------------------");
				System.out.println("Employee Added To Company");
				addEmployee();
			}
			else {
				System.out.println("Employee Not Added");
			}
			conn.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void showById() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/company","root","root");
			
			Statement st = conn.createStatement();
			String query = "select * from employee";
			ResultSet rs = st.executeQuery(query);
			
			HashMap<Integer, Employee> map = new HashMap<>();
			
			while(rs.next())
			{
				int id= rs.getInt("id");
				String name=rs.getString("name");
				String desig = rs.getString("designation");
				int salary = rs.getInt("salary");
				String email =rs.getString("email");
				String tech =rs.getString("technology");
				
				//Object of Employee Class
				
				Employee emp=new Employee();
				
				//By using Setter we store the Data
				emp.setName(name);
				emp.setDesignation(desig);
				emp.setSalary(salary);
				emp.setEmail(email);
				emp.setTechnology(tech);
				
				//employee Added to Map
				
				map.put(id, emp);
			}
			
			Set<Integer> keysOfMap = map.keySet();
			
			//Fetch Data using Foreach loop--------
			for(Integer key : keysOfMap)
			{
				System.out.print("Id :"+key+" ");
				
			}
			
			Scanner sc3= new Scanner(System.in);
			System.out.println();
			System.out.println("Enter Employee Id");
			int empID = sc3.nextInt();
			
			Employee value = map.get(empID);
			
			System.out.println(" ID : "+empID+" Name: "+value.getName()+" Designation: "+value.getDesignation()+"\n"+" Salary: "+value.getSalary()+" Email: "
			+value.getEmail()+"\n"+" Technology: "+value.getTechnology()+" Anual Salary: "+value.getSalary()*12);
			System.out.println();
			
			conn.close();
			
			showById();
			
			
		} catch (Exception e2) 
		{
			System.out.println("Enter a Valid ID");
			System.out.println();
			showById();
			
		}
	}

}
