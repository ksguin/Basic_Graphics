/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalCircleDrawing;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Subhodeep Guin
 */
public class Circle extends Applet implements ActionListener {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    int OriginX, OriginY;
    int a,b,c,f_,g_,h_;
    Button b1 = new Button();
    Button b2 = new Button();
    Button b3 = new Button();
    Button draw = new Button();
    TextField sxtf = new TextField();
    TextField sytf = new TextField();
    TextField rtf = new TextField();
    int sx,sy,r;
    int stepSize = 5;
    int j = stepSize;
    
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        //background color
        a = 0;
        b = 0;
        c = 0;
        //grid color
        f_ = 200;
        g_ = 200;
        h_ = 200;
        int fullscreen = this.getWidth()*this.getHeight();
        this.setSize(new Dimension(fullscreen, fullscreen));
        this.setBackground(new Color(a,b,c));
        b1.setLabel("Zoom In");
        b2.setLabel("Zoom Out");
        b3.setLabel("Reset");
        draw.setLabel("DRAW");
        add(b1);
        add(b2);
        
        Label sxLabel = new Label("X :");
        sxLabel.setForeground(Color.WHITE);
        Label syLabel = new Label("Y :");
        syLabel.setForeground(Color.WHITE);
        Label rLabel = new Label("R :");
        rLabel.setForeground(Color.WHITE);
        add(sxLabel); add(sxtf); add(syLabel); add(sytf); add(rLabel); add(rtf);
        //DRAW button
        add(draw);
        add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        draw.addActionListener(this);
    }
    
    
    @Override
    public void paint(Graphics g) {
        //int side = this.getHeight()-10;
        //g.setColor(Color.WHITE);
        //g.fillRect((this.getWidth()-side)/2,(this.getHeight()-side)/2, side, side);
        Color mycolor = new Color(f_,g_,h_);
        g.setColor(mycolor);
        //Vertical lines
        for(int i=this.getWidth()/2; i<=this.getWidth(); i= i+j)
            g.drawLine(i, 0, i, this.getHeight());
        for(int i= this.getWidth()/2; i>0; i = i-j)
            g.drawLine(i, 0, i, this.getHeight());
        //Horizontal Lines
        for(int i=this.getHeight()/2; i<=this.getHeight(); i=i+j)
            g.drawLine(0, i, this.getWidth(), i);
        for(int i=this.getHeight()/2; i>0; i = i-j)
            g.drawLine(0, i, this.getWidth(), i);
        
        //Axis lines
        g.setColor(new Color(0,0,200)); //X and Y axis lines
        g.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
        g.drawLine(0,this.getHeight()/2, this.getWidth(),this.getHeight()/2);
        //co-ordinates of origin
        OriginX = this.getWidth()/2;
        OriginY = this.getHeight()/2;
        //draw YOUR shapes here..
        g.setColor(Color.YELLOW);
        //g.drawLine(getCoordinate(sx, 0), getCoordinate(sy, 1), getCoordinate(ex, 0), getCoordinate(ey, 1));
        //MidPoint-Circle drawing algorithm
        Algorithm a = new Algorithm();
        try {
            a.midpointCircle(g, sx, sy, r);
        }catch(Exception e){ e.printStackTrace(); }
    }

    // TODO overwrite start(), stop() and destroy() methods
    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        if(e.getSource() == b1) {
            j += stepSize;
        }
        if(e.getSource() == b2) {
            if(j>stepSize)
            j -= stepSize;
        }
        if(e.getSource() == b3) {
            j = stepSize;
            //resetting textboxes
            sxtf.setText(""); sytf.setText(""); rtf.setText("");
        }
        if(e.getSource() == draw) {
            sx = Integer.parseInt(sxtf.getText());
            sy = Integer.parseInt(sytf.getText());
            r = Integer.parseInt(rtf.getText());
        }
        repaint();
    }
    
    public int getCoordinate(int pt, int axis) {
        if(axis == 0) { //for x axis we choose 0
            return (OriginX+(pt*j)); //where j is the increase factor
        }else {
            return (OriginY-(pt*j));
        }
    }
    
    public class Algorithm {
        
        public void midpointCircle(Graphics g, int sx, int sy, int r) {
            int x=0,y=r;
        //center point
        g.fillOval(getCoordinate(sx, 0)-j/2, getCoordinate(sy, 1)-j/2, j, j);
        //four corner points
        if(r>0) {
            g.fillRect(getCoordinate(sx, 0)-j/2, getCoordinate(sy+r, 1)-j/2, j, j); //top
            g.fillRect(getCoordinate(sx+r, 0)-j/2, getCoordinate(sy, 1)-j/2, j, j); //right
            g.fillRect(getCoordinate(sx-r, 0)-j/2, getCoordinate(sy, 1)-j/2, j, j); //left
            g.fillRect(getCoordinate(sx, 0)-j/2, getCoordinate(sy-r, 1)-j/2, j, j); //bottom
        }
        //rest
        double P = 1.0-r;
        while(y>x) {
            if(P<0) {
                P = P + 2*x + 3;
            }
            else {
                P = P + 2*x - 2*y + 5;
                y--;
            }
            x++;
            
            g.fillRect(getCoordinate(sx+x, 0)-j/2, getCoordinate(sy+y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx+x, 0)-j/2, getCoordinate(sy-y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-x, 0)-j/2, getCoordinate(sy-y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-x, 0)-j/2, getCoordinate(sy+y, 1)-j/2, j, j);
            
            g.fillRect(getCoordinate(sx+y, 0)-j/2, getCoordinate(sy+x, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx+y, 0)-j/2, getCoordinate(sy-x, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-y, 0)-j/2, getCoordinate(sy-x, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-y, 0)-j/2, getCoordinate(sy+x, 1)-j/2, j, j);
        }
        }
    }
    
    /*public class SomeotherClass {
        SomeotherClass(){}
        
        public void fill_poly(){}
        
        public void create_poly(){}
    }*/
}
//0,0,0 as background color
//200,200,200 as grid color
//0,0,200 X,Y axis
