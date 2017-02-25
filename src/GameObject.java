import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by bgc82 on 2016-11-24.
 */
public abstract class GameObject extends Transform {
    public GameObject() {
    }

    public GameObject(Vector2 dir, Vector2 pos) {
        super(dir, pos);
    }

    abstract public boolean checkCollision(ArrayList<GameObject> gameObjects);

    abstract public void update();

    abstract public void render(PApplet p);
}
