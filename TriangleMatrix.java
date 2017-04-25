import java.io.*;
import java.util.*;

public class TriangleMatrix extends Matrix{

    public TriangleMatrix(){
	super();
    }

    public TriangleMatrix(double[][] x){
	super(x);
    }

    public TriangleMatrix(TriangleMatrix T){
	super();
    }
    
    public void addTriangle(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3){
	double [] P1 = {x1, y1, z1, 1};
	double [] P2 = {x2, y2, z2, 1};
	double [] P3 = {x3, y3, z3, 1};
	addColumn(P1);
	addColumn(P2);
	addColumn(P3);
    }

    public void addTriangle(int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3){
	double a1 = (double) x1;
	double b1 = (double) y1;
	double c1 = (double) z1;
	double a2 = (double) x2;
	double b2 = (double) y2;
	double c2 = (double) z2;
	double a3 = (double) x3;
	double b3 = (double) y3;
	double c3 = (double) z3;
	addTriangle(a1, b1, c1, a2, b2, c2, a3, b3, c3);
    }

    
}
