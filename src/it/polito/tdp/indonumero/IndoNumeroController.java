package it.polito.tdp.indonumero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

public class IndoNumeroController {

	private Model model;

    public void setModel(Model model) {
		this.model = model;
		
		txtCurr.textProperty().bindBidirectional(model.tentativiProperty(), new NumberStringConverter());
		
	}

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
    	
    	model.newGame();
    	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	//txtCurr.setText(String.format("%d", model.getTentativi()));
    	txtMax.setText(String.format("%d", model.getTMAX()));
    	
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, model.getNMAX()));
    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText();
    	
    	
    	if(numS.length() == 0) {
    		txtLog.appendText(String.format("Devi inserire numero\n"));
    		return;
    	}
    	try {
    		//numero effettivamente intero
    	int num = Integer.parseInt(numS);
    	
    	if(!model.valoreValido(num)) {
    		txtLog.appendText("Numero fuori dall'intervallo!\n");
    		return;
    	}
    	
    	int risultato = model.tentativo(num);
    	
    	if(risultato == 0 ){
    		//ho vinto
    		txtLog.appendText(String.format("Hai vinto\n"));
    		
    	}
    	else if(risultato<0){
    		//troppo piccolo
    		txtLog.appendText(String.format("Troppo piccolo\n"));
    		
    	}
    	else{
    		//troppo grande
    		txtLog.appendText(String.format("Troppo grande\n"));
    		
    	}
    	
    	if(!model.isInGame()) {
    		//La partita è finita (vittoria o sconfitta)
    		if(risultato!=0) {
    			txtLog.appendText(String.format("Hai perso\n"));
    		txtLog.appendText(String.format("Hai perso! Il numero era %d\n", model.getSegreto()));
    	}


    	btnNuova.setDisable(false);

    	boxGioco.setDisable(true);
    	}
    	}
    
    	catch(NumberFormatException ex) {
    		txtLog.appendText(String.format("Il dato inserito non è un numero\n"));
    		return;
    		
    	}
	
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() { //viene chiamato quando model non c'è ancora,main non ha fatto setModel
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";
    }

}



	

    

   

    
