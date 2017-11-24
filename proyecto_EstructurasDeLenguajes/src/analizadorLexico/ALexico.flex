/*Seccion de codigo de usuario*/
package analizadorLexico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* Seccion de opciones y declaraciones de JFlex */
%%
%function nextToken
%public
%type token
%class AnalizadorLexico
%unicode

%{
	
    private int contador;
    private ArrayList<token> tokens;
    private boolean flag;

	public void exportarLog() throws IOException{
			BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter("/home/administrador/Documentos/logLexico.txt", false); //true tells to append data.
            out = new BufferedWriter(fstream);
           	for(token t: this.tokens){
				out.write("{"+t.id+", Token: "+t.token+", Tipo: "+t.tipo+", Linea: "+t.linea+", Columna: "+t.columna+ "} \n");
				out.newLine();
			}
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
        System.out.println("Los tokens reconocidos fueron exportados Correctamente");
	}
%}

%init{
    contador = 0;
	tokens = new ArrayList<token>();
	flag=true;
%init}
//Cuando se alcanza el fin del archivo de entrada
%eof{
	/*
	try{
		this.exportarLog();
	}catch(IOException ioe){
		ioe.printStackTrace();
	}
	*/
%eof}

%line
%column

//Expresiones regulares
LETRA=[A-Za-z]
DIGITO=[0-9]
ALPHANUM={LETRA}|{DIGITO}
NUMERO=({DIGITO})+
IDENTIFICADOR={LETRA}({ALPHANUM})*
TEXTO=("\"")({ALPHANUM}|{ESPACIO})*("\"")
ESPACIO=" "
SALTODELINEA="\n"
PALABRASRE= "variables" | "orden" | "estados" | "dominio" | "inicio" | "fin" | "acciones" | "all"
COMPARACION= "<=" | ">=" | "<" | ">" | "!=" | "=="
LOGICA= "&and" | "|or"
OPERACION= "+" | "-" | "*" | "/"
SEPARADOR= "(" | ")" | "[" | "]" | "{" | "}" | ","


/* Seccion de reglas lexicas */
%% 

//Palabras Reservadas
{PALABRASRE}  {
    contador++;
    token t = new token(contador,yytext(),"Palabra Reservada",yyline,yycolumn);
    tokens.add(t);
    return t;
}

//Operadores de Comparación
{COMPARACION}  {
    contador++;
    token t = new token(contador,yytext(),"Operador de Comparación",yyline,yycolumn);
    tokens.add(t);
    return t;
}

{LOGICA}  {
    contador++;
    token t = new token(contador,yytext(),"Operador Logico",yyline,yycolumn);
    tokens.add(t);
    return t;
}

//Operadores de asignación
":="  {
    contador++;
    token t = new token(contador,yytext(),"Asignación",yyline,yycolumn);
    tokens.add(t);
    return t;
}

//Operadores Aritmeticos
{OPERACION}  {
    contador++;
    token t = new token(contador,yytext(),"Operador Aritmetico",yyline,yycolumn);
    tokens.add(t);
    return t;
}

//Separadores
{SEPARADOR}  { 
    contador++;
    token t = new token(contador,yytext(),"Separador",yyline,yycolumn);
    tokens.add(t);
    return t;
}

".."  { 
    contador++;
    token t = new token(contador,yytext(),"Hasta",yyline,yycolumn);
    tokens.add(t);
    return t;
}

"_"  { 
    contador++;
    token t = new token(contador,yytext(),"Cualquiera",yyline,yycolumn);
    tokens.add(t);
    return t;
}

{NUMERO}    { 
    contador++;
    token t = new token(contador,yytext(),"Numero",yyline,yycolumn);
    tokens.add(t);
    return t;
}

{TEXTO} {
	contador++;
    token t = new token(contador,yytext(),"Texto",yyline,yycolumn);
    tokens.add(t);
    return t;
}

{IDENTIFICADOR}   { 
    contador++;
    token t = new token(contador,yytext(),"Identificador",yyline,yycolumn);
    tokens.add(t);
    return t;
}

{ESPACIO} { 
	contador++;
    token t = new token(contador,yytext(),"Espacio",yyline,yycolumn);
    tokens.add(t);
    return t;
}

{SALTODELINEA} { 
	contador++;
    token t = new token(contador, "\n","Salto de Linea",yyline,yycolumn);
    tokens.add(t);
    return t;
}

. {
	contador++;
    token t = new token(contador,yytext(),"Error",yyline,yycolumn);
    tokens.add(t);
    return t;
}
