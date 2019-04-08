/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author Henry Richard
 */
public class Tablero {
    private int numFilas;
    private int numColumnas;
    private int numMinas;
    private Casilla[][] tabla;

    /**
     * Inicializamos la Matriz y las Casillas
     * @param numFilas Numero de Filas que tendra el Tablero
     * @param numColumnas Numero de Columnas que tendra el Tablero 
     */
    public Tablero(int numFilas, int numColumnas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.numMinas = 0;
        this.tabla = new Casilla[numFilas][numColumnas];
    }

    /**
     * Inicializamos la Tabla
     */
    public void inicializarTabla(){
        for(int i = 0; i < this.tabla.length; i++){
            for(int j = 0; j < this.tabla[0].length; j++){
                tabla[i][j] = new Casilla();
            }
        }
    }
    
    /**
     * 
     * @param numMinas que Tendra el Tablero
     */
    public void insertarMinas(int numMinas){
        //CREAMOS UNA MATRIZ EN LA QUE GUARDAMOS LA POSICÓN DE LAS MINAS
        int posicionMinas[][] = new int[numMinas][2];
        int contadorMinas = 0;
        
        for (int i = 0; i < numMinas; i++) {
            //GENERAMOS LA POSICIÓN ALEATORIA DE LA MINA
            int fila = (int) (Math.random() * getNumFilas());
            int columna = (int) (Math.random() * getNumColumnas());
            
            if(!tabla[fila][columna].isMina()){
                tabla[fila][columna].setMina(true);
                //GUARDAMOS LA POSICIÓN DE LA BOMBA
                posicionMinas[contadorMinas][0] = fila;
                posicionMinas[contadorMinas][1] = columna;
                contadorMinas++;
            }else{
                i--;
            }
        }
        calcularTablero(posicionMinas);   
    }
    
    public void imprimirPrueba(){
        for(int i = 0; i < this.tabla.length; i++){
            String fila = "";
            for(int j = 0; j < this.tabla[0].length; j++){
                if(this.tabla[i][j].isMina()){
                    fila += "M  ";
                }else{
                    fila += Integer.toString(this.tabla[i][j].getNumero()) + "  ";
                }                
            }
            System.out.println(fila);
        }
    }
    
    /**
     * 
     * @param filas de la matriz
     * @param columnas de la matriz
     * @return Casilla de la Tabla
     */
    public Casilla getCasilla(int filas, int columnas){
        return this.tabla[filas][columnas];
    }
    
    /**
     * 
     * Genera el Tablero
     * @param posicicionMinas 
     */
    private void calcularTablero(int posicicionMinas[][]){
        for(int i = 0; i < posicicionMinas.length; i++){            
            int filas = posicicionMinas[i][0];
            int columnas = posicicionMinas[i][1];
            
            for(int a = (filas - 1); a <= (filas + 1); a++){
                for(int b = (columnas - 1); b <= (columnas + 1); b++){
                    if((a >= 0) && (a < this.tabla.length) && (b >= 0) && (b < this.tabla[0].length)){
                        if(!this.tabla[a][b].isMina()){
                            int num = this.tabla[a][b].getNumero();
                            num++;
                            this.tabla[a][b].setNumero(num);
                        }                        
                    }
                }
            }            
        }
    }
    
    /**
     * @return the numFilas
     */
    public int getNumFilas() {
        return numFilas;
    }

    /**
     * @param numFilas the numFilas to set
     */
    public void setNumFilas(int numFilas) {
        this.numFilas = numFilas;
    }

    /**
     * @return the numColumnas
     */
    public int getNumColumnas() {
        return numColumnas;
    }

    /**
     * @param numColumnas the numColumnas to set
     */
    public void setNumColumnas(int numColumnas) {
        this.numColumnas = numColumnas;
    }

    /**
     * @return the numMinas
     */
    public int getNumMinas() {
        return numMinas;
    }

    /**
     * @param numMinas the numMinas to set
     */
    public void setNumMinas(int numMinas) {
        this.numMinas = numMinas;
    }

    /**
     * @return the tabla
     */
    public Casilla[][] getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(Casilla[][] tabla) {
        this.tabla = tabla;
    }    
}
