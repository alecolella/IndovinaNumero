package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Model {

	private int NMAX = 100;
	private int TMAX = 7;
	private int segreto; //numero da indovinare
	//private int tentativi; //tentativi già fatti
	private IntegerProperty tentativi = new SimpleIntegerProperty();
	
	private boolean inGame = false; // dice se c'è partita in corso
	
	public Model () {
		this.inGame = false;
		
	}
	
	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto
	 */
	public void newGame() {
		segreto = (int) (Math.random()*NMAX)+1 ;
    	inGame = true;
    	tentativi.set(0);
	}
	
	
	
	public boolean isInGame() {
		return inGame;
	}
	
	

	/** 
	 * Fai un tentativo per indovinare il numero segreto
	 * @param t
	 * @return 0 se indovinato, 1 se troppo grande, -1 se troppo piccolo
	 */

	public int tentativo(int t) {
		
		if(!inGame) {
			throw new IllegalStateException("Partita non attiva");
		}
		
		if(t<1 || t>this.NMAX) {
			throw new InvalidParameterException("Tentativo fuori range");
		}
		
		this.tentativi.set(this.tentativi.get()+1);;
		if(tentativi.get()==TMAX) {
			this.inGame = false;
		}
		
		if(t== this.segreto) {
			this.inGame = false;
			return 0;
		}
		if(t < this.segreto)
			return -1;
		return 1;
	}
	
	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	/**
	 * Controlla se il tentativo fornito rispetta le regole formali del gioco, cioè
	 * è nel range[1,NMAX]
	 * @param tentativo
	 * @return {@code true} se il tentativo è valido
	 */
	public boolean valoreValido(int tentativo) {
		return tentativo >= 1 && tentativo <= this.NMAX;
	}
	
	public int getSegreto() {
		return this.segreto;
	}

	
	//restuisce riferimento alla property
	public final IntegerProperty tentativiProperty() {
		return this.tentativi;
	}
	
	//get sulla property

	public final int getTentativi() {
		return this.tentativiProperty().get();
	}
	

	
	
	
	



}
