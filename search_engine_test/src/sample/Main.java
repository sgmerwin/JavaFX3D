package sample;

import arduino.Arduino;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.net.URL;


/**
 * This was built in IntellJ
 * JavaFX sdk 13 was added to the compiler and to the VM.
 */

public class Main extends Application {

    public static final double Width = 500;
    public static final double Height = 500;

    /**
     * group is the background.
     * group2 has the mouse events and the node commands
     * and sits on top of group.
     * The width and height are an arbitrary reference.
     *
     */

    public static Group group = new Group();
    public static Group group2 = new Group();
    public static Group group3 = new Group();
    Camera camera = new PerspectiveCamera();
    Double anchorX, anchorY;
    Double anchorAngleX = 0.0;
    Double anchorAngleY = 0.0;
    final DoubleProperty angleX = new SimpleDoubleProperty(0);
    final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private Group loadModel(URL url) {
        Group modelRoot = new Group();

        ObjModelImporter importer = new ObjModelImporter();
        importer.read(url);

        for (MeshView view : importer.getImport()) {
            modelRoot.getChildren().add(view);
        }

        return modelRoot;
    }

    public void start(Stage stage){
        stage.setTitle("JavaFX Commands");
        group.getChildren().add(group2);
        group2.setLayoutX(700);
        group2.setLayoutY(300);
        group3.setLayoutX(0);
        group3.setLayoutY(0);
        group2.getChildren().add(group3);
       /* IcosahedronMesh ico = new IcosahedronMesh(5f,1);
        ico.setTextureModeVertices3D(1600,p->(double)p.x*p.y*p.z);
        ico.setScaleX(100);
        ico.setScaleY(100);
        ico.setScaleZ(100);
        group2.getChildren().add(ico);*/

        /*try {
            ObjModelImporter importer = new ObjModelImporter();
            File file = new File("/Users/steve/Documents/javafx/search_engine_test/src/sample/Scooter-obj 2/Scooter-smgrps.obj");
            importer.read(file);
            for (MeshView view : importer.getImport()) {
                view.setScaleX(200);
                view.setScaleY(200);
                view.setScaleZ(200);
                group2.getChildren().add(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*try {
            File file = new File("/Users/steve/Documents/javafx/search_engine_test/src/sample/circuit_2.jpg");
            FileInputStream inputStream = new FileInputStream(file);
            Image image = new Image(inputStream);
            ImageView imageView = new ImageView(image);
            group2.getChildren().add(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        //The true is enabling a depth buffer?
        Scene scene = new Scene(group, Width, Height,true,SceneAntialiasing.BALANCED);

        GroupGestures groupGestures = new GroupGestures(group2);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, groupGestures.getOnMousePressedEventHandler());
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, groupGestures.getOnMouseDraggedEventHandler());

        /**
         * GenTextFieldA builds the 2 text field nodes on the background group
         * and sets the default event with the return key.
         * GenRect and GenText build nodes on the background group.
         */

        GenTextFieldA(500,10);
        Funct.GenRect(800,220,15,50);
        Funct.GenText("Object List",Funct.objectx+35,Funct.objecty);

        /**
         * line_0 and line_1 will be the x and y axes.
         */

        Funct.lineM(0.0,-400.0,0.0,400.0,true);
        Funct.lineM(-400.0,0.0,400.0,0.0,true);


        /**
         * These translate Property calls puts the nodes in
         * reference to (0, 0, 0).
         * The initMouseControl puts the left click roll and
         * the scroll on group 2. There are no mouse events on
         * the background group.
         * The Drag and GroupGestures classes handle the right click
         * panning for group 2.
         */

        group2.translateXProperty().set(Width/2);
        group2.translateYProperty().set(Height/2);
        group2.translateZProperty().set(0);
        initMouseControl(group2, scene, stage);

       /* SurfacePlotMesh surface = new SurfacePlotMesh(generateFunction(0), a, b, 100, 100, 1);
        surface.getTransforms().addAll(new Rotate(200, Rotate.X_AXIS),
                new Rotate(60, Rotate.Y_AXIS));
        surface.setCullFace(CullFace.NONE);
        surface.setTextureModePattern(Patterns.CarbonPatterns.LIGHT_CARBON, 1.0d);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), (ActionEvent event) -> {
            surface.setFunction2D(generateFunction(time));
            time += 0.005;
        });
        Timeline timeLine = new Timeline(keyFrame);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
        surface.setScaleX(100);
        surface.setScaleY(100);
        surface.setScaleZ(100);
        group2.getChildren().addAll(surface);*/

        scene.setFill(Color.BISQUE);
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.show();

    }//start

    static TextField textfieldA = new TextField();
    static TextField textfieldB = new TextField();

    public void GenTextFieldA(double x, double y){
        group.getChildren().addAll(textfieldA,textfieldB);
        textfieldA.setLayoutX(x);
        textfieldA.setLayoutY(y);
        textfieldB.setLayoutX(x);
        textfieldB.setLayoutY(y+30);
        textfieldA.setText("Enter Command");
        textfieldA.setPrefColumnCount(50);
        textfieldB.setText("Return Method Call");
        textfieldB.setPrefColumnCount(75);

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
    }//mousecontrol

    /**
     * In the main method launch args has to be last.
     * Anything after launch args will not run.
     * makeRunAll builds the node command library.
     */

    public static void main(String[] args) {
        Runnable r1 = () -> {
            Arduino mega = new Arduino();

            mega.setPortDescription("/dev/cu.usbmodem14101");
            mega.setBaudRate(9600);

            mega.openConnection();

            System.out.println("open connection  " + mega.openConnection());
            System.out.println("serial port " + mega.getSerialPort());


            while (mega.openConnection()) {
                String i = mega.serialRead(1);
                System.out.println(i);

            }
        };

        Runnable r2 = () -> {
            Funct.makeRunAll();
            System.out.println("Total memory available to java in mb");
            System.out.println(Runtime.getRuntime().totalMemory() / Math.pow(10, 6));
            System.out.println("Program memory usage in mb");
            System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Math.pow(10, 6));
            System.out.println("Max memory available to java in gb");
            System.out.println(Runtime.getRuntime().maxMemory()/ Math.pow(10, 9));
        };

        Runnable r3 = () -> launch(args);

        //Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        //t1.start();
        t2.start();
        t3.start();

    }//main
}//class

