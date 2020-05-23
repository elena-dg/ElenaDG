/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ies;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class BuscaminasApp {
      public static void main(String arg[]){
        Scanner scan=new Scanner(System.in);
        try{
        int filas,columnas,numMinas,contador;
        int c=0;
        Buscaminas.espacio[][] Buscaminas;
        System.out.println("***** Buscaminas *****");
        System.out.print("Ingrese el numero de filas(5-50): ");
        filas=scan.nextInt();
        //rango del tamaño del tablero de juego
        if(filas<5||filas>50){
            filas=10;
            c=1;
        }
        System.out.print("Ingrese el numero de columnas(5-50): ");
        columnas=scan.nextInt();
        if(columnas<5||columnas>50){
            columnas=10;
            c++;
            if (c==2){
                System.out.println("Coordenadas fuera de rango tamaño predeterminado 10X10");
            }
        }
        numMinas=filas+columnas;
        contador=(filas*columnas)-(numMinas);
        System.out.println("El numero de minas es: "+numMinas);
        System.out.println("***** A jugar!!! *****");
        Buscaminas=new Buscaminas.espacio[columnas][filas];
        Buscaminas=new Buscaminas().llenarTablero(Buscaminas,0,0,columnas,filas);
        Buscaminas=new Buscaminas().colocarMinas(Buscaminas,numMinas,columnas,filas);
        Buscaminas=new Buscaminas().minasAlrededor(Buscaminas,0,0,columnas,filas);
        Buscaminas=new Buscaminas().juego(Buscaminas,columnas,filas,contador);
    } catch(Exception e){
    System.out.print("EXCEPCIÓN" + e.getMessage());
    } 
    } 
}
