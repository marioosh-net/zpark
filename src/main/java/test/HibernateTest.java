package test;

import java.util.List;
import dao.*;

public class HibernateTest {
	
	public static void main(String[] args) {
		try {
			User2 u = new User2("ala","pass");

			if(new User2DAO().checkUser(u)) {
				System.out.println("OK");				
			} else {
				System.out.println("Not OK");				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
