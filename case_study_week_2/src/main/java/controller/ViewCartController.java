package controller;

import java.sql.SQLException;

import entity.cart.Cart;

/**
 * Le Minh Duc
 * Procedural  Cohesion
 * Cac method checkAvailabilityOfProduct, getCartSubtotal trong class duoc nhom lai voi nhau vi chung su dung chung du lieu la SessionInformation
 */

/**
 * This class controls the flow of events when users view the Cart
 * @author nguyenlm
 */
public class ViewCartController extends BaseController{
    
    /**
     * This method checks the available products in Cart
     * @throws SQLException
     */
    public void checkAvailabilityOfProduct() throws SQLException{
        SessionInformation.cartInstance.checkAvailabilityOfProduct();
    }

    /**
     * This method calculates the cart subtotal
     * @return subtotal
     */
    public int getCartSubtotal(){
        int subtotal = SessionInformation.cartInstance.calSubtotal();
        return subtotal;
    }

}
