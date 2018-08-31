/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flower;

import java.applet.Applet;
import java.awt.*;
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
public class flower extends Applet implements ActionListener {

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

        Flower f = new Flower();
        try {
            f.drawFlower(g);
            
        }catch(Exception e){}
        
        
        /*int nop = 19;
        int maxv = 8;
        int [][]x = new int[nop][maxv];
        int [][]y = new int[nop][maxv];
        
        x[0][0] = //more points to fill in x and y arrays.;
        
        for(int i = 0;i<19;i++){
	 for(int j = 0;j<8;j++){
		 x[i][j] = x[i][j]-x_offset;
		 y[i][j] = y[i][j]-y_offset;
	 }
        }*/
        
        /*SomeOtherClass[] soc = new SomeOtherClass[nop];
 soc[0] = new SomeOtherClass(6,x[0],y[0],gg);
 soc[1] = new SomeOtherClass(8,x[1],y[1],gg);
 
           for(int i = 0;i<nop;i++){
            //   try{
                 soc[i].Create_poly();
                    int r1=(int)Math.round(Math.random()*255);
               int b1=(int)Math.round(Math.random()*255);
               int g1=(int)Math.round(Math.random()*255);
               Color s=new Color(r1, g1, b1);
               gg.setColor(s);
              //soc[i].Fill_Poly(gg);
               //Thread.sleep(100);
             //  }
              // catch (InterruptedException e) {
	// TODO Auto-generated catch block
	//e.printStackTrace();
       //        }
        }
        */
        
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
    public class Flower {
        public int petal_len = 14;
        public int petal_width = 6;
        public int petal_radius = 3;
        
        ArrayList<Pair<Integer,Integer>> gamla = new ArrayList<Pair<Integer,Integer>>();
        ArrayList<Pair<Integer,Integer>> leftLeaf = new ArrayList<Pair<Integer,Integer>>();
        ArrayList<Pair<Integer,Integer>> rightLeaf = new ArrayList<Pair<Integer,Integer>>();
        
        Algorithm a = new Algorithm();
        public void drawFlower(Graphics g) {
            a.drawEllipseWithRotation(g,0,0,petal_radius,petal_radius,0);   //center circle
            //a.drawEllipseWithRotation(g,0,0,30,31,0);
            a.drawEllipseWithRotation(g,0,petal_len+petal_radius, petal_len, petal_width, 90);   //upper petal
            a.drawEllipseWithRotation(g,0,-(petal_len+petal_radius), petal_len, petal_width, 90);   //lower petal
            a.drawEllipseWithRotation(g,+(petal_len+petal_radius/2),+(petal_width+petal_radius),petal_len, petal_width,30);    //NE petal
            a.drawEllipseWithRotation(g,-(petal_len+petal_radius/2),-(petal_width+petal_radius),petal_len, petal_width,30); //SW petal
            a.drawEllipseWithRotation(g,+(petal_width+petal_radius*3),-(petal_len-petal_radius*2),petal_len, petal_width,-30); //SE petal
            a.drawEllipseWithRotation(g,-(petal_width+petal_radius*3),+(petal_len-petal_radius*2),petal_len, petal_width,-30); //NW petal
            
            a.DDA(g, 0, -32, 0, -48);   //main stem
            a.DDA(g, 0, -40, 10, -36);  //auxilary right stem
            a.DDA(g, 0, -40, -10, -36); //auxilary left stem
            
            gamla.add(new Pair<Integer,Integer>(-10, -48)); //Gamla points
            gamla.add(new Pair<Integer,Integer>(10, -48));
            gamla.add(new Pair<Integer,Integer>(-10, -68));
            gamla.add(new Pair<Integer,Integer>(10, -68));
            a.drawPolygon(g,gamla);
            
            leftLeaf.add(new Pair<Integer,Integer>(7,-32));  //Left Leaf points
            leftLeaf.add(new Pair<Integer,Integer>(13,-40));
            leftLeaf.add(new Pair<Integer,Integer>(20,-28));
            a.drawPolygon(g,leftLeaf);
            
            rightLeaf.add(new Pair<Integer,Integer>(-7,-32));  //Right Leaf points
            rightLeaf.add(new Pair<Integer,Integer>(-13,-40));
            rightLeaf.add(new Pair<Integer,Integer>(-20,-28));
            a.drawPolygon(g, rightLeaf);
        }
    }
    
    public class Algorithm {
        public int rotateX(int x, int y, int deg) {
            return (int)Math.floor(x*Math.cos(Math.toRadians(-deg)) + y*Math.sin(Math.toRadians(-deg)));
        }
        
        public int rotateY(int x, int y, int deg) {
            return (int)Math.floor(y*Math.cos(Math.toRadians(-deg)) - x*Math.sin(Math.toRadians(-deg)));
        }
        
        public void drawPointWithRotation(Graphics g, int x, int y, int deg) {
            g.fillRect(getCoordinate(rotateX(x, y, deg), 0)-j/2, getCoordinate(rotateY(x, y, deg), 1)-j/2, j, j);
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
        //ellipse with explicit offset
        public void drawEllipse(Graphics g, int sx, int sy, int a, int b, int offsetX, int offsetY) {
            int x=0, y=b;
            double a_sqr = Math.pow(a, 2);
            double b_sqr = Math.pow(b, 2);
            
            //double dx = 2*b_sqr*x;
            double dx = 0;
            double dy = 2*a_sqr*y;
            
            pointPlotEllipse(g, sx+offsetX, sy+offsetY, x, y);
            
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
                pointPlotEllipse(g, sx+offsetX, sy+offsetY, x, y);
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
                pointPlotEllipse(g, sx+offsetX, sy+offsetY, x, y);
            }
        }
        //for normal ellipse without offset
        public void drawEllipse(Graphics g, int sx, int sy, int a, int b){
            drawEllipse(g,sx,sy,a,b,0,0);
        }
        
        public void pointPlotEllipse(Graphics g, int sx, int sy, int x, int y) {
            
            g.fillRect(getCoordinate(sx+x, 0)-j/2, getCoordinate(sy+y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx+x, 0)-j/2, getCoordinate(sy-y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-x, 0)-j/2, getCoordinate(sy+y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-x, 0)-j/2, getCoordinate(sy-y, 1)-j/2, j, j);
            
        }
        
        //circle with explicit offset
        public void drawCircle(Graphics g, int sx, int sy, int r, int offsetX, int offsetY) {
            int x=0,y=r;
        //center point
        g.fillOval(getCoordinate(sx+offsetX, 0)-j/2, getCoordinate(sy+offsetY, 1)-j/2, j, j);
        //four corner points
        if(r>0) {
            pointPlotCircle(g, sx+offsetX, sy+offsetY, x, y);
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
            
            pointPlotCircle(g,sx+offsetX,sy+offsetY,x,y);
            pointPlotCircle(g,sx+offsetX,sy+offsetY,y,x);            
        }
        }
        
        //for normal circle without offset
        public void drawCircle(Graphics g, int sx, int sy, int r) {
            drawCircle(g, sx, sy, r, 0, 0);
        }
        
        public void pointPlotCircle(Graphics g, int sx, int sy, int x, int y) {
            
            g.fillRect(getCoordinate(sx+x, 0)-j/2, getCoordinate(sy+y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx+x, 0)-j/2, getCoordinate(sy-y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-x, 0)-j/2, getCoordinate(sy+y, 1)-j/2, j, j);
            g.fillRect(getCoordinate(sx-x, 0)-j/2, getCoordinate(sy-y, 1)-j/2, j, j);
            
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
    
    /*public class SomeotherClass {
        SomeotherClass(){}
        
        public void fill_poly(){}
        
        public void create_poly(){}
    }*/
}
//0,0,0 as background color
//200,200,200 as grid color
//0,0,200 X,Y axis
