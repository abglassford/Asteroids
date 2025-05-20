import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Projectile extends Entity {

    public Projectile(InteractiveScene scene, int x, int y) {
        super(scene, new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
        getPolygon().setFill(Color.RED);
    }
}
