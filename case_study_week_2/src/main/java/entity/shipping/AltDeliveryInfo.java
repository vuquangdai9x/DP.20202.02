package entity.shipping;

import entity.order.Order;

import org.example.AlternativeDistanceCalculator;

public class AltDeliveryInfo extends DeliveryInfo {
	protected float width;
    protected float height;
    protected float depth;
    protected float weight;
	protected AlternativeDistanceCalculator altDistanceCalculator;
	
	public AltDeliveryInfo(String name, String phone, String province, String address,
			String shippingInstructions, AlternativeDistanceCalculator altDistanceCalculator) {
		super(name, phone, province, address, shippingInstructions, null);
		this.altDistanceCalculator = altDistanceCalculator;
	}
	
	@Override
	public int calculateShippingFee(Order order) {
		// use alt calculator
        int distance = altDistanceCalculator.calculateDistance(address);
        return (int) (distance * 1.2);
    }

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getDepth() {
		return depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
}
