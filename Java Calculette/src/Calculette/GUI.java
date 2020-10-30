package Calculette;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/** 
* 
* @author ING_TAO
*/


public class GUI extends Application {

	 TextField textfield1, textfield2, textfield3, textfield4, textfield5;
	 
	 Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
	 
	 Button button_add,button_sub,button_div,button_mult,button_virgule,button_neg,button_push,button_clear,button_backspace;
	
	 /*
      * On a crée l'objet(controleur) pour gérer tous les boutons de la calculatrice.   
      */	 
	 	
     Controleur controleur=new Controleur(this);


			@Override
			public void start(Stage primaryStage) throws Exception {
				// TODO Auto-generated method stub				
	
				/* 
			      * On a choisi AnchorPane comme fenêtre  
			      * pour construire la calculatrice    
			      */		
				
				
			AnchorPane anchorPane=new AnchorPane();		

			
			/* 
		      * Tous les composants dans AnchorPane 
		      * Deux types de composants:
		      * -TextField textfield1, textfield2, textfield3, textfield4, et textfield5.  
		      * -Button button1,button2,..,button_add,button_sub,...  
		      */
          								
			textfield1= new TextField();
	        textfield1.setPrefHeight(27.0);
			textfield1.setPrefWidth(400.0);
			textfield1.setLayoutY(109.0);
			textfield1.setEditable(false);
			textfield1.setAlignment(Pos.CENTER_RIGHT);
			anchorPane.getChildren().add(textfield1);
			
			textfield2= new TextField();
			textfield2.setPrefHeight(27.0);
			textfield2.setPrefWidth(400.0);
			textfield2.setLayoutY(82.0);
			textfield2.setEditable(false);
			textfield2.setAlignment(Pos.CENTER_RIGHT);
			anchorPane.getChildren().add(textfield2);
					
			textfield3= new TextField();
			textfield3.setPrefHeight(27.0);
			textfield3.setPrefWidth(400.0);
			textfield3.setLayoutY(55.0);
			textfield3.setEditable(false);
			textfield3.setAlignment(Pos.CENTER_RIGHT);
			anchorPane.getChildren().add(textfield3);
			
			textfield4= new TextField();
			textfield4.setPrefHeight(27.0);
			textfield4.setPrefWidth(400.0);
			textfield4.setLayoutY(28.0);
			textfield4.setEditable(false);
			textfield4.setAlignment(Pos.CENTER_RIGHT);
			anchorPane.getChildren().add(textfield4);
				
			textfield5= new TextField();
			textfield5.setPrefHeight(20.0);
			textfield5.setPrefWidth(400.0);
			textfield5.setLayoutY(1.0);
			textfield5.setEditable(false);
			textfield5.setAlignment(Pos.CENTER_RIGHT);
			anchorPane.getChildren().add(textfield5);
			
			button7 = new Button("7");
			button7.setPrefHeight(34.0);
			button7.setPrefWidth(82.0);
			button7.setLayoutY(207.0);
			button7.setLayoutX(21.0);
			button7.setMnemonicParsing(false);
			button7.setOnAction(controleur);
			anchorPane.getChildren().add(button7);
			
			button4 = new Button("4");
			button4.setPrefHeight(34.0);
			button4.setPrefWidth(82.0);
			button4.setLayoutY(241.0);
			button4.setLayoutX(21.0);
			button4.setMnemonicParsing(false);
			button4.setOnAction(controleur);	
			anchorPane.getChildren().add(button4);
			
			button1 = new Button("1");
			button1.setPrefHeight(34.0);
			button1.setPrefWidth(82.0);
			button1.setLayoutY(275.0);
			button1.setLayoutX(21.0);
			button1.setMnemonicParsing(false);
			button1.setOnAction(controleur);		
			anchorPane.getChildren().add(button1);
			
			button0 = new Button("0");
			button0.setPrefHeight(34.0);
			button0.setPrefWidth(82.0);
			button0.setLayoutY(309.0);
			button0.setLayoutX(21.0);
			button0.setMnemonicParsing(false);
			button0.setOnAction(controleur);		
			anchorPane.getChildren().add(button0);
			
			button8 = new Button("8");
			button8.setPrefHeight(34.0);
			button8.setPrefWidth(82.0);
			button8.setLayoutY(207.0);
			button8.setLayoutX(103.0);
			button8.setMnemonicParsing(false);
			button8.setOnAction(controleur);
			anchorPane.getChildren().add(button8);
			
			button9 = new Button("9");
			button9.setPrefHeight(34.0);
			button9.setPrefWidth(82.0);
			button9.setLayoutY(207.0);
			button9.setLayoutX(185.0);
			button9.setMnemonicParsing(false);
			button9.setOnAction(controleur);		
			anchorPane.getChildren().add(button9);
			
			button5 = new Button("5");
			button5.setPrefHeight(34.0);
			button5.setPrefWidth(82.0);
			button5.setLayoutY(241.0);
			button5.setLayoutX(103.0);
			button5.setMnemonicParsing(false);
			button5.setOnAction(controleur);	
			anchorPane.getChildren().add(button5);
			
			button2 = new Button("2");
			button2.setPrefHeight(34.0);
			button2.setPrefWidth(82.0);
			button2.setLayoutY(275.0);
			button2.setLayoutX(103.0);
			button2.setMnemonicParsing(false);
			button2.setOnAction(controleur);		
			anchorPane.getChildren().add(button2);
			
			button_virgule = new Button(".");
			button_virgule.setPrefHeight(34.0);
			button_virgule.setPrefWidth(82.0);
			button_virgule.setLayoutY(309.0);
			button_virgule.setLayoutX(103.0);
			button_virgule.setMnemonicParsing(false);
			button_virgule.setOnAction(controleur);			
			anchorPane.getChildren().add(button_virgule);
			
			button6 = new Button("6");
			button6.setPrefHeight(34.0);
			button6.setPrefWidth(82.0);
			button6.setLayoutY(241.0);
			button6.setLayoutX(185.0);
			button6.setMnemonicParsing(false);
			button6.setOnAction(controleur);		
			anchorPane.getChildren().add(button6);
			
			button3 = new Button("3");
			button3.setPrefHeight(34.0);
			button3.setPrefWidth(82.0);
			button3.setLayoutY(275.0);
			button3.setLayoutX(185.0);
			button3.setMnemonicParsing(false);
			button3.setOnAction(controleur);
			anchorPane.getChildren().add(button3);
			
			button_neg = new Button("+/-");
			button_neg.setPrefHeight(34.0);
			button_neg.setPrefWidth(82.0);
			button_neg.setLayoutY(309.0);
			button_neg.setLayoutX(185.0);
			button_neg.setMnemonicParsing(false);
			button_neg.setOnAction(controleur);
			anchorPane.getChildren().add(button_neg);
			
			button_add = new Button("+");
			button_add.setPrefHeight(34.0);
			button_add.setPrefWidth(82.0);
			button_add.setLayoutY(183.0);
			button_add.setLayoutX(292.0);
			button_add.setMnemonicParsing(false);
			button_add.setOnAction(controleur);
			anchorPane.getChildren().add(button_add);
		    
			button_sub = new Button("-");
			button_sub.setPrefHeight(34.0);
			button_sub.setPrefWidth(82.0);
			button_sub.setLayoutY(217.0);
			button_sub.setLayoutX(292.0);
			button_sub.setMnemonicParsing(false);
			button_sub.setOnAction(controleur);
			anchorPane.getChildren().add(button_sub);
            
			button_mult = new Button("*");
			button_mult.setPrefHeight(34.0);
			button_mult.setPrefWidth(82.0);
			button_mult.setLayoutY(251.0);
			button_mult.setLayoutX(292.0);
			button_mult.setMnemonicParsing(false);
			button_mult.setOnAction(controleur);
			anchorPane.getChildren().add(button_mult);
			
			button_div = new Button("/");
			button_div.setPrefHeight(34.0);
			button_div.setPrefWidth(82.0);
			button_div.setLayoutY(285.0);
			button_div.setLayoutX(292.0);
			button_div.setMnemonicParsing(false);
			button_div.setOnAction(controleur);
			anchorPane.getChildren().add(button_div);
			
			button_push = new Button("< >");
			button_push.setPrefHeight(34.0);
			button_push.setPrefWidth(82.0);
			button_push.setLayoutY(319.0);
			button_push.setLayoutX(292.0);
			button_push.setMnemonicParsing(false);
			button_push.setOnAction(controleur);
			anchorPane.getChildren().add(button_push);
			
			button_clear = new Button("AC");
			button_clear.setPrefHeight(34.0);
			button_clear.setPrefWidth(82.0);
			button_clear.setLayoutY(149.0);
			button_clear.setLayoutX(292.0);
			button_clear.setMnemonicParsing(false);
			button_clear.setOnAction(controleur);
			anchorPane.getChildren().add(button_clear);
			
			button_backspace = new Button("<---");
			button_backspace.setPrefHeight(34.0);
			button_backspace.setPrefWidth(82.0);
			button_backspace.setLayoutY(149.0);
			button_backspace.setLayoutX(21.0);
			button_backspace.setMnemonicParsing(false);
			button_backspace.setOnAction(controleur);
			anchorPane.getChildren().add(button_backspace);	
			
			Scene sc = new Scene(anchorPane, 400.0, 375.0);
			primaryStage.setTitle("Ma calculette");
			primaryStage.setScene(sc);
			primaryStage.show(); 
			
	  }	
}
