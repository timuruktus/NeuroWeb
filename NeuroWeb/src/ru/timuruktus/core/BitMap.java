package ru.timuruktus.core;

import java.util.logging.Logger;


public class BitMap {

    final static public int WIDTH = 100; //x
    final static public int HEIGHT = 100; //y
    static int[][] imageBitmap = new int[WIDTH][HEIGHT];
    private static Logger log = Logger.getLogger(Graphic.class.getName());


    public static void addToMatrix(int firstX, int firstY, int lastX, int lastY){
        log.info(firstX + " " + firstY + " " + lastX + " " + lastY);
        imageBitmap[firstX][firstY] = 1;
        imageBitmap[lastX][lastY] = 1;
    }




    public static void initBitMap(){
        log.info("Matrix has been initialized");
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < HEIGHT; y++){
                imageBitmap[x][y] = 0;
            }
        }
    }

    public int[][] getBitMap(){
        return imageBitmap;
    }


}
