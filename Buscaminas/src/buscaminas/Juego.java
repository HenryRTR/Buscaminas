/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Scanner;

/**
 *
 * @author Henry Richard
 */
public class Juego {
    private Tablero tablero;
    private int numMinas;
    private int numFilas;
    private int numColumnas;

    public Juego() {
    }
    
     /**
     * Pide al Usuario El Numero de Filas, Columnas y Minas
     */
    public void configurarJuego(){
        Scanner leer = new Scanner(System.in);
        
        System.out.println("Introduce el Numero de Filas");
        numFilas = leer.nextInt();
        System.out.println("");
        System.out.println("Introduce el Numero de Columnas");
        numColumnas = leer.nextInt();
        System.out.println("");
        
        int mina;
        boolean comprobar = false;
        
        do {       
            System.out.println("Introduce el Numero de Minas");
            mina = leer.nextInt();
            System.out.println("");
            if((numFilas*numColumnas) >= mina){
                numMinas = mina;
                comprobar = true;
            }else{
                System.out.println("El Numero de Minas Supera el Tablero");
            }
        } while (comprobar == false);
    }
    
    /**
     * Inicia el Tablero, Insertar Minas
     */
    public void iniciarJuego(){
        tablero = new Tablero(numFilas, numColumnas);
        tablero.inicializarTabla();
        tablero.insertarMinas(numMinas);
    }
    
    /**
     * Pide al Usuario El Numero de Fila y Columa
     * Utliza las Funciones Private
     */
    public void Jugar(){
        Scanner leer = new Scanner(System.in);
        int operacion;
        boolean partidaGanada = false;
        boolean partidaPerdida = false;
        
        do {            
            mostrarTablero();
            operacion = elegirOperacion();
            
            System.out.println("Dime la Fila");
            int fila = leer.nextInt();
            System.out.println("");
            System.out.println("Dime la Columna");
            int columna = leer.nextInt();
            System.out.println("");
            
            boolean correcto = coordenadasCorrectas(fila, columna);
            
            switch(operacion){
                case 1:            
                    if(correcto){
                        if(this.tablero.getCasilla(fila, columna).isMina()){
                            acabarJuegoMina();
                            partidaPerdida = true;
                        }else{
                            descubrirCasilla(fila, columna);
                            partidaGanada = partidaGanada();
                        }                             
                    }                        
                break;
                
                case 2:
                    if(correcto){
                        this.tablero.getCasilla(fila, columna).setBandera(true);
                    }
                break;
                
                case 3:
                    if(correcto){
                        this.tablero.getCasilla(fila, columna).setBandera(false);
                    }
                break;                
            }            
            
        }while(!partidaGanada && !partidaPerdida);
        if(partidaGanada){
            System.out.println("|-----HAS GANADO-----|");
            mostrarTablero();
        }
    }
    
    /**
     * Muestra el Tablero con los Numeros
     */
    private void mostrarTablero(){
        String fila = "  ";
        for (int i = 0; i < tablero.getNumColumnas(); i++) {
            fila += "   "+ Integer.toString(i);
        }
        
        System.out.println(fila);
        for (int j = 0; j < tablero.getNumFilas(); j++) {
            String fila1 = Integer.toString(j)+"|" + "   ";
            
            for (int x = 0; x < tablero.getNumColumnas(); x++) {
                if(tablero.getCasilla(j, x).isBandera()){
                    fila1 += "B   ";
                }else {
                    if(!tablero.getCasilla(j, x).isVisible()){
                        fila1 += ".   ";
                    }else{
                        fila1 += Integer.toString(tablero.getCasilla(j, x).getNumero())+"   ";
                    }
                }
            }
            System.out.println(fila1+"|"+Integer.toString(j));
        }
        System.out.println(fila);
    }
    
    /**
     * Menu para Elegir la Operacion a Realizar
     * @return Devuelve una Eleccion Si es Correcta
     */
    private int elegirOperacion(){
        Scanner leer = new Scanner(System.in);
        int elegir = 0;
        boolean comprobar = true;
        
        do {            
            System.out.println("");
            System.out.println("********************************");
            System.out.println("** Elije el Tipo de operación **");
            System.out.println("********************************");
            System.out.println("** 1) Descubrir               **");
            System.out.println("** 2) Poner bandera           **");
            System.out.println("** 3) Quitar bandera          **");
            System.out.println("********************************");
            try {
                elegir = leer.nextInt();
                System.out.println("");
                comprobar = true;
                if(elegir < 1 || elegir > 3){
                    System.out.println("Opcion Incorrecta");
                    comprobar = false;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Introduce Solo Numero");
            }
        } while (comprobar == false);
        return elegir;
    }   
    
    /**
     * 
     * @param fila
     * @param columna
     * @return Devuelve True si las Coordenadas Introducidas Corresponden a una Casilla Visible
     */
    private boolean coordenadasCorrectas(int fila, int columna){
        boolean comprobar = true;
        if(fila < 0 || fila > tablero.getNumFilas() || columna < 0 || columna > tablero.getNumColumnas()){
            comprobar = false;
        }else{
            if(this.tablero.getCasilla(fila, columna).isVisible()){
                comprobar = false;
            }
        }
        
        if(!comprobar){
            System.out.println("Coordenada Incorrecta");
        }
        
        return comprobar;
    }
    
    /**
     * Muestra La Solucion de Donde Estaban las Minas
     */
    private void acabarJuegoMina(){
        System.out.println("Juego Finalizado has Pisado una Mina");
        System.out.println("");
        String fila = "  ";
        for (int i = 0; i < tablero.getNumColumnas(); i++) {
            fila += "   "+Integer.toString(i);
        }
        System.out.println(fila);
        
        for (int x = 0; x < tablero.getNumFilas(); x++) {
            String fila1 = Integer.toString(x)+"|" + "   ";
            for (int j = 0; j < tablero.getNumColumnas(); j++) {
                if(tablero.getCasilla(x, j).isMina()){
                    fila1 += "M   ";
                }else {
                    if(tablero.getCasilla(x, j).isBandera()){
                        fila1 += "B   ";
                    }else{
                        fila1 += Integer.toString(this.tablero.getCasilla(x, j).getNumero()) + "   ";
                    }
                }
            }
            System.out.println(fila1 +"|"+ Integer.toString(x));
        }
        System.out.println(fila);
    }
    
    /**
     * 
     * @param fila
     * @param columna 
     * @return Devuelve un True si el Juego Prosigue tras descubrir la Casilla y la pone Visible
     */
    private boolean descubrirCasilla(int fila, int columna){
        boolean comprobar = true;
        if(tablero.getCasilla(fila, columna).isMina()){
            comprobar = false;
        }else{
            descubrirBlanco(fila, columna);
        }
        return comprobar;
    }
    
    /**
     * Hace Visible la casilla de la posicion Indicada,
     * Descubre todas las que estan a su alrededor
     * @param fila
     * @param columna 
     */
    private void descubrirBlanco(int fila, int columna){
        this.tablero.getCasilla(fila, columna).setVisible(true);
        
        boolean terminado;
        
        do{
            terminado = true;            
            for(int i = 0; i < this.tablero.getNumFilas(); i++){
                for(int j = 0; j < this.tablero.getNumColumnas(); j++){
                    if(this.tablero.getCasilla(i, j).isVisible() && this.tablero.getCasilla(i, j).getNumero() == 0){
                        for(int a = (i - 1); a <= (i + 1); a++){
                            if(a >= 0 && a < this.tablero.getNumFilas()){
                                for(int b = (j - 1); b <= (j + 1); b++){ 
                                    if(b >= 0 && b < this.tablero.getNumColumnas()){
                                        if(!this.tablero.getCasilla(a, b).isVisible()){
                                            this.tablero.getCasilla(a, b).setVisible(true);
                                            terminado = false;
                                        }  
                                    }                                                              
                                }
                            }                            
                        }
                    }
                }
            }            
        }while(!terminado);        
    }
    
    /**
     * Se Comprueba si Ha Ganado la Partida
     * @return 
     */
    private boolean partidaGanada(){
        boolean comprobar = false;
        
        int numCasillas = this.tablero.getNumFilas() * this.tablero.getNumColumnas();
        
        int contador = 0;
        
        for(int i = 0; i < this.tablero.getNumFilas(); i++){            
            for(int j = 0; j < this.tablero.getNumColumnas(); j++){
                if(this.tablero.getCasilla(i, j).isVisible() || this.tablero.getCasilla(i, j).isMina()){
                    contador++;
                }
            }            
        }
        
        if(numCasillas == contador){
            comprobar = true;
        }
        
        return comprobar; 
    }
    
    
}
