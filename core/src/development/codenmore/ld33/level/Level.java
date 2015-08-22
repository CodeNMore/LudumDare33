package development.codenmore.ld33.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld33.Main;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.buildings.Building;
import development.codenmore.ld33.buildings.TallBuilding;
import development.codenmore.ld33.entities.EntityManager;
import development.codenmore.ld33.entities.Player;
import development.codenmore.ld33.entities.Turret;

public class Level {
	
	//Level
	private int width = 20, height = 6;
//	private Background background;
	private byte[] tiles;
	private Array<Building> buildings;
	//Entities
	private EntityManager entityManager;
	
	public Level(){
		entityManager = new EntityManager(new Player());
		buildings = new Array<Building>();
		tiles = new byte[width * height];
		genLevel();
		
		addBuilding(new TallBuilding(500, getGroundLevel()));
		addBuilding(new TallBuilding(300, getGroundLevel()));
		
		new Turret(entityManager, 170, getGroundLevel(), 4.0f);
	}
	
	public void tick(float delta){
		entityManager.tick(delta);
		for(Building b : buildings)
			b.tick(delta);
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(Color.DARK_GRAY);
		batch.draw(Assets.getRegion("color"), 0, 0, Main.WIDTH, Main.HEIGHT);
		batch.setColor(Color.WHITE);
		
		for(int x = 0;x < width;++x){
			for(int y = 0;y < height;++y){
				getTile(x, y).render(batch, x * Tile.TILESIZE, y * Tile.TILESIZE);
			}
		}
		
		for(Building b : buildings)
			b.render(batch);
		
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
				}else if(y == 0){
					tiles[x + y * width] = Tile.blackTile.getId();
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
	
	public void addBuilding(Building b){
		buildings.add(b);
	}
	
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
