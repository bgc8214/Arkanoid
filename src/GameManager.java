import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Iterator;

public class GameManager extends PApplet {

    private ArrayList<GameObject> balls;
    private ArrayList<GameObject> blocks;
    private ArrayList<GameObject> items;
    private Player player;
    private int effectTime;
    private int sceneNumber;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private String[] mapFile;

    public GameManager() {
    }

    public static void main(String[] args) {
        GameManager gm = new GameManager();
        gm.runSketch();
    }


    @Override
    public void settings() {
        size(800, 800);
        initGame();
    }

    public void initGame() {
        balls = new ArrayList<GameObject>();
        blocks = new ArrayList<GameObject>();
        items = new ArrayList<GameObject>();
        player = new Player(400, 770, this);

        sceneNumber = 0;
        balls.add(new Ball(new Vector2(1, -1), new Vector2(player.getPos().x + Constant.PLAYER_WIDTH / 2, player.getPos().y - 5), this));
        mapFile = loadStrings("map1.txt");

        for (int i = 0; i < mapFile.length; i++) {
            for (int j = 0; j < mapFile[i].length(); j++) {
                if (mapFile[i].charAt(j) == ' ')
                    continue;
                blocks.add(new Block(j, i, mapFile[i].charAt(j)));
            }
        }
    }

    @Override
    public void draw() {
        if (sceneNumber == 0) {
            background(0);
            drawBall();
            drawBlock();
            drawItem();
            drawPlayer();
        } else if (sceneNumber == 1)
            drawGameOver();
    }

    @Override
    public void keyPressed() {
        if (keyCode == LEFT) {
            isLeftPressed = true;
        } else if (keyCode == RIGHT) {
            isRightPressed = true;
        } else if (key == 'r' && sceneNumber == 1) {
            System.out.print("ss");
            initGame();
        }
    }

    @Override
    public void keyReleased() {
        if (keyCode == LEFT) {
            isLeftPressed = false;
        } else if (keyCode == RIGHT) {
            isRightPressed = false;
        }
    }

    public void drawBall() {
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).update();
            if (effectTime < millis()) {
                ((Ball) balls.get(i)).setDrilled(false);
            }
            if (balls.get(i).getPos().y > 820)
                balls.remove(balls.get(i));
            if (balls.size() == 0) {
                sceneNumber = 1;
            }
        }
        for (GameObject ball : balls) {
            ball.render(this);
        }
    }

    public void drawBlock() {
        for (Iterator<GameObject> iterator = blocks.iterator(); iterator.hasNext(); ) {
            GameObject block = iterator.next();
            if (block.checkCollision(balls)) {
                iterator.remove();
                if (((Block) block).getBlockType() != '0')
                    items.add(new BallItem(block.getPos().x, block.getPos().y, ((Block) block).getBlockType()));
            }
            block.render(this);
        }
    }

    public void drawItem() {
        for (Iterator<GameObject> iterator = items.iterator(); iterator.hasNext(); ) {
            GameObject item = iterator.next();
            if (((BallItem) item).checkCollision(player)) {
                iterator.remove();
                useItem(((BallItem) item).getItemType());
            }
            item.update();
        }
        for (GameObject item : items) {
            item.render(this);
        }
    }

    public void drawPlayer() {
        player.checkCollision(balls);
        player.update();
        player.render(this);
    }

    public void drawGameOver() {
        textSize(50);
        fill(255, 0, 0);
        text("Game Over", width / 2 - 120, height / 2 - 200);
        textSize(32);
        fill(0, 200, 150);
        text("Press 'R' key to restart", width / 2 - 160, height / 2 + 150);
    }

    public void useItem(char itemType) {
        for (int i = 0; i < balls.size(); i++) {
            switch (itemType) {
                case '1':
                    ((Ball) balls.get(i)).setItem(Item.FAST);
                    break;
                case '2':
                    ((Ball) balls.get(i)).setItem(Item.SLOW);
                    break;
                case '3':
                    ((Ball) balls.get(i)).setItem(Item.DUPLICATE);
                    break;
                case '4':
                    ((Ball) balls.get(i)).setItem(Item.DRILL);
                    effectTime = millis() + 5000;
                    break;
            }
        }
    }

    public ArrayList getBalls() {
        return balls;
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }
}
