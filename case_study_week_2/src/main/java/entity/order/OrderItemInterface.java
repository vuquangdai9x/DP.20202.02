package entity.order;

import entity.media.Media;

public interface OrderItemInterface {
	public Media getMedia();

    public void setMedia(Media media);

    public int getQuantity();

    public void setQuantity(int quantity);

    public int getPrice();

    public void setPrice(int price);
}
