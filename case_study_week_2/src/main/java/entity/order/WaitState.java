package entity.order;

import common.exception.CancelOrderNotAllowException;

public class WaitState implements IOrderState {
private OrderTracker context;
	
	@Override
	public void SetContext(OrderTracker context) {
		this.context = context;
	}

	@Override
	public void cancelOrder() {
		context.SetState(new CanceledState());
	}
}
