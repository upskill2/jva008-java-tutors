package com.luxoft.jva008.module06;

import static com.luxoft.jva008.Logger.log;
import static com.luxoft.jva008.module06.ClassSpyTutor.printMembers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;
import org.mockito.internal.util.reflection.Fields;

public class ReflectionTutor {
	final static String introspectClass = "com.luxoft.jva008.module01.NumbersTutor";

	@Test
	public void testReflection() {
		try {
			// TODO: load ExampleClass at runtime by name
			Class aClass = Class.forName(introspectClass);
			
			// TODO: show all constructors (use method showConstructors())
				showConstructors(aClass);

			// TODO: list all methods, return types and arguments
			Method[] methods = aClass.getMethods();

			printMembers(methods,"List of all methods");
		//	System.out.println("methods: " +  Arrays.toString(methods));
			
			// TODO: list all fields and types of the class
			Field[] fields = aClass.getDeclaredFields();
			System.out.println(aClass.getCanonicalName());

			for (Field field: fields) {
				Class<?> fieldType = field.getType();
				System.out.println();
				System.out.println("Field name " + field.getName());
				System.out.println("Field type " + fieldType.getName());
			}

			// TODO: call the printIt() method

				Class<ExampleClass> s = ExampleClass.class;

			try{
				s.getDeclaredConstructor().newInstance().printIt();
			} catch (InstantiationException e){
				e.printStackTrace();
			}

			// TODO: call the printItString() method, pass a String param
			try{
				s.getDeclaredConstructor().newInstance().printItString("temp");
			} catch (InstantiationException e){
				e.printStackTrace();
			}

			// TODO: call the printItInt() method, pass a int param
			try{
				s.getDeclaredConstructor().newInstance().printItInt(25);
			} catch (InstantiationException e){
				e.printStackTrace();
			}

			// TODO: call the setCounter() method, pass a int param
			try{
				s.getDeclaredConstructor().newInstance().setCounter(25);
			} catch (InstantiationException e){
				e.printStackTrace();
			}


			// TODO: call the printCounter() method

			try{
				s.getDeclaredConstructor().newInstance().printItInt(25);
			} catch (InstantiationException e){
				e.printStackTrace();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void showConstructors(Class<String> clazz) {
		for (Constructor<?> constr: clazz.getConstructors()) {
			StringBuilder sb = new StringBuilder();
			for (Class<?> param: constr.getParameterTypes()) {
				if (sb.length() > 0) { 
					sb.append(", ");
				}
				sb.append(param.getSimpleName());
			}
			sb.insert(0, "constructor: " + constr.getName() + "(");
			sb.append(")");
			log(sb.toString());
		}
		log("SuperClass: " + clazz.getSuperclass().getSimpleName());
	}
	@Test
	public void testShowConstructors() {
		System.out.println("------------------");
		showConstructors(java.lang.String.class);
	}

}

