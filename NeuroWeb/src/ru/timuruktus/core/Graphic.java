package ru.timuruktus.core;


import ru.timuruktus.neuroweb.Neuron;
import ru.timuruktus.neuroweb.Perseptron;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.util.logging.Logger;

public class Graphic extends Canvas implements ActionListener {

    private int firstX, firstY;
    private int lastX, lastY;
    private boolean clear = false;
    private static BitMap bitMap;
    private static Logger log = Logger.getLogger(Graphic.class.getName());
    JLabel text;
    JFrame frame;
    Neuron n = new Neuron();


    public static void main(String args[]) {
        Graphic g = new Graphic();
        g.setGraphicSettings();
        bitMap.initBitMap();
    }

    public Graphic() {
        super();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //координаты нажатия ЛКМ
                firstX = e.getX();
                firstY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //кординаты следующей позиции мыши
                lastX=e.getX();
                lastY=e.getY();
                repaint();
            }
        });


        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()==' ') {
                    bitMap.initBitMap();
                    clear = true;
                    repaint();
                }
            }
        });

    }

    // вызывается каждый раз при вызове repaint()
    public void update(Graphics g) {
        // Если нажат пробел
        if (clear) {
            // Очистить экран (прямоугольник, левая верхняя точка =
            //0,0, ширина и длина по всему экрану.
            g.clearRect(0, 0, getWidth(), getHeight());
            clear = false;
        } else {
            //Если не нажат пробел- то это значит, что экран обновился
            // из-за рисования => рисуем линию
            bitMap.addToMatrix(firstX, firstY, lastX, lastY);
            g.drawLine(firstX, firstY, lastX, lastY);
            firstX=lastX;
            firstY=lastY;
        }
    }


    public void setGraphicSettings(){
        // Создаем окно, называем его "NeuroWeb"
        frame = new JFrame("NeuroWeb");
        JPanel panel = new JPanel(new BorderLayout());
        JPanel botPanel = new JPanel(new GridLayout(1,3));
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JButton button = new JButton("Res");
        JButton btnOk = new JButton("OK");
        JTextField yourNeuron = new JTextField();
        text = new JLabel(" ");
        // Размеры Окна (500 х 400 пикселей)(ширина\высота)
        frame.setSize(170, 165);

        Canvas c = new Graphic();
        c.setBackground(Color.gray);
        frame.add(c, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.add(button, BorderLayout.EAST);
        frame.add(botPanel, BorderLayout.SOUTH);


        botPanel.add(text);
        botPanel.add(btnOk);
        botPanel.add(yourNeuron);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                n = new Neuron();
                int neuronId = Integer.valueOf(yourNeuron.getText());
                n.correctInnerSignalsCoef(neuronId);
            }
        });

        button.addActionListener(this);
    }





    public void actionPerformed(ActionEvent e) {
        Perseptron p = new Perseptron();
        n = new Neuron();
        p.initInnerSignals();
        String textAnswer = String.valueOf(n.getAnswers(p.getInnerSignals()));
        text.setText(textAnswer);

    }



}
