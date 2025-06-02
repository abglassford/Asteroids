import javafx.scene.shape.Polygon;

public class Ship extends Entity {
    public Ship(InteractiveScene parent, int x, int y) {
        super(parent, new Polygon(-5, 5, 0, -10, 5, 5), x, y);
    }

    public void turnLeft() {
        polygon.setRotate(polygon.getRotate() - 5);
    }

    public void turnRight() {
        polygon.setRotate(polygon.getRotate() + 5);
    }

}
