package ru.timuruktus.neuroweb;


import ru.timuruktus.core.WorkWithFiles;

import java.util.logging.Logger;

public class Neuron {


    private double[][] innerSignalsCoef = new double[Perseptron.MATRIX_LAYER_SIZE_X][Perseptron.MATRIX_LAYER_SIZE_Y];
    WorkWithFiles file = new WorkWithFiles();
    private static Logger log = Logger.getLogger(Neuron.class.getName());
    Perseptron p = new Perseptron();


    public int getActivationStepFor(int num){
        switch (num){
            case 0:
                return 200;
            case 1:
                return 190;
            case 2:
                return 180;
            case 3:
                return 170;
            case 4:
                return 160;
            case 5:
                return 150;
            case 6:
                return 140;
            case 7:
                return 130;
            case 8:
                return 120;
            case 9:
                return 110;
        }
        return -1;
    }



    public void correctInnerSignalsCoef(int neuronId){
        log.info("neuronID == " + neuronId);
        for(int i = 0; i < 10; i++) {
            if (i == neuronId) {
                file.openFile("res//" + neuronId + ".txt");
                this.innerSignalsCoef = file.readFile();
                for (int x = 0; x < Perseptron.MATRIX_LAYER_SIZE_X; x++) {
                    for (int y = 0; y < Perseptron.MATRIX_LAYER_SIZE_Y; y++) {
                        if (p.innerSignals[x][y] == 1) {
                            log.info("innerSignals == 1");
                            if(neuronId != 0) {
                                this.innerSignalsCoef[x][y] += Perseptron.LEARNING_COEF * 100 * neuronId;
                            }else{
                                this.innerSignalsCoef[x][y] += Perseptron.LEARNING_COEF * 100 ;
                            }
                        } else if (p.innerSignals[x][y] == 0) {
                            this.innerSignalsCoef[x][y] -= Perseptron.LEARNING_COEF * 10;
                        }
                    }
                }

                file.write("res//" + neuronId + ".txt", this.innerSignalsCoef);
            } else {
                for (int a = 0; a < 10; a++) {
                    if (a != neuronId) {
                        file.openFile("res//" + a + ".txt");
                        this.innerSignalsCoef = file.readFile();
                        for (int x = 0; x < Perseptron.MATRIX_LAYER_SIZE_X; x++) {
                            for (int y = 0; y < Perseptron.MATRIX_LAYER_SIZE_Y; y++) {
                                if (p.innerSignals[x][y] == 1) {
                                    log.info("innerSignals == 1");
                                    this.innerSignalsCoef[x][y] -= Perseptron.LEARNING_COEF * 100;
                                }
                            }
                            file.write("res//" + a + ".txt", innerSignalsCoef);
                        }
                    }
                }
            }
        }
    }

    public int getAnswers(int innerSignals[][]){
        int answer = -1;
        for(int i = 0; i < 10; i++){
            double sum = 0;
            file.openFile("res//" + i + ".txt");
            this.innerSignalsCoef = file.readFile();
            for(int x = 0; x < Perseptron.MATRIX_LAYER_SIZE_X; x++){
                for(int y = 0; y < Perseptron.MATRIX_LAYER_SIZE_Y; y++){
                    sum += innerSignals[x][y] * innerSignalsCoef[x][y];
                    System.out.println("innerSignals = " + innerSignals[x][y] + " * " + innerSignalsCoef[x][y] +" sum = " + sum);
                    if(sum > getActivationStepFor(i)){ return i;}
                }
            }
        }
        return answer;
    }


}