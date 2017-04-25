import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Script{

    public static void main(String[] args){

	Canvas c = new Canvas();
	TMatrix T = new TMatrix();
	Pixel p = new Pixel(255, 0, 255);
    
	//Scanner s = new Scanner( "script");

	File file = new File(args[0]);
	Scanner s;

	try {
	    s = new Scanner(file);
	} 
	catch (FileNotFoundException e) {
	    e.printStackTrace();
	    s = new Scanner("");
	}
	String line = "";
	String axis = "";

    
	while (s.hasNextLine()){
	    //System.out.println("Entering loop");

	    line = s.nextLine();
	    System.out.println(line);
	
	    if (line.equals("line")){
		c.addEdge(s.nextDouble(),s.nextDouble(),s.nextDouble(),
			  s.nextDouble(),s.nextDouble(),s.nextDouble());
	    }

	    else if (line.equals("triangle")){
		c.addTriangle(s.nextDouble(),s.nextDouble(),s.nextDouble(),
			      s.nextDouble(),s.nextDouble(),s.nextDouble(),
			      s.nextDouble(),s.nextDouble(),s.nextDouble());
	    }

	    else if (line.equals("display")){
		c.draw (p);
		System.out.println("edge matrix:\n" + c.edges);
		System.out.println("triangle matrix:\n" + c.triangles);
		
		c.display();
		c.clear();
	    }

	    else if (line.equals("ident")){
		T = new TMatrix();
	    }

	    else if (line.equals("clear")){
		c.edges=new EdgeMatrix();
		c.triangles=new TriangleMatrix();
	    }

	    else if (line.equals("scale")){
		T.scale( s.nextDouble(),s.nextDouble(),s.nextDouble());
	    }

	    else if (line.equals("apply")){
		c.edges.matrixMultiply(T);
		c.triangles.matrixMultiply(T);
	    }

	    else if (line.equals("move")){
		T.translate( s.nextDouble(),s.nextDouble(),s.nextDouble());
	    }

	    else if (line.equals("rotate")){
		axis = s.next();

		if (axis.equals("z")) T.zRotate(s.nextDouble());
		else if (axis.equals("x")) T.xRotate(s.nextDouble());
		else if (axis.equals("y")) T.yRotate(s.nextDouble());
	    }

	    else if (line.equals("circle")){
		c.circle( s.nextInt(), s.nextInt(), s.nextInt(),
			s.nextInt());
	    }

	    else if (line.equals("bezier")){
		c.spline( s.nextDouble(), s.nextDouble(), s.nextDouble(),
			s.nextDouble(), s.nextDouble(), s.nextDouble(),
			       s.nextDouble(), s.nextDouble(), 0);
	    }
	    
	    else if (line.equals("hermite")){
		c.spline( s.nextDouble(), s.nextDouble(), s.nextDouble(),
			s.nextDouble(), s.nextDouble(), s.nextDouble(),
			       s.nextDouble(), s.nextDouble(), 1);
	    }

	    else if (line.equals("box")){
		c.box( s.nextInt(), s.nextInt(), s.nextInt(),
			    s.nextInt(), s.nextInt(), s.nextInt());
	    }

	    else if (line.equals("sphere")){
		c.sphere( s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
	    }

	    else if (line.equals("torus")){
		c.torus( s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
	    }
	    
	    else if (line.equals("save")){
		System.out.println("edge matrix:\n" + c.edges);
		c.save_extension(s.next());
	    }

	}

    }
}
