package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import utils.Utils;
import utils.Hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Le Minh Duc
 * SOLID: Vi pham nguyen ly Single Responsibility Principle
 * Class thuc hien nhieu hon mot nhiem vu
 */

/**
 * @author
 */
public class AuthenticationController extends BaseController {

    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }

    public User getMainUser() throws ExpiredSessionException {
        if (validateUser()) {
            logout();
            throw new ExpiredSessionException();
        } else return SessionInformation.getInstance().mainUser.cloneInformation();
    }

    private boolean validateUser() {
    	return SessionInformation.getInstance().mainUser == null || 
    			SessionInformation.getInstance().expiredTime == null || 
    			SessionInformation.getInstance().expiredTime.isBefore(LocalDateTime.now());
    }
    
/**
 * Le Minh Duc
 * SOLID: Vi pham nguyen ly  Dependency Inversion Principle
 * Vi class muc cao la AuthenticationController phu thuoc truc tiep vao class muc thap la UserDAO
 */
    public void login(String email, String password) throws Exception {
        try {
        	Hash hash = new Hash();
        	
            User user = new UserDAO().authenticate(email, hash.md5(password));
            if (Objects.isNull(user)) throw new FailLoginException();
            SessionInformation.getInstance().mainUser = user;
            SessionInformation.getInstance().expiredTime = LocalDateTime.now().plusHours(24);
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    public void logout() {
        SessionInformation.getInstance().mainUser = null;
        SessionInformation.getInstance().expiredTime = null;
    }

    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
    /**
	 * <p>Vu Quang Dai</p>
	 * <p>Coicidental Cohesion</p>
	 * <p>md5 encryption is not functional relate to Authentication. This method should be place in an utility class</p>
	 */
//    private String md5(String message) {
//        String digest = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
//            // converting byte array to Hexadecimal String
//            StringBuilder sb = new StringBuilder(2 * hash.length);
//            for (byte b : hash) {
//                sb.append(String.format("%02x", b & 0xff));
//            }
//            digest = sb.toString();
//        } catch (NoSuchAlgorithmException ex) {
//            Utils.getLogger(Utils.class.getName());
//            digest = "";
//        }
//        return digest;
//    }

}
