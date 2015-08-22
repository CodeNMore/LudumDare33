package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class EntityManager {
	
	//Player
	private Player player;
	//Entities
	private Array<Entity> entities;
	private Array<Integer> toRemove;
	private Array<Bullet> bullets;
	
	public EntityManager(Player player){
		this.player = player;
		entities = new Array<Entity>();
		toRemove = new Array<Integer>();
		entities.add(player);
	}
	
	public void tick(float delta){
		Entity entity;
		for(int i = 0;i < entities.size;++i){
			entity = entities.get(i);
			entity.tick(delta);
			if(entity.isRemove()){
				toRemove.add(i);
			}
		}
		//Remove entities
		for(Integer i : toRemove){
			entities.removeIndex(i);
		}
		toRemove.clear();
	}
	
	public void render(SpriteBatch batch){
		for(Entity e : entities){
			if(e.equals(player))
				continue;
			e.render(batch);
		}
		player.render(batch);
	}
	
	//ENTITIES
	
	public void add(Entity e){
		entities.add(e);
	}
	
	public void remove(Entity e){
		entities.removeValue(e, true);
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
