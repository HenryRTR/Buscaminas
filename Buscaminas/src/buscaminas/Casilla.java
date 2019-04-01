/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author HenryR
 */
public class Casilla {
    private boolean mina;
    private boolean blanca;
    private boolean bandera;
    private boolean visible;
    private int numero;

    /**
     * Constructor Por Defecto
     */
    public Casilla() {
        this.mina = false;
        this.blanca = false;
        this.bandera = false;
        this.visible = false;
        this.numero = 0;
    } 

    /**
     * @return the mina
     */
    public boolean isMina() {
        return mina;
    }

    /**
     * @param mina the mina to set
     */
    public void setMina(boolean mina) {
        this.mina = mina;
    }

    /**
     * @return the blanca
     */
    public boolean isBlanca() {
        return blanca;
    }

    /**
     * @param blanca the blanca to set
     */
    public void setBlanca(boolean blanca) {
        this.blanca = blanca;
    }

    /**
     * @return the bandera
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }    
}
