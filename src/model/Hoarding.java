package model;

public class Hoarding {

	private int Height;
	private int Width;
	private boolean use;
	private String brand;
	public Hoarding(int height, int width, boolean use, String brand) {
		Height = height;
		Width = width;
		this.use = use;
		this.brand = brand;
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int height) {
		Height = height;
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
	public boolean isUse() {
		return use;
	}
	public void setUse(boolean use) {
		this.use = use;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
