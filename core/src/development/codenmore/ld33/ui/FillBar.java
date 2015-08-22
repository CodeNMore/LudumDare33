package development.codenmore.ld33.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld33.assets.Assets;

public class FillBar {
	
	//For rendering
	private static TextureRegion texture = Assets.getRegion("color");
	private Color backColor, color;
	//For filling
	private float x, y;
	private int width, height;
	private float fill, maxFill;
	
	public FillBar(float x, float y, int width, int height, float fill,
			float maxFill, Color backColor, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fill = fill;
		this.maxFill = maxFill;
		this.backColor = backColor;
		this.color = color;
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(backColor);
		batch.draw(texture, x, y, width, height);
		batch.setColor(color);
		batch.draw(texture, x, y, width * (fill / maxFill), height);
		batch.setColor(Color.WHITE);
	}
	
	//GETTERS SETTERS

	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getFill() {
		return fill;
	}

	public void setFill(float fill) {
		this.fill = fill;
	}

	public float getMaxFill() {
		return maxFill;
	}

	public void setMaxFill(float maxFill) {
		this.maxFill = maxFill;
	}

}
