package development.codenmore.ld33.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld33.Main;
import development.codenmore.ld33.buildings.Building;
import development.codenmore.ld33.entities.EntityManager;
import development.codenmore.ld33.entities.Player;
import development.codenmore.ld33.states.GameState;
import development.codenmore.ld33.ui.Background;

public class Level {

	// Level
	private GameState gameState;
	private int width = 20, height = 6;
	private int biome;
	 private Background background;
	private byte[] tiles;
	private Array<Building> buildings;
	// Entities
	private EntityManager entityManager;

	public Level(GameState gameState, int biome) {
		this.gameState = gameState;
		this.biome = biome;
		entityManager = new EntityManager(this, new Player());
		buildings = new Array<Building>();
		tiles = new byte[width * height];
		background = new Background(gameState.getDay().getNightTime(), 
				0, Tile.TILESIZE * height, Main.WIDTH,
				Main.HEIGHT - Tile.TILESIZE * height, 14.0f);
		genLevel(biome);// 0-grass 1-desert 2-industrial

//		addBuilding(new Pub(400, getGroundLevel()));
//		addBuilding(new House(300, getGroundLevel()));
//		addBuilding(new TallBuilding(200, getGroundLevel()));
//		addBuilding(new Barn(100, getGroundLevel()));
//
//		new Turret(entityManager, 170, getGroundLevel(), 5.0f);
//		
//		for(int i = 0;i < 4;++i){
//			new Cow(entityManager, 270, getGroundLevel());
//			new Human(entityManager, 270, getGroundLevel(), false);
//		}
//		
//		new Rock(entityManager, 270, getGroundLevel());
	}

	public void tick(float delta) {
		background.tick(delta);
		entityManager.tick(delta);
		for (Building b : buildings)
			b.tick(delta);
	}

	public void render(SpriteBatch batch) {
		background.render(batch);

		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				getTile(x, y).render(batch, x * Tile.TILESIZE,
						y * Tile.TILESIZE);
			}
		}

		for (Building b : buildings)
			b.render(batch);

		entityManager.render(batch);
	}

	public Tile getTile(int x, int y) {
		return Tile.tiles[tiles[x + y * width]];
	}

	private void genLevel(int biome) {
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (y == height - 1) {// Grass
					if (biome == 1) {
						tiles[x + y * width] = Tile.sandTile.getId();
					} else if(biome == 2){
						tiles[x + y * width] = Tile.roadTile.getId();
					}else {
						tiles[x + y * width] = Tile.grassTile.getId();
					}
				} else if (y == height - 2) {// Transition
					if (biome == 1) {
						tiles[x + y * width] = Tile.sandToDeepTile.getId();
					} else if(biome == 2){
						tiles[x + y * width] = Tile.roadUndergroundTile.getId();
					}else {
						tiles[x + y * width] = Tile.grassDirtTile.getId();
					}
				} else if (y == 0) {
					tiles[x + y * width] = Tile.blackTile.getId();
				} else {// Underground
					if (MathUtils.randomBoolean(0.1f)) {
						if (biome == 1) {
							tiles[x + y * width] = Tile.goldTile.getId();
						} else if(biome == 2){
							tiles[x + y * width] = Tile.silverTile.getId();
						} else {
							tiles[x + y * width] = Tile.rockTile.getId();
						}
					} else {
						if (biome == 1) {
							tiles[x + y * width] = Tile.deepSandTile.getId();
						} else if(biome == 2){
							tiles[x + y * width] = Tile.undergroundTile.getId();
						} else {
							tiles[x + y * width] = Tile.dirtTile.getId();
						}
					}
				}
			}
		}
	}

	// HELPERS

	public void addBuilding(Building b) {
		buildings.add(b);
	}

	public int getGroundLevel() {
		return (int) (height * Tile.TILESIZE - Tile.TILESIZE / 1.5f);
	}

	// GETTERS SETTERS

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

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public Array<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(Array<Building> buildings) {
		this.buildings = buildings;
	}

	public int getBiome() {
		return biome;
	}

	public void setBiome(int biome) {
		this.biome = biome;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

}
