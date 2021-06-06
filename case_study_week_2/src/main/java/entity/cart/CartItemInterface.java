package entity.cart;

import entity.media.Media;

public interface CartItemInterface {
	public Media getMedia();

    public void setMedia(Media media);

    public int getQuantity();

    public void setQuantity(int quantity);

    public int getPrice();

    public void setPrice(int price);
}
