package com.mypack.hibernate;

import java.util.List;
import java.util.Scanner;
//import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mypack.hibernate.config.HibernateConfig;
import com.mypack.hibernate.entity.Employee;
import com.mypack.hibernate.service.EmployeeService;
//import com.mypack.hibernate.utility.EmployeeUtility;

public class EmployeeController {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char ch;
		do {
			System.out.println("press 1 for save employee Data...");
			System.out.println("press 2 to get employee Data by Id...");
			System.out.println("press 3 to delete employee Data using Primary Key..");
			System.out.println("press 4 to update employee Data using Primary Key");
			System.out.println("press 5 to excecute Query select * from employee;");
			System.out.println("press 6 to excecute Query select * from employee where empId>=5 and empid<=10; ");
			System.out.println("press 7 get employee data where name equal to user input data");
			System.out.println("press 8 get employee data using like condition");
			System.out.println("press 9 to display only employee age");
			System.out.println("press 10 to get specific number of columns from Employee Table");
			System.out.println();
			System.out.println(
					"***********Below are chioce to get Aggregation functionality using CriteriaBuilder***********");
			System.out.println();
			System.out.println("press 11 to get Total Salary of Employee");
			System.out.println("press 12 to count total number of Employee");
			System.out.println("press 13 to display employee having min salary ");

			System.out.println("***************************************");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				System.out.println("************************************");
				Employee createEmployeeData = EmployeeService.createEmployeeData(scanner);
				Session session = HibernateConfig.createSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				try {
					if (createEmployeeData != null) {
						session.save(createEmployeeData);
						transaction.commit();
						System.out.println("Save Employee Data successfully...");
					} else {
						System.out.println("not Saved");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					session.close();
				}

				break;
			}
			case 2: {

				System.out.println("*************************************");
				System.out.println("Enter employeeId...");
				long empId = scanner.nextLong();
				Employee employee = EmployeeService.getEmployeeDataById(empId);
				if (employee != null) {
					System.out.println(employee);
				} else {
					System.out.println("Not found Employee with Id = " + empId);
				}

				break;
			}
			case 3: {
				System.out.println("*************************************");
				System.out.println("Enter employee id to delete...");
				long empId = scanner.nextLong();
				boolean isDeleted = EmployeeService.deleteEmployeeById(empId);
				if (isDeleted) {
					System.out.println("Employee with Id = " + empId + " is deleted..");
				} else {
					System.out.println("Emplyoee not found...");
				}

				break;
			}
			case 4: {
				System.out.println("**************************************");
				System.out.println("Enter EMployee Id to update Data....");
				long empId = scanner.nextLong();
				boolean isUpdated = EmployeeService.updateEmployeeData(empId,scanner);
				if (isUpdated) {
					System.out.println("Updated Employee with empId = " + empId);
				} else {
					System.out.println("Not found employee data with Id  = " + empId);
				}

				break;
			}
			case 5: {
				System.out.println("**************************************");
				List<Employee> allEmployeeData = EmployeeService.getALlEmployeeData();
				if (!allEmployeeData.isEmpty()) {
					for (Employee employee : allEmployeeData) {
						System.out.println(employee);
					}

				} else {
					System.out.println("Not found record...");
				}

				break;
			}
			case 6: {
				List<Employee> filterEmployeeData = EmployeeService.getDataWhereEmpIdGE5andLE10();
				if (filterEmployeeData == null || filterEmployeeData.isEmpty()) {
					System.out.println("Not filtered");
					System.out.println(filterEmployeeData);

				}

				else {
					System.out.println("***************");
					for (Employee employee : filterEmployeeData) {
						System.out.println(employee);

					}
				}

				break;
			}
			case 7: {
				System.out.println("Select * from employee where empName = userInput");
				System.out.println("******************");
				System.out.println("Enter employee name to fetch data");
				String empName = scanner.next();
				List<Employee> empDataByName = EmployeeService.getEmpDataByName(empName);
				if (empDataByName != null) {
					empDataByName.forEach(System.out::println);
				} else {
					System.out.println("Record Not Found with given Input...");
				}

				break;
			}

			case 8: {
				System.out.println("select * from employee where empName like 'R%'");
				List<Employee> empDataUsingLikeCondition = EmployeeService.getEmpDataUsingLikeCondition();
				if (empDataUsingLikeCondition != null) {

					empDataUsingLikeCondition.forEach(System.out::println);

				} else {
					System.out.println("Not found record");
				}

				break;
			}
			case 9: {
				System.out.println("Sql Query = Select empAge from employee ");
				List<Integer> allEMpAge = EmployeeService.getAllEmployeeAge();
				if (!(allEMpAge == null)) {
					for (Integer integer : allEMpAge) {
						System.out.println(integer);

					}
				}

				else {
					System.out.println("Not execute...");
				}

				break;
			}
			case 10: {
				System.out.println("Select empId, empName,empSalary from Employee..");
				List<Object[]> specificPropertiesFromEmpolyee = EmployeeService.getSpecificPropertiesFromEmployee();
				if (!(specificPropertiesFromEmpolyee == null)) {
					for (Object[] objects : specificPropertiesFromEmpolyee) {
						long empId = (long) objects[0];
						String empName = (String) objects[1];
						double empSalary = (double) objects[2];

						System.out
								.println("EmpId = " + empId + "  EmpName = " + empName + "   EmpSalary = " + empSalary);

					}

				} else {
					System.out.println("Record not Fetch....");
				}
				break;
			}
			case 11: {
				List<Double> sumOfEmployeeSalary = EmployeeService.getSumOfEmployeeSalary();
				if (!(sumOfEmployeeSalary == null)) {
					sumOfEmployeeSalary.forEach(System.out::println);
				} else {
					System.out.println("UnEable to fetch record...");
				}
				break;
			}

			case 12: {
				List<Long> totalEmployes = EmployeeService.getTotalEmployes();
				if (!(totalEmployes == null)) {
					System.out.println(totalEmployes);
				} else {
					System.out.println("UnEable to fetch Data");
				}
				break;
			}
			case 13: {
				List<Double> employeeMinSalary = EmployeeService.getEmployeeMinSalary();
				if (!(employeeMinSalary == null)) {
					System.out.println(employeeMinSalary);
				} else {
					System.out.println("UnEable to fetch Data");
				}
				break;
			}


			default:
				break;
			}
			System.out.println("Do you want to continue : y/n");
			ch = scanner.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("Program terminitted...");

	}

}
