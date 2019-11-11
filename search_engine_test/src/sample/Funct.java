package sample;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PointLight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        makeRun(fxmlloader,"fxmlloader","fxmlloader, String file","FXMLLoader will load the provide fxml file");
        makeRun(color2D,"color2d","color2d, int red, int green, int blue, String name, int pos","Color2D adds the RGB value to the 2D node");
        makeRun(color3D,"color3d","color3d int diffRed, int diffGreen, int diffBlue, String name, int pos","Color3D adds a diffused to a 3D shape");
        makeRun(scale2D,"scale2d","scale, int x, int y, int z, String name, int pos","scale changes the scale of the object");
        makeRun(scale3D,"scale3d","scale, int x, int y, int z, String name, int pos","scale changes the scale of the object");
        makeRun(light,"light","light, x, y, z, red, green, blue","Adds a light to the group");
        makeRun(drawmodeline,"drawmodeline","drawmodeline, String name, int pos", "Sets the draw mode of a 3D shape to line");
        makeRun(drawmodefill,"drawmodefill","drawmodefill, String name, int pos", "Sets the draw mode of a 3D shape to fill");
        makeRun(floatarray,"floatarray","floatarray, {your numbers}","Stores the float array in an array");
        makeRun(intarray,"intarray","intarray, {your numbers}","stores the int array in an array");
        makeRun(mesh,"mesh","mesh, int x, int y, int z, float array list vertex pts, int array list faces","First the arrays have to be made with floatarray and intarray. Then the mesh lambda calls the mesh method with x,y,z float array, int array");
        makeRun(linepow,"linepow","linepow,Double start, Double stop, Double inc, Double coeff, Double pow, Double con","Plots a power function on group 3");
        makeRun(graphmove,"graphmove","graphmove, int x, int y, int z","Moves the group3 where the graphs live");
        makeRun(graphaxes,"graphaxes","graphaxes, int xstart, int xend, int ystart, int yend","Puts a set of axes on group 3");
        makeRun(graphscale,"graphscale","graphscale int x, int y, int z","Scales group 3");
        makeRun(point,"point","point, double x, double y", "Plots a point on group 3 ");
        makeRun(stlimport,"stlimport","stlimport String file, int scale","Imports the STL file into group 2");
        makeRun(stlmove,"stlmove","stlmove,int obj, int x, int y, int z","Moves the STL at tne meshview position");
        makeRun(objimporter,"objimporter","objimporter, String filename","First use the file method to load the file into the file array");
        makeRun(file,"file","file, String file, String name","Loads the file into the file array so that the name reference can be used in lambda calls");
        makeRun(loadjpg,"loadjpg","loadjpg, String file name","First load the file into the file array and just the name reference is passed to the lambda");
        makeRun(scalejpg,"scalejpg","scalejpg, double x, double y, int pos","Scales the jpg that has been imported. The pos is the imageview array reference");
        makeRun(movejpg,"movejpg","movejpg, double x, double y, double z, int pos","Moves the jpg at the imageview pos");
        makeRun(scaleobj,"scaleobj","scaleobj, double x, double y, double z, int pos","Scales the obj at the meshview pos");
        makeRun(moveobj,"moveobj","moveobj, double x, double y, double z, int pos", "moves the object ar the meshview pos");
        makeRun(stlrotate,"stlrotate","stlrotate, int obj, int x, int y, int z, int angle","rotates the stl");
        makeRun(stlscale,"stlscale","stlscale, int obj, double x, double y, double z","scales the stl");
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
            else if(name.equals("fxmlloader")){
                int i = runs.indexOf(name);
                Function funct = (Function) runs.get(i+3);
                try {
                    String[] editParts = new String[parts.length];
                    for (int k = 0; k < parts.length; k++) {
                        editParts[k] = parts[k].trim();
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
        arrNodes.add("parent");
        arrNodes.add(arrParent);
        arrNodes.add("color");
        arrNodes.add(arrColor);
        arrNodes.add("phong");
        arrNodes.add(arrPhong);
        arrNodes.add("line");
        arrNodes.add(arrLine);
        arrNodes.add("light");
        arrNodes.add(arrLight);
        arrNodes.add("floatarray");
        arrNodes.add(arrFloat);
        arrNodes.add("intarray");
        arrNodes.add(arrInt);
    }

    static ArrayList<CubicCurve> arrCubicCurve = new ArrayList<>();
    static int cubicCurveCount = 0;
    static List<java.io.Serializable> cubicCurveList = new ArrayList<java.io.Serializable>();

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
    static List<java.io.Serializable> circleList = new ArrayList<>();

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
    static List<java.io.Serializable> lineList = new ArrayList<>();

    static void lineM(Double x1, Double y1, Double x2, Double y2, boolean print ){
        if(print == true) {
        arrLine.add(new Line(x1,y1,x2,y2));
        Main.group2.getChildren().add(arrLine.get(lineCount));
            arrString.add("Line_" + lineCount);
            GenText("Line_" + lineCount + " (" + x1 + " ," + y1 + " ," + x2 + " ," + y2 + ")", objectx, objecty + 25 + strCount * 20);
            lineList.add("Line_" + lineCount);
            lineList.add(strCount);
            lineList.add(objecty + 25 + strCount * 20);
            ++strCount;
            ++lineCount;
        }
        else{
            Main.group3.getChildren().add(new Line(x1,y1,x2,y2));
        }
    }//method

    static Function<String[], Boolean> line = (String[] str) ->{
        if(!(str.length == 6)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                if(str[5].replace(" ","").toLowerCase().equals(true)) {
                    lineM(Double.parseDouble(str[1]),Double.parseDouble(str[2]),Double.parseDouble(str[3]),Double.parseDouble(str[4]),true);
                }
                else if(str[5].replace(" ","").toLowerCase().equals(false)){
                    lineM(Double.parseDouble(str[1]),Double.parseDouble(str[2]),Double.parseDouble(str[3]),Double.parseDouble(str[4]),false);
                }
                else{
                    Main.textfieldB.setText("Not A Command");
                    return false;
                }

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

    static List<java.io.Serializable> cylinderList = new ArrayList<>();
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
    static List<java.io.Serializable> boxList = new ArrayList<>();

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
    static List<java.io.Serializable> sphereList = new ArrayList<java.io.Serializable>();

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
    static List rectangleList = new ArrayList<>();

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
    static List<java.io.Serializable> transitionList = new ArrayList<java.io.Serializable>();

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
    static List<java.io.Serializable> transStopList = new ArrayList<java.io.Serializable>();

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
    static List<java.io.Serializable> textDisplayList = new ArrayList<java.io.Serializable>();

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
    static List rotateList = new ArrayList<>();

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
                arrString.add("rotate_" + rotateCount);
                GenText("Rotate" + rotateCount + " " + name + "_" + pos+" angle "+angle+" axis "+axis, objectx, objecty + 25 + strCount * 20);
                rotateList.add("Rotate_" + rotateCount);
                rotateList.add(strCount);
                rotateList.add(objecty + 25 + strCount * 20);
                ++rotateCount;
                ++strCount;
            }catch(Exception e){
                Main.textfieldB.setText("Error in rotate call");
            }
        }
        else
            Main.textfieldB.setText("Not A Command");
    }//method

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

    static ArrayList<FXMLLoader> arrFMXLLoader = new ArrayList<>();
    static int FXMLLoaderCount = 0;
    static List<java.io.Serializable> FXMLLoaderList = new ArrayList<>();
    static ArrayList<Parent> arrParent = new ArrayList<>();
    static int parentCount = 0;
    static List<String> parentList = new ArrayList<>();

    static void fxmlloaderM(String file) {

        try {
            arrFMXLLoader.add(new FXMLLoader());
            arrParent.add(arrFMXLLoader.get(FXMLLoaderCount).load(Funct.class.getResource(file)));
            Main.group2.getChildren().add(arrParent.get(parentCount));
            arrString.add("FXMLLoader_"+FXMLLoaderCount);
            GenText("Parent_"+parentCount+" "+file+" ",objectx,objecty+25+strCount*20);
            FXMLLoaderList.add("FXMLloader_"+FXMLLoaderCount);
            FXMLLoaderList.add(strCount);
            FXMLLoaderList.add(objecty+25+strCount*20);
            ++FXMLLoaderCount;
            ++strCount;
            parentList.add("FXMLLoader_"+FXMLLoaderCount);
            ++parentCount;
        } catch (IOException e) {
            Main.textfieldB.setText("FXML did not load");
        }
    }//method

    static Function<String[], Boolean> fxmlloader = (String[] str) ->{
        if(!(str.length == 2)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        try {
            fxmlloaderM(str[1]);
        }
        catch (Exception e) {
            Main.textfieldB.setText("Something went wrong inside of fxmlloader call");
            return false;
        }
        return true;
    };//function

    static ArrayList<Color> arrColor = new ArrayList<>();
    static int colorCount = 0;
    static List<java.io.Serializable> colorList = new ArrayList<>();
    //arrRect.get(rectCount).setFill(Color.LIGHTBLUE);

    static void color2DM(int red, int green, int blue, String name, int pos){
        Color c = Color.rgb(red, green, blue);
        arrColor.add(c);
        if(arrNodes.contains(name)) {
            try {
                int i = arrNodes.indexOf(name);
                List nodes = (ArrayList) arrNodes.get(i + 1);
                Shape node = (Shape) nodes.get(pos);
                node.setFill(arrColor.get(colorCount));
                arrString.add("color_" + colorCount);
                GenText("Color" + colorCount + " " + name + "_" + pos+"(" + red +","+green+","+blue+")", objectx, objecty + 25 + strCount * 20);
                colorList.add("color_" + colorCount);
                colorList.add(strCount);
                colorList.add(objecty + 25 + strCount * 20);
                ++colorCount;
                ++strCount;
            }catch(Exception e){
                Main.textfieldB.setText("Error in color method call");
            }
        }
        else
            Main.textfieldB.setText("Not A Command");
    }//method

    //int red, int green, int blue, String name, int pos
    static Function<String[], Boolean> color2D = (String[] str) ->{
        if(!(str.length == 6)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int red = Integer.parseInt(str[1]);
                int green = Integer.parseInt(str[2]);
                int blue = Integer.parseInt(str[3]);
                String name = str[4].replace(" ","").toLowerCase();
                int pos = Integer.parseInt(str[5]);
                color2DM(red,green,blue,name,pos);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of Color lambda call");
                return false;
            }
            return true;
        }
    };//function

    static ArrayList<PhongMaterial> arrPhong = new ArrayList<>();
    static int phongCount = 0;
    static List<java.io.Serializable> phongList = new ArrayList<java.io.Serializable>();

    static void color3DM(int diffRed, int diffGreen, int diffBlue, String name, int pos){
        Color d = Color.rgb(diffRed, diffGreen, diffBlue);
        arrColor.add(d);
        ++colorCount;
        arrPhong.add(new PhongMaterial());
        arrPhong.get(phongCount).setDiffuseColor(d);
        if(arrNodes.contains(name)) {
            try {
                int i = arrNodes.indexOf(name);
                List nodes = (ArrayList) arrNodes.get(i + 1);
                Shape3D node = (Shape3D) nodes.get(pos);
                node.setMaterial(arrPhong.get(phongCount));
                arrString.add("PhongMaterial_" + phongCount);
                GenText("PhongMaterial" + phongCount + " " + name + "_" + pos, objectx, objecty + 25 + strCount * 20);
                phongList.add("phong_" + phongCount);
                phongList.add(strCount);
                phongList.add(objecty + 25 + strCount * 20);
                ++phongCount;
                ++strCount;
            }catch(Exception e){
                Main.textfieldB.setText("Error in color method call");
            }
        }
        else
            Main.textfieldB.setText("Not A Command");
    }//method

    //int diffRed, int diffGreen, int diffBlue, String name, int pos
    static Function<String[], Boolean> color3D = (String[] str) ->{
        if(!(str.length == 6)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int diffRed = Integer.parseInt(str[1]);
                int diffGreen = Integer.parseInt(str[2]);
                int diffBlue = Integer.parseInt(str[3]);
                String name = str[4].replace(" ","").toLowerCase();
                int pos = Integer.parseInt(str[5]);
                Main.textfieldB.setText(diffRed+" "+diffGreen+" "+diffBlue+" "+name+" "+pos);
                color3DM(diffRed,diffGreen,diffBlue,name,pos);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of Color lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void scale2DM(int x, int y, int z, String name, int pos){
        if(arrNodes.contains(name)) {
            try {
                int i = arrNodes.indexOf(name);
                List nodes = (ArrayList) arrNodes.get(i + 1);
                Shape node = (Shape) nodes.get(pos);
                node.setScaleX(x);
                node.setScaleY(y);
                node.setScaleZ(z);
                GenText("Scale" +" ("+x +","+y+","+z + ") " + name + "_" + pos, objectx, objecty + 25 + strCount * 20);
                ++strCount;
            }catch(Exception e){
                Main.textfieldB.setText("Error in scale method call");
            }
        }
        else
            Main.textfieldB.setText("Not A Command");
    }//method


    //int x, int y, int z, String name, int pos
    static Function<String[], Boolean> scale2D = (String[] str) ->{
        if(!(str.length == 6)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int x = Integer.parseInt(str[1]);
                int y = Integer.parseInt(str[2]);
                int z = Integer.parseInt(str[3]);
                String name = str[4].replace(" ","").toLowerCase();
                int pos = Integer.parseInt(str[5]);
                Main.textfieldB.setText(x+" "+y+" "+z+" "+name+" "+pos);
                scale2DM(x,y,z,name,pos);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of Scale lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void scale3DM(int x, int y, int z, String name, int pos){
        if(arrNodes.contains(name)) {
            try {
                int i = arrNodes.indexOf(name);
                List nodes = (ArrayList) arrNodes.get(i + 1);
                Shape3D node = (Shape3D) nodes.get(pos);
                node.setScaleX(x);
                node.setScaleY(y);
                node.setScaleZ(z);
                GenText("Scale" +" ("+x +","+y+","+z + ") " + name + "_" + pos, objectx, objecty + 25 + strCount * 20);
                ++strCount;
            }catch(Exception e){
                Main.textfieldB.setText("Error in scale method call");
            }
        }
        else
            Main.textfieldB.setText("Not A Command");
    }//method


    //int x, int y, int z, String name, int pos
    static Function<String[], Boolean> scale3D = (String[] str) ->{
        if(!(str.length == 6)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int x = Integer.parseInt(str[1]);
                int y = Integer.parseInt(str[2]);
                int z = Integer.parseInt(str[3]);
                String name = str[4].replace(" ","").toLowerCase();
                int pos = Integer.parseInt(str[5]);
                Main.textfieldB.setText(x+" "+y+" "+z+" "+name+" "+pos);
                scale3DM(x,y,z,name,pos);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of Scale lambda call");
                return false;
            }
            return true;
        }
    };//function

    static ArrayList<PointLight> arrLight = new ArrayList<>();
    static int lightCount = 0;
    static List<java.io.Serializable> lightList = new ArrayList<>();

    static void lightM(int x, int y, int z, int red, int green, int blue){
        try{
            arrLight.add(new PointLight());
            arrLight.get(lightCount).setTranslateX(x);
            arrLight.get(lightCount).setTranslateY(y);
            arrLight.get(lightCount).setTranslateZ(z);
            Color d = Color.rgb(red, green, blue);
            arrColor.add(d);
            ++colorCount;
            arrLight.get(lightCount).setColor(d);
            Main.group2.getChildren().add(arrLight.get(lightCount));
            arrString.add("PointLight_"+lightCount);
            lightList.add("PointLight_"+lightCount);
            lightList.add(strCount);
            lightList.add(objecty+25+strCount*20);
            GenText("PointLight" +" ("+x +","+y+","+z + ") "+"color ("+red+","+green+","+blue+")", objectx, objecty + 25 + strCount * 20);
            ++lightCount;
            ++strCount;
        }catch(Exception e){
                Main.textfieldB.setText("Error in PointLight method call");
            }
    }//method

    static Function<String[], Boolean> light = (String[] str) ->{
        if(!(str.length == 7)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int x = Integer.parseInt(str[1]);
                int y = Integer.parseInt(str[2]);
                int z = Integer.parseInt(str[3]);
                int red = Integer.parseInt(str[4]);
                int green = Integer.parseInt(str[5]);
                int blue = Integer.parseInt(str[6]);
                lightM(x,y,z,red,green,blue);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of PointLight lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void drawmodelineM(String name, int pos){
        if(arrNodes.contains(name)) {
            try {
                int i = arrNodes.indexOf(name);
                List nodes = (ArrayList) arrNodes.get(i + 1);
                Shape3D node = (Shape3D) nodes.get(pos);
                node.setDrawMode(DrawMode.LINE);
            }catch(Exception e){
                Main.textfieldB.setText("Error in Draw Mode Line method call");
            }
        }
        else
            Main.textfieldB.setText("Not A Command");
    }//method

    static Function<String[], Boolean> drawmodeline = (String[] str) ->{
        if(!(str.length == 3)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                String name = str[1].replace(" ","").toLowerCase();
                int pos = Integer.parseInt(str[2]);
                Main.textfieldB.setText("draw line mode " +name+"_"+pos);
                drawmodelineM(name,pos);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of drawmodeline lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void drawmodefillM(String name, int pos){
        if(arrNodes.contains(name)) {
            try {
                int i = arrNodes.indexOf(name);
                List nodes = (ArrayList) arrNodes.get(i + 1);
                Shape3D node = (Shape3D) nodes.get(pos);
                node.setDrawMode(DrawMode.FILL);
            }catch(Exception e){
                Main.textfieldB.setText("Error in Draw Mode Fill method call");
            }
        }
        else
            Main.textfieldB.setText("Not A Command");
    }//method

    static Function<String[], Boolean> drawmodefill = (String[] str) ->{
        if(!(str.length == 3)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                String name = str[1].replace(" ","").toLowerCase();
                int pos = Integer.parseInt(str[2]);
                Main.textfieldB.setText("draw fill mode " +name+"_"+pos);
                drawmodefillM(name,pos);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of drawmodefill lambda call");
                return false;
            }
            return true;
        }
    };//function

    static int[] editfaces(ArrayList<Integer> faces) {

        int count = 0;

        int[] newfaces = new int[faces.size() * 2];

        for (int i = 0; i<newfaces.length; i++)
            newfaces[i] = 0;

        for (int i=0;i<newfaces.length;i++) {
            if(i%2 == 0){
                newfaces[i] = faces.get(count);
                ++count;
            }
        }
        return newfaces;
    }//method

    static float[] editvertexpts(ArrayList<Float> vertexpts) {

        int count = 0;

        float[] newvertexpts = new float[vertexpts.size()];

        for (int i = 0; i<newvertexpts.length; i++)
            newvertexpts[i] = vertexpts.get(i);

        return newvertexpts;
    }//method

    static Function<String[], Boolean> mesh = (String[] str) ->{
        if(!(str.length == 8)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int x = Integer.parseInt(str[1]);
                int y = Integer.parseInt(str[2]);
                int z = Integer.parseInt(str[3]);

                String namef = str[4].replace(" ","").toLowerCase();
                int posf = Integer.parseInt(str[5]);
                String namei = str[6].replace(" ","").toLowerCase();
                int posi = Integer.parseInt(str[7]);

                int i = arrNodes.indexOf(namef);
                List nodesf = (ArrayList) arrNodes.get(i + 1);
                ArrayList nodef = (ArrayList) nodesf.get(posf);

                i = arrNodes.indexOf(namei);
                List nodesi = (ArrayList) arrNodes.get(i + 1);
                ArrayList nodei = (ArrayList) nodesi.get(posi);
                meshM(x,y,z,nodef,nodei);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of mesh lambda call");
                return false;
            }
            return true;
        }
    };//function

    static ArrayList<TriangleMesh> arrTriMesh = new ArrayList<>();
    static int trimeshCount = 0;
    static List trimeshList = new ArrayList();

    static ArrayList<MeshView> arrMeshView = new ArrayList<>();
    static int meshviewCount = 0;
    static List<String> meshviewList = new ArrayList<>();

    static void meshM(int x, int y, int z, ArrayList vertexpts, ArrayList faces)
    {
        float[] texCoords =
                {
                        0.0f, 0.0f,
                        0.0f, 1.0f,
                        1.0f, 1.0f
                };
        int[] newfaces = editfaces(faces);
        float[] newvertexpts = editvertexpts(vertexpts);

        // Create a TriangleMesh
        arrTriMesh.add(new TriangleMesh());
        arrTriMesh.get(trimeshCount).getPoints().addAll(newvertexpts);
        arrTriMesh.get(trimeshCount).getTexCoords().addAll(texCoords);
        arrTriMesh.get(trimeshCount).getFaces().addAll(newfaces);
        arrString.add("TriMesh_"+trimeshCount);
        trimeshList.add("TriMesh_"+trimeshCount);
        trimeshList.add(strCount);
        trimeshList.add(objecty+25+strCount*20);
        GenText("TriMesh_"+trimeshCount +" ("+x +","+y+","+z + ") ", objectx, objecty + 25 + strCount * 20);
        ++strCount;
        // Create a MeshView
        arrMeshView.add(new MeshView());
        arrMeshView.get(meshviewCount).setMesh(arrTriMesh.get(trimeshCount));
        arrMeshView.get(meshviewCount).setTranslateX(x);
        arrMeshView.get(meshviewCount).setTranslateY(y);
        arrMeshView.get(meshviewCount).setTranslateZ(z);
        meshviewList.add("MeshView_"+meshviewCount);
        Main.group2.getChildren().add(arrMeshView.get(meshviewCount));
        Main.textfieldB.setText(x+" "+y+" "+z+" "+faces.toString()+" "+vertexpts.toString());
        ++trimeshCount;
        ++meshviewCount;

    }//method

    //floatarray,{ 1,2,3,4}
    static ArrayList<ArrayList<Float>> arrFloat = new ArrayList<>();
    static int floatarrCount = 0;
    static List floatarrList = new ArrayList<>();

    static Function<String[], Boolean> floatarray = (String[] str) ->{
        if(!(str[1].contains("{"))) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                ArrayList<Integer> openb = new ArrayList<>();
                ArrayList<Integer> closeb = new ArrayList<>();
                for(int i = 2; i < str.length; i++){
                    if(str[i].contains("{"))
                        openb.add(i);
                    else if(str[i].contains("}"))
                        closeb.add(i);
                }
                if(!(openb.size()==0)){
                    Main.textfieldB.setText("Not A Command");
                    return false;
                }
                if(!(closeb.size()==1)){
                    Main.textfieldB.setText("Not A Command");
                    return false;
                }
                String temp = str[1].replace("{","");
                float f1 = Float.parseFloat(temp);
                ArrayList<Float> ftemp = new ArrayList<>();
                ftemp.add(f1);
                for(int i = 2; i < closeb.get(0); i++){
                    f1 = Float.parseFloat(str[i]);
                    ftemp.add(f1);
                }
                temp = str[closeb.get(0)].replace("}","");
                f1 = Float.parseFloat(temp);
                ftemp.add(f1);
                arrFloat.add(ftemp);
                Main.textfieldB.setText(arrFloat.get(floatarrCount).toString());
                arrString.add("arrFloat_"+floatarrCount);
                floatarrList.add("arrFloat_"+floatarrCount);
                floatarrList.add(strCount);
                floatarrList.add(objecty+25+strCount*20);
                GenText("FloatArray_"+floatarrCount, objectx, objecty + 25 + strCount * 20);
                ++floatarrCount;
                ++strCount;

            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of floatarray lambda call");
                return false;
            }
            return true;
        }
    };//function

    //intarray,{ 1,2,3,4}
    static ArrayList<ArrayList<Integer>> arrInt = new ArrayList<>();
    static int intarrCount = 0;
    static List intarrList = new ArrayList<>();

    static Function<String[], Boolean> intarray = (String[] str) ->{
        if(!(str[1].contains("{"))) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                ArrayList<Integer> openb = new ArrayList<>();
                ArrayList<Integer> closeb = new ArrayList<>();
                for(int i = 2; i < str.length; i++){
                    if(str[i].contains("{"))
                        openb.add(i);
                    else if(str[i].contains("}"))
                        closeb.add(i);
                }
                if(!(openb.size()==0)){
                    Main.textfieldB.setText("Not A Command");
                    return false;
                }
                if(!(closeb.size()==1)){
                    Main.textfieldB.setText("Not A Command");
                    return false;
                }
                String temp = str[1].replace("{","");
                int f1 = Integer.parseInt(temp);
                ArrayList<Integer> ftemp = new ArrayList<>();
                ftemp.add(f1);
                for(int i = 2; i < closeb.get(0); i++){
                    f1 = Integer.parseInt(str[i]);
                    ftemp.add(f1);
                }
                temp = str[closeb.get(0)].replace("}","");
                f1 = Integer.parseInt(temp);
                ftemp.add(f1);
                arrInt.add(ftemp);
                Main.textfieldB.setText(arrInt.get(intarrCount).toString());
                arrString.add("arrInt_"+intarrCount);
                intarrList.add("arrInt_"+intarrCount);
                intarrList.add(strCount);
                intarrList.add(objecty+25+strCount*20);
                GenText("IntArray_"+intarrCount, objectx, objecty + 25 + strCount * 20);
                ++intarrCount;
                ++strCount;
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of intarray lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void linepowM(Double start, Double stop, Double inc, Double coeff, Double pow, Double con){
        for(double i = start; i<stop-inc; i+= inc){
            double x1 = i;
            double y1 = coeff*Math.pow(x1,pow)+con;
            double x2 = i+.1;
            double y2 = coeff*Math.pow(x2,pow)+con;
            Funct.lineM(x1,y1,x2,y2,false);
        }
    }//method

    //Double start, Double stop, Double inc, Double coeff, Double pow, Double con
    static Function<String[], Boolean> linepow = (String[] str) ->{
        if(!(str.length == 7)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                double start = Double.parseDouble(str[1]);
                double stop = Double.parseDouble(str[2]);
                double inc = Double.parseDouble(str[3]);
                double coeff = Double.parseDouble(str[4]);
                double pow = Double.parseDouble(str[5]);
                double con = Double.parseDouble(str[6]);

                linepowM(start,stop,inc,coeff,pow,con);

            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of linepow lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void graphmoveM(int x, int y, int z){
        Main.group3.setTranslateX(x);
        Main.group3.setTranslateY(y);
        Main.group3.setTranslateZ(z);
    }//method

    //int x, int y, int z
    static Function<String[], Boolean> graphmove = (String[] str) ->{
        if(!(str.length == 4)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int x = Integer.parseInt(str[1]);
                int y = Integer.parseInt(str[2]);
                int z = Integer.parseInt(str[3]);

                graphmoveM(x,y,z);

            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of graphmove lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void graphaxesM(int xstart, int xend, int ystart, int yend){
        Line xaxis = new Line(xstart,0,xend,0);
        Line yaxis = new Line(0,ystart,0,yend);
        Main.group3.getChildren().addAll(xaxis, yaxis);
    }//method

    static Function<String[], Boolean> graphaxes = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int xstart = Integer.parseInt(str[1]);
                int xend = Integer.parseInt(str[2]);
                int ystart = Integer.parseInt(str[3]);
                int yend = Integer.parseInt(str[4]);
                int width = Math.abs(xstart-xend);
                int height = Math.abs(ystart-yend);
                Rectangle rect = new Rectangle();
                rect.setWidth(width);
                rect.setHeight(height);
                rect.setTranslateX(-width/2);
                rect.setTranslateY(-height/2);
                rect.setFill(Color.WHITE);
                Main.group3.getChildren().add(rect);
                graphaxesM(xstart,xend,ystart,yend);

            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of graphaxes lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void graphscaleM(int x, int y, int z){
        Main.group3.setScaleX(x);
        Main.group3.setScaleY(y);
        Main.group3.setScaleZ(z);

    }//method

    static Function<String[], Boolean> graphscale = (String[] str) ->{
        if(!(str.length == 4)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int x = Integer.parseInt(str[1]);
                int y = Integer.parseInt(str[2]);
                int z = Integer.parseInt(str[3]);
                graphscaleM(x,y,z);

            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of graphaxes lambda call");
                return false;
            }
            return true;
        }
    };//function

    static Function<String[], Boolean> point = (String[] str) ->{
        if(!(str.length == 3)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                double x = Double.parseDouble(str[1]);
                double y = Double.parseDouble(str[2]);
                pointM(x,y);

            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of point lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void pointM(double x, double y){
        Circle circle = new Circle();
        circle.setRadius(2);
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setFill(Color.RED);
        Main.group3.getChildren().add(circle);
    }//method

    static ArrayList<StlMeshImporter> stlimportarr = new ArrayList<>();
    static ArrayList<Mesh> stlmesharr = new ArrayList<>();
    static ArrayList<MeshView> stlmeshviewarr = new ArrayList<>();
    static int stlimportCount = 0;
    static int stlmeshCount = 0;
    static int stlmeshviewCount = 0;

    static void stlimportM(String file, int scale){
        stlimportarr.add(new StlMeshImporter());
        stlimportarr.get(stlimportCount).read(file);
        Mesh mesh = stlimportarr.get(stlimportCount).getImport();
        stlmesharr.add(mesh);
        stlmeshviewarr.add(new MeshView(mesh));
        stlmeshviewarr.get(stlmeshviewCount).setScaleX(scale);
        stlmeshviewarr.get(stlmeshviewCount).setScaleY(scale);
        stlmeshviewarr.get(stlmeshviewCount).setScaleZ(scale);
        PhongMaterial sample = new PhongMaterial(Color.GREY);
        stlmeshviewarr.get(stlmeshviewCount).setMaterial(sample);
        Main.group2.getChildren().add(stlmeshviewarr.get(stlmeshviewCount));
        arrString.add("STLmeshview_"+stlmeshviewCount);
        GenText("STLmeshview_"+stlmeshviewCount+" file",objectx,objecty+25+strCount*20);
        ++strCount;
        ++stlimportCount;
        ++stlmeshCount;
        ++stlmeshviewCount;
    }//method

    static Function<String[], Boolean> stlimport = (String[] str) ->{
        if(!(str.length == 3)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int pos = Funct.filearr.indexOf(str[1]);
                String path = Funct.filearr.get(pos+1);
                int scale = Integer.parseInt(str[2]);
                stlimportM(path,scale);
                Main.textfieldB.setText("STL was loaded");
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of stlimport lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void stlrotateM(int obj, int x, int y, int z, int angle){
        try {
            stlmeshviewarr.get(obj).setRotate(angle);
            Point3D point = new Point3D(x,y,z);
            stlmeshviewarr.get(obj).setRotationAxis(point);
        } catch (Exception e) {
            Main.textfieldB.setText("Bad object number");
        }
    }//method

    static Function<String[], Boolean> stlrotate = (String[] str) ->{
        if(!(str.length == 6)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int obj = Integer.parseInt(str[1]);
                int x = Integer.parseInt(str[2]);
                int y = Integer.parseInt(str[3]);
                int z = Integer.parseInt(str[4]);
                int angle = Integer.parseInt(str[5]);
                stlrotateM(obj,x,y,z,angle);

            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of stlrotate lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void stlmoveM(int obj, int x, int y, int z){
        try {
            stlmeshviewarr.get(obj).setTranslateX(x);
            stlmeshviewarr.get(obj).setTranslateY(y);
            stlmeshviewarr.get(obj).setTranslateZ(z);
        } catch (Exception e) {
            Main.textfieldB.setText("Bad object number");
        }
    }//method

    static Function<String[], Boolean> stlmove = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int obj = Integer.parseInt(str[1]);
                int x = Integer.parseInt(str[2]);
                int y = Integer.parseInt(str[3]);
                int z = Integer.parseInt(str[4]);
                stlmoveM(obj,x,y,z);

            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of stlmove lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void stlscaleM(int obj, double x, double y, double z){
        try {
            stlmeshviewarr.get(obj).setScaleX(x);
            stlmeshviewarr.get(obj).setScaleY(y);
            stlmeshviewarr.get(obj).setScaleZ(z);
        } catch (Exception e) {
            Main.textfieldB.setText("Bad object number");
        }
    }//method

    static Function<String[], Boolean> stlscale = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                int obj = Integer.parseInt(str[1]);
                double x = Double.parseDouble(str[2]);
                double y = Double.parseDouble(str[3]);
                double z = Double.parseDouble(str[4]);
                stlscaleM(obj,x,y,z);
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of stlscale lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void movejpgM(double x, double y, double z, int pos){
        ImageView view = jpgimageview.get(pos);
        view.setTranslateX(x);
        view.setTranslateY(y);
        view.setTranslateZ(z);
    }//method

    static Function<String[], Boolean> movejpg = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                double x = Double.parseDouble(str[1]);
                double y = Double.parseDouble(str[2]);
                double z = Double.parseDouble(str[3]);
                int pos = Integer.parseInt(str[4]);
                if(Funct.jpgimageviewCount < pos){
                    Main.textfieldB.setText("pos is too large");
                    return false;
                }//if
                movejpgM(x,y,z,pos);
                Main.textfieldB.setText("JPG was moved");
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of movejpg lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void scalejpgM(double x, double y, int pos){
        ImageView view = jpgimageview.get(pos);
        view.setScaleX(x);
        view.setScaleY(y);
    }//method

    static Function<String[], Boolean> scalejpg = (String[] str) ->{
        if(!(str.length == 4)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                double x = Double.parseDouble(str[1]);
                double y = Double.parseDouble(str[2]);
                int pos = Integer.parseInt(str[3]);
                if(Funct.jpgimageviewCount < pos || pos < 0){
                    Main.textfieldB.setText("pos is wrong");
                    return false;
                }//if
                scalejpgM(x,y,pos);
                Main.textfieldB.setText("JPG was scaled");
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of scalejpg lambda call");
                return false;
            }
            return true;
        }
    };//function
    
    static ArrayList<Image> jpgimagearr = new ArrayList<>();
    static ArrayList<FileInputStream> jpginputstream = new ArrayList<>();
    static ArrayList<ImageView> jpgimageview = new ArrayList<>();
    static int jpgimageCount = 0;
    static int jpginputstreamCount = 0;
    static int jpgimageviewCount = 0;

    static void loadjpgM(String path){
        try {
            File file = new File(path);
            jpginputstream.add( new FileInputStream(file));
            jpgimagearr.add(new Image(jpginputstream.get(jpginputstreamCount)));
            jpgimageview.add(new ImageView(jpgimagearr.get(jpgimageCount)));
            Main.group2.getChildren().add(jpgimageview.get(jpgimageviewCount));
            arrString.add("JPGimageview_"+jpgimageviewCount);
            GenText("JPGimageview_"+jpgimageviewCount,objectx,objecty+25+strCount*20);
            ++strCount;
            ++jpgimageCount;
            ++jpginputstreamCount;
            ++jpgimageviewCount;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }//method

    static Function<String[], Boolean> loadjpg = (String[] str) ->{
        if(!(str.length == 2)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else if(!Funct.filearr.contains(str[1])){
            Main.textfieldB.setText("Name is not in the file array");
            return false;
            }
        else{
            try {
                int pos = Funct.filearr.indexOf(str[1]);
                String path = Funct.filearr.get(pos+1);
                loadjpgM(path);
                Main.textfieldB.setText("JPG was loaded");
            } catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of load jpg lambda call");
                return false;
            }
            return true;
        }
    };//function

    static ArrayList<String> filearr = new ArrayList<>();
    static int filearrCount = 0;

    static void fileM(String file, String name){
        filearr.add(name);
        filearr.add(file);
        ++filearrCount;
    }//method

    static Function<String[], Boolean> file = (String[] str) ->{
        if(!(str.length == 3)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else if(Funct.filearr.contains(str[2])){
            Main.textfieldB.setText("The name already exists in the file array");
            return false;
        }
        else{
            try {
                String file = str[1];
                String name = str[2];
                fileM(file,name);
                Main.textfieldB.setText(str[2]+" is in the file array");
            } catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of file lambda call");
                return false;
            }
            return true;
        }
    };//function

    static ArrayList<ObjModelImporter> objimporterarr = new ArrayList<>();
    static ArrayList<MeshView> objmeshview = new ArrayList<>();
    static int objimporterCount = 0;
    static int objmeshviewCount = 0;

    static void objimporterM(String path) {
        try {
            objimporterarr.add(new ObjModelImporter());
            Main.textfieldB.setText(path);
            objimporterarr.get(objimporterCount).read(path);
            for (MeshView view : objimporterarr.get(objimporterCount).getImport()){
                Main.group2.getChildren().add(view);
                objmeshview.add(view);
            }//for
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        arrString.add("OBJmeshview_"+objmeshviewCount);
        GenText("OBJmeshview_"+objmeshviewCount,objectx,objecty+25+strCount*20);
        ++strCount;
        ++objmeshviewCount;
        ++objmeshviewCount;
    }//method

    static Function<String[], Boolean> objimporter = (String[] str) ->{
        if(!(str.length == 2)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else if(!Funct.filearr.contains(str[1])){
            Main.textfieldB.setText("Name not in file array");
            return false;
        }
        else{
            try {
                int pos = Funct.filearr.indexOf(str[1]);
                String path = Funct.filearr.get(pos+1);
                //File file = (File) Funct.filearr.get(pos+1);
                Funct.objimporterM(path);
                //Main.textfieldB.setText("Obj was loaded");
            } catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of obj importer lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void scaleobjM(double x, double y, double z, int pos){
        for (MeshView view : Funct.objimporterarr.get(pos).getImport()) {
            view.setScaleX(x);
            view.setScaleY(y);
            view.setScaleZ(z);
        }//for
    }//method

    static Function<String[], Boolean> scaleobj = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                double x = Double.parseDouble(str[1]);
                double y = Double.parseDouble(str[2]);
                double z = Double.parseDouble(str[3]);
                int pos = Integer.parseInt(str[4]);
                if(Funct.objmeshviewCount < pos || pos < 0){
                    Main.textfieldB.setText("pos is wrong");
                    return false;
                }//if
                scaleobjM(x,y,z,pos);
                Main.textfieldB.setText("OBJ was scaled");
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of scaleobj lambda call");
                return false;
            }
            return true;
        }
    };//function

    static void moveobjM(double x, double y, double z, int pos){
        for (MeshView view : Funct.objimporterarr.get(pos).getImport()) {
            view.setTranslateX(x);
            view.setTranslateY(y);
            view.setTranslateZ(z);
        }//for
    }//method

    static Function<String[], Boolean> moveobj = (String[] str) ->{
        if(!(str.length == 5)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                double x = Double.parseDouble(str[1]);
                double y = Double.parseDouble(str[2]);
                double z = Double.parseDouble(str[3]);
                int pos = Integer.parseInt(str[4]);
                if(Funct.objmeshviewCount < pos || pos < 0){
                    Main.textfieldB.setText("pos is wrong");
                    return false;
                }//if
                moveobjM(x,y,z,pos);
                Main.textfieldB.setText("OBJ was moved");
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            catch (Exception e) {
                Main.textfieldB.setText("Something went wrong inside of moveobj lambda call");
                return false;
            }
            return true;
        }
    };//function


}//class