package com.mypack.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	private long empId;
	private String empName;
	private int empAge;
	private double empSalary;
	private String empPlace;

	public Employee() {

	}

	public Employee(long empId, String empName, int empAge, double empSalary, String empPlace) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.empSalary = empSalary;
		this.empPlace = empPlace;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	public String getEmpPlace() {
		return empPlace;
	}

	public void setEmpPlace(String empPlace) {
		this.empPlace = empPlace;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", empSalary=" + empSalary
				+ ", empPlace=" + empPlace + "]";
	}

}
