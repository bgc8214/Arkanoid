import processing.core.PApplet;

import java.util.ArrayList;


public class BallItem extends GameObject {
    private char itemType;

    public BallItem(float x, float y, char itemType) {
        setPos(x, y);
        setDir(0, 1);
        setVelocity(2);
        this.itemType = itemType;
    }

    @Override
    public boolean checkCollision(ArrayList<GameObject> gameObjects) {
        return false;
    }

    public boolean checkCollision(GameObject gameObjects) {
        if (getPos().x + 20 > gameObjects.getPos().x && getPos().x < gameObjects.getPos().x + Constant.PLAYER_WIDTH
                && getPos().y + 20 > gameObjects.getPos().y && getPos().y < gameObjects.getPos().y + Constant.BLOCK_HEIGHT) {
            return true;
        }
        return false;
    }

    public char getItemType() {
        return this.itemType;
    }

    @Override
    public void update() {
        moveObject();
    }

    @Override
    public void render(PApplet p) {
        switch (itemType) {
            case '1':
                p.fill(0, 200, 0);
                break;
            case '2':
                p.fill(200, 0, 0);
                break;
            case '3':
                p.fill(0, 0, 200);
                break;
            case '4':
                p.fill(100, 100, 100);
                break;
        }
        p.rect(getPos().x, getPos().y, 20, 20);
    }
}
