package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld33.particles.ParticleEmitter;

public class EntityManager {
	
	//Player
	private Player player;
	//Entities
	private Array<Entity> entities;
	private Array<Entity> toRemove;
	private Array<Bullet> bullets;
	private Array<ParticleEmitter> emitters;
	
	public EntityManager(Player player){
		this.player = player;
		entities = new Array<Entity>();
		bullets = new Array<Bullet>();
		toRemove = new Array<Entity>();
		emitters = new Array<ParticleEmitter>();
		add(player);
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
		//Remove entities
		for(Entity e : toRemove){
			emitters.add(new ParticleEmitter(10, 0f,
					e.getParticleColor(), e.getX() + e.getWidth() / 2,
					e.getY() + e.getHeight() / 2,
					3, 3, 40, 70, 60, 120, 2, 4));
			entities.removeValue(e, true);
		}
		toRemove.clear();
		//Bullets
		for(Bullet b : bullets){
			if(b.tick(delta)){
				emitters.add(new ParticleEmitter(10, 0f,
						Color.LIGHT_GRAY, b.getX() + 3,
						b.getY() + 3,
						1, 1, 30, 50, 60, 120, 1, 2));
				bullets.removeValue(b, true);
			}
		}
		//Particles
		for(ParticleEmitter p : emitters){
			if(p.tick(delta))
				emitters.removeValue(p, true);
		}
	}
	
	public void render(SpriteBatch batch){
		//Entities
		for(Entity e : entities){
			if(!e.equals(player))
				e.render(batch);
		}
		//Player
		player.render(batch);
		//Bullets
		for(Bullet b : bullets){
			b.render(batch);
		}
		//Particles
		for(ParticleEmitter p : emitters)
			p.render(batch);
	}
	
	//BULLETS
	
	public void addBullet(Bullet b){
		bullets.add(b);
	}
	
	//EMITTERS
	
	public void addEmitter(ParticleEmitter e){
		emitters.add(e);
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
