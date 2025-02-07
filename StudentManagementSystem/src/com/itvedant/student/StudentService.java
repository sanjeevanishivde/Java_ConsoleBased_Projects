package com.itvedant.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentService {

	Connection con = DBConnect.getConnection();
	AtomicInteger counter = new AtomicInteger();
	
	public List<Student> getRecords(){
		
		List<Student> students = new ArrayList<Student>();
		
		//Static Query
		String query = "select * from student";
		
		try {
			
			Statement stats = con.createStatement();
			
			ResultSet rs = stats.executeQuery(query);
			
			while(rs.next()) {
				
				Student s = new Student();
				
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setMarks(rs.getFloat("marks"));
				
				students.add(s);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return students;
	}
	public boolean insertRecord(String name, int age, float marks) {
		
		String query = "insert into student values (?, ?, ?, ?)";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, counter.getAndIncrement());
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setFloat(4, marks);
			
			int i = ps.executeUpdate();
			
			if(i > 0) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
		return false;
	}
	
	public Student searchStudent(String name) {
		
		Student s = null;
		
		String query = " select * from student where name = ?";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				s = new Student();
				
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setMarks(rs.getFloat("marks"));
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return s;
	}
	
	public boolean updateRecord(Student s){
		
		String query = "update student set name = ?, age = ?, marks = ? where id = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setFloat(3, s.getMarks());
			ps.setInt(4, s.getId());
			
			int i = ps.executeUpdate();
			
			if(i > 0){
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean deleteRecord(int id) {
		
		String query = " delete from student where id = ?";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			int i = ps.executeUpdate();
			
			if(i > 0) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
