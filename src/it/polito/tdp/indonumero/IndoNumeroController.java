package it.polito.tdp.indonumero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	private int NMAX = 100;
	private int TMAX = 7;
	private int segreto; //numero da indovinare
	private int tentativi; //tentativi già fatti
	
	private boolean inGame = false; // dice se c'è partita in corso

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtCurr;

    @FXML
    private TextField txtMax;

    @FXML
    private HBox boxGioco;

    @FXML
    private TextArea txtTentativo;

    @FXML
    private TextField txtLog;

    @FXML
    void handleNuova(ActionEvent event) {
    	segreto = (int) (Math.random()*NMAX)+1 ;
    	inGame = true;
    	tentativi = 0;
    	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCurr.setText(String.format("%d", tentativi));
    	txtMax.setText(String.format("%d", TMAX));
    	
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, NMAX));
    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText();
    	int num;
    	
    	if(numS.length() == 0) {
    		txtLog.appendText(String.format("Devi inserire numero\n"));
    		return;
    	}
    	try {
    		//numero effettivamente intero
    	num = Integer.parseInt(numS);
    	
    	if(num<1 || num>NMAX) {
    		txtLog.appendText("Numero fuori dall'intervallo!\n");
    		return;
    	}
    	
    	}
    	catch(NumberFormatException ex) {
    		txtLog.appendText(String.format("Il dato inserito non è un numero\n"));
    		return;
    		
    	}

    	tentativi++;
    	
    	txtCurr.setText(String.format("%d", tentativi));
    	txtMax.setText(String.format("%d", TMAX));
    	
    	if(tentativi == TMAX) {
    		//ho perso
    		txtLog.appendText(String.format("Hai perso! Il numero era %d\n", this.segreto));
    		//chiudo partita
    		boxGioco.setDisable(true);
    		btnNuova.setDisable(false);
    		this.inGame = false;
    		return;
    	}
    	if(num == segreto) {
    		//ho vinto
    		txtLog.appendText(String.format("Hai vinto\n"));
    		//chiudo partita
    		boxGioco.setDisable(true);
    		btnNuova.setDisable(false);
    		this.inGame = false;
    		return;
    	}
    	if(num<segreto) {
    		//troppo piccolo
    		txtLog.appendText(String.format("Troppo piccolo\n"));
    		return;
    	}
    	if(num>segreto) {
    		//troppo grande
    		txtLog.appendText(String.format("Troppo grande\n"));
    		return;
    	}
    		
    }

}



	

    

   

    
