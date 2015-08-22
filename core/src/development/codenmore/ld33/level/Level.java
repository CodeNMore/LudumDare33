package development.codenmore.ld33.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld33.entities.EntityManager;
import development.codenmore.ld33.entities.Player;
import development.codenmore.ld33.particles.ParticleEmitter;

public class Level {
	
	//Level
	private final int width = 20, height = 7;
//	private TextureRegion background;
	private byte[] tiles;
	//Entities
	private EntityManager entityManager;
	ParticleEmitter e;
	
	public Level(){
		entityManager = new EntityManager(new Player());
		tiles = new byte[width * height];
		genLevel();
		
		e = new ParticleEmitter(-1, Color.GREEN, 300, 300, 5, 5, 40, 80, 0, 360, 1, 2);
	}
	
	public void tick(float delta){
		entityManager.tick(delta);
		
		e.tick(delta);
	}
	
	public void render(SpriteBatch batch){
		for(int x = 0;x < width;++x){
			for(int y = 0;y < height;++y){
				getTile(x, y).render(batch, x * Tile.TILESIZE, y * Tile.TILESIZE);
			}
		}
		entityManager.render(batch);
		
		e.render(batch);
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
					if(MathUtils.randomBoolean(0.2f)){						
						tiles[x + y * width] = Tile.rockTile.getId();
					}else{
						tiles[x + y * width] = Tile.dirtTile.getId();
					}
				}
			}
		}
	}

}
