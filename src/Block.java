import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by bgc82 on 2016-11-25.
 */
public class Block extends GameObject {
    private char blockType;

    public Block(float x, float y, char blockType) {
        setPos(x * Constant.BLOCK_WIDTH, y * Constant.BLOCK_HEIGHT);
        this.blockType = blockType;
    }

    @Override
    public boolean checkCollision(ArrayList<GameObject> gameObjects) {
        for (int i = 0; i < gameObjects.size(); i++) {
            float y = Math.abs(gameObjects.get(i).getPos().y - (getPos().y));
            if (y < Constant.BALL_RADIUS && gameObjects.get(i).getPos().x > getPos().x && gameObjects.get(i).getPos().x < getPos().x + Constant.BLOCK_WIDTH) {
                if (!((Ball) gameObjects.get(i)).isDrilled())
                    gameObjects.get(i).getDir().y = -gameObjects.get(i).getDir().y;
                return true;
            }

            y = Math.abs(gameObjects.get(i).getPos().y - (getPos().y + Constant.BLOCK_HEIGHT));
            if (y < Constant.BALL_RADIUS && gameObjects.get(i).getPos().x > getPos().x && gameObjects.get(i).getPos().x < getPos().x + Constant.BLOCK_WIDTH) {
                if (!((Ball) gameObjects.get(i)).isDrilled())
                    gameObjects.get(i).getDir().y = -gameObjects.get(i).getDir().y;
                return true;
            }

            float x = Math.abs(gameObjects.get(i).getPos().x - (getPos().x));
            if (x < Constant.BALL_RADIUS && gameObjects.get(i).getPos().y > getPos().y && gameObjects.get(i).getPos().y < getPos().y + Constant.BLOCK_HEIGHT) {
                if (!((Ball) gameObjects.get(i)).isDrilled())
                    gameObjects.get(i).getDir().x = -gameObjects.get(i).getDir().x;
                return true;
            }

            x = Math.abs(gameObjects.get(i).getPos().x - (getPos().x + Constant.BLOCK_WIDTH));
            if (x < Constant.BALL_RADIUS && gameObjects.get(i).getPos().y > getPos().y && gameObjects.get(i).getPos().y < getPos().y + Constant.BLOCK_HEIGHT) {
                if (!((Ball) gameObjects.get(i)).isDrilled())
                    gameObjects.get(i).getDir().x = -gameObjects.get(i).getDir().x;
                return true;
            }
        }
        return false;
    }

    public char getBlockType() {
        return this.blockType;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(PApplet p) {
        p.fill(124, 120, 40);
        p.rect(getPos().x, getPos().y, Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT);
    }
}
