package development.codenmore.ld33.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets {
	
	private static String[] soundPaths = {
		"hurt", "shoot", "destroy", "collect"
	};
	
	private static AssetManager manager = new AssetManager();
	private static ObjectMap<String, TextureRegion> regions = new ObjectMap<String, TextureRegion>();
	private static ObjectMap<String, Sound> sounds = new ObjectMap<String, Sound>();
	private static BitmapFont font;
	private static TextureAtlas atlas;
	private static boolean loaded = false;
	private static Music music;
	
	private Assets(){}
	
	public static void init(){
		manager.load("font/font.fnt", BitmapFont.class);
		manager.load("textures/atlas.pack", TextureAtlas.class);
		
		for(String file : soundPaths){
			manager.load("sounds/" + file + ".wav", Sound.class);
		}
		manager.load("music/music.wav", Music.class);
	}
	
	public static void postInit(){
		font = manager.get("font/font.fnt");
		font.getData().setLineHeight(8.5f);
		atlas = manager.get("textures/atlas.pack", TextureAtlas.class);
		for(String file : soundPaths){
			sounds.put(file, manager.get("sounds/" + file + ".wav", Sound.class));
		}
		music = manager.get("music/music.wav", Music.class);
		
		loaded = true;
	}
	
	public static Sound getSound(String name){
		return sounds.get(name);
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
	
	public static void setFontMicro(){
		font.getData().setScale(1.8f);
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

	public static Music getMusic() {
		return music;
	}

	public static boolean isLoaded() {
		return loaded;
	}

}
