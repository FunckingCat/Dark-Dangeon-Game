package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

public class Enemy extends DynamicObject{

    public Enemy(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.GHOST_SPRITE);
    }

}
