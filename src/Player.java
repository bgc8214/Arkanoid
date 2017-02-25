import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by bgc82 on 2016-11-24.
 */
public class Player extends GameObject {
    GameManager gameManager;

    public Player(float x, float y, GameManager gameManager) {
        setPos(x, y);
        setDir(1, 0);
        this.gameManager = gameManager;
    }

    public void setPlayerVelocity() {
        if (gameManager.isLeftPressed()) {
            setVelocity(getVelocity() - 0.3f);
            if (getVelocity() > 5)
                setVelocity(5);
        } else if (gameManager.isRightPressed()) {
            setVelocity(getVelocity() + 0.3f);
            if (getVelocity() < -5)
                setVelocity(-5);
        } else {
            setVelocity(getVelocity() * 0.9f);
        }
    }

    @Override
    public boolean checkCollision(ArrayList<GameObject> gameObjects) {
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i).getPos().y + Constant.BALL_RADIUS > getPos().y
                    && gameObjects.get(i).getPos().y + Constant.BALL_RADIUS < getPos().y + Constant.PLAYER_HEIGHT
                    && gameObjects.get(i).getPos().x > getPos().x
                    && gameObjects.get(i).getPos().x < getPos().x + Constant.PLAYER_WIDTH) {
                gameObjects.get(i).getDir().y = -gameObjects.get(i).getDir().y;
                gameObjects.get(i).getDir().x = -1 + (gameObjects.get(i).getPos().x - getPos().x) * 0.016f;
            }
        }
        return false;
    }

    @Override
    public void update() {
        setPlayerVelocity();
        moveObject();
        if (getPos().x < 0)
            setPos(0, getPos().y);
        else if (getPos().x > 800 - Constant.PLAYER_WIDTH)
            setPos(800 - Constant.PLAYER_WIDTH, getPos().y);
    }

    @Override
    public void render(PApplet p) {
        p.fill(100,0,0);
        p.rect(getPos().x, getPos().y, Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT);
    }
}
