import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by bgc82 on 2016-11-25.
 */
public enum Item {
    NORMAL {
        public void use(ArrayList ball) {
            System.out.println("normal");
        }
    },
    FAST {
        public void use(ArrayList ball) {
            for (Object b : ball)
                ((Ball) b).setVelocity(6);
        }
    },
    SLOW {
        public void use(ArrayList ball) {
            for (Object b : ball)
                ((Ball) b).setVelocity(1);
        }
    },
    DUPLICATE {
        public void use(ArrayList ball) {
            Ball cloneBall = ((Ball) ball.get(0)).clone();
            cloneBall.setDir(-((Ball) ball.get(0)).getDir().x, ((Ball) ball.get(0)).getDir().y);
            ball.add(cloneBall);
        }
    },
    DRILL {
        public void use(ArrayList ball) {
            for (Object b : ball)
                ((Ball) b).setDrilled(true);
        }
    };

    abstract void use(ArrayList ball);
}
