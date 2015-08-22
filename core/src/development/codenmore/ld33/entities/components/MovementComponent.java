package development.codenmore.ld33.entities.components;

import development.codenmore.ld33.entities.Entity;

public class MovementComponent extends Component {
	
	//ID
	public static final int ID = 0;
	//Data
	private float x, y, speed;
	
	public MovementComponent(float speed){
		super(ID);
		this.speed = speed;
	}
	
	public MovementComponent(){
		this(120);
	}

	@Override
	public void tick(Entity e, float delta) {
		e.incX(x * delta);
		e.incY(y * delta);
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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
