 package parrot.backend.analizadores;

import java_cup.runtime.*;
import static parrot.backend.analizadores.sym2.*;

%% //separador de area

/* opciones y declaraciones de jflex */

%public
%class Lexer2
%cup
%cupdebug
%line
%column

%{
    StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }


  private void error(String message) {
    System.out.println("Error en linea line "+(yyline+1)+", columna "+(yycolumn+1)+" : "+message);
  }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = [ \t\f]
Letra = [a-zA-Z]
Entero = [0-9]
Digito = [1-9][0-9]*
TextoParametro = "[" [^*] ~"]" | "[" "]"
Slash = "/"
Comillas = "\""
Space = ({WhiteSpace})*
CorcheteAbierto = "["
CorcheteCerrado = "]"
SignoMenor = "<"
SignoMayor = ">"
Usuarios = {SignoMenor} "usuarios" {SignoMayor}
UsuariosCerrado = {SignoMenor}{Slash} "usuarios" {SignoMayor}
Usuario = {SignoMenor} "usuario" {SignoMayor}
UsuarioCerrado = {SignoMenor}{Slash} "usuario" {SignoMayor}
Nombre = {SignoMenor} "nombre" {SignoMayor}
NombreCerrado = {SignoMenor}{Slash} "nombre" {SignoMayor}
Id = {SignoMenor} "id" {SignoMayor}
IdCerrado = {SignoMenor}{Slash} "id" {SignoMayor}
Pass = {SignoMenor} "pass" {SignoMayor}
PassCerrado = {SignoMenor}{Slash} "pass" {SignoMayor}
Identificador = {Letra}({Letra}|{Digito}|"-"|"_"|"$")*
IdentificadorParametro = {CorcheteAbierto}{Identificador}{CorcheteCerrado}

%% // separador de areas

/* reglas lexicas */
<YYINITIAL> {

	{Usuarios} {return symbol(USUARIOS_ABIERTO, yytext());}
	{UsuariosCerrado} {return symbol(USUARIOS_CERRADO, yytext());}
	{Usuario} {return symbol(USUARIO_ABIERTO, yytext());}
	{UsuarioCerrado} {return symbol(USUARIO_CERRADO, yytext());}
	{Nombre} {return symbol(NOMBRE_ABIERTO, yytext());}
	{NombreCerrado} {return symbol(NOMBRE_CERRADO, yytext());}
	{Id} {return symbol(ID_ABIERTO, yytext());}
	{IdCerrado} {return symbol(ID_CERRADO, yytext());}
	{Pass} {return symbol(PASS_ABIERTO, yytext());}
	{PassCerrado} {return symbol(PASS_CERRADO, yytext());}
	{IdentificadorParametro} {return symbol(IDENTIFICADOR, yytext());}
	{TextoParametro} {return symbol(TEXTO_PARAMETRO, yytext());}
	{WhiteSpace} 		{/*Nothing to do*/}
	{LineTerminator} 	{/*Nothing to do*/}

}
[^] {error("Simbolo invalido <"+ yytext()+">");}
<<EOF>>                 { return symbol(EOF); }
