package ru.timuruktus.neuroweb;


import ru.timuruktus.core.BitMap;


public class Perseptron {
    public static final int MATRIX_LAYER_SIZE_X = 10;
    public static  final int MATRIX_LAYER_SIZE_Y = 10;

    protected static int[][] innerSignals = new int[MATRIX_LAYER_SIZE_X][MATRIX_LAYER_SIZE_Y];
    BitMap b = new BitMap();

    protected int[][] bitMapCopy;

    private int matrixLayerPosX, matrixLayerPosY;


    public final static double LEARNING_COEF = 0.6;


    public void initInnerSignals(){
        bitMapCopy = b.getBitMap();
        for(matrixLayerPosY = 0; matrixLayerPosY < 10; matrixLayerPosY++){
            for(matrixLayerPosX = 0; matrixLayerPosX < 10; matrixLayerPosX++){
                innerSignals[matrixLayerPosY] [matrixLayerPosX] = 0;
            }
        }
        for(matrixLayerPosY = 0; matrixLayerPosY < 10; matrixLayerPosY++){
            for(matrixLayerPosX = 0; matrixLayerPosX < 10; matrixLayerPosX++){
                moveMatrixLayer(matrixLayerPosY, matrixLayerPosX);
            }
        }
    }



    public void moveMatrixLayer(int matrixLayerPosY, int matrixLayerPosX){
        for(int y = matrixLayerPosY * 10; y < matrixLayerPosY * 10 + 10; y++){
            for(int x = matrixLayerPosX * 10; x < matrixLayerPosX * 10 + 10; x++){
                if(bitMapCopy[x][y] == 1){
                    System.out.println("В moveMatrixLayer мы заходим");
                    innerSignals[matrixLayerPosX][matrixLayerPosY] = 1;
                }
            }
        }
    }

    public void test11(){
        for(int i = 0; i < 10; i++){
            for(int a = 0; a < 10; a++){
                if(innerSignals[i][a] != 0) {
                    System.out.println(innerSignals[i][a]);
                }
            }
        }
    }


    public int[][] getInnerSignals(){
        return innerSignals;
    }

}
