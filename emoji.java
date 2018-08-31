/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emojis;

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
public class emoji extends Applet implements ActionListener{

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
    TextField mood = new TextField();
    int stepSize = 5;
    int j = stepSize;
    String moodText;
    
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
        mood.setPreferredSize(new Dimension(80, 25));
        add(b1);
        add(b2);
        add(mood);  add(draw);
        add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        draw.addActionListener(this);
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
        Emoji e = new Emoji();
        try {
            switch (moodText) {
                case "happy":
                    e.happyFace(g);
                    break;
                case "upset":
                    e.upsetFace(g);
                    break;
                case "angry":
                    e.angryFace(g);
                    break;
                case "cunning":
                    e.cunningFace(g);
                    break;
                case "fearful":
                    e.fearfulFace(g);
                    break;
                case "surprised":
                    e.surprisedFace(g);
                    break;
                case "sad":
                    e.sadFace(g);
                    break;
                default:
                    break;
            }
        }catch(Exception ex){}      
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
            mood.setText("");
        }
        if(e.getSource() == draw) {
            moodText = mood.getText().toLowerCase();
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
    
    public class Emoji {
        public Emoji() {
            faceRadius = 20;
            //Graphics g= getGraphics();
            //g.setColor(Color.YELLOW));
            //a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
        }
        public Emoji(int radius) {
            faceRadius = radius;
            //Graphics g= getGraphics();
            //g.setColor(Color.YELLOW));
            //a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
        }
        int faceRadius;
        
        Algorithm a = new Algorithm();
                
        public void happyFace(Graphics g) {
            //all the above directions are from the viewer's perspective and not the emoji's perspective
            a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
            a.drawSemiEllipseWithRotation(g, 0, -faceRadius/3, faceRadius/3, faceRadius/2, -90);    //mouth
            a.drawEllipseWithRotation(g, -faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //left eye
            a.drawEllipseWithRotation(g, faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //right eye
            a.DDA(g, -faceRadius/2, faceRadius/2, -faceRadius/4, faceRadius/2+faceRadius/6);    //left eyebrow
            a.DDA(g, faceRadius/2, faceRadius/2, faceRadius/4, faceRadius/2+faceRadius/6);    //right eyebrow
        }
        
        public void cunningFace(Graphics g) {
            a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
            a.drawSemiEllipseWithRotation(g, 0, -faceRadius/3, faceRadius/3, faceRadius/2, -90);    //mouth
            a.drawEllipseWithRotation(g, -faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //left eye
            a.drawEllipseWithRotation(g, faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //right eye
            a.DDA(g, -faceRadius/10, faceRadius/2-faceRadius/8, -faceRadius/2, faceRadius/2+faceRadius/6);    //left eyebrow
            a.DDA(g, faceRadius/10, faceRadius/2-faceRadius/8, faceRadius/2, faceRadius/2+faceRadius/6);    //right eyebrow
        }
        
        public void angryFace(Graphics g) {
            a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
            a.drawSemiEllipseWithRotation(g, 0, -faceRadius/2, faceRadius/3, faceRadius/2, 90);    //mouth
            a.drawEllipseWithRotation(g, -faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //left eye
            a.drawEllipseWithRotation(g, faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //right eye
            a.DDA(g, -faceRadius/10, faceRadius/2-faceRadius/8, -faceRadius/2, faceRadius/2+faceRadius/6);    //left eyebrow
            a.DDA(g, faceRadius/10, faceRadius/2-faceRadius/8, faceRadius/2, faceRadius/2+faceRadius/6);    //right eyebrow
        }
        
        public void upsetFace(Graphics g) {
            a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
            a.drawSemiEllipseWithRotation(g, 0, -faceRadius/2, faceRadius/4, faceRadius/3, 90);    //mouth
            a.drawEllipseWithRotation(g, -faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //left eye
            a.drawEllipseWithRotation(g, faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //right eye
            a.DDA(g, -faceRadius/10, faceRadius/2-faceRadius/8, -faceRadius/2, faceRadius/2+faceRadius/6);    //left eyebrow
            a.DDA(g, faceRadius/10, faceRadius/2-faceRadius/8, faceRadius/2, faceRadius/2+faceRadius/6);    //right eyebrow
        }
        
        public void fearfulFace(Graphics g) {
            a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
            a.drawSemiEllipseWithRotation(g, 0, -faceRadius/2, faceRadius/3, faceRadius/2, 90);    //mouth
            a.drawEllipseWithRotation(g, -faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //left eye
            a.drawEllipseWithRotation(g, faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //right eye
            a.DDA(g, -faceRadius/2, faceRadius/2, -faceRadius/4, faceRadius/2+faceRadius/6);    //left eyebrow
            a.DDA(g, faceRadius/2, faceRadius/2, faceRadius/4, faceRadius/2+faceRadius/6);    //right eyebrow
        }
        
        public void surprisedFace(Graphics g) {
            a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
            a.drawEllipseWithRotation(g, 0, -faceRadius/2, faceRadius/3, faceRadius/4, 90);    //mouth
            a.drawEllipseWithRotation(g, -faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //left eye
            a.drawEllipseWithRotation(g, faceRadius/3, faceRadius/4, faceRadius/6, faceRadius/6, 0);   //right eye
            a.DDA(g, -faceRadius/2, faceRadius/2, -faceRadius/4, faceRadius/2+faceRadius/6);    //left eyebrow
            a.DDA(g, faceRadius/2, faceRadius/2, faceRadius/4, faceRadius/2+faceRadius/6);    //right eyebrow
        }
        
        public void sadFace(Graphics g) {
            a.drawEllipseWithRotation(g, 0, 0, faceRadius, faceRadius, 0);  //outer large circle
            a.drawSemiEllipseWithRotation(g, 0, -faceRadius/2, faceRadius/3, faceRadius/2, 90);    //mouth
            a.DDA(g, -faceRadius/2, faceRadius/3, -faceRadius/5, faceRadius/3);    //left eye
            a.DDA(g, faceRadius/2, faceRadius/3, faceRadius/5, faceRadius/3);    //right eye
            a.DDA(g, -faceRadius/2, faceRadius/2, -faceRadius/4, faceRadius/2+faceRadius/6);    //left eyebrow
            a.DDA(g, faceRadius/2, faceRadius/2, faceRadius/4, faceRadius/2+faceRadius/6);    //right eyebrow
        }
    }
    public class Algorithm {
        //public void colorEllipse(Graphics g, int sx, int sy, int a, int b, int deg) {}
        public void drawSemiEllipseWithRotation(Graphics g, int sx, int sy, int a, int b, int deg) {
            
            int x=0, y=b;
            double a_sqr = Math.pow(a, 2);
            double b_sqr = Math.pow(b, 2);
            
            //double dx = 2*b_sqr*x;
            double dx = 0;
            double dy = 2*a_sqr*y;
            
            //specialPointPlotEllipse(g, sx+offsetX, sy+offsetY, x, y, deg);
            pointPlotSemiEllipse(g, sx, sy, x, y, deg);
            
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
                pointPlotSemiEllipse(g, sx, sy, x, y, deg);
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
                pointPlotSemiEllipse(g, sx, sy, x, y, deg);
            }
        }
        public void pointPlotSemiEllipse(Graphics g, int sx, int sy, int x, int y, int deg) {
            g.fillRect(getCoordinate(sx+rotateX(x, y, deg), 0)-j/2, getCoordinate(sy+rotateY(x, y, deg), 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx+rotateX(x, y, -deg), 0)-j/2, getCoordinate(sy-rotateY(x, y, -deg), 1)-j/2, j, j);
            //g.fillRect(getCoordinate(sx-rotateX(x, y, -deg), 0)-j/2, getCoordinate(sy+rotateY(x, y, -deg), 1)-j/2, j, j);
            //g.fillRect(getCoordinate(sx-rotateX(x, y, deg), 0)-j/2, getCoordinate(sy-rotateY(x, y, deg), 1)-j/2, j, j);
        }
        
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
        
        public int rotateX(int x, int y, int deg) {
            return (int)Math.floor(x*Math.cos(Math.toRadians(-deg)) + y*Math.sin(Math.toRadians(-deg)));
        }
        
        public int rotateY(int x, int y, int deg) {
            return (int)Math.floor(y*Math.cos(Math.toRadians(-deg)) - x*Math.sin(Math.toRadians(-deg)));
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
