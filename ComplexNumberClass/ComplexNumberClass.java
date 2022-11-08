class Complex
{
    // member variables
    private double real;
    private double imaginary;  //10/10
    public Complex(double real, double imaginary)  //10/10
    {
        this.real = real;
        this.imaginary = imaginary;
    }
    public void changeValue(double a, double b)  //10/10
    {
        real = a;
        imaginary = b;
    }
    //member methods
    public double getReal() {return real;}   //10/10
    public double getImaginary() {return imaginary;}
    public void print() { System.out.println(real + "+" + imaginary + "i");}  //10/10
    public void print(boolean huh) {System.out.println("(" + real + "," + imaginary + ")");}  //10/10
    public Complex add(Complex secondNum) //40/40
    {
        Complex myNum = new Complex(real + secondNum.getReal(), imaginary + secondNum.getImaginary());
        return myNum;
    }
    public Complex mult(Complex secondNum)  //24/40
    {
        Complex myNum = new Complex((real * secondNum.getReal()) - (imaginary*secondNum.getImaginary()), (real * secondNum.getImaginary()) + (imaginary * secondNum.getReal()));//IMAGINARY PART IS OFF
        return myNum;
    }
}
public class ComplexNumberClass {
    public static void main(String[] args)   
    {
        Complex x = new Complex(2,3);
        Complex y = new Complex (4,2);
        Complex sum = x.add(y);
        sum.print();
        Complex product = x.mult(y);  //CHECK ANSWER  30/40
        product.print();
    }   
}
//GRADE: 134/160 SEE COMMENTS ABOVE IN CAPS.