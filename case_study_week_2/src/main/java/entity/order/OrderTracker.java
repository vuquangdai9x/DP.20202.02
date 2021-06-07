package entity.order;

import common.exception.CancelOrderNotAllowException;

public class OrderTracker {
	private Order order;
	private IOrderState currentState;
	
	public Order getOrder() {
		return order;
	}

	public OrderTracker(Order order, IOrderState currentState) {
		super();
		this.order = order;
		this.currentState = currentState;
	}

	public void SetState(IOrderState state) {
		currentState = state;
	}
	
	public void cancelOrder() throws CancelOrderNotAllowException{
		currentState.cancelOrder();
	}
}
