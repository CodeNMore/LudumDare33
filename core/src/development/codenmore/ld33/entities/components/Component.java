package development.codenmore.ld33.entities.components;

import development.codenmore.ld33.entities.Entity;

public abstract class Component {
	
	private final int id;
	
	public Component(int id){
		this.id = id;
	}
	
	public abstract void tick(Entity e, float delta);
	
	public int getId(){
		return id;
	}

}
