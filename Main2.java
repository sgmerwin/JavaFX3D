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

class Drag {
    double mouseAnchorX;
    double mouseAnchorY;
    double translateAnchorX;
    double translateAnchorY;
}

class GroupGestures {

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

public class Main2 extends Application {

    public static final double Width = 500;
    public static final double Height = 500;
    Group group = new Group();
    Group group2 = new Group();

    //GridPane gridPaneTop = new GridPane();
    //GridPane gridPaneLeft = new GridPane();
    //GridPane gridPaneMain = new GridPane();
    Camera camera = new PerspectiveCamera();
    Double anchorX, anchorY;
    Double anchorAngleX = 0.0;
    Double anchorAngleY = 0.0;
    final DoubleProperty angleX = new SimpleDoubleProperty(0);
    final DoubleProperty angleY = new SimpleDoubleProperty(0);

    //ArrayList<Cylinder> arrCyl = new ArrayList<>();
    Cylinder[] arrCyl = new Cylinder[100];
    static int cylinderCount = 0;
    Box[] arrBox = new Box[100];
    static int boxCount = 0;
    Sphere[] arrSphere = new Sphere[100];
    static int sphereCount = 0;
    Line[] arrLine = new Line[100];
    static int lineCount = 0;
    Text[] arrText = new Text[100];
    static int textCount = 0;
    static String strFieldA = "";
    Rectangle[] arrRect = new Rectangle[100];
    static int rectCount = 0;
    static int objectx = 15;
    static int objecty = 70;
    String[] arrString = new String[100];
    static int strCount = 0;
    List boxList = new ArrayList();
    List sphereList = new ArrayList();
    List cylinderList = new ArrayList();
    List lineList = new ArrayList();
    List lineSin = new ArrayList();
    static int sinCount = 0;

    TextField textfieldA = new TextField();
    TextField textfieldB = new TextField();

    Circle[] arrCircle = new Circle[100];
    static int circleCount = 0;
    CubicCurve[] arrCubicCurve = new CubicCurve[100];
    static int cubiccurveCount = 0;

    public void start(Stage stage){

        stage.setTitle("Testing Layouts");
        group.getChildren().add(group2);
        group2.setLayoutX(700);
        group2.setLayoutY(300);

        Scene scene = new Scene(group, Width, Height);

        GroupGestures groupGestures = new GroupGestures(group2);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, groupGestures.getOnMousePressedEventHandler());
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, groupGestures.getOnMouseDraggedEventHandler());

        GenTextFieldA(group, 500,10);
        GenRect(group,800,200,15,50);
        GenText(group, "Object List",objectx+35,objecty);
        GenLine(0,-400,0,400);
        GenLine(-400,0,400,0);

        group2.translateXProperty().set(Width/2);
        group2.translateYProperty().set(Height/2);
        group2.translateZProperty().set(0);
        initMouseControl(group2, scene, stage);

        scene.setFill(Color.WHITE);
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.show();

    }//start

    public void mathSin(double x1, double x2, double inc){
        ArrayList<Double> domain = new ArrayList<>();
        ArrayList<Double> range = new ArrayList<>();
        if (x1 >= x2)
            textfieldB.setText("Not A Correct Domain");
        else
        {
            while(x1 < x2){
                domain.add(x1);
                range.add(Math.sin(x1)*100);
                x1 = x1+inc;
            }
            for(int i = 0; i< domain.size(); i+=2){
                Line line = new Line(domain.get(i),range.get(i),domain.get(i+1),range.get(i+1));
                group2.getChildren().add(line);
            }
        }
    }

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
                strFieldA = textfieldA.getText();
                if(strFieldA.toLowerCase().equals("sphere"))
                {
                    textfieldB.setText("sphere,radius,x,y");
                }
                else if(strFieldA.toLowerCase().equals("cylinder"))
                {
                    textfieldB.setText("cylinder,radius,height,x,y");
                }
                else if(strFieldA.toLowerCase().equals("box"))
                {
                    textfieldB.setText("box,depth,width,height,x,y");
                }
                else if(strFieldA.toLowerCase().equals("line"))
                {
                    textfieldB.setText("line,x1,y1,x2,y2");
                }
                else if(strFieldA.toLowerCase().equals("remove"))
                {
                    textfieldB.setText("remove,object,array position or remove,axis");
                }
                else if(strFieldA.toLowerCase().equals("sin"))
                {
                    textfieldB.setText("sin,start x, end x, increment");
                }
                else if(strFieldA.toLowerCase().equals("circle"))
                {
                    textfieldB.setText("circle, radius, x, y");
                }
                else if(strFieldA.toLowerCase().equals("cubiccurve"))
                {
                    textfieldB.setText("cubiccurve, start x, start y, control x1, control y1, control x2, control y2, end x, end y");
                }
                else if(strFieldA.contains(","))
                {
                    String parts[] = strFieldA.split(",");
                    if(parts[0].toLowerCase().equals("cylinder")  && parts.length ==5){
                        GenCyl(Double.parseDouble(parts[1]),Double.parseDouble(parts[2]),Double.parseDouble(parts[3]),Double.parseDouble(parts[4]));
                        textfieldB.setText("GenCyl("+parts[1]+","+parts[2]+","+parts[3]+","+parts[4]+")");
                    }//cylinder
                    else if(parts[0].toLowerCase().equals("box")  && parts.length ==6) {
                        GenBox(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));
                        textfieldB.setText("GenBox(" + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + ")");
                    }
                    else if(parts[0].toLowerCase().equals("sphere")  && parts.length ==4) {GenSphere(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
                        textfieldB.setText("GenSphere(" + parts[1] + "," + parts[2] + "," + parts[3] + ")");
                    }
                    else if(parts[0].toLowerCase().equals("line")  && parts.length ==5) {GenLine(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]),Double.parseDouble(parts[4]));
                        textfieldB.setText("GenLine(" + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4]+")");
                    }
                    else if(parts[0].toLowerCase().equals("sin")  && parts.length ==4) {mathSin(1,2,.1);
                        textfieldB.setText("mathSin(" + parts[1] + "," + parts[2] + "," + parts[3] + ")");
                    }
                    else if(parts[0].toLowerCase().equals("circle")  && parts.length ==4) {GenCircle(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
                        textfieldB.setText("GenCircle(" + parts[1] + "," + parts[2] + "," + parts[3] + ")");
                    }
                    else if(parts[0].toLowerCase().equals("cubiccurve")  && parts.length ==9) {GenCubicCurve(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4]),Integer.parseInt(parts[5]),Integer.parseInt(parts[6]),Integer.parseInt(parts[7]),Integer.parseInt(parts[8]));
                        textfieldB.setText("GenCubicCurve(" + parts[1] + "," + parts[2] + "," + parts[3] + parts[4]+","+parts[5]+","+parts[6]+","+parts[7]+","+parts[8]+")");
                    }
                    else if(parts[0].toLowerCase().equals("remove")){
                        if(parts[1].toLowerCase().equals("box")){
                            group2.getChildren().remove(arrBox[Integer.parseInt(parts[2])]);
                        }
                        else if(parts[1].toLowerCase().equals("sphere")){
                            group2.getChildren().remove(arrSphere[Integer.parseInt(parts[2])]);
                        }
                        else if(parts[1].toLowerCase().equals("cylinder")){
                            group2.getChildren().remove(arrCyl[Integer.parseInt(parts[2])]);
                        }
                        else if(parts[1].toLowerCase().equals("line")){
                            group2.getChildren().remove(arrLine[Integer.parseInt(parts[2])]);
                        }
                        else if(parts[1].toLowerCase().equals("axis")){
                            group2.getChildren().remove(arrLine[0]);
                            group2.getChildren().remove(arrLine[1]);
                        }
                    }
                }

                else{
                    textfieldB.setText("Not A Command");
                }
            }
        };
        textfieldA.setOnAction(eventA);
    }

    public void GenCubicCurve(int startx, int starty, int cx1, int cy1, int cx2, int cy2, int endx, int endy){
        arrCubicCurve[cubiccurveCount] = new CubicCurve();
        arrCubicCurve[cubiccurveCount].setStartX(startx);
        arrCubicCurve[cubiccurveCount].setStartY(starty);
        arrCubicCurve[cubiccurveCount].setControlX1(cx1);
        arrCubicCurve[cubiccurveCount].setControlY1(cy1);
        arrCubicCurve[cubiccurveCount].setControlX2(cx2);
        arrCubicCurve[cubiccurveCount].setControlY2(cy2);
        arrCubicCurve[cubiccurveCount].setEndX(endx);
        arrCubicCurve[cubiccurveCount].setEndY(endy);
        arrCubicCurve[cubiccurveCount].setFill(Color.TRANSPARENT);
        arrCubicCurve[cubiccurveCount].setStroke(Color.BLACK);
        group2.getChildren().add(arrCubicCurve[cubiccurveCount]);
        ++cubiccurveCount;
    }

    public void GenCircle(int radius, int x, int y){
        arrCircle[circleCount] = new Circle();
        arrCircle[circleCount].setRadius(radius);
        arrCircle[circleCount].setLayoutX(x);
        arrCircle[circleCount].setLayoutY(y);
        arrCircle[circleCount].setFill(Color.TRANSPARENT);
        arrCircle[circleCount].setStroke(Color.BLACK);
        group2.getChildren().add(arrCircle[circleCount]);
        ++circleCount;
    }

    public void GenRect(Group group, int height, int width, int x, int y){
        arrRect[rectCount] = new Rectangle();
        arrRect[rectCount].setWidth(width);
        arrRect[rectCount].setHeight(height);
        arrRect[rectCount].setLayoutX(x);
        arrRect[rectCount].setLayoutY(y);
        arrRect[rectCount].setFill(Color.LIGHTBLUE);
        group.getChildren().add(arrRect[rectCount]);
        ++rectCount;
    }

    public void GenText(Group group, String str, int x , int y ){
        arrText[textCount] = new Text();
        arrText[textCount].setText(str);
        arrText[textCount].setLayoutX(x);
        arrText[textCount].setLayoutY(y);
        group.getChildren().add(arrText[textCount]);
        ++textCount;
    }//method

    public void GenCyl(double radius, double height, double x, double y){
        arrCyl[cylinderCount] = new Cylinder();
        arrCyl[cylinderCount].setRadius(radius);
        arrCyl[cylinderCount].setHeight(height);
        arrCyl[cylinderCount].setLayoutX(x);
        arrCyl[cylinderCount].setLayoutY(y);
        group2.getChildren().add(arrCyl[cylinderCount]);
        arrString[strCount]="Cylinder_"+cylinderCount;
        GenText(group, "Cylinder_"+cylinderCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        cylinderList.add("Cylinder_"+cylinderCount);
        cylinderList.add(strCount);
        cylinderList.add(objecty+25+strCount*20);
        ++cylinderCount;
        ++strCount;
    }//method

    public void GenBox(double depth, double width, double height, double x, double y){
        arrBox[boxCount] = new Box();
        arrBox[boxCount].setDepth(depth);
        arrBox[boxCount].setWidth(width);
        arrBox[boxCount].setHeight(height);
        arrBox[boxCount].setLayoutX(x);
        arrBox[boxCount].setLayoutY(y);
        group2.getChildren().add(arrBox[boxCount]);
        arrString[strCount]="Box_"+boxCount;
        GenText(group, "Box_"+boxCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        boxList.add("Box_"+boxCount);
        boxList.add(strCount);
        boxList.add(objecty+25+strCount*20);
        ++boxCount;
        ++strCount;

        //PhongMaterial material = new PhongMaterial();
        //material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/metal.jpeg")));
        //box.setMaterial(material);
    }//method

    public void GenSphere(double radius, double x, double y){
        arrSphere[sphereCount] = new Sphere();
        arrSphere[sphereCount].setRadius(radius);
        arrSphere[sphereCount].setLayoutX(x);
        arrSphere[sphereCount].setLayoutY(y);
        group2.getChildren().add(arrSphere[sphereCount]);
        arrString[strCount]="Sphere_"+sphereCount;
        GenText(group, "Sphere_"+sphereCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        sphereList.add("Sphere_"+sphereCount);
        sphereList.add(strCount);
        sphereList.add(objecty+25+strCount*20);
        ++sphereCount;
        ++strCount;
    }//method

    public void GenLine(double x1, double y1, double x2, double y2 ){
        arrLine[lineCount] = new Line(x1,y1,x2,y2);
        group2.getChildren().add(arrLine[lineCount]);
        arrString[strCount]="Sphere_"+sphereCount;
        GenText(group, "Line_"+lineCount+" ("+x1+" ,"+y1+" ,"+x2+" ,"+y2+")",objectx,objecty+25+strCount*20);
        lineList.add("Line_"+lineCount);
        lineList.add(strCount);
        lineList.add(objecty+25+strCount*20);
        ++strCount;
        ++lineCount;
    }//method

    public void grid(GridPane gridPane){
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.LEFT);
       // ColumnConstraints columnTwoConstraints = new ColumnConstraints(200,200,Double.MAX_VALUE);
       // columnTwoConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints);
    }//grid

    public void addUIControlsTOP(GridPane gridPane){
        Label headerLabel = new Label("Top Grid Pane");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20,0,20,0));

        Label CommandLabel = new Label("Enter Commands ");
        gridPane.add(CommandLabel, 1,0,2,1);

        TextField CommandField = new TextField();
        CommandField.setPrefHeight(40);
        gridPane.add(CommandField, 1,3);
    }

    public void addUIControlsLEFT(GridPane gridPane){
        Label headerLabel = new Label("Left Grid Pane");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.LEFT);
        GridPane.setMargin(headerLabel, new Insets(20,0,20,0));
    }

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
        launch(args);


    }
}

