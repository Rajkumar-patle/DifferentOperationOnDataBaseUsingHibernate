package com.mypack.hibernate.utility;

import java.util.Scanner;

import com.mypack.hibernate.entity.Employee;

public class EmployeeUtility {

	public static Employee createEmployeeData(Scanner scanner) {

		System.out.println("Enter Employee Id..");
		long empId = scanner.nextLong();
		System.out.println("Enter Employee Name..");
		String empName = scanner.next();
		System.out.println("Enter Employee Age..");
		int empAge = scanner.nextInt();
		System.out.println("Enter Employee Salary..");
		double empSalary = scanner.nextDouble();
		System.out.println("Enter Employee Place..");
		String empPlace = scanner.next();
		Employee employee = new Employee(empId, empName, empAge, empSalary, empPlace);
		return employee;
	}

}
