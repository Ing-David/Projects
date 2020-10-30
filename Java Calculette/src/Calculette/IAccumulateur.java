package Calculette;


/** 
* 
* @author ING_TAO
*/


public interface IAccumulateur {
      
	 public void push();
     public void drop();
     public void swap();
     public void add();
     public void sub();
     public void mult();
     public void div();
     public void neg();
     public void backspace();
     public void accumuler(char character);
     public void reset();
	 public void Clear();
     
}
