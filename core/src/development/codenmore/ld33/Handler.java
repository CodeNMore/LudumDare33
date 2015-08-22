package development.codenmore.ld33;

import development.codenmore.ld33.level.Level;

public class Handler {
	
	private static Level level;
	
	private Handler(){}
	
	public static Level getLevel(){
		return level;
	}
	
	public static void setLevel(Level level){
		Handler.level = level;
	}

}
