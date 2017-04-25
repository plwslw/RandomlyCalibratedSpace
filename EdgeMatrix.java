import java.io.*;
import java.util.*;

public class EdgeMatrix extends Matrix{

    public EdgeMatrix(){
	super();
    }

    public EdgeMatrix(double[][] x){
	super(x);
    }

    public EdgeMatrix(EdgeMatrix E){
	super();
    }
    
    public void addEdge(double x1, double y1, double z1, double x2, double y2, double z2){
	double [] P1={x1, y1, z1, 1};
	double [] P2={x2, y2, z2, 1};
	addColumn(P1);
	addColumn(P2);
    }

    public void addEdge(int x1, int y1, int z1, int x2, int y2, int z2){
	double a1 = (double) x1;
	double a2 = (double) x2;
	double b1 = (double) y1;
	double b2 = (double) y2;
	double c1 = (double) z1;
	double c2 = (double) z2;
	addEdge(a1, b1, c1, a2, b2, c2);
    }

    public static void main(String[] args){
	EdgeMatrix E = new EdgeMatrix();
	E.addEdge(0, 100, 0, 100, 0, 100);
	System.out.println(E);
    }
}
