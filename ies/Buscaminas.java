/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ies;

import java.util.Scanner;

/**
 *
 * @author Elena
 */
public class Buscaminas {

    /**
     * @param args the command line arguments
     */

    Scanner scan=new Scanner(System.in);
    
    /**
     * Metodo para rellenar el tablero
     * @param buscaminas el tamaño del array bidimensional 
     * @param i
     * @param j
     * @param c valor de la columna
     * @param f valor de la fila
     * @return 
     */
    public espacio[][] llenarTablero(espacio[][] buscaminas,int i,int j,int c,int f){
        if(j<f){
            if(i<c){
                buscaminas[i][j]=new espacio();
                buscaminas=llenarTablero(buscaminas,++i,j,c,f);
            }
            else{
                i=0;
                buscaminas=llenarTablero(buscaminas,i,++j,c,f);
            }
        }
        return buscaminas;
    }
    /**
     * Metodo para coloczar las minas
     * Se colocan de manera aleatoria
     * @param buscaminas
     * @param n
     * @param c valor de la columna
     * @param f valor de la fila
     * @return devuelve la posicion de las minas
     */
    public espacio[][] colocarMinas(espacio[][] buscaminas,int n,int c,int f){
        int azar1=(int)(Math.random()*(c-1));
        int azar2=(int)(Math.random()*(f-1));
        if(n>0){
            if(buscaminas[azar1][azar2].verMina()==false){
                buscaminas[azar1][azar2].colocarMina();
                n--;
            }
            buscaminas=colocarMinas(buscaminas,n,c,f);
        }
        return buscaminas;
    }
    
    /**
     * Metodo para saber las minas que hay alrededor
     * @param buscaminas
     * @param i
     * @param j
     * @param c valor de la columna
     * @param f valor de la fila
     * @return devuelve la posicion de las minas
     */
    public espacio[][] minasAlrededor(espacio[][] buscaminas,int i,int j,int c,int f){
        if(j<f){
            if(i<c){
                if(buscaminas[i][j].verMina()==true){
                    if(i>0){
                        buscaminas[i-1][j].aumentarMinas();
                        if(j>0){
                            buscaminas[i-1][j-1].aumentarMinas();
                        }
                        if(j<f-1){
                            buscaminas[i-1][j+1].aumentarMinas();
                        }
                    }
                    if(i<c-1){
                        buscaminas[i+1][j].aumentarMinas();
                        if(j>0){
                            buscaminas[i+1][j-1].aumentarMinas();
                        }
                        if(j<f-1){
                            buscaminas[i+1][j+1].aumentarMinas();
                        }
                    }
                    if(j>0){
                        buscaminas[i][j-1].aumentarMinas();
                    }
                    if(j<f-1){
                        buscaminas[i][j+1].aumentarMinas();
                    }
                }
                buscaminas=minasAlrededor(buscaminas,++i,j,c,f);
            }
            else{
                i=0;
                buscaminas=minasAlrededor(buscaminas,i,++j,c,f);
            }
        }
        return buscaminas;
    }
    /**
     * metodo para imprimir el tablero y las instrucciones
     * @param buscaminas
     * @param i
     * @param j
     * @param c valor de la columna
     * @param f valor de la fila
     */
    public void imprimir(espacio[][] buscaminas,int i,int j,int c,int f){
        if(j<f){
            if(i<c){
                System.out.print(buscaminas[i][j]+" ");
                imprimir(buscaminas,++i,j,c,f);
            }
            else{
                i=0;
                System.out.println("");
                imprimir(buscaminas,i,++j,c,f);
            }
        }
    }
    /**
     * este metodo sirve para saber el tamaño del tablero y si se gana o se pierde el juego
     * @param buscaminas
     * @param columnas
     * @param filas
     * @param contador
     * @return 
     */
    public espacio[][] juego(espacio[][] buscaminas,int columnas,int filas,int contador){

        imprimir(buscaminas,0,0,columnas,filas);
        System.out.println("Ingrese el numero de fila y columna que desea explorar");
        System.out.print("Ingrese el numero de la fila: ");
        int f=scan.nextInt();
        System.out.print("Ingrese el numero de la columna: ");
        int c=scan.nextInt();
       
        if(f<=filas&&c<=columnas){
            if(buscaminas[c-1][f-1].verRevelado()==false){
                buscaminas[c-1][f-1].cambiarEstado();
                contador--;
            }
            if(contador==0){
                System.out.println("***** GANASTE!!! *****");
                System.out.println("***** WINNER *****");
            }
            else{
                if(buscaminas[c-1][f-1].verMina()==true){
                    imprimir(buscaminas,0,0,columnas,filas);
                    System.out.println("***** BOOOOOOOOM!!! *****");
                    System.out.println("***** GAME OVER *****");
                }
                else{
                    juego(buscaminas,columnas,filas,contador);
                }
            }
        }
        else{
            juego(buscaminas,columnas,filas,contador);
        }
        return buscaminas;
    }

    public class espacio{
        private int minasAlrededor;
        private boolean esMina;
        private boolean revelado;
        
        /**
         * Constructor
         */
        public espacio(){
            minasAlrededor=0;
            esMina=false;
        }
        
        /**
         * Este metodo se encarga de colocar las minas y de saber si hay una mina o no
         */
        public void colocarMina(){
            esMina=true;
        }
        
        /**
         * este metodo se encarga de aumenta el numero alrededor de la mina
         */
        public void aumentarMinas(){
            if(esMina==false){
                minasAlrededor++;
            }
        }
        /**
         * metodo que dice si hay mina o no en la casilla introducida por teclado
         * @return 
         */
        public boolean verMina(){
            return esMina;
        }
        /**
         * este metodo revela lo que hay en la casilla seleccionada 
         * @return un valor boolean 
         */
        public boolean verRevelado(){
            return revelado;
        }
        
        /**
         * metodo que cambia de estado la casilla, pasa de un asterisco a un numero o una mina
         */
        public void cambiarEstado(){
            revelado=true;
        }
        
        @Override
        public String toString(){
            //antes de revelarse 
            String res="*";
            if(revelado==true){
                res=""+minasAlrededor;
            }
            // si es una mina se muestra una x
            if(esMina==true&&revelado==true)
                res="X";
                return res;
        }
    }
}

