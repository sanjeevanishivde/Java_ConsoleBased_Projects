package com.itvedant.student;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
		
		//Connection con = DBConnect.getConnection();

		//StudentService service = new StudentService();
		
		//List<Student> studs = service.getRecords();
		
		//System.out.println(studs);
		
		//DBConnect.closeConnection();
		
		Scanner sc = new Scanner(System.in);
		StudentService service = new StudentService();
		
		System.out.println("Welcome to the Student Management System!!!");
		
		System.out.println();
		
		while(true) {
			System.out.println();
			System.out.println("Kindly enter choice for operation you want to perform");
			System.out.println("1. Add Student");
			System.out.println("2. View All Student");
			System.out.println("3. Search Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Exit");
			
			int userInput = sc.nextInt();
			
			String name;
			int age;
			float marks;
			boolean b;
			
			if(userInput == 6) {
				System.out.println();
				System.out.println("Good Bye!!!");
				break;
			}else {
				switch(userInput) {
				
				case 1 : System.out.println("Enter Name : ");
				         name = sc.next();
				         
				         System.out.println("Enter Age : ");
				         age = sc.nextInt();
				         
				         System.out.println("Enter Marks : ");
				         marks = sc.nextFloat();
				         
				         b = service.insertRecord(name, age, marks);
				         
				         if(b) {
				        	 System.out.println("Data Inserted");
				         }else {
				        	 System.out.println("Insertion failed");
				         }
				         
				         break;
				         
				case 2 : List<Student> studs = service.getRecords();
						 studs.forEach(n -> System.out.println(n));
						 break;
						 
				case 3 : System.out.println("Enter Name : ");
							name = sc.next();
							
						Student s = service.searchStudent(name);
						System.out.println(s);
						break;
				
				case 4 : System.out.println("Enter the name of student you want to update");
				
							name = sc.next();
							
							s = service.searchStudent(name);
							
							System.out.println("Enter the choice you want to update");
							System.out.println("a. Name");
							System.out.println("b. Age");
							System.out.println("c. Marks");
							
							char c = sc.next().charAt(0);
							
							switch(c) {
							
							case 'a' : System.out.println("Enter Name : ");
										name = sc.next();
										s.setName(name);
										break;
										
							case 'b' : System.out.println("Enter Age : ");
										age = sc.nextInt();
										s.setAge(age);
										break;
							case 'c' : System.out.println("Enter Marks : ");
										marks = sc.nextFloat();
										s.setMarks(marks);
										break;
										
							default : System.out.println("Invalid Input");
							
							}
							
							b = service.updateRecord(s);
							
							if(b) {
								System.out.println("Student Data Updated");
							}else {
								System.out.println("Error");
							}
							
							break;
							
				case 5 : System.out.println("Enter the id of student you want to delete");
							int id = sc.nextInt();
							
							b = service.deleteRecord(id);
							
							if(b) {
								System.out.println("Student data deleted");
							}else {
								System.out.println("Error");
							}
							break;
						 
				default : System.out.println("Invalid Input");
				
				}
			}
		}
	}

}
