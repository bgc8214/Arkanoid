/**
 * Created by bgc82 on 2016-11-24.
 */
public class Transform {
    private Vector2 dir;
    private Vector2 pos;
    private float velocity;

    public Transform() {
    }

    public Transform(Vector2 dir, Vector2 pos) {
        this.dir = dir;
        this.pos = pos;
        velocity = 1;
    }

    public void moveObject() {
        dir.normalize();
        pos = Vector2.add(pos, Vector2.multiple(dir, velocity));
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getVelocity() {
        return velocity;
    }

    public Vector2 getDir() {
        return dir;
    }

    public void setDir(float x, float y) {
        this.dir = new Vector2(x, y);
    }

    public void setPos(float x, float y) {
        this.pos = new Vector2(x, y);
    }

    public Vector2 getPos() {
        return pos;
    }

}
