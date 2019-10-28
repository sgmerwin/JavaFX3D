package sample;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static final double Width = 500;
    public static final double Height = 500;
    public static Group group = new Group();
    public static Group group2 = new Group();
    Camera camera = new PerspectiveCamera();
    Double anchorX, anchorY;
    Double anchorAngleX = 0.0;
    Double anchorAngleY = 0.0;
    final DoubleProperty angleX = new SimpleDoubleProperty(0);
    final DoubleProperty angleY = new SimpleDoubleProperty(0);

    public void start(Stage stage){
        stage.setTitle("Testing Method Calls");
        group.getChildren().add(group2);
        group2.setLayoutX(700);
        group2.setLayoutY(300);

        Scene scene = new Scene(group, Width, Height);

        GroupGestures groupGestures = new GroupGestures(group2);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, groupGestures.getOnMousePressedEventHandler());
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, groupGestures.getOnMouseDraggedEventHandler());

        GenTextFieldA(group, 500,10);
        Funct.GenRect(800,220,15,50);
        Funct.GenText("Object List",Funct.objectx+35,Funct.objecty);
        Funct.lineM(0,-400,0,400);
        Funct.lineM(-400,0,400,0);

        group2.translateXProperty().set(Width/2);
        group2.translateYProperty().set(Height/2);
        group2.translateZProperty().set(0);
        initMouseControl(group2, scene, stage);

        scene.setFill(Color.WHITE);
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.show();

    }//start

    static TextField textfieldA = new TextField();
    static TextField textfieldB = new TextField();

    public void GenTextFieldA(Group group, double x, double y){
        group.getChildren().addAll(textfieldA,textfieldB);
        textfieldA.setLayoutX(x);
        textfieldA.setLayoutY(y);
        textfieldB.setLayoutX(x);
        textfieldB.setLayoutY(y+30);
        textfieldA.setText("Enter Command");
        textfieldA.setPrefColumnCount(50);
        textfieldB.setText("Return Method Call");
        textfieldB.setPrefColumnCount(50);

        EventHandler<ActionEvent> eventA = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                Funct.decision();
            }
        };
        textfieldA.setOnAction(eventA);
    }//GenTextFieldA

    private void initMouseControl (Group group, Scene scene, Stage stage){
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
        scene.setOnMousePressed(event ->{
            if(event.getButton() == MouseButton.PRIMARY) {
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
                anchorAngleX = angleX.get();
                anchorAngleY = angleY.get();
            }
        });
        scene.setOnMouseDragged((MouseEvent event) -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
                angleY.set(anchorAngleY - (anchorX - event.getSceneX()));
            }
        });
        stage.addEventHandler(ScrollEvent.SCROLL, event -> {double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() + delta);
        });
    }

    public static void main(String[] args) {
        Funct.makeRunAll();
        launch(args);



    }
}

