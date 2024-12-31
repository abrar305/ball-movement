package com.mycompany.ballmovement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallMovement {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Movement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        BallPanel ballPanel = new BallPanel();
        frame.add(ballPanel, BorderLayout.CENTER);

        
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ballPanel.centerBall();
            }
        });

        
        JPanel controlPanel = new JPanel();
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton resetButton = new JButton("Reset");

        
        leftButton.addActionListener(e -> ballPanel.move(-ballPanel.getStep(), 0));
        rightButton.addActionListener(e -> ballPanel.move(ballPanel.getStep(), 0));
        upButton.addActionListener(e -> ballPanel.move(0, -ballPanel.getStep()));
        downButton.addActionListener(e -> ballPanel.move(0, ballPanel.getStep()));
        resetButton.addActionListener(e -> ballPanel.reset());

        
        controlPanel.add(leftButton);
        controlPanel.add(rightButton);
        controlPanel.add(upButton);
        controlPanel.add(downButton);
        controlPanel.add(resetButton);

        
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(3, 1, 10, 10)); // تخطيط عمودي
        JButton step20Button = new JButton("Step 20");
        JButton step50Button = new JButton("Step 50");
        JButton step100Button = new JButton("Step 100");

        
        step20Button.addActionListener(e -> ballPanel.setStep(20));
        step50Button.addActionListener(e -> ballPanel.setStep(50));
        step100Button.addActionListener(e -> ballPanel.setStep(100));

        
        sidePanel.add(step20Button);
        sidePanel.add(step50Button);
        sidePanel.add(step100Button);

       
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(sidePanel, BorderLayout.EAST);

        
        frame.setVisible(true);
        ballPanel.centerBall();
    }
}

class BallPanel extends JPanel {
    private int x, y; 
    private final int DIAMETER = 100; 
    private Color ballColor = Color.RED;
    private int moveCount = 0;
    private int step = 20; 

    public BallPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ballColor);
        g.fillOval(x, y, DIAMETER, DIAMETER); 
        g.setColor(Color.white);
        g.drawString("Moves: " + moveCount, 10, 20); 
    }

    public void move(int dx, int dy) {
        
        x += dx;
        y += dy;
        if (x < 0) {
            x = getWidth() - DIAMETER;
        } else if (x > getWidth() - DIAMETER) {
            x = 0;
        }

        if (y < 0) {
            y = getHeight() - DIAMETER;
        } else if (y > getHeight() - DIAMETER) {
            y = 0;
        }

        ballColor = new Color((int) (Math.random() * 0x1000000)); 
        moveCount++;
        repaint();
    }

    public void reset() {
        centerBall();
        moveCount = 0; 
        ballColor = Color.RED; 
        repaint();
    }

    public void centerBall() {
        x = (getWidth() - DIAMETER) / 2; 
        y = (getHeight() - DIAMETER) / 2; 
        repaint();
    }

    
    public void setStep(int step) {
        this.step = step;
    }

    public int getStep() {
        return step;
    }
}
