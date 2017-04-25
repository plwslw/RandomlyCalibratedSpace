import java.util.*;

public class RCS{

    public Stack<TMatrix> base;
    //public TMatrix top;//new rcs
    
    public RCS(){
	base = new Stack<TMatrix>();
	//top = new TMatrix();
    }

    public RCS(RCS old){
	base = old.base;
    }

    public push(TMatrix top){
	base.push(top);
    }

    public pop(){
	base.pop();
    }
	
    public static void main(String[] args){}

}
