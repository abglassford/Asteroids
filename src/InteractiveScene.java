import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class InteractiveScene extends Scene{
    private Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

    public InteractiveScene(Pane paneParent) {
        super(paneParent);

        setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });

        setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
    }


    public Map<KeyCode, Boolean> getPressedKeys() {
        return pressedKeys;
    }

}
