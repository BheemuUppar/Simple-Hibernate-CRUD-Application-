package com.hibernate;



import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainClass {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Configuration configuration = new Configuration().configure();

		configuration.addAnnotatedClass(com.hibernate.Student.class);

		StandardServiceRegistryBuilder builder =new  StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());

		SessionFactory factory = configuration.buildSessionFactory(builder.build());

		Session session =  factory.openSession();

		Transaction transaction =  session.beginTransaction();

		//		Student s1 = new Student(1, "Tom", 70);

		//		session.save(s1);
//		transaction.commit();


		int choice = 0; 



		while(choice != 5) {
			System.out.println("=========");

			System.out.println("1. Display Data \n"
					+ "2. Insert Data \n"
					+ "3. Update Marks \n"
					+ "4. Delete Data \n"
					+ "5. Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1: 
			{
				System.out.println("Enter Roll Number");
				int rollNo = sc.nextInt();
				Student s = session.get(com.hibernate.Student.class, rollNo);
				System.out.println(s);
					break;
			}
			case 2: 
			{
				System.out.println("Enter Roll Number");
				int rollNo = sc.nextInt();  sc.nextLine();
				System.out.println("Enter Student Name");

				String name  = sc.nextLine();
				System.out.println("Enter Marks");

				int marks  = sc.nextInt();

				Student s = new Student(rollNo, name, marks);

				session.save(s);
				transaction.commit();
				System.out.println("Data inserted Successfully");
				
				break;

			}
			case 4: {
				System.out.println("Enter Roll Number");
				int rollNo = sc.nextInt();
				Student s = session.get(com.hibernate.Student.class, rollNo);
				session.delete(s);
				transaction.commit();
				System.out.println("data deleted..!!");
				break;
			}
			case 3:{
				System.out.println("Enter Roll Number");
				int rollNo = sc.nextInt();
				Student s = session.get(com.hibernate.Student.class, rollNo);
					System.out.println("Enter marks to Update");
				s.setMarks(sc.nextInt());
				transaction.commit();
				System.out.println("marks updated..!!!");
				break;

			}
			
			}
			System.out.println("=========");
		}
		System.out.println("Loop exited ");
		sc.close();
		
		session.close();
		
	}




}
