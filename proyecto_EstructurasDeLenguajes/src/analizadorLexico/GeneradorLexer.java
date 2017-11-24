/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico;

import java.io.File;

/**
 *
 * @author Mario A. Yusti
 */
public class GeneradorLexer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "/home/administrador/NetBeansProjects/ProyectoEstructurasDeLenguajes/proyecto_EstructurasDeLenguajes/src/analizadorLexico/ALexico.flex";
        generarLexer(path);
    }

    public static void generarLexer(String path) {
        File file = new File(path);
        jflex.Main.generate(file);
    }
}
