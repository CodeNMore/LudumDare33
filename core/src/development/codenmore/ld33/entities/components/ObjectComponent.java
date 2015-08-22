package development.codenmore.ld33.entities.components;

import development.codenmore.ld33.entities.Entity;

public class ObjectComponent extends Component {

	//ID
	public static final int ID = 6;
	//Data
	private int health;
	private boolean falling = false;
	
	public ObjectComponent(int health) {
		super(ID);
		this.health = health;
	}
	
	@Override
	public void tick(Entity e, float delta){
		
	}

}
