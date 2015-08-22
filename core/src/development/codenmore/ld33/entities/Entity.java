package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld33.Main;
import development.codenmore.ld33.entities.components.Component;

public class Entity {
	
	//Basic entity info
	protected float x, y;
	protected int width, height;
	private boolean remove = false;
	private TextureRegion texture;
	//Components
	private Array<Component> components;
	
	public Entity(float x, float y, int width, int height, TextureRegion texture){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
		components = new Array<Component>();
	}
	
	public Entity(float x, float y){
		this(x, y, 1, 1, null);
	}
	
	public Entity(){
		this(0, 0);
	}
	
	public void tick(float delta){
		for(int i = 0;i < components.size;++i){
			components.get(i).tick(this, delta);
		}
		checkBounds();
	}
	
	public void render(SpriteBatch batch){
		if(texture != null)
			batch.draw(texture, x, y, width, height);
	}
	
	//BOUNDS
	
	protected void checkBounds(){
		if(x < 0)
			x = 0;
		else if(x + width > Main.WIDTH)
			x = Main.WIDTH - width;
	}
	
	// COMPONENT METHODS
	
	public void addComponent(Component c){
		components.add(c);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(int id){
		for(Component c : components){
			if(c.getId() == id)
				return (T) c;
		}
		return null;
	}
	
	public void removeComponent(int id){
		for(Component c : components){
			if(c.getId() == id){
				components.removeValue(c, true);
				return;
			}
		}
	}
	
	public boolean hasComponent(int id){
		if(getComponent(id) != null)
			return true;
		return false;
	}
	
	//HELPERS
	
	public void incX(float x){
		this.x += x;
	}
	
	public void incY(float y){
		this.y += y;
	}
	
	//GETTERS SETTERS

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

	public TextureRegion getTexture() {
		return texture;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

}
