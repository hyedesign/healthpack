package core;

public class test {


	public static void main(String[] args) {
		User u = new User();
		if(!u.lookupUser("testman", "testmanrules"))
			System.out.println("Failed lookup");
		System.out.println(u.userName);
		System.out.println(u.userFirstName);
	}
}
