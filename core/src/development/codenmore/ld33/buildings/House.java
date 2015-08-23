package development.codenmore.ld33.buildings;

import development.codenmore.ld33.assets.Assets;

public class House extends Building {

	public House(float x, float y) {
		super(x, y, 80, 80, Assets.getRegion("house"));
	}

}
