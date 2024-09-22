/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bingogameeee;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

/**
 *
 * @author Merve
 */
class CircleButton extends JButton {

      public CircleButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setBorderPainted(false); // Kenar çizgisi olmadan düğmeyi ayarla
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.RED);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        // Kalın bir kenar çizgisi için Stroke oluştur
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4)); // Kalınlığı ayarla (örneğin 3)
        
        // Daha koyu bir kenar rengi seç
        Color darkerBorderColor = new Color(178,34,34);
        g.setColor(darkerBorderColor);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
    Shape shape;

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}

