import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by bgc82 on 2016-11-23.
 */
public class Ball extends GameObject implements Cloneable {
    private Item item;
    private boolean isItemUsed;
    private boolean isDrilled;
    private GameManager gameManager;

    public Ball(Vector2 dir, Vector2 pos, GameManager gameManager) {
        super(dir, pos);
        this.item = Item.NORMAL;
        this.isItemUsed = false;
        this.isDrilled = false;
        this.gameManager = gameManager;
    }

    @Override
    public Ball clone(){
        try {
            Ball ball = (Ball)super.clone();
            return ball;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkCollision(ArrayList<GameObject> gameObjects) {
        return false;
    }

    public void setItem(Item item) {
        this.item = item;
        isItemUsed = false;
    }

    public void setDrilled(boolean isDrilled) {
        this.isDrilled = isDrilled;
    }

    public boolean isDrilled() {
        return isDrilled;
    }

    public void use(ArrayList ball) {
        item.use(ball);
    }

    public void reflectWall() {
        if (getPos().x < 0)
            getDir().x = -getDir().x;
        else if (getPos().x > 800)
            getDir().x = -getDir().x;

        if (getPos().y < 0)
            getDir().y = -getDir().y;
    }

    @Override
    public void update() {
        if (!isItemUsed) {
            use(gameManager.getBalls());
            isItemUsed = true;
        }
        reflectWall();
        setVelocity(getVelocity() + 0.01f);
        if (getVelocity() > Constant.BALL_MAXSPEED)
            setVelocity(Constant.BALL_MAXSPEED);
        moveObject();
    }

    @Override
    public void render(PApplet p) {
        p.fill(200, 200, 200);
        p.ellipse(getPos().x, getPos().y, Constant.BALL_RADIUS * 2, Constant.BALL_RADIUS * 2);
    }

}
