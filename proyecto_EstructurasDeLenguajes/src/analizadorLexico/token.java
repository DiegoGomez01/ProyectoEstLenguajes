/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico;

/**
 *
 * @author Mario A. Yusti
 */
public class token {

    public int id;
    public String token;
    public String tipo;
    public int linea;
    public int columna;

    token(int id, String token, String tipo, int linea, int columna) {
        this.id = id;
        this.token = token;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    public String getToken() {
        return "Token #" + id + ": " + token + " C.Lexico: " + tipo + " (" + linea + "," + columna + ")";
    }

}
