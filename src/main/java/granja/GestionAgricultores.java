/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package granja;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author imad
 */
public class GestionAgricultores {

    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        boolean repetir = true;
        int ventana = 0;
        String[] botones = {"Calcular manualmente", "Importar fichero", "salir"};
        do {

            ventana = JOptionPane.showOptionDialog(null, "Bienvenido al sistema, ¿Qué desea realizar?", " ",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);

            switch (ventana) {

                case 0:
                    introduccionDatos();
                    break;
                case 1:
                    System.out.println("--------Calculando unidades totales recogidas desde el fichero----");
                    leerFichero();
                    break;
                case 2:
                    // al pulsar en case 2 se pone reptir en false por lo tanto sale del programa y
                    // del bucle
                    repetir = false;

                    break;

            }

        } while (repetir);

    }

    public static void introduccionDatos() {
        String melocotones = "";
        int numeroAgricultores = 0;
        double numeroLimpio = 0;
        double cantidadTotal = 0;
        ListaAgricultores lista1 = new ListaAgricultores();
        int melecotonString = 0;
        int numOrdenAgri = 1;
        do {

            try {
                melocotones = JOptionPane.showInputDialog("Introduce la cantidad de fruta que trae el agricultor "+numOrdenAgri);
                melecotonString = Integer.parseInt(melocotones);

            } catch (NumberFormatException nfe) {
                //Mensaje de error
                JOptionPane.showMessageDialog(null, "Formato incorrecto:\n"
                        + "Por favor ingrese un valor valido", "Error de formato",
                        JOptionPane.ERROR_MESSAGE);
            }

            lista1.anadirNuevoAgricultor(new Agricultores(melecotonString));
            numOrdenAgri++;

        } while (Integer.parseInt(melocotones) != 0);
        for (int i = 0; i < lista1.getAgricultores().size(); i++) {

            cantidadTotal += lista1.getAgricultores().get(i).getUnidadesRecogidas();

        }

        numeroAgricultores = lista1.getAgricultores().size();
        numeroLimpio = (cantidadTotal / numeroAgricultores);
        if ((cantidadTotal / numeroAgricultores) == Math.floor(numeroLimpio)) {

            JOptionPane.showMessageDialog(null, "Han traido una cantidad total de " + cantidadTotal + " melecotones\nTenemos " + numeroAgricultores + " agricultores\ncada agricultor le tocan: " + (cantidadTotal / lista1.getAgricultores().size()) + " melecotones");

        } else {
            JOptionPane.showMessageDialog(null, "Cantidad incorrecta:\n"
                    + "No puede hacerse la divison", "Error de reparticion",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void leerFichero() {
        int numeroAgricultores = 0;
        double numeroLimpio = 0;
        double cantidadTotal = 0;
        ListaAgricultores lista1 = new ListaAgricultores();

        lista1.setListaAgricultores(LecturaFicheroCSV.leeFichero("cantidadFruta.csv"));

        numeroAgricultores = lista1.getAgricultores().size();
        for (int i = 0; i < lista1.getAgricultores().size(); i++) {

            cantidadTotal += lista1.getAgricultores().get(i).getUnidadesRecogidas();

        }
        numeroLimpio = (cantidadTotal / numeroAgricultores);
        if ((cantidadTotal / numeroAgricultores) == Math.floor(numeroLimpio)) {
            JOptionPane.showMessageDialog(null, "Han traido una cantidad total de " + cantidadTotal + " melecotones\nTenemos " + numeroAgricultores + " agricultores\ncada agricultor le tocan: " + (cantidadTotal / lista1.getAgricultores().size()) + " melecotones");

        } else {
            JOptionPane.showMessageDialog(null, "Cantidad incorrecta:\n"
                    + "Por favor ingrese un valor valido", "Error de reparticion",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
