package development.codenmore.ld33.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld33.assets.Assets;

public class Tile {
	
	//MANAGER
	
	public static Tile[] tiles = new Tile[13];
	public static Tile dirtTile = new Tile(0, Assets.getRegion("dirtTile"));
	public static Tile grassTile = new Tile(1, Assets.getRegion("grassTileFull"));
	public static Tile grassDirtTile = new Tile(2, Assets.getRegion("grassTile"));
	public static Tile rockTile = new Tile(3, Assets.getRegion("rockTile"));
	public static Tile blackTile = new Tile(4, null, Color.BLACK);
	public static Tile deepSandTile = new Tile(5, Assets.getRegion("deepSandTile"));
	public static Tile sandTile = new Tile(6, Assets.getRegion("sandTileFull"));
	public static Tile sandToDeepTile = new Tile(7, Assets.getRegion("sandTile"));
	public static Tile goldTile = new Tile(8, Assets.getRegion("goldTile"));
	public static Tile undergroundTile = new Tile(9, Assets.getRegion("undergroundTile"));
	public static Tile roadTile = new Tile(10, Assets.getRegion("roadTileFull"));
	public static Tile roadUndergroundTile = new Tile(11, Assets.getRegion("roadTile"));
	public static Tile silverTile = new Tile(12, Assets.getRegion("silverTile"));
	
	//CLASS
	
	public static final int TILESIZE = 32;
	private static TextureRegion tex = Assets.getRegion("color");
	private byte id;
	private TextureRegion texture;
	private Color color;
	
	public Tile(int id, TextureRegion texture){
		this.id = (byte) id;
		tiles[id] = this;
		this.texture = texture;
	}
	
	public Tile(int id, TextureRegion texture, Color color){
		this(id, texture);
		this.color = color;
	}
	
	public void render(SpriteBatch batch, int x, int y){
		if(texture != null)
			batch.draw(texture, x, y, TILESIZE, TILESIZE);
		else{
			batch.setColor(color);
			batch.draw(tex, x, y, TILESIZE, TILESIZE);
			batch.setColor(Color.WHITE);
		}
	}
	
	public byte getId(){
		return id;
	}

}
