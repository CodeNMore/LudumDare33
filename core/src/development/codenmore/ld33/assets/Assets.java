package development.codenmore.ld33.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets {
	
	private static AssetManager manager = new AssetManager();
	private static ObjectMap<String, TextureRegion> regions = new ObjectMap<String, TextureRegion>();
	private static BitmapFont font;
	private static TextureAtlas atlas;
	
	private Assets(){}
	
	public static void init(){
		manager.load("font/font.fnt", BitmapFont.class);
		manager.load("textures/atlas.pack", TextureAtlas.class);
	}
	
	public static void postInit(){
		font = manager.get("font/font.fnt");
		atlas = manager.get("textures/atlas.pack", TextureAtlas.class);
	}
	
	public static TextureRegion getRegion(String name){
		if(regions.containsKey(name))
			return regions.get(name);
		regions.put(name, atlas.findRegion(name));
		return regions.get(name);
	}
	
	public static TextureRegion[] getSeries(String name, int end){//Assume start = 1
		TextureRegion[] ret = new TextureRegion[end];
		for(int i = 1;i <= end;++i)
			ret[i - 1] = getRegion(name + i);
		return ret;
	}
	
	public static void setFontSmall(){
		font.getData().setScale(2.0f);
	}
	
	public static void setFontMed(){
		font.getData().setScale(4.5f);
	}
	
	public static void setFontLarge(){
		font.getData().setScale(6.0f);
	}
	
	public static void setFontColor(Color color){
		font.setColor(color);
	}
	
	public static void drawString(SpriteBatch batch, String str, float x, float y){
		font.draw(batch, str.toUpperCase(), x, y);
	}
	
	public static void dispose(){
		manager.dispose();
	}
	
	public static boolean step(){
		return manager.update();
	}
	
	public static int getProgress(){
		return (int) (manager.getProgress() * 100);
	}

}
