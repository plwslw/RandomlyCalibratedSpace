import java.io.*;
import java.util.*;

// ===================================================
// Canvas Class - Drawing and Saving
// ===================================================
public class Canvas {
    public Pixel[][] canvas; // Drawing Canvas
    public EdgeMatrix edges; // Lines
    public TriangleMatrix triangles;
    public int x, y; // Dimensions
    
    // Constructors
    public Canvas() {
	canvas = new Pixel[501][501];
	x = 500;
	y = 500;
	fill(255, 255, 255);
	edges = new EdgeMatrix();
	triangles = new TriangleMatrix();
    }
    public Canvas(int x, int y) {
	canvas = new Pixel[y+1][x+1];
	this.x = x+1;
	this.y = y+1;
	fill(255, 255, 255);
	edges = new EdgeMatrix();
	triangles = new TriangleMatrix();
    }
    public Canvas(int x, int y, Pixel p) {
	this(x, y);
	fill(p);
    }
    public Canvas(int x, int y, int R, int G, int B) {
	this(x, y);
	fill(R, G, B);
    }

    // Accessors + Mutators
    public int[] getXY() {
	return new int[]{x, y};
    }
    public int getX() {
	return x;
    }
    public int getY() {
	return y;
    }

    // Canvas Methods
    public boolean draw_pixel(int a, int b, Pixel p) {

	if (a >= 0 && a <x  && b > 0 && b < y){
	    canvas[b][a] = new Pixel(p);
	    return true;
	}
	return false;
	
    }
    
    public boolean draw_pixel(int x, int y) {
	return draw_pixel(x, y, new Pixel(0, 0, 0));
    }
    public boolean draw_pixel(int x, int y, int R, int G, int B) {
	return draw_pixel(x, y, new Pixel(R, G, B));
    }

    public boolean fill(Pixel p) {
	for (int i = 0; i < y; i++) {
	    for (int j = 0; j < x; j++) {
		canvas[i][j] = p;
	    }
	}
	return true;
    }
    public boolean fill(int R, int G, int B) {
	return fill(new Pixel(R, G, B));
    }

    public void clear(){
	fill(255, 255, 255);
    }

    public void drawEdges(Pixel p, int lines){
	int N = (lines < edges.length()) ? lines : edges.length();
	if (N==0) return;
	for (int i=0;i<N;i+=2)
	    line(edges.A[0][i], edges.A[1][i], edges.A[0][i+1], edges.A[1][i+1], p);
    }

    public void drawEdges(Pixel p){
	int lines = edges.length();
	drawEdges (p, lines);
    }

    public void drawTriangles(Pixel p, int lines){
	int N = (lines < triangles.length()) ? lines : triangles.length();
	if (N==0) return;
	
	for (int i=0;i<N;i+=3){
	    line(triangles.A[0][i], triangles.A[1][i], triangles.A[0][i+1], triangles.A[1][i+1], p);
	    line(triangles.A[0][i], triangles.A[1][i], triangles.A[0][i+2], triangles.A[1][i+2], p);
	    line(triangles.A[0][i+2], triangles.A[1][i+2], triangles.A[0][i+1], triangles.A[1][i+1], p);
	}
    }

    public void drawTriangles(Pixel p){
	int lines = triangles.length();
	drawTriangles (p, lines);
    }

    public void draw(Pixel p){
	drawEdges(p);
	drawTriangles(p);
    }

    /*
      ===============================

      // Bresenham's Line Algorithm - 8 Octants

      ===============================
    */
    public boolean line(double x1, double y1, double x2, double y2){
	return line(x1, y1, x2, y2, new Pixel(0, 0, 0));
    }

    public boolean line(double x1, double y1, double x2, double y2, Pixel P){
	int a1,b1,a2,b2;
	a1 = (int) (x1 + 0.5);
	b1 = (int) (y1 + 0.5);
	a2 = (int) (x2 + 0.5);
	b2 = (int) (y2 + 0.5);
	return line(a1, b1, a2, b2, P);
    }

    public boolean line(int x1, int y1, int x2, int y2) {
	return line(x1, y1, x2, y2, new Pixel(0, 0, 0));
    }
    public boolean line(int x1, int y1, int x2, int y2, Pixel p) {
	if (x2 < x1) return line(x2, y2, x1, y1, p);
	int dy = y2 > y1 ? y2 - y1 : y1 - y2; // positive difference
	int dx = x2 - x1; // always positive
	int m = y2 > y1 ? 1 : -1;
	if (dy > dx)
	    if (m > 0)
		return line2(x1, y1, x2, y2, p); // Vertical - Octant 2
	    else
		return line7(x1, y1, x2, y2, p); // Vertical - Octant 7
	else
	    if (m > 0)
		return line1(x1, y1, x2, y2, p); // Horizontal - Octant 1
	    else
		return line8(x1, y1, x2, y2, p); // Horizontal - Octant 8
    }
    public boolean line7(int x1, int y1, int x2, int y2, Pixel p) {
	int A = y2 - y1; // dy
	int B = x1 - x2; // -dx
	int d = -2 * B + A;
	A = 2 * A;
	B = -2 * B;
	while (y1 >= y2) {
	    draw_pixel(x1, y1, p);
	    if (d > 0) {
		x1++;
		d += A;
	    }
	    y1--;
	    d += B;
	}
	return true;
    }
    public boolean line2(int x1, int y1, int x2, int y2, Pixel p) {
	int A = y2 - y1; // dy
	int B = x1 - x2; // -dx
	int d = 2 * B + A;
	A = 2 * A;
	B = 2 * B;
	while (y1 <= y2) {
	    draw_pixel(x1, y1, p);
	    if (d < 0) {
		x1++;
		d += A;
	    }
	    y1++;
	    d += B;
	}
	return true;
    }
    public boolean line8(int x1, int y1, int x2, int y2, Pixel p) {
	int A = y2 - y1; // dy
	int B = x1 - x2; // -dx
	int d = 2 * A - B;
	A = 2 * A;
	B = -2 * B;
	while (x1 <= x2) {
	    draw_pixel(x1, y1, p);
	    if (d < 0) {
		y1--;
		d += B;
	    }
	    x1++;
	    d += A;
	}
	return true;
    }
    public boolean line1(int x1, int y1, int x2, int y2, Pixel p) {
	int A = y2 - y1; // dy
	int B = x1 - x2; // -dx
	int d = 2 * A + B;
	A = 2 * A;
	B = 2 * B;
	while (x1 <= x2) {
	    draw_pixel(x1, y1, p);
	    if (d > 0) {
		y1++;
		d += B;
	    }
	    x1++;
	    d += A;
	}
	return true;
    }

    // Other Designs
    public boolean triangle(int x, int y, Pixel p) {
	int layer = 0;
	while (y > -1) {
	    for (int i = Math.max(0, x - layer); i < Math.min(x + layer + 1, this.x); i++) {
		canvas[y][i] = p;
	    }
	    layer++; y--;
	}
	return true;
    }

    /*

      ================================

      //Curves

      ================================

    */

    public void addEdge(double x1, double y1, double z1, double x2, double y2, double z2){
	edges.addEdge(x1, y1, z1, x2, y2, z2);
    }
    
    public void addEdge(int x1, int y1, int z1, int x2, int y2, int z2){
	edges.addEdge(x1, y1, z1, x2, y2, z2);
    }
    
    public void circle (double cx, double cy, double cz, double r, int steps){
	
	double x0, y0, x, y;
	x0 = cx + r;
	y0 = cy;
	
	double t;
	for (int i=1;i<=steps;i++){
	    t = (2 * Math.PI *i)/steps;

	    x = cx + r*Math.cos(t);
	    y = cy + r*Math.sin(t);
	    
	    addEdge(x0, y0, cz, x, y, cz);
	    x0 = x; y0 = y;
	}
	
    }
    
    // 0 --> Bezier
    // 1 --> Hermite
    public void spline (double x0, double y0, double x1, double y1,
			  double x2, double y2, double x3, double y3,
			  int steps, int flag){

	double a0, b0, a, b;
	double [] coeffX, coeffY;
	
	double [] T = {0, 0, 0, 1};
	
	double [][] bezier = {{-1, 3, -3, 1}, {3, -6, 3, 0}, {-3, 3, 0, 0}, {1, 0, 0, 0}};
	double [][] hermite = {{2, -2, 1, 1}, {-3, 3, -2, -1}, {0, 0, 1, 0}, {1, 0, 0, 0}};
	
	double [][] X = {{x0}, {x1}, {x2}, {x3}};
	double [][] Y = {{y0}, {y1}, {y2}, {y3}};
	
	Matrix coeffXmatrix = new Matrix(X);
	Matrix coeffYmatrix = new Matrix(Y);

	if (flag == 0){
	    coeffXmatrix.matrixMultiply(bezier);
	    coeffYmatrix.matrixMultiply(bezier);
	}
	else if (flag == 1){
	    coeffXmatrix.matrixMultiply(hermite);
	    coeffYmatrix.matrixMultiply(hermite);
	}

	System.out.println("" + coeffXmatrix + " " + coeffYmatrix);
	
	a0 = Matrix.rc(T, coeffXmatrix);
	b0 = Matrix.rc(T, coeffYmatrix);

	double t;

	for (int i=1;i<=steps;i++){
	    t=(1.0*i)/steps;
	    T[0] = t*t*t;
	    T[1] = t*t;
	    T[2] = t;
	    T[3] = 1;

	    a = Matrix.rc(T, coeffXmatrix);
	    b = Matrix.rc(T, coeffYmatrix);

	    addEdge( a0, b0, 0, a, b, 0);
	    a0 = a; b0 = b;
	}

    }

    public void circle (double cx, double cy, double cz, double r){
	circle (cx, cy, cz, r, 80);
    }

    /*
    public void circle (int cx, int cy, int cz, int r){
	circle (double (cx),double (cy),double (cz),double (r));
    }
    */

    public void spline (double x0, double y0, double x1, double y1,
			  double x2, double y2, double x3, double y3,
			  int flag){
	spline (x0, y0, x1, y1, x2, y2, x3, y3, 289, flag);
    }

    /*
    
      =================================

      // 3D Shapes

      =================================

    */

    public void addTriangle(int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3){
	triangles.addTriangle(x1, y1, z1, x2, y2, z2, x3, y3, z3);
    }

    public void addTriangle(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3){
	triangles.addTriangle(x1, y1, z1, x2, y2, z2, x3, y3, z3);
    }
    
    public void box (int x, int y, int z, int height, int width, int depth){
	int x1 = x + width;
	int y1 = y + height;
	int z1 = z + depth;

	/*
	addEdge(x, y, z, x1, y, z);
	addEdge(x, y, z, x, y1, z);
	addEdge(x, y, z, x, y, z1);
	addEdge(x1, y1, z1, x, y1, z1);
	addEdge(x1, y1, z1, x1, y, z1);
	addEdge(x1, y1, z1, x1, y1, z);
	addEdge(x1, y1, z1, x, y1, z1);
	addEdge(x1, y, z, x1, y1, z);
	addEdge(x1, y, z, x1, y, z1);
	addEdge(x, y1, z, x1, y1, z);
	addEdge(x, y1, z, x, y1, z1);
	addEdge(x, y, z1, x, y1, z1);
	addEdge(x, y, z1, x1, y, z1);
	*/

	addTriangle(x, y, z, x1, y, z, x, y1, z);//F
	addTriangle(x1, y1, z1, x1, y, z1, x, y1, z1);//B
	addTriangle(x, y, z, x1, y, z1, x, y, z1);//D
	addTriangle(x1, y1, z1, x, y1, z, x1, y1, z);//U
	addTriangle(x, y, z1, x, y1, z1, x, y1, z);//L
	addTriangle(x1, y, z, x1, y, z1, x1, y1, z);//R
    }
    
    public void poly_sphere (int cx, int cy, int cz, int r, int steps){
	double theta, phi;
	int endi = 5; int endj = steps + 1;

	double[][] x, y, z;
	
	for (int i=0;i<endi;i++){
	    theta = (Math.PI * i)/steps;
	    for (int j=0;j<endj;j++){
		phi = (2 * Math.PI * j)/steps;

		x[i][j] = r*Math.cos(phi) + cx;
		y[i][j] = r*Math.cos(theta)*Math.sin(phi) + cy;
		z[i][j] = r*Math.sin(theta)*Math.sin(phi) + cz;
	    }
	}

	
    }
    
    public void sphere (int cx, int cy, int cz, int r, int steps){
	double theta, phi;
	int endi = 5; int endj = steps + 1;

	double x, y, z, x0, y0, z0;
	
	x0 = r + cx;
	y0 = cy;
	z0 = cz;

	for (int i=0;i<endi;i++){
	    theta = (Math.PI * i)/steps;
	    for (int j=0;j<endj;j++){
		phi = (2 * Math.PI * j)/steps;

		x = r*Math.cos(phi) + cx;
		y = r*Math.cos(theta)*Math.sin(phi) + cy;
		z = r*Math.sin(theta)*Math.sin(phi) + cz;

		
		//addEdge(x0, y0, z0, x, y, z);

		x0 = x; y0 = y; z0 = z;
	    }
	}
    }

    public void torus (int cx, int cy, int cz, int r, int R, int steps){
	double theta, phi;
	int endi = steps + 1; int endj = steps + 1;
	double x, y, z, x0, y0, z0;
	
	x0 = r + R + cx;
	y0 = cy;
	z0 = cz;

	for (int i=0;i<endi;i++){
	    theta = (2 * Math.PI * i)/steps;
	    for (int j=0;j<endj;j++){
		phi = (2 * Math.PI * j)/steps;

		x = r*Math.cos(theta)*Math.cos(phi) + R*Math.cos(theta) + cx;
		y = r*Math.sin(phi) + cy;
		z = -1*r*Math.sin(theta)*Math.cos(phi) - R*Math.sin(theta) + cz;

		addEdge(x0, y0, z0, x, y, z);

		x0 = x; y0 = y; z0 = z;
	    }
	}
    }

    public void sphere (int cx, int cy, int cz, int r){
	sphere(cx, cy, cz, r, 17);
    }

    public void torus (int cx, int cy, int cz, int r, int R){
	torus(cx, cy, cz, r, R, 34);
    }

    
    /*
    
      =================================

      // File Creation

      =================================

    */
    public boolean save(String name) throws FileNotFoundException {
	PrintWriter pw = new PrintWriter(new File(name));
	pw.print("P3 " + x + " " + y + " 255\n"); // Heading
	for (int i = y - 1; i > -1; i--) {
	    for (int j = 0; j < x; j++) {
		// System.out.printf("x: %d\ty: %d\n", j, i); // Debugging
		pw.print(canvas[i][j]);
	    }
	}
	pw.close();
	return true;
    }

    public void save_extension(String file) {

	try{
	    save("temp.ppm");
	} catch (FileNotFoundException r) {
	    System.out.println("Error: File not found");
	}
	
	try{
	    Process process = Runtime.getRuntime().exec("convert temp.ppm "+ file);    
	    try {
		Thread.sleep(1000);
	    } catch(InterruptedException e) {
		Thread.currentThread().interrupt();
	    }
	    process = Runtime.getRuntime().exec("rm temp.ppm");
	} catch (IOException e) {
	    System.out.println(e);
	}

    }
    
    
    public void display(){

	try{
	    save("temp.ppm");
	} catch (FileNotFoundException r) {
	    System.out.println("Error: File not found");
	}
	
	try{
	    Process process = Runtime.getRuntime().exec("display temp.ppm");    
	    try {
		Thread.sleep(1000);
	    } catch(InterruptedException ex) {
		Thread.currentThread().interrupt();
	    }
	    process = Runtime.getRuntime().exec("rm temp.ppm");
	} catch (IOException e) {
	    System.out.println(e);
	}

    }

    public static void main(String [] args){
	Canvas c = new Canvas();
	Pixel p = new Pixel(255, 0, 0);

	c.line(200, 200, 200, 0, p);

	for (int y=0;y<=500;y+=50){
	    p.adjust(0, 30, 20);
	    c.line(200, 200, 250, y, p);
	}

	c.display();

	try{
	    c.save("image.ppm");
	} catch (FileNotFoundException e){
	    System.out.println(e);
	}

    }
}
