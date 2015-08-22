package development.codenmore.ld33.entities.components;

import com.badlogic.gdx.math.Rectangle;

import development.codenmore.ld33.entities.Entity;

public class CollisionComponent extends Component {
	
	//ID
	public static final int ID = 3;
	//Data
	private Entity entity;
	private float x, y;
	private int width, height;
	
	public CollisionComponent(Entity entity, float x, float y){
		super(ID);
		this.entity = entity;
		this.x = x;
		this.y = y;
		this.width = entity.getWidth();
		this.height = entity.getHeight();
	}
	
	public CollisionComponent(){
		this(null, 0, 0);
	}

	@Override
	public void tick(Entity e, float delta) {}
	
	public Rectangle getBounds(){
		if(entity == null)
			return new Rectangle();
		return new Rectangle(entity.getX() + x, entity.getY() + y, width, height);
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
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

}
