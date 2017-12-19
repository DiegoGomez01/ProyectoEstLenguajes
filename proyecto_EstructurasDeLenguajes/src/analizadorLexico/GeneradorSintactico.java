/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author administrador
 */
public class GeneradorSintactico {
    public static void main(String[] args) {
        String path = "/home/administrador/NetBeansProjects/ProyectoEstructurasDeLenguajes/proyecto_EstructurasDeLenguajes/src/analizadorLexico/asintactico.cup";
        String[] asintactico = {"-parser", "AnalizadorSintactico", path};
        try {
                java_cup.Main.main(asintactico);
        } catch (Exception ex) {
            Logger.getLogger(GeneradorSintactico.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se moverán los archivos siguientes a la carpeta de analizadorLexico
        moverArch("AnalizadorSintactico.java");
        moverArch("sym.java");
    }
    public static void moverArch(String archNombre) {
        File arch = new File(archNombre);
        if (arch.exists()) {
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "analizadorLexico" + File.separator + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            arch.renameTo(new File(nuevoDir));
        } else {
            System.out.println("\n*** Codigo no existente ***\n");
        }
    }
}
