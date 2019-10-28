package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Funct {

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
        makeRun(cubicCurve,"cubiccurve","cubiccurve,int startx, int starty, int cx1, int cy1, int cx2, int cy2, int endx, int endy","Calls cubic curve 2D from javafx");
        makeRun(circle,"circle","circle, int radius, int x, int y","circle 2D from javafx");
        makeRun(cylinder,"cylinder","cylinder, int radius, int height, int x, int y","cylinder 3D from javafx");
        makeRun(box,"box","box, int depth, int width, int height, int x, int y","box 3D from javafx");
        makeRun(sphere,"sphere","sphere, int radius, int x, int y","sphere 3D from javafx");
        makeRun(commands,"commands","No Call","Prints all possible commands");
        makeRun(line,"line","line,int x1, int y1, int x2, int y2","Line 2D only over the x y plane");
        makeRun(rectangle,"rectangle","rectangle, int height, int width, int x, int y","Rectangle 2D from java fx");
        makeRun(remove,"remove","remove,String object, int array position","removes object from the group");


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
            if(!runs.contains(name))
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
    static String[] arrString = new String[100];
    static int strCount = 0;

    static CubicCurve[] arrCubicCurve = new CubicCurve[100];
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
        arrCubicCurve[cubicCurveCount] = new CubicCurve();
        arrCubicCurve[cubicCurveCount].setStartX(startx);
        arrCubicCurve[cubicCurveCount].setStartY(starty);
        arrCubicCurve[cubicCurveCount].setControlX1(cx1);
        arrCubicCurve[cubicCurveCount].setControlY1(cy1);
        arrCubicCurve[cubicCurveCount].setControlX2(cx2);
        arrCubicCurve[cubicCurveCount].setControlY2(cy2);
        arrCubicCurve[cubicCurveCount].setEndX(endx);
        arrCubicCurve[cubicCurveCount].setEndY(endy);
        arrCubicCurve[cubicCurveCount].setFill(Color.TRANSPARENT);
        arrCubicCurve[cubicCurveCount].setStroke(Color.BLACK);
        Main.group2.getChildren().add(arrCubicCurve[cubicCurveCount]);
        arrString[strCount]="CubicCurve_"+cubicCurveCount;
        GenText("CubicCurve_"+cubicCurveCount+" ("+startx+" ,"+starty+" ,"+endx+" ,"+endy+")",objectx,objecty+25+strCount*20);
        cubicCurveList.add("CubicCurve_"+cubicCurveCount);
        cubicCurveList.add(strCount);
        cubicCurveList.add(objecty+25+strCount*20);
        ++strCount;
        ++cubicCurveCount;
    }//method

    static Circle[] arrCircle = new Circle[100];
    static int circleCount = 0;
    static List circleList = new ArrayList();

    static void circleM(int radius, int x, int y){
        arrCircle[circleCount] = new Circle();
        arrCircle[circleCount].setRadius(radius);
        arrCircle[circleCount].setLayoutX(x);
        arrCircle[circleCount].setLayoutY(y);
        arrCircle[circleCount].setFill(Color.TRANSPARENT);
        arrCircle[circleCount].setStroke(Color.BLACK);
        Main.group2.getChildren().add(arrCircle[circleCount]);
        arrString[strCount]="Circle_"+circleCount;
        GenText("Circle_"+circleCount+" ("+x+" ,"+y+")",objectx,objecty+25+strCount*20);
        circleList.add("Line_"+lineCount);
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

    static Line[] arrLine = new Line[100];
    static int lineCount = 0;
    static List lineList = new ArrayList();

    static void lineM(int x1, int y1, int x2, int y2 ){
        arrLine[lineCount] = new Line(x1,y1,x2,y2);
        Main.group2.getChildren().add(arrLine[lineCount]);
        arrString[strCount]="Line_"+lineCount;
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

    static Text[] arrText = new Text[100];
    static int textCount = 0;

    static void GenText(String str, int x , int y ){
        arrText[textCount] = new Text();
        arrText[textCount].setText(str);
        arrText[textCount].setLayoutX(x);
        arrText[textCount].setLayoutY(y);
        Main.group.getChildren().add(arrText[textCount]);
        ++textCount;
    }//method

    static Rectangle[] arrRect = new Rectangle[100];
    static int rectCount = 0;

    static void GenRect(int height, int width, int x, int y){
        arrRect[rectCount] = new Rectangle();
        arrRect[rectCount].setWidth(width);
        arrRect[rectCount].setHeight(height);
        arrRect[rectCount].setLayoutX(x);
        arrRect[rectCount].setLayoutY(y);
        arrRect[rectCount].setFill(Color.LIGHTBLUE);
        Main.group.getChildren().add(arrRect[rectCount]);
        ++rectCount;
    }//method

    static List cylinderList = new ArrayList();
    static Cylinder[] arrCyl = new Cylinder[100];
    static int cylinderCount = 0;

    static void cylinderM(int radius, int height, int x, int y){
        arrCyl[cylinderCount] = new Cylinder();
        arrCyl[cylinderCount].setRadius(radius);
        arrCyl[cylinderCount].setHeight(height);
        arrCyl[cylinderCount].setLayoutX(x);
        arrCyl[cylinderCount].setLayoutY(y);
        Main.group2.getChildren().add(arrCyl[cylinderCount]);
        arrString[strCount]="Cylinder_"+cylinderCount;
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

    static Box[] arrBox = new Box[100];
    static int boxCount = 0;
    static List boxList = new ArrayList();

    static void boxM(int depth, int width, int height, int x, int y){
        arrBox[boxCount] = new Box();
        arrBox[boxCount].setDepth(depth);
        arrBox[boxCount].setWidth(width);
        arrBox[boxCount].setHeight(height);
        arrBox[boxCount].setLayoutX(x);
        arrBox[boxCount].setLayoutY(y);
        Main.group2.getChildren().add(arrBox[boxCount]);
        arrString[strCount]="Box_"+boxCount;
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


    static Sphere[] arrSphere = new Sphere[100];
    static int sphereCount = 0;
    static List sphereList = new ArrayList();

    static void sphereM(int radius, int x, int y){
        arrSphere[sphereCount] = new Sphere();
        arrSphere[sphereCount].setRadius(radius);
        arrSphere[sphereCount].setLayoutX(x);
        arrSphere[sphereCount].setLayoutY(y);
        Main.group2.getChildren().add(arrSphere[sphereCount]);
        arrString[strCount]="Sphere_"+sphereCount;
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

    static Rectangle[] arrRectangle = new Rectangle[100];
    static int rectangleCount = 0;
    static List rectangleList = new ArrayList();

    static void rectM(int height, int width, int x, int y){
        arrRectangle[rectangleCount] = new Rectangle();
        arrRectangle[rectangleCount].setHeight(height);
        arrRectangle[rectangleCount].setWidth(width);
        arrRectangle[rectangleCount].setLayoutX(x);
        arrRectangle[rectangleCount].setLayoutY(y);
        arrRectangle[rectangleCount].setFill(Color.TRANSPARENT);
        arrRectangle[rectangleCount].setStroke(Color.BLACK);
        Main.group2.getChildren().add(arrRectangle[rectangleCount]);
        arrString[strCount]="Rectangle_"+rectangleCount;
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
        //remove,object,array position
        //if(parts[1].toLowerCase().equals("box")){
        //group2.getChildren().remove(arrBox[Integer.parseInt(parts[2])]);
        //}
        if(!(str.length == 3)) {
            Main.textfieldB.setText("Not A Command");
            return false;
        }
        else{
            try {
                removeM(str[1],Integer.parseInt(str[2]));
            } catch (NumberFormatException e) {
                Main.textfieldB.setText("Not A Command");
                return false;
            }
            return true;
        }
    };//function

    static void removeM(String str, int i){
        try {
            if(str.equals("box") && i < boxCount) {
                Main.group2.getChildren().remove(arrBox[i]);
                Main.textfieldB.setText("remove, box, " + i);
            }
            else if(str.equals("cylinder") && i < cylinderCount) {
                Main.group2.getChildren().remove(arrCyl[i]);
                Main.textfieldB.setText("remove, cylinder, " + i);
            }
            else if(str.equals("sphere") && i < sphereCount) {
                Main.group2.getChildren().remove(arrSphere[i]);
                Main.textfieldB.setText("remove, sphere, " + i);
            }
            else if(str.equals("rectangle") && i < rectangleCount) {
                Main.group2.getChildren().remove(arrRectangle[i]);
                Main.textfieldB.setText("remove, rectangle, " + i);
            }
            else if(str.equals("line") && i < lineCount) {
                Main.group2.getChildren().remove(arrLine[i]);
                Main.textfieldB.setText("remove, line, " + i);
            }
            else if(str.equals("cubiccurve") && i < cubicCurveCount) {
                Main.group2.getChildren().remove(arrCubicCurve[i]);
                Main.textfieldB.setText("remove, cubiccurve, " + i);
            }
            else if(str.equals("cylinder") && i < cylinderCount) {
                Main.group2.getChildren().remove(arrCyl[i]);
                Main.textfieldB.setText("remove, cylinder, " + i);
            }
            else if(str.equals("circle") && i < circleCount) {
                Main.group2.getChildren().remove(arrCircle[i]);
                Main.textfieldB.setText("remove, circle, " + i);
            }
            else{
                Main.textfieldB.setText("Ran the remove function but the object did not exist");
            }
        } catch (Exception e) {
            Main.textfieldB.setText("Ran the remove function but the object did not exist");
        }
    }



}//class