import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    public static int WIDTH = 440;
    public static int HEIGHT = 300;

    private InteractiveScene scene;
    private Pane pane;

    List<Enemy> enemies = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private Ship ship;

    @Override
    public void start(Stage window) {
        initScene();
        initShip();
        initEnemies();
        initKeyboardListener();


        window.setTitle("Asteroids!");
        window.setScene(scene);
        window.show();
    }

    private void initKeyboardListener() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (scene.getPressedKeys().getOrDefault(KeyCode.A, false)) {
                    ship.moveLeft();
                    System.out.println("LEFT");
                }
                if (scene.getPressedKeys().getOrDefault(KeyCode.D, false)) {
                    ship.moveRight();
                    System.out.println("RIGHT");
                }
                if (scene.getPressedKeys().getOrDefault(KeyCode.W, false)) {
                    ship.moveForward();
                    System.out.println("UP");
                }
                if (scene.getPressedKeys().getOrDefault(KeyCode.S, false)) {
                    ship.moveBackward();
                    System.out.println("DOWN");
                }

                if (scene.getPressedKeys().getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 5) {
                    Projectile projectile = new Projectile(scene, (int) ship.getPolygon().getTranslateX(),
                            (int) ship.getPolygon().getTranslateY());
                    projectile.getPolygon().setRotate(ship.getPolygon().getRotate() - 90.0);
                    projectiles.add(projectile);

                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));

                    pane.getChildren().add(projectile.getPolygon());
                }

                projectiles.forEach(projectile -> projectile.move());
                // asteroids.forEach(asteroid -> asteroid.move());
                // ship.move();

                // asteroids.forEach(asteroid -> {
                // if (ship.collide(asteroid)) {
                // asteroid.getPolygon().setFill(Color.color(Math.random(), Math.random(),
                // Math.random()));
                // // stop();
                // }
                // });

                // projectiles.forEach(projectile -> {
                // asteroids.forEach(asteroid -> {
                // if (projectile.collide(asteroid)) {
                // projectile.setAlive(false);
                // asteroid.setAlive(false);
                // }
                // });
                // });

                projectiles.stream()
                        .filter(projectile -> !projectile.isAlive())
                        .forEach(projectile -> pane.getChildren().remove(projectile.getPolygon()));
                projectiles.removeAll(projectiles.stream()
                        .filter(projectile -> !projectile.isAlive())
                        .collect(Collectors.toList()));

                enemies.stream()
                        .filter(enemy -> !enemy.isAlive())
                        .forEach(enemy -> pane.getChildren().remove(enemy.getPolygon()));
                enemies.removeAll(enemies.stream()
                        .filter(enemy -> !enemy.isAlive())
                        .collect(Collectors.toList()));

                projectiles.forEach(projectile -> {
                    enemies.forEach(enemy -> {
                        if (projectile.collide(enemy)) {
                            projectile.setAlive(false);
                            enemy.setAlive(false);
                        }
                    });

                });

                // if (Math.random() < 0.005) {
                // Asteroid asteroid = new Asteroid(scene, WIDTH, HEIGHT);
                // if (!asteroid.collide(ship)) {
                // asteroids.add(asteroid);
                // pane.getChildren().add(asteroid.getPolygon());
                // System.out.println("Asteroid added!");
                // }
                // }
            }
        }.start();
    }

    private void initScene() {
        pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);

        scene = new InteractiveScene(pane);
    }

    private void initShip() {
        ship = new Ship(scene, WIDTH / 2, HEIGHT / 2);

        pane.getChildren().add(ship.getPolygon());
    }

    private void initEnemies() {
        int fleetWidth = WIDTH;

        for (int i = 1; i < 7; i++) {
            int padding = fleetWidth / 6;
            int xpos = padding / 2 * i;
            int ypos = 4;

            Enemy enemy = new Enemy(scene, xpos, ypos);
            enemies.add(enemy);
        }
        enemies.forEach(enemy -> pane.getChildren().add(enemy.getPolygon()));

    }
}
