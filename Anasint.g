class Anasint extends Parser;

options{
    buildAST=true;
    }

tokens{
    PROCEDURE;
    PERFIL;
    NOMBRE;
    PARAMETROS;
    RESULTADOS;
    VARIABLESLOCALES;
    INSTRS;
    VARS;
    EXPRS;
    }

procedure!: a:perfil b:variableslocales c:instrucciones EOF!
  {#procedure = #(#[PROCEDURE,"PROCEDURE"], #a, #b, #c);}
            ;

perfil !: a:IDENT PA b:parametros PC DEV PA c:tipos PC
        {#perfil = #(#[PERFIL,"PERFIL"], #(#[NOMBRE,"NOMBRE"], #a),
        #(#[PARAMETROS,"PARAMETROS"], #b),
        #(#[RESULTADOS,"RESULTADOS"], #c));}
        ;
parametros : (parametro COMA) => parametro COMA! parametros
            | parametro
            |
            ;

parametro : tipo IDENT^
            ;

tipos: (tipo COMA)=> tipo COMA! tipos
    | tipo
    |
    ;

variableslocales !: VARIABLES LOCALES DP a:variables
        {#variableslocales = #(#[VARIABLESLOCALES,"VARIABLESLOCALES"], #a);}
        ;

variables : decl_vars variables
            |
            ;

decl_vars !: a:tipo b:vars PyC {#decl_vars = #(#a, #b);} ;

vars: (IDENT COMA)=> IDENT COMA! vars
 | IDENT
 ;

instrucciones: INSTRUCCIONES! DP! (instruccion)* FIN!
 {#instrucciones = #(#[INSTRS,"INSTRUCCIONES"], ##);}
 ;

instruccion : asignacion
            | iteracion
            | seleccion
            | retorno
            ;

asignacion : asignacionsimple
           | asignacionmultiple
           ;
 
asignacionsimple: IDENT ASIG^ expr PyC!;

asignacionmultiple!: PA a:vars PC b:ASIG PA c:exprs PC PyC!
            {#asignacionmultiple =
            #(#b,#(#[VARS,"VARIABLES"],#a),#(#[EXPRS,"EXPRESIONES"],#c));}
            ;

iteracion: MIENTRAS^ PA! expr PC! HACER! bloque FINMIENTRAS!;

seleccion: (SI PA expr PC ENTONCES bloque SINO) => SI^ PA! expr PC! ENTONCES! bloque SINO! bloque FINSI!
          | SI^ PA! expr PC! ENTONCES! bloque FINSI!
          ;

bloque : (instruccion)*;


retorno: DEV^ PA! exprs PC! PyC!;


tipo: VECTOR^ PA! tipo PC! CA! indice RANGO! indice CC!
    | ENTERO
    | BOOLEANO
    ;


indice : NUMERO | IDENT;


exprs : (expr COMA) => expr COMA! exprs
      | expr
      |
      ;

expr: (expr2 (Y|O))=> expr2 (Y^|O^) expr
    | expr2
    ;

expr2: (termino (MAYOR|MENOR|MAYORIGUAL|MENORIGUAL|IGUAL|DISTINTO))=> termino (MAYOR^|MENOR^|MAYORIGUAL^|MENORIGUAL^|IGUAL^|DISTINTO^) termino
    | termino
    ;

termino : (termino2 (MAS|MENOS|POR|DIV))=> termino2 (MAS^|MENOS^|POR^|DIV^) termino
        | termino2
        ;

termino2: (IDENT CA) => IDENT^ CA! expr CC!
        | (IDENT PA) => IDENT^ PA! exprs PC!
        | IDENT
        | NUMERO
        | FALSO
        | CIERTO
        ;

