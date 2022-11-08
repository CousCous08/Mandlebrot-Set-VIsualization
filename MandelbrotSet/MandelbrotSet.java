/* 
Marcus Lee, 2/23/2022, Mandlebrot Set

*/
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.Color;
import java.lang.Math;



public class MandelbrotSet {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
		frame.setSize(400,400);
		frame.setTitle("MandelbrotSet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //kill switch
		frame.setVisible(true); //makes frame visible(default is invisible)
		GraphicsComponent componentObject = new GraphicsComponent();
		frame.add(componentObject);
    }
}
class MandelbrotSetPlane {
    private Complex cPoint;
    private int scaleFactor;
    public MandelbrotSetPlane(Complex center, int scale)
    {
        cPoint = center;
        scaleFactor = scale;
    }
    public Complex getCenterPoint() {return cPoint;}
    public void editCenterPoint(Complex newPoint) {cPoint = newPoint;}
    public int getScaleFactor() {return scaleFactor;}
    public void editScaleFactor(int num) {scaleFactor = num;}
    public void drawPoint(Graphics g, Complex c)
    {
        g.setColor(getPointColor(c));
        g.fillRect((int)(200 + (c.getReal()-cPoint.getReal())*scaleFactor), (int)(200 - (c.getImaginary()-cPoint.getImaginary())*scaleFactor), 1, 1);
    }
    

    public int testPoint(Complex c) {
        int counter = 0;
        Complex z = new Complex(0f, 0f);
        z = z.mult(z).add(c);
        float distance = (float)Math.sqrt( (Math.pow(z.getReal(), 2)) + (Math.pow(z.getImaginary(),2)) );
        while(distance < 2) 
        {
            counter++;
            z = z.mult(z).add(c);
            distance = (float)Math.sqrt( (Math.pow(z.getReal(), 2)) + (Math.pow(z.getImaginary(),2)) );

            if(counter >= 100)
            {
                return 100;
            }
            
        }
        return counter;
    }
    public Color getPointColor(Complex c)
    {
        int iterations = testPoint(c);
        if(iterations >= 100) {return Color.BLACK;}
        else if(iterations >= 75) {return Color.CYAN;}
        else if(iterations >= 50) {return Color.MAGENTA;}
        else if(iterations >=25) {return Color.RED;}
        else return (Color.WHITE);
    }
    public void drawEntireGrid(Graphics g) {
        for(float real = cPoint.getReal() -200f/scaleFactor; real<cPoint.getReal()+200f/scaleFactor; real += 1f/scaleFactor) {
            for(float imaginary = cPoint.getImaginary()-200f/scaleFactor ; imaginary<cPoint.getImaginary() + 200f/scaleFactor ; imaginary += 1f/scaleFactor) {
                Complex newPoint = new Complex(real, imaginary);
                drawPoint(g, newPoint);
            }
        }
    }

}


/////////////////////////////////////////////////////////////////////////
class Complex
{
    // member variables
    private float real;
    private float imaginary;  //10/10
    public Complex(float real, float imaginary)  //10/10
    {
        this.real = real;
        this.imaginary = imaginary;
    }
    public void changeValue(float a, float b) 
    {
        real = a;
        imaginary = b;
    }
    //member methods
    public float getReal() {return real;}  
    public float getImaginary() {return imaginary;}
    public void print() { System.out.println(real + "+" + imaginary + "i");}  
    public void print(boolean huh) {System.out.println("(" + real + "," + imaginary + ")");}  
    public Complex add(Complex secondNum) 
    {
        Complex myNum = new Complex(real + secondNum.getReal(), imaginary + secondNum.getImaginary());
        return myNum;
    }
    public Complex mult(Complex secondNum)
    {
        Complex myNum = new Complex((real * secondNum.getReal()) - (imaginary*secondNum.getImaginary()), (real * secondNum.getImaginary()) + (imaginary * secondNum.getReal()));
        return myNum;
    }
}

class GraphicsComponent extends JComponent //Component where we draw to in Graphics Window
{
    private MandelbrotSetPlane myPlane;
	//like main where all the drawing happens(g is like paintbrush
	public void paintComponent(Graphics g) 
	{
		//Graphics2D g2 = (Graphics2D)g; //upgrades g for more member mehtods
        g.drawString("It works", 200, 200);
        myPlane.drawEntireGrid(g);
	}
    public GraphicsComponent() {
        myPlane = new MandelbrotSetPlane(new Complex(0f, 0f), 100);
        MouseListener mouse = new MouseListener();
        this.addMouseListener(mouse);
        ButtonListener button = new ButtonListener();
        JButton zoom = new JButton("zoom");
        this.add(zoom);
        setLayout(null);
        zoom.setBounds(0,0,100,50);
        zoom.addActionListener(button);
    }
    class MouseListener extends MouseAdapter{
      public void mouseClicked(MouseEvent event)
      {

        float dx = ((float)event.getX() - 200f) / myPlane.getScaleFactor();
        float dy = ((float)event.getY() - 200f) / myPlane.getScaleFactor();
        float x = myPlane.getCenterPoint().getReal() + dx;
        float y = myPlane.getCenterPoint().getImaginary() - dy;
        myPlane.editCenterPoint(new Complex(x,y));
        repaint();
    }
    }
    class ButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         myPlane.editScaleFactor(myPlane.getScaleFactor()*2);
         repaint();
      
      }
    }

}