/**
 * Created by bgc82 on 2016-11-23.
 */
public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 multiple(Vector2 v, float s) {
        return new Vector2(v.x * s, v.y * s);
    }

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public float distance() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        float dist = distance();
        this.x = x / dist;
        this.y = y / dist;
    }
}
