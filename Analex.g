class Analex extends Lexer;

options{
    importVocab=Anasint;
    k=2;
}

tokens{
    VARIABLES = "variables";
    LOCALES = "locales";
    INSTRUCCIONES = "instrucciones";
    DEV = "devuelve";
    BOOLEANO = "booleano";
    ENTERO = "entero";
    VECTOR = "vector";
    FIN = "fin";
    MIENTRAS = "mientras";
    SI = "si";
    FINMIENTRAS = "finmientras";
    FINSI = "finsi";
    SINO = "sino";
    ENTONCES = "entonces";
    HACER = "hacer";
    Y = "y";
    O = "o";
    NO = "no";
    CIERTO = "cierto";
    FALSO = "falso";
}


protected NL: "\n" {newline();};


protected DIGITO : '0'..'9';


protected LETRA : 'a'..'z'|'A'..'Z';


BTN : (' '|'\t'|NL) {$setType(Token.SKIP);};


NUMERO : (DIGITO)+;
IDENT : (LETRA)(LETRA|DIGITO)*;
OP : ("==")=>"==" {$setType(IGUAL);} | "=" {$setType(ASIG);};

MAYOR:'>' ;
MENOR:'<' ;
MAYORIGUAL:">=" ;
MENORIGUAL:"<=" ;
DP:':' ;
RANGO: ".." ;
DISTINTO:"!=" ;
COMA: ',' ;
PA : '(' ;
PC : ')' ;
CA : '[' ;
CC : ']' ;
PyC : ';' ;
MAS : '+' ;
MENOS : '-' ;
POR : '*' ;
DIV : '/' ; 



