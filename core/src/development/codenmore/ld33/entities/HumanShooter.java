package development.codenmore.ld33.entities;

import development.codenmore.ld33.entities.components.HumanShooterComponent;

public class HumanShooter extends Human {
	
	public HumanShooter(EntityManager manager, float x, float y){
		super(manager, x, y);
		addComponent(new HumanShooterComponent());
	}
	
	public HumanShooter(){
		this(null, 0, 0);
	}

}
