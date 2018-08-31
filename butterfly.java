/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ButterFly;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Pair;

/**
 *
 * @author Subhodeep Guin
 */
public class butterfly extends Applet implements ActionListener{

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
    TextField rxtf = new TextField();
    TextField rytf = new TextField();
    int sx,sy,rx,ry;
    int stepSize = 2;
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
        
        add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    // TODO overwrite start(), stop() and destroy() methods
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

        Butterfly b = new Butterfly();
        try {
            b.drawButterfly(g);
        }catch(Exception e){}        
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
            sxtf.setText(""); sytf.setText(""); rxtf.setText(""); rytf.setText("");
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
    public class Butterfly {
        public int wing_len = 30;
        public int wing_width = 10;
        public int body_circle = 5;
        
        ArrayList<Pair<Integer,Integer>> head = new ArrayList<Pair<Integer,Integer>>();
        
        Algorithm a = new Algorithm();
        public void drawButterfly(Graphics g) {
            
            a.drawEllipseWithRotation(g,0,4*body_circle,body_circle,body_circle,0);   //topmost circle
            a.drawEllipseWithRotation(g,0,2*body_circle,body_circle,body_circle,0);   //top circle
            a.drawEllipseWithRotation(g,0,0,body_circle,body_circle,0);   //center circle
            a.drawEllipseWithRotation(g,0,-2*body_circle,body_circle,body_circle,0);   //bottom circle
            a.drawEllipseWithRotation(g,0,-4*body_circle,body_circle,body_circle,0);   //second to bottom circle
            a.drawEllipseWithRotation(g,0,-6*body_circle,body_circle,body_circle,0);   //bottommost circle
            
            //wings
            a.drawEllipseWithRotation(g, actualCoordinate(-45,0), actualCoordinate(-45, 1), wing_len, wing_width, 45);    //NE wing
            a.drawEllipseWithRotation(g, actualCoordinate(45, 0), actualCoordinate(45, 1), wing_len, wing_width, -45); //SW wing
            a.drawEllipseWithRotation(g,-actualCoordinate(-45, 0), actualCoordinate(-45, 1), wing_len, wing_width, -45); //NW wing
            a.drawEllipseWithRotation(g,-actualCoordinate(45, 0), actualCoordinate(45, 1), wing_len, wing_width, 45); //SW wing
            
            /*
            a.DDA(g, 0, -32, 0, -48);   //main stem
            a.DDA(g, 0, -40, 10, -36);  //auxilary right stem
            a.DDA(g, 0, -40, -10, -36); //auxilary left stem
            */
            head.add(new Pair<Integer,Integer>(-body_circle*2, 5*body_circle)); //Gamla points
            head.add(new Pair<Integer,Integer>(body_circle*2, 5*body_circle));
            head.add(new Pair<Integer,Integer>(body_circle*2, 8*body_circle));
            head.add(new Pair<Integer,Integer>(-body_circle*2, 8*body_circle));
            a.drawPolygon(g,head);
            
            a.DDA(g, 2*body_circle, 8*body_circle, 4*body_circle, 10*body_circle); //left head antennae
            a.DDA(g, -2*body_circle, 8*body_circle, -4*body_circle, 10*body_circle);   //right head antennae
            
            a.drawEllipseWithRotation(g,-4*body_circle,10*body_circle,body_circle/2,body_circle/2,0);   //left head circle
            a.drawEllipseWithRotation(g,4*body_circle,10*body_circle,body_circle/2,body_circle/2,0);    //right head circle
        }
        
        private int actualCoordinate(int degree, int mode) {
            if(mode == 0) { // 0 for finding sx, 1 for sy
                return (int)((wing_len-body_circle/2)*Math.round(Math.cos(Math.toRadians(-degree))));
            }
            return (int)((wing_len-body_circle)*Math.round(Math.sin(Math.toRadians(-degree))));
        }
    }
    
    public class Algorithm {
        public int rotateX(int x, int y, int deg) {
            return (int)Math.floor(x*Math.cos(Math.toRadians(-deg)) + y*Math.sin(Math.toRadians(-deg)));
        }
        
        public int rotateY(int x, int y, int deg) {
            return (int)Math.floor(y*Math.cos(Math.toRadians(-deg)) - x*Math.sin(Math.toRadians(-deg)));
        }
        
        
        //public void colorEllipse(Graphics g, int sx, int sy, int a, int b, int deg) {}
        public void drawEllipseWithRotation(Graphics g, int sx, int sy, int a, int b, int deg) {
            
            int x=0, y=b;
            double a_sqr = Math.pow(a, 2);
            double b_sqr = Math.pow(b, 2);
            
            //double dx = 2*b_sqr*x;
            double dx = 0;
            double dy = 2*a_sqr*y;
            
            //specialPointPlotEllipse(g, sx+offsetX, sy+offsetY, x, y, deg);
            specialPointPlotEllipse(g, sx, sy, x, y, deg);
            
            //REGION 1
            double d1 = ( b_sqr ) + ( a_sqr/4 ) - ( a_sqr*b );
            while(dx<dy){
                if(d1<0) {
                    x += 1;
                    dx += 2*b_sqr;
                    d1 += b_sqr + dx;
                }
                else {
                    x += 1;
                    y -= 1;
                    dx += 2*b_sqr;
                    dy -= 2*a_sqr;
                    d1 += dx-dy + b_sqr;
                }
                specialPointPlotEllipse(g, sx, sy, x, y, deg);
            }
            
            //REGION 2
            double d2 = ( b_sqr*Math.pow((x + 0.5), 2) ) + ( a_sqr*Math.pow((y-1), 2) ) - ( a_sqr*b_sqr );
            while(y>0){
                if(d2>0) {
                    y -= 1;
                    dy -= 2*a_sqr;
                    d2 += a_sqr - dy;
                }
                else {
                    x += 1;
                    y -= 1;
                    dy -= 2*a_sqr;
                    dx += 2*b_sqr;
                    d2 += a_sqr + dx-dy;
                }
                specialPointPlotEllipse(g, sx, sy, x, y, deg);
            }
        }
        public void specialPointPlotEllipse(Graphics g, int sx, int sy, int x, int y, int deg) {
            g.fillRect(getCoordinate(sx+rotateX(x, y, deg), 0)-j/2, getCoordinate(sy+rotateY(x, y, deg), 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx+rotateX(x, y, -deg), 0)-j/2, getCoordinate(sy-rotateY(x, y, -deg), 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-rotateX(x, y, -deg), 0)-j/2, getCoordinate(sy+rotateY(x, y, -deg), 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-rotateX(x, y, deg), 0)-j/2, getCoordinate(sy-rotateY(x, y, deg), 1)-j/2, j, j);
        }
        
        
        public void DDA(Graphics g, int sx,int sy,int ex,int ey){

        float dx = (ex-sx);
        float dy = (ey-sy);
        float steps;
        if(abs(dx)>abs(dy))
            steps = abs(dx);
        else
            steps = abs(dy);
        
        float Xinc;
        float Yinc;
        if(steps != 0)
        {
            Xinc = (dx) / steps;
            Yinc = (dy) / steps;
        }
        else
        {
            Xinc = (dx);
            Yinc = (dy);
        }
        
        float Xs = (float)sx;
        float Ys = (float)sy;
        int X,Y;
        for(int i=0; i<=steps; i++) {
            X = (int)Math.round(Xs);
            Y = (int)Math.round(Ys);
            //System.out.format("%d %d\n", X, Y);
            g.fillRect(getCoordinate(X, 0)-j/2, getCoordinate(Y, 1)-j/2, j, j);
            Xs += Xinc;
            Ys += Yinc;
        }
    }

    public void drawPolygon(Graphics g, ArrayList<Pair<Integer,Integer>> arr)
    {
        int xs,ys,xe = 0,ye = 0;
        int flag = 0;

        Iterator<Pair<Integer,Integer>> itr = arr.iterator();

        for (Pair<Integer,Integer> temp:
                arr) {
            //System.out.println(temp.getKey()+" "+temp.getValue());
            if(flag == 0)
            {
                xe = temp.getKey();
                ye = temp.getValue();
                flag = 1;
            }
            else
            {
                xs = xe;
                ys = ye;
                xe = temp.getKey();
                ye = temp.getValue();
                DDA(g,xs,ys,xe,ye);
                //DDA(getCoordinate(xs, 0),getCoordinate(ys, 1),getCoordinate(xe,0),getCoordinate(ye,1));
            }
        }

        Pair<Integer,Integer> first = itr.next();
        //DDA(getCoordinate(xe, 0),getCoordinate(ye, 1),getCoordinate(first.getKey(),0),getCoordinate(first.getValue(),1));
        DDA(g,xe,ye,first.getKey(),first.getValue());
        itr = arr.iterator();
        int xpoints[] = new int[arr.size()];
        int ypoints[] = new int[arr.size()];
        int i = 0;
        while(itr.hasNext()) {
            Pair<Integer,Integer> point = itr.next();
            xpoints[i] = (getCoordinate(point.getKey(),0));
            ypoints[i] = (getCoordinate(point.getValue(),1));
            i++;
        }
        //Graphics g = getGraphics();
        //g.setColor(new Color(200,2,1));
        //g.fillPolygon(xpoints, ypoints, i);
        }
    }
    
}
//0,0,0 as background color
//200,200,200 as grid color
//0,0,200 X,Y axis
