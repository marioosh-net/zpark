package test;

import java.util.List;
import dao.*;

public class HibernateTest {
	
	public static void main(String[] args) {
		try {
			User u = new User("ala","pass");

			if(new UserDAO().checkUser(u)) {
				System.out.println("OK");				
			} else {
				System.out.println("Not OK");				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
