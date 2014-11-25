package testLogIn;

import java.util.Scanner;

public class LogIn {

	public static void main(String[]args){
	Scanner input = new Scanner(System.in);
	UserAuth userobj = new UserAuth();
	ObjectTranslator objTrans = new ObjectTranslator();

	System.out.println("client running");

	boolean userAutenticated = false;
	System.out.println("hello user,please login");

	while(!userAutenticated){
		System.out.println("user email:");
		userobj.setAuthUserEmail(input.next());
		System.out.println("user password");
		userObj.setAuthUserPassword(input.next());
		
		objTrans.sendObject(userObj);
		userObj= objTrans.recieveUSerAuth();
		
		if (userObj.isAuthenicated()==true){
			userAuthnticated = true;
			System.out.println("welcome to the sever");
		}else{
			System.out.println("something wrong");
			
		}
	}
	System.out.println("you have exited the loop");
			
		}
	}
	}
