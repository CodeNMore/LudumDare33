package development.codenmore.ld33.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld33.assets.Assets;

public class Tile {
	
	//MANAGER
	
	public static Tile[] tiles = new Tile[4];
	public static Tile dirtTile = new Tile(0, Assets.getRegion("dirtTile"));
	public static Tile grassTile = new Tile(1, Assets.getRegion("grassTileFull"));
	public static Tile grassDirtTile = new Tile(2, Assets.getRegion("grassTile"));
	public static Tile rockTile = new Tile(3, Assets.getRegion("rockTile"));
	
	//CLASS
	
	public static final int TILESIZE = 32;
	private byte id;
	private TextureRegion texture;
	
	public Tile(int id, TextureRegion texture){
		this.id = (byte) id;
		tiles[id] = this;
		this.texture = texture;
	}
	
	public void render(SpriteBatch batch, int x, int y){
		batch.draw(texture, x, y, TILESIZE, TILESIZE);
	}
	
	public byte getId(){
		return id;
	}

}
