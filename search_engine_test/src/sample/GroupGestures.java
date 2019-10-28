package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class GroupGestures {

    private static final double MAX_SCALE = 10.0d;
    private static final double MIN_SCALE = .1d;

    private Drag groupDrag = new Drag();

    Group group;

    public GroupGestures(Group group) {
        this.group = group;
    }

    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return onMousePressedEventHandler;
    }

    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return onMouseDraggedEventHandler;
    }

    private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {

            // right mouse button => panning
            if (!event.isSecondaryButtonDown())
                return;

            groupDrag.mouseAnchorX = event.getSceneX();
            groupDrag.mouseAnchorY = event.getSceneY();

            groupDrag.translateAnchorX = group.getTranslateX();
            groupDrag.translateAnchorY = group.getTranslateY();

        }

    };

    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {

            // right mouse button => panning
            if (!event.isSecondaryButtonDown())
                return;

            group.setTranslateX(groupDrag.translateAnchorX + event.getSceneX() - groupDrag.mouseAnchorX);
            group.setTranslateY(groupDrag.translateAnchorY + event.getSceneY() - groupDrag.mouseAnchorY);

            event.consume();
        }
    };
}
