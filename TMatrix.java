public class TMatrix extends Matrix{

    public TMatrix(){
	super(identity(4));
    }

    public TMatrix(TMatrix T){
	super(T);
    }

    public void compose(TMatrix T){
	matrixMultiply(T);
    }

    public static TMatrix translateMatrix(double dx, double dy, double dz){
	TMatrix T = new TMatrix();
	T.A[0][3] = dx;
	T.A[1][3] = dy;
	T.A[2][3] = dz;
	return T;
    }

    public static TMatrix scaleMatrix(double kx, double ky, double kz){
	TMatrix T = new TMatrix();
	T.A[0][0] = kx;
	T.A[1][1] = ky;
	T.A[2][2] = kz;
	return T;
    }

    public static TMatrix zRotateMatrix(double R){
	R = Math.toRadians(R);
	TMatrix T = new TMatrix();
	T.A[0][0] = Math.cos(R);
	T.A[0][1] = -Math.sin(R);
	T.A[1][0] = Math.sin(R);
	T.A[1][1] = Math.cos(R);
	return T;
    }

    public static TMatrix xRotateMatrix(double R){
	R = Math.toRadians(R);
	TMatrix T = new TMatrix();
	T.A[1][1] = Math.cos(R);
	T.A[1][2] = -Math.sin(R);
	T.A[2][1] = Math.sin(R);
	T.A[2][2] = Math.cos(R);
	return T;
    }

    public static TMatrix yRotateMatrix(double R){
	R = Math.toRadians(R);
	TMatrix T = new TMatrix();
	T.A[0][0] = Math.cos(R);
	T.A[0][2] = Math.sin(R);
	T.A[2][0] = -Math.sin(R);
	T.A[2][2] = Math.cos(R);
	return T;
    }
    
    public void translate(double dx, double dy, double dz){
	compose(translateMatrix(dx, dy, dz));
    }

    public void scale(double dx, double dy, double dz){
	compose(scaleMatrix(dx, dy, dz));
    }
    
    public void xRotate(double theta){
	compose(xRotateMatrix(theta));
    }

    public void yRotate(double theta){
	compose(yRotateMatrix(theta));
    }

    public void zRotate(double theta){
	compose(zRotateMatrix(theta));
    }
    
    public static void main(String[] args){
	TMatrix T = new TMatrix();
	System.out.println(T);
	EdgeMatrix E = new EdgeMatrix();
	E.addEdge(0, 300, 0, 300, 0, 300);
	System.out.println("\n"+ E);

	/*System.out.println("Translation Matrix: (100, 100, 100)\n" + translateMatrix(100.0,100.0,100.0));
	System.out.println("Scale Matrix: (100, 100, 100)\n" + scaleMatrix(100.0,100.0,100.0));
	System.out.println("Rotation Matrix: 60 degrees, z axis\n" + ZRotateMatrix(60));
	System.out.println("Rotation Matrix: 60 degrees, x axis\n" + XRotateMatrix(60));
	System.out.println("Rotation Matrix: 60 degrees, y axis\n" + YRotateMatrix(60));
	*/

	T.translate(100, 100, 100);
	System.out.println(T);
	T.scale(.5,1,2);
	System.out.println(T);
    }

}

    
