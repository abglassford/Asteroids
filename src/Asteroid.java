import java.util.Random;

public class Asteroid extends Entity {
    private double rotationalMovement;

    public Asteroid(InteractiveScene parent, int x, int y) {
        super(parent, new PolygonFactory().createPolygon(), x, y);
        Random rnd = new Random();

        super.getPolygon().setRotate(rnd.nextInt(360));

        int accelerationAmount = 1 + rnd.nextInt(10);
        for (int i = 0; i < accelerationAmount; i++) {
            accelerate();
        }

        this.rotationalMovement = 0.5 - rnd.nextDouble();
    }

    @Override
    public void move() {
        super.move();
        super.getPolygon().setRotate(super.getPolygon().getRotate() + rotationalMovement);
    }
}
