package com.mypack.hibernate.dao;

import java.util.List;
//import java.util.stream.Stream;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mypack.hibernate.config.HibernateConfig;
import com.mypack.hibernate.entity.Employee;

public class EmployeeDao {

	public static Employee getEmployeeDataByID(long empId) {
		Employee employee = null;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {
			employee = session.get(Employee.class, empId);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return employee;
	}

	public static boolean deleteEmployeeById(long empId) {
		Employee employee = getEmployeeDataByID(empId);
		boolean isDeleted = false;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {
			if (employee != null) {
				session.delete(employee);
				isDeleted = true;
			} else {
				System.out.println("Employee not found with id = " + empId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	public static boolean updateEmployeeData(long empId,Scanner scanner) {
		boolean isUpdated = false;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {
			Transaction transaction = session.beginTransaction();

			Employee employee = session.get(Employee.class, empId);
			// System.out.println(employee);
			if (employee != null) {
				System.out.println("Enter empName...");
				String empName = scanner.next();
				employee.setEmpName(empName);
				System.out.println("Enter empSalary...");
				double empSalary = scanner.nextDouble();
				employee.setEmpSalary(empSalary);
				System.out.println("Enter empPlace...");
				String empPlace = scanner.next();
				employee.setEmpPlace(empPlace);
				
				session.update(employee);
				transaction.commit();
				isUpdated = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return isUpdated;
	}

	public static List<Employee> getAllEmployeeData() {

		List<Employee> empList = null;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> createQuery = criteriaBuilder.createQuery(Employee.class);
			// Employee.class give all column data, if you want specific column
			// If you want only empName column data in place all column data
			// then provide String.class in place Employee.class as empName is String type
			Root<Employee> root = createQuery.from(Employee.class);
			createQuery.select(root);
			Query<Employee> query = session.createQuery(createQuery);
			empList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empList;
	}

	public static List<Employee> getDataWhereEmpIdGE5andLE10() {
		List<Employee> filterEmpList = null;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> createQuery = criteriaBuilder.createQuery(Employee.class);

			Root<Employee> root = createQuery.from(Employee.class);

			createQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.ge(root.get("empId"), 102),
					criteriaBuilder.le(root.get("empId"), 105)));

			Query<Employee> query = session.createQuery(createQuery);
			filterEmpList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterEmpList;
	}

	public static List<Employee> getEmpDataByName(String empName) {

		List<Employee> empList = null;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> root = cq.from(Employee.class);
			cq.select(root).where(cb.equal(root.get("empName"), empName));
			Query<Employee> query = session.createQuery(cq);
			empList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empList;
	}

	public static List<Employee> getEmpDataUsingLikeCondition() {
		List<Employee> resultList = null;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class); // here createQuery method is JPA specific
																			// javax.persistence
			Root<Employee> root = cq.from(Employee.class);
			cq.select(root).where(cb.like(root.get("empName"), "R%"));
			Query<Employee> query = session.createQuery(cq); // here createQuery method is hibernate specific
																// org.hibernate
			resultList = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public static List<Integer> getAllEmployeeAge() {

		List<Integer> allEmpAge = null;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
			Root<Employee> root = cq.from(Employee.class);
			cq.select(root.get("empAge"));
			Query<Integer> query = session.createQuery(cq);
			allEmpAge = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allEmpAge;
	}

	public static List<Object[]> getSpecificPropertiesFromEmployee() {
		List<Object[]> resultList = null;
		try (Session session = HibernateConfig.createSessionFactory().openSession();) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> createQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Employee> root = createQuery.from(Employee.class);
			createQuery.multiselect(root.get("empId"), root.get("empName"), root.get("empSalary"));
			Query<Object[]> query = session.createQuery(createQuery);
			resultList = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	public static List<Double> getSumOfEmployeeSalary() {
		List<Double> sumOfSalary = null;
		try(Session session = HibernateConfig.createSessionFactory().openSession();){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Double> createQuery = criteriaBuilder.createQuery(Double.class);
			Root<Employee> root = createQuery.from(Employee.class);
			createQuery.select(criteriaBuilder.sum(root.get("empSalary")));
			Query<Double> query = session.createQuery(createQuery);
			sumOfSalary = query.list();
			
		}
		return sumOfSalary;
	}

	public static List<Long> getTotalEmployes() {
		List<Long> totalCount = null;
		try(Session session = HibernateConfig.createSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Long> createQuery = criteriaBuilder.createQuery(Long.class);
			Root<Employee> root = createQuery.from(Employee.class);
			createQuery.select(criteriaBuilder.count(root));
			Query<Long> query = session.createQuery(createQuery);	
			totalCount = query.list();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
		
	}

	public static List<Double>  getEmployeeMinSalary() {
		List<Double> minSalary = null;
		try(Session session = HibernateConfig.createSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Double> createQuery = criteriaBuilder.createQuery(Double.class);
			Root<Employee> root = createQuery.from(Employee.class);
			createQuery.select(criteriaBuilder.min(root.get("empSalary")));
			Query<Double> query = session.createQuery(createQuery);
			minSalary = query.list();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return minSalary;
	}

}
