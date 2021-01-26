package Start;

import java.util.*;
/**
 * 
 * @author alyssadayton
 * @version 1.0
 */
public class part1 {

	private Date datecreated;

	public static void main(String[] args) {

		Student[] students = new Student[100];

		int user = 0;

		user = prompt();

		while (user != 1 && user != 2) {
			user = prompt();
		}
		student(user);
		user = prompt();
	}
	public Date getDateCreated(){
		return datecreated;
	}
	/* 
	 * Method for the initial prompt
	 * @user (int, is the different options to pick)
	 */
	public static int prompt() {

		Scanner input = new Scanner(System.in);

		int user = 0;

		while (user != 1 && user != 2) {
			System.out.println("What option would you like?");
			System.out.println("1) Enter Student/s names");
			System.out.println("2) Log in");
			user = input.nextInt();
		}

		return user;
	}
	/*
	 * Method for second prompt
	 * @number (int, is for the different options to pick)
	 */
	public static int option() {

		Scanner input = new Scanner(System.in);

		int number = 0;

		System.out.println("Which option would you like: ");
		System.out.println("1) Notes ");
		System.out.println("2) Current Assignment ");
		System.out.println("3) Future Assignment ");
		number = input.nextInt();

		return number;
	}
	/*
	 * Method for storing students
	 */
	public static void student(int user) {

		Scanner input = new Scanner(System.in);
		int index = 0;
		int number = 0;
		String nullstring = null;

		System.out.println("Enter the number of students desired: ");
		int num = Integer.parseInt(input.nextLine());

		String[] student = new String[num];
		String[] Notes = new String[100];
		String[] current = new String[100];
		String[] future = new String[100];

		for(int i = 0; i < student.length; i++) {
			System.out.println("Enter the student " + (i + 1) + " name: ");
			student[i] = input.nextLine();
		}
		while(true) {
			for(int i = 0; i < student.length; i++) {
				System.out.println("Which student would you like? ");
				input.nextLine();
				student[i] = input.nextLine();

				number = option();

				if (number == 1) { 
					System.out.println("1) See previous notes: ");
					System.out.println("2) Enter new notes: ");
					int newNotes = input.nextInt();

					if (newNotes == 1) {
						System.out.println("Notes for: " + student[i]);
						for(int k = 0; k < Notes.length; k++) {
							if(Notes[k] != null) {
								System.out.println(Notes[k]);
							}
						}
					}
					else if (newNotes == 2) {
						char yes = 'y';
						while (yes == 'y') {
							System.out.println("Enter notes: ");
							input.nextLine();
							Notes[index] = input.nextLine();

							System.out.println("Do you want to enter any more notes: (y or n): ");
							yes = input.next().charAt(0);
							index++;
						}
					}
				}
				else if (number == 2) {
					System.out.println("1) See current assignment information: ");
					System.out.println("2) Enter new current assignment information");
					int newcurrent = input.nextInt();	

					if(newcurrent == 1) {
						System.out.println("Current assigmnent for " + student[i] + " : ");
						for(int k = 0; k < Notes.length; k++) {
							if(current[k] != null) {
								System.out.println(current[k]);
							}
						}
					}
					if(newcurrent == 2) {
						char yes = 'y';
						while (yes == 'y') {
							System.out.println("Enter assignment: ");
							input.nextLine();
							current[index] = input.nextLine();

							System.out.println("Do you want to enter anymore: (y or n): ");
							yes = input.next().charAt(0);
							index++;
						}
					}
				}

				else if (number == 3) {
					System.out.println("1) See future assignment information: ");
					System.out.println("2) Enter Future assignment information: ");
					int newfuture = input.nextInt();	

					if(newfuture == 1) {
						System.out.println("Future assignment for " + student[i] + " : ");
						for(int k = 0; k < future.length; k++) {
							if(future[k] != null) {
								System.out.println(future[k]);
							}
						}
					}
					if (newfuture == 2) {
						char yes = 'y';
						while (yes == 'y') {
							System.out.println("Enter assignment: ");
							input.nextLine();
							future[index] = input.nextLine();

							System.out.println("Do you want to enter anymore: (y or n): ");
							yes = input.next().charAt(0);
							index++;
						}
					}
				}
			}
		}
	}
}


