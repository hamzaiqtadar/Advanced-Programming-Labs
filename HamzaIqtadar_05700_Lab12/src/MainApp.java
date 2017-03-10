import java.util.Scanner;

import attendance.ui.AttendanceSystem;

public class MainApp {
public static void main(String[] args)
{
	AttendanceSystem obj=new AttendanceSystem();
	App obj2=new App();
	Scanner s=new Scanner(System.in);
	System.out.println("Enter 1 for attendance System and 2 for gradebook");
	int input=s.nextInt();
	switch(input)
	{
		case 1:
			obj.main(args);
			break;
		case 2:
			obj2.main(args);
			break;
	}
}
}
