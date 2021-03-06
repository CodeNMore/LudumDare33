package development.codenmore.ld33.entities.components;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.entities.Entity;
import development.codenmore.ld33.entities.Player;
import development.codenmore.ld33.entities.Turret;

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
		CollisionComponent cc = e.getComponent(CollisionComponent.ID);
		
		if(falling){
			for(Entity entity : Handler.getLevel().getEntityManager().getEntities()){
				CollisionComponent ecc = entity.getComponent(CollisionComponent.ID);
				if(ecc != null && !ecc.equals(cc) && !entity.isRemove()){
					if(cc.getBounds().overlaps(ecc.getBounds())){
						if(entity.hasComponent(PlayerComponent.ID)){
							//Damage player
							((Player) entity).damage(0.7f);//cause it runs 2 times for some reason
							//Destroy object
							e.setRemove(true);
							return;
						}
						entity.setRemove(true);
						if(entity instanceof Turret)//Subtract two cause it takes more hits
							health -= 2;
						//Minus health
						health--;
						if(health <= 0){
							e.setRemove(true);
							return;
						}
					}
				}
			}
		}
	}
	
	//GETTERS SETTERS

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

}
