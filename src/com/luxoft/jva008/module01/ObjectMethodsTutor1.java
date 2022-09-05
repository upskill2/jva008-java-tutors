package com.luxoft.jva008.module01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


/**
 * 1) It is necessary to implement hashCode () and equals ()
 * �  In the EmployeeByAge, EmployeeByFullName
 * 2) Implement hashCode () and equals () for the Employee class,
 * �  Which will compare all fields (name, surname, age).
 */
public class ObjectMethodsTutor1 {
	public Employee createEmployee(EmployeeType type, String name, String surname, int age) {
		switch (type) {
			case ByName:
				return new EmployeeByName(name, surname, age);
			case ByAge:
				return new EmployeeByAge(name, surname, age);
			case ByFullName:
				return new EmployeeByFullName(name, surname, age);
			case Default:
				break;
			default:
				break;
		}
		return new Employee(name, surname, age);
	}

	public List<Employee> getEmployees(EmployeeType type) {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(createEmployee(type, "Ivan", "Ivanov", 35));
		employees.add(createEmployee(type, "Ivan", "Ivanov", 30));
		employees.add(createEmployee(type, "Ivan", "Petrov", 25));
		employees.add(createEmployee(type, "Ivan", "Sidorov", 25));
		return employees;
	}

	@Test
	public void testEmployees() {
		List<Employee> employees = getEmployees(EmployeeType.Default);
		int index = 0;
		for (Employee employee : employees) {
			log("emp" + index++ + "=" + employee);
		}

		log("***Testing equals: EmployeeByName***");
		employees = getEmployees(EmployeeType.ByName);
		assertEquals(employees.get(0), employees.get(1));
		assertEquals(employees.get(1), employees.get(2));
		assertEquals(employees.get(2), employees.get(3));
		for (int i = 0; i < 3; i++) {
			log("emp" + i + ".equals(emp" + (i + 1) + ")=" +
					employees.get(i).equals(employees.get(i + 1)));
		}

		log("***Testing equals: EmployeeByAge***");
		employees = getEmployees(EmployeeType.ByAge);
		assertNotSame(employees.get(0), employees.get(1));
		assertNotSame(employees.get(1), employees.get(2));
		assertEquals(employees.get(2), employees.get(3));
		for (int i = 0; i < 3; i++) {
			log("emp" + i + ".equals(emp" + (i + 1) + ")=" +
					employees.get(i).equals(employees.get(i + 1)));
		}

		log("***Testing equals: EmployeeByFullName***");
		employees = getEmployees(EmployeeType.ByFullName);
		assertEquals(employees.get(0), employees.get(1));
		assertNotSame(employees.get(1), employees.get(2));
		assertNotSame(employees.get(2), employees.get(3));
		for (int i = 0; i < 3; i++) {
			log("emp" + i + ".equals(emp" + (i + 1) + ")=" +
					employees.get(i).equals(employees.get(i + 1)));
		}
	}

	public Set<Employee> getEmployeesSet(EmployeeType type) {
		Set<Employee> employeesSet = new HashSet<Employee>();
		employeesSet.addAll(getEmployees(type));
		return employeesSet;
	}

	@Test
	public void testEmployeesSet() {
		Set<Employee> employeesSet;
		log("***Testing HashSet: EmployeeByName");
		employeesSet = getEmployeesSet(EmployeeType.ByName);
		for (Employee employee : employeesSet) {
			log(employee);
		}
		assertEquals(employeesSet.size(), 1);

		log("***Testing HashSet: EmployeeByAge");
		employeesSet = getEmployeesSet(EmployeeType.ByAge);
		for (Employee employee : employeesSet) {
			log(employee);
		}
		assertEquals(employeesSet.size(), 3);

		log("***Testing HashSet: EmployeeByFullName");
		employeesSet = getEmployeesSet(EmployeeType.ByFullName);
		for (Employee employee : employeesSet) {
			log(employee);
		}
		assertEquals(employeesSet.size(), 3);

		log("***Testing HashSet: the hashCode and equals by default");
		employeesSet = getEmployeesSet(EmployeeType.Default);
		for (Employee employee : employeesSet) {
			log(employee);
		}
		assertEquals(employeesSet.size(), 4);

	}

	enum EmployeeType {
		ByName, ByFullName, ByAge, Default
	}

	class Employee {
		public String name;
		public String surname;
		public int age;

		public Employee(String name, String surname, int age) {
			this.name = name;
			this.surname = surname;
			this.age = age;
		}

		public String toString() {
			return this.name + " " + this.surname + ", Age:" + age;
		}
	}

	class EmployeeByName extends Employee {
		public EmployeeByName(String name, String surname, int age) {
			super(name, surname, age);
		}

		public int hashCode() {
			return this.name.hashCode();
		}

		public boolean equals(Object obj) {
			if (obj instanceof Employee) {
				Employee emp = (Employee) obj;
				return emp.name.equals(this.name);
			}
			return super.equals(obj);
		}
	}

	class EmployeeByFullName extends Employee {
		public EmployeeByFullName(String name, String surname, int age) {
			super(name, surname, age);
		}

		public String getFullName() {
			return this.name + " " + this.surname;
		}

		public int hashCode() {
			return getFullName().hashCode();
		}

		public boolean equals(Object obj) {
			if (obj instanceof EmployeeByFullName) {
				EmployeeByFullName emp = (EmployeeByFullName) obj;
				return emp.getFullName().equals(this.getFullName());
			}
			return super.equals(obj);
		}
	}

	class EmployeeByAge extends Employee {
		public EmployeeByAge(String name, String surname, int age) {
			super(name, surname, age);
		}

		public int hashCode() {
			return this.age;
		}

		public boolean equals(Object obj) {
			if (obj instanceof Employee) {
				Employee emp = (Employee) obj;
				return emp.age == this.age;
			}
			return super.equals(obj);
		}
	}

}
