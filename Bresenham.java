/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bresenham;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Subhodeep Guin
 */
public class Bresenham extends Applet implements ActionListener {

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
    TextField extf = new TextField();
    TextField eytf = new TextField();
    int sx,sy,ex,ey;
    int stepSize = 40;
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
        
        Label sxLabel = new Label("X1 :");
        sxLabel.setForeground(Color.WHITE);
        Label syLabel = new Label("Y1 :");
        syLabel.setForeground(Color.WHITE);
        Label exLabel = new Label("X2 :");
        exLabel.setForeground(Color.WHITE);
        Label eyLabel = new Label("Y2 :");
        eyLabel.setForeground(Color.WHITE);
        add(sxLabel); add(sxtf); add(syLabel); add(sytf); add(exLabel); add(extf); add(eyLabel); add(eytf);
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
        //Bresenham's algorithm
        int X,Y;
        if(sx>ex)
        {
            X=ex;
            Y=ey;
        }
        else
        {
            X=sx;
            Y=sy;
        }
        //int dx = (X==sx)?(ex-X):(sx-X);
        //int dy = (Y==sy)?(ey-Y):(sy-Y);
        int dx = ex-sx;
        int dy = ey - sy;
        int count=dx;
        int p=2*dy-dx;
        float slope = (float)dy/dx;
        g.fillRect(getCoordinate(X, 0)-j/2, getCoordinate(Y, 1)-j/2, j, j);
        
        while(count>0)
        {
            X+=1;
            if(p<0)
            {
                if(slope<0.0)
                    Y-=1;
                p=p+2*(dy);
            }
            else if(p>0)
            {
                Y+=1;
                p=p+2*(dy-dx);
            }
            g.fillRect(getCoordinate(X, 0)-j/2, getCoordinate(Y, 1)-j/2, j, j);
            count--;
        }
        g.setColor(Color.RED);
        g.drawLine(getCoordinate(sx,0), getCoordinate(sy, 1), getCoordinate(ex, 0), getCoordinate(ey, 1));
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
            sxtf.setText(""); sytf.setText(""); extf.setText(""); eytf.setText("");
        }
        if(e.getSource() == draw) {
            sx = Integer.parseInt(sxtf.getText());
            sy = Integer.parseInt(sytf.getText());
            ex = Integer.parseInt(extf.getText());
            ey = Integer.parseInt(eytf.getText());
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
}
//0,0,0 as background color
//200,200,200 as grid color
//0,0,200 X,Y axis