import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Entity {
    protected Polygon polygon;
    protected InteractiveScene parent;
    protected Point2D movement;
    private boolean isAlive = true;

    public Entity(InteractiveScene parent, Polygon polygon, int x, int y) {
        this.polygon = polygon;

        this.parent = parent;

        polygon.setTranslateX(x);
        polygon.setTranslateY(y);

        this.movement = new Point2D(0, 0);

    }

    public void move() {
        this.polygon.setTranslateX(this.polygon.getTranslateX() + this.movement.getX());
        this.polygon.setTranslateY(this.polygon.getTranslateY() + this.movement.getY());

        if (this.polygon.getTranslateX() < 0) {
            this.polygon.setTranslateX(this.polygon.getTranslateX() + App.WIDTH);
        }

        if (this.polygon.getTranslateX() > App.WIDTH) {
            this.polygon.setTranslateX(this.polygon.getTranslateX() % App.WIDTH);
        }

        if (this.polygon.getTranslateY() < 0) {
            this.polygon.setTranslateY(this.polygon.getTranslateY() + App.HEIGHT);
        }

        if (this.polygon.getTranslateY() > App.HEIGHT) {
            this.polygon.setTranslateY(this.polygon.getTranslateY() % App.HEIGHT);
        }

    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D movement) {
        this.movement = movement;
    }

    public void moveLeft() {
        this.polygon.setTranslateX(this.polygon.getTranslateX() - 3);
    }

    public void moveRight() {
        this.polygon.setTranslateX(this.polygon.getTranslateX() + 3);
    }

    public void moveForward() {
        this.polygon.setTranslateY(this.polygon.getTranslateY() - 3);
    }

    public void moveBackward() {
        this.polygon.setTranslateY(this.polygon.getTranslateY() + 3);
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(polygon.getRotate())) * 0.05;
        double changeY = Math.sin(Math.toRadians(polygon.getRotate())) * 0.05;

        this.movement = this.movement.add(changeX, changeY);
    }

    public boolean collide(Entity other) {
        Shape collisionArea = Shape.intersect(this.polygon, other.getPolygon());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
