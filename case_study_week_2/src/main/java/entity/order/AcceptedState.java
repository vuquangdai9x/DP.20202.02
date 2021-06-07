package entity.order;

import common.exception.CancelOrderNotAllowException;

public class AcceptedState implements IOrderState {
	private OrderTracker context;
	
	@Override
	public void SetContext(OrderTracker context) {
		this.context = context;
	}

	@Override
	public void cancelOrder() throws CancelOrderNotAllowException{
		throw new CancelOrderNotAllowException();
	}
}