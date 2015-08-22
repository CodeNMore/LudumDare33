package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class EntityManager {
	
	//Player
	private Player player;
	//Entities
	private Array<Entity> entities;
	
	public EntityManager(Player player){
		this.player = player;
		entities = new Array<Entity>();
		entities.add(player);
	}
	
	public void tick(float delta){
		for(int i = 0;i < entities.size;++i){
			entities.get(i).tick(delta);
		}
	}
	
	public void render(SpriteBatch batch){
		for(Entity e : entities)
			e.render(batch);
	}
	
	//GETTERS SETTERS

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Array<Entity> getEntities() {
		return entities;
	}

	public void setEntities(Array<Entity> entities) {
		this.entities = entities;
	}

}
