package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
public class SessionInformation {
	private static SessionInformation instance;
	
	private SessionInformation() {

	};
	
	public static synchronized SessionInformation getInstance() {
		if (instance == null) {
			instance = new SessionInformation();
		}
		
		return instance;
	}
	
    public User mainUser;
    public Cart cartInstance = new Cart();
    public LocalDateTime expiredTime;

}
