package ru.timuruktus.neuroweb;


import ru.timuruktus.core.WorkWithFiles;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Neuron {


    private double[][] innerSignalsCoef = new double[Perseptron.MATRIX_LAYER_SIZE_X][Perseptron.MATRIX_LAYER_SIZE_Y];
    WorkWithFiles file = new WorkWithFiles();
    private static Logger log = Logger.getLogger(Neuron.class.getName());
    Perseptron p = new Perseptron();
    private int neuronId;
    Map<Integer, Double> sums = new HashMap<Integer, Double>();


    public void getActivationStepFor(int i, double sum){
        sums.put(i, sum);
    }
    
    public int setAnswer(){
    	double maxSum = sums.get(0);
    	int sumsId = 0;
    	for (int i =0; i < 10; i++){
    		if(sums.get(i) > maxSum){
    			maxSum = sums.get(i);
    			sumsId = i;
    		}
    	}
    	return sumsId;
    }



    public void correctInnerSignalsCoef(int neuronId){
    	this.neuronId = neuronId;
        log.info("neuronID == " + neuronId);
        file.openFile("res//" + neuronId + ".txt");
        this.innerSignalsCoef = file.readFile();
            for (int x = 0; x < Perseptron.MATRIX_LAYER_SIZE_X; x++) {
                for (int y = 0; y < Perseptron.MATRIX_LAYER_SIZE_Y; y++) {
                    if (p.innerSignals[x][y] == 1) {
                        log.info("innerSignals == 1");
                        this.innerSignalsCoef[x][y] += Perseptron.LEARNING_COEF;
                    }  
                    else if (p.innerSignals[x][y] == 0) {
                        this.innerSignalsCoef[x][y] -= Perseptron.LEARNING_COEF;
                    }
                }
            }
                   
                file.write("res//" + neuronId + ".txt", this.innerSignalsCoef);
                
                for (int x = 0; x < Perseptron.MATRIX_LAYER_SIZE_X; x++) {
                    for (int y = 0; y < Perseptron.MATRIX_LAYER_SIZE_Y; y++) {
                    	this.innerSignalsCoef[x][y] = 0;
                    }
                }
                    
                
                for (int a = 0; a < 10; a++) {
                    if (a != neuronId) {
                        file.openFile("res//" + a + ".txt");
                        this.innerSignalsCoef = file.readFile();
                        for (int x = 0; x < Perseptron.MATRIX_LAYER_SIZE_X; x++) {
                            for (int y = 0; y < Perseptron.MATRIX_LAYER_SIZE_Y; y++) {
                                if (p.innerSignals[x][y] == 1) {
                                    log.info("innerSignals == 1");
                                    this.innerSignalsCoef[x][y] -= Perseptron.LEARNING_COEF / 12;
                                }
                            }
                        }
                        file.write("res//" + a + ".txt", innerSignalsCoef);
                        for (int x = 0; x < Perseptron.MATRIX_LAYER_SIZE_X; x++) {
                            for (int y = 0; y < Perseptron.MATRIX_LAYER_SIZE_Y; y++) {
                            	this.innerSignalsCoef[x][y] = 0;
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
                    
                }
            }
            getActivationStepFor(i,sum);
        }
        answer = setAnswer();
        return answer;
    }


}