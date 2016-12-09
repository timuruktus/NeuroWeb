package ru.timuruktus.core;


import ru.timuruktus.neuroweb.Perseptron;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class WorkWithFiles {

    private static Scanner scn;
    private static PrintWriter out;
    private static FileWriter fileWriting;


    public static void write(String path, double[][] innerSignalsCoef){

        try {
            fileWriting = new FileWriter(path);
            for(int x = 0; x < Perseptron.MATRIX_LAYER_SIZE_X; x++){
                for(int y = 0; y < Perseptron.MATRIX_LAYER_SIZE_Y; y++){
                    double num = innerSignalsCoef[x][y];
                    String number = String.format("%.2f ", num);
                    fileWriting.write(number);
                }
                fileWriting.write("\n");
            }
            fileWriting.close();
        }catch(IOException ex){JOptionPane.showMessageDialog(null, "File doesn't exists.");}
    }

    public static double[][] readFile(){
        double[][] matrix = new double[Perseptron.MATRIX_LAYER_SIZE_X][Perseptron.MATRIX_LAYER_SIZE_Y];
        while(scn.hasNext()){
            for(int i = 0; i < Perseptron.MATRIX_LAYER_SIZE_X; i ++){
                for(int a = 0; a < Perseptron.MATRIX_LAYER_SIZE_Y; a++){
                    matrix[i][a] = scn.nextDouble();
                }
            }
        }
        scn.close();
        return matrix;
    }

    public static void openFile(String path){
        try {
            scn = new Scanner(new File(path));
        }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Presets Doesn't exists!");
        }
    }

}
