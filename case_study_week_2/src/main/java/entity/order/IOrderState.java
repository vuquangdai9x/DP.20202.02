package entity.order;

public interface IOrderState {
	public void SetContext(OrderTracker context);
	public void cancelOrder();
}
