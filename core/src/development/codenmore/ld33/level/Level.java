package development.codenmore.ld33.level;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.Entity;
import development.codenmore.ld33.entities.EntityManager;
import development.codenmore.ld33.entities.Player;
import development.codenmore.ld33.entities.components.AnimationComponent;
import development.codenmore.ld33.entities.components.CollisionComponent;
import development.codenmore.ld33.entities.components.HumanMovementComponent;
import development.codenmore.ld33.entities.components.HumanShooterComponent;
import development.codenmore.ld33.entities.components.LiftComponent;
import development.codenmore.ld33.entities.components.MovementComponent;

public class Level {
	
	//Level
	private int width = 20, height = 6;
//	private Background background;
	private byte[] tiles;
	//Entities
	private EntityManager entityManager;
	
	public Level(){
		entityManager = new EntityManager(new Player());
		tiles = new byte[width * height];
		genLevel();
		
		Entity e = new Entity(300, Tile.TILESIZE * 6 - 16, 12, 20, Assets.getRegion("human.stand.1"));
		e.addComponent(new MovementComponent(60f));
		e.addComponent(new CollisionComponent(e, 0, 0));
		e.addComponent(new HumanMovementComponent());
		e.addComponent(new HumanShooterComponent());
		e.addComponent(new LiftComponent());
		AnimationComponent a = new AnimationComponent();
		a.setLeft(new Animation(0.2f, Assets.getSeries("human.left.", 3)));
		a.setRight(new Animation(0.2f, Assets.getSeries("human.right.", 3)));
		a.setStanding(new Animation(0.3f, Assets.getSeries("human.stand.", 2)));
		a.setShooting(new Animation(0.1f, Assets.getSeries("human.shoot.", 2)));
		e.addComponent(a);
		entityManager.add(e);
		
		Entity e2 = new Entity(200, Tile.TILESIZE * 6 - 16, 12, 20, Assets.getRegion("human.stand.1"));
		e2.addComponent(new MovementComponent(60f));
		e2.addComponent(new CollisionComponent(e2, 0, 0));
		e2.addComponent(new HumanMovementComponent());
		e2.addComponent(new LiftComponent());
		AnimationComponent a2 = new AnimationComponent();
		a2.setLeft(new Animation(0.2f, Assets.getSeries("human.left.", 3)));
		a2.setRight(new Animation(0.2f, Assets.getSeries("human.right.", 3)));
		a2.setStanding(new Animation(0.3f, Assets.getSeries("human.stand.", 2)));
		a2.setShooting(new Animation(0.1f, Assets.getSeries("human.shoot.", 2)));
		e2.addComponent(a2);
		entityManager.add(e2);
	}
	
	public void tick(float delta){
		entityManager.tick(delta);
	}
	
	public void render(SpriteBatch batch){
		for(int x = 0;x < width;++x){
			for(int y = 0;y < height;++y){
				getTile(x, y).render(batch, x * Tile.TILESIZE, y * Tile.TILESIZE);
			}
		}
		entityManager.render(batch);
	}
	
	public Tile getTile(int x, int y){
		return Tile.tiles[tiles[x + y * width]];
	}
	
	private void genLevel(){
		for(int y = 0;y < height;++y){
			for(int x = 0;x < width;++x){
				if(y == height - 1){//Grass
					tiles[x + y * width] = Tile.grassTile.getId();
				}else if(y == height - 2){//Transition
					tiles[x + y * width] = Tile.grassDirtTile.getId();
				}else{//Underground
					if(MathUtils.randomBoolean(0.1f)){						
						tiles[x + y * width] = Tile.rockTile.getId();
					}else{
						tiles[x + y * width] = Tile.dirtTile.getId();
					}
				}
			}
		}
	}
	
	//HELPERS
	
	public int getGroundLevel(){
		return (int) (height * Tile.TILESIZE - Tile.TILESIZE / 1.5f);
	}
	
	//GETTERS SETTERS

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
