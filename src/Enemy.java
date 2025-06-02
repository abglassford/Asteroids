import javafx.scene.shape.Polygon;

public class Enemy extends Entity {

    public Enemy(InteractiveScene parent, int x, int y) {
        super(parent, createShape(), x, y);
    }

    private static Polygon createShape() {
        int sides = 7;
        double radius = 8;
        double centerX = 100;
        double centerY = 100;

        Polygon heptagon = new Polygon();

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides - Math.PI / 2; // start at the top
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            heptagon.getPoints().addAll(x, y);
        }

        return heptagon; 
    }

}
