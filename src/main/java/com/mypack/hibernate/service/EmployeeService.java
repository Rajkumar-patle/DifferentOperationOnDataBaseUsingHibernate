package com.mypack.hibernate.service;

import java.util.List;
import java.util.Scanner;
//import java.util.stream.Stream;


import com.mypack.hibernate.dao.EmployeeDao;
import com.mypack.hibernate.entity.Employee;
import com.mypack.hibernate.utility.EmployeeUtility;

public class EmployeeService {

	public static Employee createEmployeeData(Scanner scanner) {
		return EmployeeUtility.createEmployeeData(scanner);

	}

	public static Employee getEmployeeDataById(long empId) {
		return EmployeeDao.getEmployeeDataByID(empId);

	}

	public static boolean deleteEmployeeById(long empId) {
		return EmployeeDao.deleteEmployeeById(empId);

	}

	public static boolean updateEmployeeData(long empId,Scanner scanner) {
		return EmployeeDao.updateEmployeeData(empId,scanner);

	}

	public static List<Employee> getALlEmployeeData() {
		return EmployeeDao.getAllEmployeeData();

	}

	public static List<Employee> getDataWhereEmpIdGE5andLE10() {
		return EmployeeDao.getDataWhereEmpIdGE5andLE10();

	}

	public static List<Employee> getEmpDataByName(String empName) {
		return EmployeeDao.getEmpDataByName(empName);

	}

	public static List<Employee> getEmpDataUsingLikeCondition() {
		return EmployeeDao.getEmpDataUsingLikeCondition();

	}

	public static List<Integer> getAllEmployeeAge() {
		return EmployeeDao.getAllEmployeeAge();

	}

	public static List<Object[]> getSpecificPropertiesFromEmployee() {
		return EmployeeDao.getSpecificPropertiesFromEmployee();
		
	}

	public static List<Double> getSumOfEmployeeSalary() {
		return EmployeeDao.getSumOfEmployeeSalary();
		
	}

	public static List<Long> getTotalEmployes() {
		return EmployeeDao.getTotalEmployes();
		
	}

	public static List<Double> getEmployeeMinSalary() {
		return EmployeeDao.getEmployeeMinSalary();
		
	}

}
