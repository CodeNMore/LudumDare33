package development.codenmore.ld33.entities.components;

import development.codenmore.ld33.entities.Entity;

public class MovementComponent extends Component {
	
	//ID
	public static final int ID = 0;
	//Data
	private float x, y;
	
	public MovementComponent(){
		super(ID);
	}

	@Override
	public void tick(Entity e, float delta) {
		e.incX(x * delta);
		e.incY(y * delta);
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

}
