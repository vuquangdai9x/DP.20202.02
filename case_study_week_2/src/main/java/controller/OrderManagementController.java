package controller;

import common.exception.CancelOrderNotAllowException;
import entity.order.OrderTracker;

public class OrderManagementController extends BaseController {
	
	public void cancelOrder(OrderTracker orderTracker) {
		try {
			orderTracker.cancelOrder();
		} catch (CancelOrderNotAllowException e) {
			e.printStackTrace();
		}
	}
}
