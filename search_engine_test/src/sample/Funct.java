package sample;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Funct {

    /**
     * This Funct class builds the node command library.
     * It holds arraylists, methods, and instances of the Function interface.
     */

    static List runs = new ArrayList<>();

    static void makeRun(Function run, String name, String call, String desc){
            String editName = name.replace(" ","").toLowerCase();
        if(!runs.contains(run) && !runs.contains(editName)) {
            runs.add(editName);
            runs.add(call);
            runs.add(desc);
            runs.add(run);
        }
        else
            System.out.println("Duplicate makeRun Call");
    }//method

    static void makeRunAll(){
        makeArrList();
        makeRun(cubicCurve,"cubiccurve","cubiccurve,int startx, int starty, int cx1, int cy1, int cx2, int cy2, int endx, int endy","Calls cubic curve 2D from javafx");
        makeRun(circle,"circle","circle, int radius, int x, int y","circle 2D from javafx");
        makeRun(cylinder,"cylinder","cylinder, int radius, int height, int x, int y","cylinder 3D from javafx");
        makeRun(box,"box","box, int depth, int width, int height, int x, int y","box 3D from javafx");
        makeRun(sphere,"sphere","sphere, int radius, int x, int y","sphere 3D from javafx");
        makeRun(commands,"commands","No Call","Prints all possible commands");
        makeRun(line,"line","line,int x1, int y1, int x2, int y2","Line 2D only over the x y plane");
        makeRun(rectangle,"rectangle","rectangle, int height, int width, int x, int y","Rectangle 2D from java fx");
        makeRun(remove,"remove","remove,String object, int array position","removes object from the group");
        makeRun(putBack,"putback","putback,String object, int array position","puts back object from the group's history");
        makeRun(transition,"transition","Object type, array position, int sec, int x, int y, int z, boolean for animation cycle","Animation for node objects");
        makeRun(transitionStop,"transitionstop","transitionstop, array position","stops the animation at the given array position");
        makeRun(textdisplay,"textdisplay","textdisplay, String text displayed, int x , int y, String font type, int font size","Displays text");
        makeRun(rotate,"rotate","rotate, int angle, int x, int y, int z, String axis, String name, int pos","Rotate the given object");
    }//method

    static void decision(){
        String strFieldA = Main.textfieldA.getText();
        if(!strFieldA.contains(",")){
            String name = strFieldA.replace(" ","").toLowerCase();
            if(!runs.contains(name))
                Main.textfieldB.setText("Not A Command");
            else if(name.equals("commands")){
                int i = runs.indexOf(name);
                Function funct = (Function) runs.get(i+3);
                try {
                    String[] temp = new String[0];
                    funct.apply(temp);
                } catch (Exception e) {
                    System.out.println("Function Call Failed");
                }
            }
            else{
                int i = runs.indexOf(name);
                String output = runs.get(i+1).toString() + " "+ runs.get(i+2).toString();
                Main.textfieldB.setText(output);
            }
            }//if
        else{
            String[] parts = strFieldA.split(",");
            String name = parts[0].replace(" ","").toLowerCase();
            if(name.equals("textdisplay")){
                int i = runs.indexOf(name);
                Function funct = (Function) runs.get(i+3);
                try {
                    String[] editParts = new String[parts.length];
                    for (int k = 0; k < parts.length; k++) {
                        editParts[k] = parts[k].replace(" ","");
                    }
                    funct.apply(editParts);
                } catch (Exception e) {
                    System.out.println("Function Call Failed");
                }
            }
            else if(!runs.contains(name))
                Main.textfieldB.setText("Not A Command");
            else{
                int i = runs.indexOf(name);
                Function funct = (Function) runs.get(i+3);
                try {
                    String[] editParts = new String[parts.length];
                    for (int k = 0; k < parts.length; k++) {
                        editParts[k] = parts[k].replace(" ","").toLowerCase();
                    }
                    funct.apply(editParts);
                } catch (Exception e) {
                    System.out.println("Function Call Failed");
                }
            }
        }//else
        }//decision

    static int objectx = 15;
    static int objecty = 70;
    static ArrayList<String> arrString = new ArrayList<>();
    static int strCount = 0;

    static List arrNodes = new ArrayList<>();
    static void makeArrList(){
        arrNodes.add("box");
        arrNodes.add(arrBox);
        arrNodes.add("circle");
        arrNodes.add(arrCircle);
        arrNodes.add("rectangle");
        arrNodes.add(arrRectangle);
        arrNodes.add("sphere");
        arrNodes.add(arrSphere);
        arrNodes.add("cylinder");
        arrNodes.add(arrCyl);
        arrNodes.add("transition");
        arrNodes.add(arrTrans);
        arrNodes.add("cubiccurve");
        arrNodes.add(arrCubicCurve);
        arrNodes.add("transitionstop");
        arrNodes.add(arrTransStop);
        arrNodes.add("textdisplay");
        arrNodes.add(arrTextDisplay);
        arrNodes.add("rotate");
        arrNodes.add(arrRotate);
    }

    static ArrayList<CubicCurve> arrCubicCurve = new ArrayList<>();
    static int cubicCurveCount = 0;
    static List cubicCurveList = new ArrayList();

    static Function<String[], Boolean> cubicCurve = (String[] str) ->{
        if(!(str.length == 9)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                cubicCurveM(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]),Integer.parseInt(str[5]),Integer.parseInt(str[6]),Integer.parseInt(str[7]),Integer.parseInt(str[8]));
                Main.textfieldB.setText("cubicCurveM("+Integer.parseInt(str[1])+","+Integer.parseInt(str[2])+","+Integer.parseInt(str[3])+","+Integer.parseInt(str[4])+","+Integer.parseInt(str[5])+","+Integer.parseInt(str[6])+","+Integer.parseInt(str[7])+","+Integer.parseInt(str[8])+")");
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function

    static void cubicCurveM(int startx, int starty, int cx1, int cy1, int cx2, int cy2, int endx, int endy){
        arrCubicCurve.add(new CubicCurve());
        arrCubicCurve.get(cubicCurveCount).setStartX(startx);
        arrCubicCurve.get(cubicCurveCount).setStartY(starty);
        arrCubicCurve.get(cubicCurveCount).setControlX1(cx1);
        arrCubicCurve.get(cubicCurveCount).setControlY1(cy1);
        arrCubicCurve.get(cubicCurveCount).setControlX2(cx2);
        arrCubicCurve.get(cubicCurveCount).setControlY2(cy2);
        arrCubicCurve.get(cubicCurveCount).setEndX(endx);
        arrCubicCurve.get(cubicCurveCount).setEndY(endy);
        arrCubicCurve.get(cubicCurveCount).setFill(Color.TRANSPARENT);
        arrCubicCurve.get(cubicCurveCount).setStroke(Color.BLACK);
        Main.group2.getChildren().add(arrCubicCurve.get(cubicCurveCount));
        arrString.add("CubicCurve_"+cubicCurveCount);
        GenText("CubicCurve_"+cubicCurveCount+" ("+startx+" ,"+starty+" ,"+endx+" ,"+endy+")",objectx,objecty+25+strCount*20);
        cubicCurveList.add("CubicCurve_"+cubicCurveCount);
        cubicCurveList.add(strCount);
        cubicCurveList.add(objecty+25+strCount*20);
        ++strCount;
        ++cubicCurveCount;
    }//method

    static ArrayList<Circle> arrCircle = new ArrayList<>();
    static int circleCount = 0;
    static List circleList = new ArrayList();

    static void circleM(int radius, int x, int y){
        arrCircle.add(new Circle());
        arrCircle.get(circleCount).setRadius(radius);
        arrCircle.get(circleCount).setLayoutX(x);
        arrCircle.get(circleCount).setLayoutY(y);
        arrCircle.get(circleCount).setFill(Color.TRANSPARENT);
        arrCircle.get(circleCount).setStroke(Color.BLACK);
        Main.group2.getChildren().add(arrCircle.get(circleCount));
        arrString.add("Circle_"+circleCount);
        GenText("Circle_"+circleCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        circleList.add("Circle_"+circleCount);
        circleList.add(strCount);
        circleList.add(objecty+25+strCount*20);
        ++strCount;
        ++circleCount;
    }//method

    static Function<String[], Boolean> circle = (String[] str) ->{
        if(!(str.length == 4)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                circleM(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]));
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function

    static ArrayList<Line> arrLine = new ArrayList<>();
    static int lineCount = 0;
    static List lineList = new ArrayList();

    static void lineM(int x1, int y1, int x2, int y2 ){
        arrLine.add(new Line(x1,y1,x2,y2));
        Main.group2.getChildren().add(arrLine.get(lineCount));
        arrString.add("Line_"+lineCount);
        GenText("Line_"+lineCount+" ("+x1+" ,"+y1+" ,"+x2+" ,"+y2+")",objectx,objecty+25+strCount*20);
        lineList.add("Line_"+lineCount);
        lineList.add(strCount);
        lineList.add(objecty+25+strCount*20);
        ++strCount;
        ++lineCount;
    }//method

    static Function<String[], Boolean> line = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                lineM(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]));
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function
    /**
     * GenText and GenRect are only being used by the
     * background group in the scene where there is not any
     * mouse action events.
     * GenRec make the rectangle the object list is in.
     * GenText makes the object list.
     * Otherwise there would have to be a group parameter in
     * these method call.
     * Make a group parameter or make different methods?
     */
    static ArrayList<Text> arrText = new ArrayList<>();
    static int textCount = 0;

    static void GenText(String str, int x , int y ){
        arrText.add(new Text());
        arrText.get(textCount).setText(str);
        arrText.get(textCount).setLayoutX(x);
        arrText.get(textCount).setLayoutY(y);
        Main.group.getChildren().add(arrText.get(textCount));
        ++textCount;
    }//method

    static int rectCount = 0;
    static ArrayList<Rectangle> arrRect = new ArrayList<>();

    static void GenRect(int height, int width, int x, int y){
        arrRect.add(new Rectangle());
        arrRect.get(rectCount).setHeight(height);
        arrRect.get(rectCount).setWidth(width);
        arrRect.get(rectCount).setLayoutX(x);
        arrRect.get(rectCount).setLayoutY(y);
        arrRect.get(rectCount).setFill(Color.LIGHTBLUE);
        Main.group.getChildren().add(arrRect.get(rectCount));
        ++rectCount;
    }//method

    static List cylinderList = new ArrayList();
    static ArrayList<Cylinder> arrCyl = new ArrayList<>();
    static int cylinderCount = 0;

    static void cylinderM(int radius, int height, int x, int y){
        arrCyl.add(new Cylinder());
        arrCyl.get(cylinderCount).setRadius(radius);
        arrCyl.get(cylinderCount).setHeight(height);
        arrCyl.get(cylinderCount).setLayoutX(x);
        arrCyl.get(cylinderCount).setLayoutY(y);
        Main.group2.getChildren().add(arrCyl.get(cylinderCount));
        arrString.add("Cylinder_"+cylinderCount);
        GenText("Cylinder_"+cylinderCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        cylinderList.add("Cylinder_"+cylinderCount);
        cylinderList.add(strCount);
        cylinderList.add(objecty+25+strCount*20);
        ++cylinderCount;
        ++strCount;
    }//method

    static Function<String[], Boolean> cylinder = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                cylinderM(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]));
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function

    static ArrayList<Box> arrBox = new ArrayList<>();
    static int boxCount = 0;
    static List boxList = new ArrayList();

    static void boxM(int depth, int width, int height, int x, int y){
        arrBox.add(new Box());
        arrBox.get(boxCount).setDepth(depth);
        arrBox.get(boxCount).setWidth(width);
        arrBox.get(boxCount).setHeight(height);
        arrBox.get(boxCount).setLayoutX(x);
        arrBox.get(boxCount).setLayoutY(y);
        Main.group2.getChildren().add(arrBox.get(boxCount));
        arrString.add("Box_"+boxCount);
        GenText("Box_"+boxCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        boxList.add("Box_"+boxCount);
        boxList.add(strCount);
        boxList.add(objecty+25+strCount*20);
        ++boxCount;
        ++strCount;

        //PhongMaterial material = new PhongMaterial();
        //material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/metal.jpeg")));
        //box.setMaterial(material);
    }//method

    static Function<String[], Boolean> box = (String[] str) ->{
        if(!(str.length == 6)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                boxM(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]),Integer.parseInt(str[5]));
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function


    static ArrayList<Sphere> arrSphere = new ArrayList<>();
    static int sphereCount = 0;
    static List sphereList = new ArrayList();

    static void sphereM(int radius, int x, int y){
        arrSphere.add(new Sphere());
        arrSphere.get(sphereCount).setRadius(radius);
        arrSphere.get(sphereCount).setLayoutX(x);
        arrSphere.get(sphereCount).setLayoutY(y);
        Main.group2.getChildren().add(arrSphere.get(sphereCount));
        arrString.add("Sphere_"+sphereCount);
        GenText("Sphere_"+sphereCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        sphereList.add("Sphere_"+sphereCount);
        sphereList.add(strCount);
        sphereList.add(objecty+25+strCount*20);
        ++sphereCount;
        ++strCount;
    }//method

    static Function<String[], Boolean> sphere = (String[] str) ->{
        if(!(str.length == 4)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                sphereM(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]));
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function

    static Function<String[], Boolean> commands = (String[] str) ->{
            try {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < runs.size(); i+=4) {
                    sb.append(runs.get(i).toString());
                    sb.append(" , ");
                }
                Main.textfieldB.setText(sb.toString());
            } catch (Exception e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
    };//function

    static ArrayList<Rectangle> arrRectangle = new ArrayList<>();
    static int rectangleCount = 0;
    static List rectangleList = new ArrayList();

    static void rectM(int height, int width, int x, int y){
        arrRectangle.add(new Rectangle());
        arrRectangle.get(rectangleCount).setHeight(height);
        arrRectangle.get(rectangleCount).setWidth(width);
        arrRectangle.get(rectangleCount).setLayoutX(x);
        arrRectangle.get(rectangleCount).setLayoutY(y);
        arrRectangle.get(rectangleCount).setFill(Color.TRANSPARENT);
        arrRectangle.get(rectangleCount).setStroke(Color.BLACK);
        System.out.println(arrRectangle.get(rectangleCount).getClass());
        Main.group2.getChildren().add(arrRectangle.get(rectangleCount));
        arrString.add("Rectangle_"+rectangleCount);
        GenText("Rectangle_"+rectangleCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        rectangleList.add("Rectangle_"+rectangleCount);
        rectangleList.add(strCount);
        rectangleList.add(objecty+25+strCount*20);
        ++rectangleCount;
        ++strCount;
    }//method

    static Function<String[], Boolean> rectangle = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                rectM(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]));
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function

    static Function<String[], Boolean> remove = (String[] str) ->{
        if(!(str.length == 3)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else if(!arrNodes.contains(str[1])){
            Main.textfieldB.setText("Node not in List");
            return false;
        }
        else{
            try {
                int i = arrNodes.indexOf(str[1]);
                int j = Integer.parseInt(str[2]);
                List nodes = (ArrayList) arrNodes.get(i+1);
                Object node = nodes.get(j);
                removeM(str[1],j,node);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function

    static void removeM(String name, int i, Object node){
        try {
            Main.group2.getChildren().remove((Node) node);
            Main.textfieldB.setText("remove, "+name+"_" + i);
        } catch (Exception e) {
            Main.textfieldB.setText("Ran the remove function but the object did not exist");
        }
    }//method

    static Function<String[], Boolean> putBack = (String[] str) ->{
        if(!(str.length == 3)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else if(!arrNodes.contains(str[1])){
            Main.textfieldB.setText("Node not in List");
            return false;
        }
        else{
            try {
                int i = arrNodes.indexOf(str[1]);
                int j = Integer.parseInt(str[2]);
                List nodes = (ArrayList) arrNodes.get(i+1);
                Object node = nodes.get(j);
                putBackM(str[1],j,node);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function

    static void putBackM(String name, int i, Object node){
        try {
            Main.group2.getChildren().add((Node) node);
            Main.textfieldB.setText("add, "+name+"_" + i);
        } catch (Exception e) {
            Main.textfieldB.setText("Ran the putBack function but the object did not exist");
        }
    }//method

    static ArrayList<TranslateTransition> arrTrans = new ArrayList<>();
    static int transCount = 0;
    static List transitionList = new ArrayList();

    static void transM(int sec, int x, int y, int z, Object node, boolean cycle){
        arrTrans.add(new TranslateTransition());
        arrTrans.get(transCount).setDuration(Duration.seconds(sec));
        arrTrans.get(transCount).setToX(x);
        arrTrans.get(transCount).setToY(y);
        arrTrans.get(transCount).setToZ(z);
        if (cycle){
            arrTrans.get(transCount).setAutoReverse(true);
            arrTrans.get(transCount).setCycleCount(Animation.INDEFINITE);
        }
        arrTrans.get(transCount).setNode((Node) node);
        arrTrans.get(transCount).play();
        arrString.add("transition_"+transCount);
        GenText("Transition_"+transCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        transitionList.add("Transition_"+transCount);
        transitionList.add(strCount);
        transitionList.add(objecty+25+strCount*20);
        ++transCount;
        ++strCount;
    }//method

    static Function<String[], Boolean> transition = (String[] str) ->{
        if(!(str.length == 8)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else if(!arrNodes.contains(str[1])){
            Main.textfieldB.setText("Node not in Transition List");
            return false;
        }
        else{
            try {
                int i = arrNodes.indexOf(str[1]);
                int j = Integer.parseInt(str[2]);
                boolean cycle = false;
                List nodes = (ArrayList) arrNodes.get(i+1);
                Object node = nodes.get(j);
                if(str[7].toLowerCase().replace(" ","").equals("true"))
                    cycle = true;
                transM(Integer.parseInt(str[3]),Integer.parseInt(str[4]),Integer.parseInt(str[5]),Integer.parseInt(str[6]),node,cycle);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
            Main.textfieldB.setText("Something went wrong inside of Transition call");
            return false;
            }
            return true;
        }
    };//function

    static Function<String[], Boolean> transitionStop = (String[] str) ->{
        if(!(str.length == 2)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        int i = Integer.parseInt(str[1]);
        if(arrTrans.size()<(i+1)){
            Main.textfieldB.setText("Node not in Transition List");
            return false;
        }
        else{
            try {
                transStopM(i);
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of Transition call");
                return false;
            }
            return true;
        }
    };//function

    static ArrayList<TranslateTransition> arrTransStop = new ArrayList<>();
    static int transStopCount = 0;
    static List transStopList = new ArrayList();

    static void transStopM(int i){
        try{
            arrTrans.get(i).stop();
            arrTransStop.add(arrTrans.get(i));
            arrString.add("transitionStop_"+transStopCount);
            GenText("TransitionStop_"+transStopCount,objectx,objecty+25+strCount*20);
            transStopList.add("TransitionStop_"+transStopCount);
            transStopList.add(strCount);
            transStopList.add(objecty+25+strCount*20);
            ++transStopCount;
            ++strCount;
        }
        catch(Exception e){
            Main.textfieldB.setText("Something went wrong inside of Transition call");
        }
    }//method

    static ArrayList<Text> arrTextDisplay = new ArrayList<>();
    static int textDisplayCount = 0;
    static List textDisplayList = new ArrayList();

    static void textM(String str, int x , int y, String type, int size){
        arrTextDisplay.add(new Text());
        arrTextDisplay.get(textDisplayCount).setText(str);
        arrTextDisplay.get(textDisplayCount).setLayoutX(x);
        arrTextDisplay.get(textDisplayCount).setLayoutY(y);
        arrTextDisplay.get(textDisplayCount).setFont(Font.font(type,size));
        arrTextDisplay.get(textDisplayCount).setFill(Color.BLACK);
        Main.group2.getChildren().add(arrTextDisplay.get(textDisplayCount));
        arrString.add("textDisplay_"+textDisplayCount);
        GenText("TextDisplay_"+textDisplayCount+"( "+x+" ,"+y+" )",objectx,objecty+25+strCount*20);
        textDisplayList.add("TestDisplay_"+textDisplayCount);
        textDisplayList.add(strCount);
        textDisplayList.add(objecty+25+strCount*20);
        ++textDisplayCount;
        ++strCount;
    }//method

    static Function<String[], Boolean> textdisplay = (String[] str) ->{
        if(!(str.length == 6)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        int i = Integer.parseInt(str[5]);
        int x = Integer.parseInt(str[2]);
        int y = Integer.parseInt(str[3]);
            try {
                textM(str[1],x,y,str[4],i);
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of Text call");
                return false;
            }
            return true;
        };//function

    static ArrayList<Rotate> arrRotate = new ArrayList<>();
    static int rotateCount = 0;
    static List rotateList = new ArrayList();

    static void rotateM(int angle, int x, int y, int z, String axis, String name, int pos){
        arrRotate.add(new Rotate());
        arrRotate.get(rotateCount).setAngle(angle);
        arrRotate.get(rotateCount).setPivotX(x);
        arrRotate.get(rotateCount).setPivotY(y);
        arrRotate.get(rotateCount).setPivotZ(z);
        if(axis.equals("x"))
            arrRotate.get(rotateCount).setAxis(Rotate.X_AXIS);
        else if(axis.equals("y"))
            arrRotate.get(rotateCount).setAxis(Rotate.Y_AXIS);
        else
            arrRotate.get(rotateCount).setAxis(Rotate.Z_AXIS);
        if(arrNodes.contains(name)) {
            try {
                int i = arrNodes.indexOf(name);
                List nodes = (ArrayList) arrNodes.get(i + 1);
                Node node = (Node) nodes.get(pos);
                node.getTransforms().add(arrRotate.get(rotateCount));
                rotateB(name, pos, angle, axis);
            }catch(Exception e){
                Main.textfieldB.setText("Error in rotate call");
            }
        }
        else
            Main.textfieldB.setText("Not A Command");
    }//method

    static void rotateB(String name, int pos, int angle, String axis) {
        arrString.add("rotate_" + rotateCount);
        GenText("Rotate" + rotateCount + " " + name + "_" + pos+" angle "+angle+" axis "+axis, objectx, objecty + 25 + strCount * 20);
        rotateList.add("Rotation_" + rotateCount);
        rotateList.add(strCount);
        rotateList.add(objecty + 25 + strCount * 20);
        ++rotateCount;
        ++rotateCount;
    }

    static Function<String[], Boolean> rotate = (String[] str) ->{
        if(!(str.length == 8)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int angle = Integer.parseInt(str[1]);
                int x = Integer.parseInt(str[2]);
                int y = Integer.parseInt(str[3]);
                int z = Integer.parseInt(str[4]);
                String axis = str[5].replace(" ","").toLowerCase();
                String name = str[6].replace(" ","").toLowerCase();
                int pos = Integer.parseInt(str[7]);
                rotateM(angle,x,y,z,axis,name,pos);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of Rotation call");
                return false;
            }
            return true;
        }
    };//function





}//class