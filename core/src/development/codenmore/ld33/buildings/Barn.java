package development.codenmore.ld33.buildings;

import development.codenmore.ld33.assets.Assets;

public class Barn extends Building {

	public Barn(float x, float y) {
		super(x, y, 80, 80, Assets.getRegion("barn"));
	}
	
	public Barn(){
		this(0, 0);
	}

}
