package Calculette;
import javafx.event.EventHandler;


/** 
* 
* @author ING_TAO
*/


public class Controleur implements EventHandler<javafx.event.ActionEvent>{

    GUI vue;
    Accumulateur modele;
    
    
    /* 
     * On a crée le constructeur du contrôleur  
     * pour manipuler sur le GUI(vue) et l'Accumulateur(modele)    
     */

    public Controleur(GUI vue) {
	   this.vue=vue;
	   modele=new Accumulateur();	 
    }
    
    
    /* 
     * On a crée la méthode de substitution(override) de l'interface EventHandler (void handle) 
     * pour gérer la programme quand il y a les actions sur tous les boutons.     
     */
    
	
	@Override
	public void handle(javafx.event.ActionEvent event) {
		// TODO Auto-generated method stub

		/* 
	     * Tous les conditions sur le textfield1   
	     */
			 
				if(vue.textfield1.getText().isEmpty() || modele.pile.empty()) {
			        if (event.getSource() == vue.button1) {
			        	modele.accumuler('1');
			        	vue.textfield1.setText(modele.nombre);
			       }else if (event.getSource() == vue.button2) {
			    	    modele.accumuler('2');
			    	    vue.textfield1.setText(modele.nombre);
			       }else if (event.getSource() == vue.button3) {
			    	    modele.accumuler('3');
			    	    vue.textfield1.setText(modele.nombre);
			       }else if (event.getSource() == vue.button4) {
			    	    modele.accumuler('4');
			    	    vue.textfield1.setText(modele.nombre);
			       }else if (event.getSource() == vue.button5) {
			    	    modele.accumuler('5');
			    	    vue.textfield1.setText(modele.nombre);
			       }else if (event.getSource() == vue.button6) {
			    	    modele.accumuler('6');
			    	    vue.textfield1.setText(modele.nombre);
			       }else if (event.getSource() == vue.button7) {
			    	    modele.accumuler('7');
			    	    vue.textfield1.setText(modele.nombre);
			       }else if (event.getSource() == vue.button8) {
			    	    modele.accumuler('8');
			    	    vue.textfield1.setText(modele.nombre);         
			       }else if (event.getSource() == vue.button9) {
			    	    modele.accumuler('9');
			    	    vue.textfield1.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button0) {
			    		   modele.accumuler('0');
				    	   vue.textfield1.setText(modele.nombre);				            
			       }else if (event.getSource() == vue.button_virgule) {
			    	   if(!vue.textfield1.getText().contains(".") && !vue.textfield1.getText().isEmpty()) {
			    		   modele.accumuler('.');
				    	   vue.textfield1.setText(modele.nombre);			    		  
			    	   }
			       }else if (event.getSource() == vue.button_push) {
			    	   
			    	       if( !modele.nombre.equals("0") && !modele.nombre.isEmpty()){
			    	    	  modele.pile.push(Double.parseDouble(vue.textfield1.getText()));
			    	    	  modele.Clear();
			    	       }
			    	       else {
			    	    	   vue.textfield1.setText("On dois mettre les chiffres mais pas 0 avant pousser sur la pile");
			    	    	   modele.Clear();
			    	       }
			       }else if (event.getSource() == vue.button_backspace) {
			    	   if(!vue.textfield1.getText().isEmpty()) {
			    		   modele.backspace();
			    		   vue.textfield1.setText(modele.nombre);			    	      
			           }
			       }			        
			    }
			 
				
				/* 
			     * Tous les conditions sur le textfield2   
			     */		
				
				else if(vue.textfield2.getText().isEmpty() || modele.pile.size()<2) {
				  
			        if (event.getSource() == vue.button1) {
					    modele.accumuler('1');
					    vue.textfield2.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button2) {
			    	    modele.accumuler('2');
			    	    vue.textfield2.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button3) {
			    	    modele.accumuler('3');
			    	    vue.textfield2.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button4) {
			    	    modele.accumuler('4');
			    	    vue.textfield2.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button5) {
			    	    modele.accumuler('5');
			    	    vue.textfield2.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button6) {
			    	    modele.accumuler('6');
			    	    vue.textfield2.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button7) {
			    	    modele.accumuler('7');
			    	    vue.textfield2.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button8) {
			    	    modele.accumuler('8');
			    	    vue.textfield2.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button9) {
			    	    modele.accumuler('9');
			    	    vue.textfield2.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button0) {
			    	    modele.accumuler('0');
			    	    vue.textfield2.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button_virgule) {
			    	   if(!vue.textfield2.getText().contains(".") && !vue.textfield2.getText().isEmpty()) {
			    		   modele.accumuler('.');
				    	   vue.textfield2.setText(modele.nombre);			    		   
			    	  }
			       }else if (event.getSource() == vue.button_push) {
			    	   if( !modele.nombre.equals("0") && !modele.nombre.isEmpty()) {
			    	    	  modele.pile.push(Double.parseDouble(vue.textfield2.getText()));
			    	    	  modele.Clear();
			    	       }
			    	       else {
			    	    	   vue.textfield2.setText("On dois mettre les chiffres mais pas 0 avant pousser sur la pile");
			    	    	   modele.Clear();
			    	       }
			    	       
			       }else if (event.getSource() == vue.button_backspace) {
			    	   if(!vue.textfield2.getText().isEmpty()) {
			    		   modele.backspace();
			    		   vue.textfield2.setText(modele.nombre);			    	       
			           }
			       }
			    }
				
				/* 
			     * Tous les conditions sur le textfield3   
			     */
				
				
				else if(vue.textfield3.getText().isEmpty() || modele.pile.size()<3) {
					  
			        if (event.getSource() == vue.button1) {
					          modele.accumuler('1');
					          vue.textfield3.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button2) {
			    	    modele.accumuler('2');
			    	    vue.textfield3.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button3) {
			    	    modele.accumuler('3');
			    	    vue.textfield3.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button4) {
			    	    modele.accumuler('4');
			    	    vue.textfield3.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button5) {
			    	    modele.accumuler('5');
			    	    vue.textfield3.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button6) {
			    	    modele.accumuler('6');
			    	    vue.textfield3.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button7) {
			    	    modele.accumuler('7');
			    	    vue.textfield3.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button8) {
			    	    modele.accumuler('8');
			    	    vue.textfield3.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button9) {
			    	    modele.accumuler('9');
			    	    vue.textfield3.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button0) {
			    	    modele.accumuler('0');
			    	    vue.textfield3.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button_virgule) {
			    	   if(!vue.textfield3.getText().contains(".") && !vue.textfield3.getText().isEmpty()) {
			    		   modele.accumuler('.');
				    	   vue.textfield3.setText(modele.nombre);			    		   
			    	  }
			       }else if (event.getSource() == vue.button_push) {
			    	   if(!modele.nombre.equals("0") && !modele.nombre.isEmpty()) {
			    	    	  modele.pile.push(Double.parseDouble(vue.textfield3.getText()));
			    	    	  modele.Clear();
			    	       }
			    	       else {
			    	    	   vue.textfield3.setText("On dois mettre les chiffres mais pas 0 avant pousser sur la pile");
			    	    	   modele.Clear();
			    	       }
			    	       
			       }else if (event.getSource() == vue.button_backspace) {
			    	   if(!vue.textfield3.getText().isEmpty()) {
			    		   modele.backspace();
			    		   vue.textfield3.setText(modele.nombre);			    	       
			           }
			       }
			    }
				
				
				/* 
			     * Tous les conditions sur le textfield4   
			     */
				
				
				else if(vue.textfield4.getText().isEmpty() || modele.pile.size()<4) {
					  
			        if (event.getSource() == vue.button1) {
					          modele.accumuler('1');
					          vue.textfield4.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button2) {
			    	    modele.accumuler('2');
			    	    vue.textfield4.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button3) {
			    	    modele.accumuler('3');
			    	    vue.textfield4.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button4) {
			    	    modele.accumuler('4');
			    	    vue.textfield4.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button5) {
			    	    modele.accumuler('5');
			    	    vue.textfield4.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button6) {
			    	    modele.accumuler('6');
			    	    vue.textfield4.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button7) {
			    	    modele.accumuler('7');
			    	    vue.textfield4.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button8) {
			    	    modele.accumuler('8');
			    	    vue.textfield4.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button9) {
			    	    modele.accumuler('9');
			    	    vue.textfield4.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button0) {
			    	    modele.accumuler('0');
			    	    vue.textfield4.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button_virgule) {
			    	   if(!vue.textfield4.getText().contains(".") && !vue.textfield4.getText().isEmpty()) {
			    		   modele.accumuler('.');
				    	   vue.textfield4.setText(modele.nombre);			    		   
			    	  }
			       }else if (event.getSource() == vue.button_push) {
			    	   if(!modele.nombre.equals("0") && !modele.nombre.isEmpty()) {
			    	    	  modele.pile.push(Double.parseDouble(vue.textfield4.getText()));
			    	    	  modele.Clear();
			    	       }
			    	       else {
			    	    	   vue.textfield4.setText("On dois mettre les chiffres mais pas 0 avant pousser sur la pile");
			    	    	   modele.Clear();
			    	       }
			    	       
			       }else if (event.getSource() == vue.button_backspace) {
			    	   if(!vue.textfield4.getText().isEmpty()) {
			    		   modele.backspace();
			    		   vue.textfield4.setText(modele.nombre);			    	       
			           }
			       }
			}
				
				/* 
			     * Tous les conditions sur le textfield5   
			     */
								
				else if(vue.textfield5.getText().isEmpty() || modele.pile.size()<5) {
					  
			        if (event.getSource() == vue.button1) {
					          modele.accumuler('1');
					          vue.textfield5.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button2) {
			    	    modele.accumuler('2');
			    	    vue.textfield5.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button3) {
			    	    modele.accumuler('3');
			    	    vue.textfield5.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button4) {
			    	    modele.accumuler('4');
			    	    vue.textfield5.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button5) {
			    	    modele.accumuler('5');
			    	    vue.textfield5.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button6) {
			    	    modele.accumuler('6');
			    	    vue.textfield5.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button7) {
			    	    modele.accumuler('7');
			    	    vue.textfield5.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button8) {
			    	    modele.accumuler('8');
			    	    vue.textfield5.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button9) {
			    	    modele.accumuler('9');
			    	    vue.textfield5.setText(modele.nombre);			            
			       }else if (event.getSource() == vue.button0) {
			    	    modele.accumuler('0');
			    	    vue.textfield5.setText(modele.nombre);			           
			       }else if (event.getSource() == vue.button_virgule) {
			    	   if(!vue.textfield5.getText().contains(".") && !vue.textfield5.getText().isEmpty()) {
			    		   modele.accumuler('.');
				    	   vue.textfield5.setText(modele.nombre);			    		   
			    	  }
			       }else if (event.getSource() == vue.button_push) {
			    	   if(!modele.nombre.equals("0") && !modele.nombre.isEmpty()) {
			    	    	  modele.pile.push(Double.parseDouble(vue.textfield5.getText()));
			    	    	  modele.Clear();
			    	       }
			    	       else {
			    	    	   vue.textfield5.setText("On dois mettre les chiffres mais pas 0 avant pousser sur la pile");
			    	    	   modele.Clear();
			    	       }
			    	       
			       }else if (event.getSource() == vue.button_backspace) {
			    	   if(!vue.textfield5.getText().isEmpty()) {
			    		   modele.backspace();
			    		   vue.textfield5.setText(modele.nombre);			    	       
			           }
			       }
			}
				
				/* 
			     * Tous les conditions sur le bouton (+)   
			     */
			
			if(event.getSource()==vue.button_add) {
				 if(!modele.pile.isEmpty()) {
					  if(modele.pile.size()>=2) {			      	
			    	      modele.add();
			    	      if(modele.pile.size()==1) {
				    		  vue.textfield2.setText("");
				    		  vue.textfield1.setText(Double.toString(modele.pile.get(0)));
				    	  }
				    	  if(modele.pile.size()==2) {
				    		  vue.textfield3.setText("");
				    		  vue.textfield2.setText(Double.toString(modele.pile.get(1)));
				    	  }
				    	  if(modele.pile.size()==3) {
				    		  vue.textfield4.setText("");
				    		  vue.textfield3.setText(Double.toString(modele.pile.get(2)));
				    	  }
				    	  if(modele.pile.size()==4) {
				    		  vue.textfield5.setText("");
				    		  vue.textfield4.setText(Double.toString(modele.pile.get(3)));
				    	  }
				    	  if(modele.pile.size()==5) {
				    		  vue.textfield5.setText("");
				    		  vue.textfield4.setText(Double.toString(modele.pile.get(4)));
				    	  }		
			          }
					  else {
						  vue.textfield2.setText("On doit ajouter 2 chiffres dans la pile avant mettre l'operateur.");
						  modele.Clear();
					  }
				}
			}		   
					
			/* 
		     * Tous les conditions sur le bouton (-)   
		     */
						
			if(event.getSource()==vue.button_sub) {
				if(!modele.pile.isEmpty()) {
				   if(modele.pile.size()>=2) { 
		    	         modele.sub();
			    	  if(modele.pile.size()==1) {
			    		  vue.textfield2.setText("");
			    		  vue.textfield1.setText(Double.toString(modele.pile.get(0)));
			    	  }
			    	  if(modele.pile.size()==2) {
			    		  vue.textfield3.setText("");
			    		  vue.textfield2.setText(Double.toString(modele.pile.get(1)));
			    	  }
			    	  if(modele.pile.size()==3) {
			    		  vue.textfield4.setText("");
			    		  vue.textfield3.setText(Double.toString(modele.pile.get(2)));
			    	  }
			    	  if(modele.pile.size()==4) {
			    		  vue.textfield5.setText("");
			    		  vue.textfield4.setText(Double.toString(modele.pile.get(3)));
			    	  }
			    	  if(modele.pile.size()==5) {
			    		  vue.textfield5.setText("");
			    		  vue.textfield4.setText(Double.toString(modele.pile.get(4)));
			    	  }		
		        }
				else {
					 vue.textfield2.setText("On doit ajouter 2 chiffres dans la pile avant mettre l'operateur.");
					 modele.Clear();
				     }
			    } 
		   }
			
			/* 
		     * Tous les conditions sur le bouton (*)   
		     */
				
		   if(event.getSource()==vue.button_mult) {
				if(!modele.pile.isEmpty()) {
				   if(modele.pile.size()>=2) {
		    	         modele.mult();
			    	  if(modele.pile.size()==1) {
			    		  vue.textfield2.setText("");
			    		  vue.textfield1.setText(Double.toString(modele.pile.get(0)));
			    	  }
			    	  if(modele.pile.size()==2) {
			    		  vue.textfield3.setText("");
			    		  vue.textfield2.setText(Double.toString(modele.pile.get(1)));
			    	  }
			    	  if(modele.pile.size()==3) {
			    		  vue.textfield4.setText("");
			    		  vue.textfield3.setText(Double.toString(modele.pile.get(2)));
			    	  }
			    	  if(modele.pile.size()==4) {
			    		  vue.textfield5.setText("");
			    		  vue.textfield4.setText(Double.toString(modele.pile.get(3)));
			    	  }
			    	  if(modele.pile.size()==5) {
			    		  vue.textfield5.setText("");
			    		  vue.textfield4.setText(Double.toString(modele.pile.get(4)));
			    	  }		
		           }
				   else {
					   vue.textfield2.setText("On doit ajouter 2 chiffres dans la pile avant mettre l'operateur.");
					   modele.Clear();
				   }
				}
		   }
		   
		   /* 
		     * Tous les conditions sur le bouton (/)   
		     */
		   
		   if(event.getSource()==vue.button_div) {
				if(!modele.pile.isEmpty()) {
				   if(modele.pile.size()>=2) {
		    	         modele.div();
			    	  if(modele.pile.size()==1) {
			    		  vue.textfield2.setText("");
			    		  vue.textfield1.setText(Double.toString(modele.pile.get(0)));
			    	  }
			    	  if(modele.pile.size()==2) {
			    		  vue.textfield3.setText("");
			    		  vue.textfield2.setText(Double.toString(modele.pile.get(1)));
			    	  }
			    	  if(modele.pile.size()==3) {
			    		  vue.textfield4.setText("");
			    		  vue.textfield3.setText(Double.toString(modele.pile.get(2)));
			    	  }
			    	  if(modele.pile.size()==4) {
			    		  vue.textfield5.setText("");
			    		  vue.textfield4.setText(Double.toString(modele.pile.get(3)));
			    	  }
			    	  if(modele.pile.size()==5) {
			    		  vue.textfield5.setText("");
			    		  vue.textfield4.setText(Double.toString(modele.pile.get(4)));
			    	  }		
		           }
				   else {
					   vue.textfield2.setText("On doit ajouter 2 chiffres dans la pile avant mettre l'operateur.");
					   modele.Clear();
				   }
				}
		   }
		   
		   /* 
		     * Tous les conditions sur le bouton (+/-)   
		     */	
		   
				if(event.getSource()==vue.button_neg) {
					if(!modele.pile.isEmpty()) {
		    	      modele.neg();
			    	  if(modele.pile.size()==1) {
			    		  vue.textfield1.setText(Double.toString(modele.pile.get(0)));
			    	  }
			    	  if(modele.pile.size()==2) {
			    		  vue.textfield2.setText(Double.toString(modele.pile.get(1)));
			    	  }
			    	  if(modele.pile.size()==3) {
			    		  vue.textfield3.setText(Double.toString(modele.pile.get(2)));
			    	  }
			    	  if(modele.pile.size()==4) {
			    		  vue.textfield4.setText(Double.toString(modele.pile.get(3)));
			    	  }
			    	  if(modele.pile.size()==5) {
			    		  vue.textfield5.setText(Double.toString(modele.pile.get(4)));
			    	  }		
		           } 
				}
				
				/* 
			     * Tous les conditions sur le bouton (AC)   
			     */
				
				if(event.getSource()==vue.button_clear) {
		    	   modele.Clear();
		    	   modele.pile.clear();
		    	   vue.textfield1.setText("");
		    	   vue.textfield2.setText("");
		    	   vue.textfield3.setText("");
		    	   vue.textfield4.setText("");
		    	   vue.textfield5.setText("");
		     }	
	 }
	
}