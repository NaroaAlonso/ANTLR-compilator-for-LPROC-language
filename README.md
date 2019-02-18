**LPROC LANGUAGE, LENGUAJE LPROC**

This language is called LPROC designed to express prodecures like the one
showed in the example.

Supongamos un lenguaje llamado LPROC diseñado para expresar procedimientos como
el mostrado en el siguiente ejemplo.


buscar(entero e, vector(entero)[1..n] v) devuelve (booleano, entero)

variables locales:
 booleano resultado;
 entero elemento, i;
 
instrucciones:
 (resultado, i)=(falso, 1);
 mientras (resultado == falso y i<=numeroElementos(v)) hacer
 elemento = v[i];
 si (elemento == e) entonces
 resultado = cierto;
 sino
 i=i+1;
 finsi
 finmientras
 devuelve (resultado,i);
fin



The profile of a procedure has a name, parameters and results.
Internally the procedure is structires with a set of local variables and
a sequence of instructions. Elemental types are "entero" (corresponding to
integer) and "booleano" (corresponding to boolean). The only non elemental type
is de "vector" type, which can include booleans or integers. The declaration
of the type vector will always include the type of its elements and the range
of its index.

El perfil de un procedimiento consta de nombre, parámetros y resultados. 
Internamente el procedimiento se estructura con un conjunto de variables 
locales y una secuencia de instrucciones. Los tipos elementales en LPROC son 
el tipo lógico y el tipo entero. El único tipo no elemental es el tipo vector 
(de elementos enteros o lógicos). La declaración de un vector siempre
incluirá el tipo de sus elementos y el rango de sus índices. 

LPROC has 5 types of instructions: simple assignations, multiple assignations,
loops, conditionals and result returns.

LPROC tiene 5 tipos de instrucciones:
asignaciones simples, asignaciones múltiples, iteraciones, condicionales y 
devolución de resultados. 




