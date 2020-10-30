package Calculette;


/** 
* 
* @author ING_TAO
*/


public class Accumulateur implements IAccumulateur{
	
      Pile pile= new Pile();
      String nombre=new String("");

      @Override
      public void push() {
    	  pile.push(Integer.parseInt(nombre));
      }
      @Override
      public void drop() {
    	  pile.drop();
      }
      @Override
      public void swap() {
    	  pile.swap();
      }
      @Override
      public void add() {
    	  double x= pile.pop();
    	  double y= pile.pop();
    	  pile.push(x+y);
      }
      @Override
      public void sub() {
    	  double x=pile.pop();
    	  double y=pile.pop();
    	  pile.push(x-y);
      }
      @Override
      public void mult() {
    	  double x=pile.pop();
    	  double y=pile.pop();
    	  pile.push(x*y);
      }
      @Override
      public void div() {
    	  double x=pile.pop();
    	  double y=pile.pop();
    	  pile.push(x/y);
      }
      @Override
      public void neg() {
    	  double x=pile.pop();
    	  pile.push(-x);
      }
      @Override
      public void backspace() {
    	  if(!nombre.equals("")) {
    		 nombre=nombre.substring(0,nombre.length()-1);
    	  }   	  
      }
      @Override
      public void accumuler(char character) { 
    	  if(nombre!=null || nombre==null) {
    		  nombre+=character;
    	  } 	  
      }
      @Override
      public void reset() {
    	  pile.clear();
      }
      
      /* 
	      * Le méthod Clear() pour vider le contenu de l'accumulateur chaque fois après pousser dans la pile      
	      */
      
      @Override
      public void Clear() {
    	  nombre="";
      }
}
