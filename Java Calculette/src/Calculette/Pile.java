package Calculette;
import java.util.Stack;


/** 
* 
* @author ING_TAO
*/


public class Pile extends Stack<Double>{
	private static final long serialVersionUID = 1L;


    public void push(double nombre) { 
  	  Pile.super.push(nombre);
    }
    
    public Double pop() {
  	 return Pile.super.pop();
    }
    
    public void drop() {
  	 if(Pile.super.peek()!=null){
  		 Pile.super.remove(Pile.super.peek());
  	 }
    }
    
    public void clear() {
       Pile.super.clear();    	  
    }
    
    public void swap() {
  	  if(Pile.super.peek()!=null) {
  		  double a=Pile.super.peek();
  		  Pile.super.pop();
  		  double b=Pile.super.peek();
  		  Pile.super.pop();
            Pile.super.push(a);
            Pile.super.push(b);
  	  }
    }
    
}