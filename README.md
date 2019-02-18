Supongamos un lenguaje llamado LPROC diseñado para expresar procedimientos 
como el mostrado en el siguiente ejemplo.


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

    
    El perfil de un procedimiento consta de nombre, parámetros y resultados. 
Internamente el procedimiento se estructura con un conjunto de variables 
locales y una secuencia de instrucciones. Los tipos elementales en LPROC son el 
tipo lógico y el tipo entero. El único tipo no elemental es el tipo vector 
(de elementos enteros o lógicos). La declaración de un vector siempre
incluirá el tipo de sus elementos y el rango de sus índices. LPROC tiene 5 
tipos de instrucciones: asignaciones simples, asignaciones múltiples, 
iteraciones, condicionales y devolución de resultados. 