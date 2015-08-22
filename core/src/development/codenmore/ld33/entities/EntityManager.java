package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class EntityManager {
	
	//Player
	private Player player;
	//Entities
	private Array<Entity> entities;
	private Array<Entity> toRemove;
	private Array<Bullet> bullets;
	
	public EntityManager(Player player){
		this.player = player;
		entities = new Array<Entity>();
		bullets = new Array<Bullet>();
		toRemove = new Array<Entity>();
	}
	
	public void tick(float delta){
		Entity entity;
		for(int i = 0;i < entities.size;++i){
			entity = entities.get(i);
			entity.tick(delta);
			if(entity.isRemove()){
				toRemove.add(entity);
			}
		}
		//Player
		player.tick(delta);
		//Remove entities
		for(Entity e : toRemove){
			entities.removeValue(e, true);
		}
		toRemove.clear();
		//Bullets
		for(Bullet b : bullets){
			if(b.tick(delta))
				bullets.removeValue(b, true);
		}
	}
	
	public void render(SpriteBatch batch){
		//Entities
		for(Entity e : entities){
			e.render(batch);
		}
		//Player
		player.render(batch);
		//Bullets
		for(Bullet b : bullets){
			b.render(batch);
		}
	}
	
	//BULLETS
	
	public void addBullet(Bullet b){
		bullets.add(b);
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
