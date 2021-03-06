package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderItem;
import entity.shipping.AltDeliveryInfo;
import entity.shipping.DeliveryInfo;
import entity.shipping.ShippingConfigs;

import org.example.AlternativeDistanceCalculator;
import org.example.DistanceCalculator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.Validate;

/**
 * Le Minh Duc
 * Procedural  Cohesion
 * Cac method createOrder, createOrder, placeOrder trong class duoc nhom lai voi nhau vi chung duoc thuc hien theo trinh tu
 */

/**
 * Le Minh Duc
 * SOLID: Vi pham nguyen ly Single Responsibility Principle
 * PlaceOrderController extends BaseController nhung 2 method của BaseController khong lien quan đến PlaceOrderController
 */

/**
 * Le Minh Duc
 * SOLID: Vi pham nguyen ly Liskov Substitution Principle
 * Class thuc hien nhieu hon mot nhiem vu
 */

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());


    //Nguyen Dinh Duc. Common Coupling. Truy cập đến SessionInformation.getInstance().cartInstance thuộc lớp SessionInformation.getInstance()
    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException {
        SessionInformation.getInstance().cartInstance.checkAvailabilityOfProduct();
    }


    //Nguyen Dinh Duc. Common Coupling. Truy cập đến SessionInformation.getInstance().cartInstance thuộc lớp SessionInformation.getInstance()
    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
        return new Order(SessionInformation.getInstance().cartInstance);
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }


    // Nguyen Dinh Duc. Concidental cohesion. Tinh chi phi chua duoc su dung o class nay
    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public DeliveryInfo processDeliveryInfo(HashMap info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
		LOGGER.info("Process Delivery Info");
		LOGGER.info(info.toString());
		
		Validate validate = new Validate();
		
		validate.validateDeliveryInfo(info);
                
        DeliveryInfo deliveryInfo = new DeliveryInfo(
                String.valueOf(info.get("name")),
                String.valueOf(info.get("phone")),
                String.valueOf(info.get("province")),
                String.valueOf(info.get("address")),
                String.valueOf(info.get("instructions")),
                new DistanceCalculator());
        
        // if using new distance calculator, use the code below
//        AltDeliveryInfo deliveryInfo = new AltDeliveryInfo(
//                String.valueOf(info.get("name")),
//                String.valueOf(info.get("phone")),
//                String.valueOf(info.get("province")),
//                String.valueOf(info.get("address")),
//                String.valueOf(info.get("instructions")),
//                new AlternativeDistanceCalculator());
//        deliveryInfo.setDepth(1);
//        deliveryInfo.setHeight(2);
//        deliveryInfo.setWeight(3);
//        deliveryInfo.setWeight(4);
        
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
    }
}
