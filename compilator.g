header{
    import java.util.*;
    import antlr.*;
    import java.io.*;
}

class Compilador extends TreeParser; //Compilador
    options{
        importVocab = Anasint;
    }

{




    ///////////////////////////////////////////////////////////////////
    //                     FILE WRITING METHODS                      //
    ///////////////////////////////////////////////////////////////////


//Opening and closing files

    FileWriter fichero;

    private void open_file(){
    try{
        fichero = new FileWriter("src/Programa.java");

    }catch(IOException e){
        System.out.println("open_file (exception): "+e.toString());}
    }


    private void close_file(){
    try{
          fichero.close();
    }catch(IOException e){
        System.out.println("close_file (exception): "+e.toString());}
    }


//------------------------------------------------------------------------------------


//Generating spaces

    int espacios = 0;

    private void gencode_espacios(){
    try{
        for (int i = 1; i<=espacios;i++)
           fichero.write("   ");
    }catch(IOException e)
       {System.out.println("gencode_espacios (exception): "+e.toString());}
    }

//------------------------------------------------------------------------------------









    ///////////////////////////////////////////////////////////////////
    //                       AUXILIAR METHODS                        //
    ///////////////////////////////////////////////////////////////////




//----------------------------------------------METHOD reorder_expression:

//Checks if dependencies exist and reorders variables for a right compilation in Java

private static List<List<String>> reordena_expresion(List<String> idents,
List<String> expr){

    int i,j;

    List<String> asignadas = new ArrayList<String>();
    asignadas.add(idents.get(0));

    List<String> exprAsignadas = new ArrayList<String>();
    exprAsignadas.add(expr.get(0));



    for(i=1;i<expr.size();i++){

        for(j=0;j<asignadas.size();j++){

            if (!expr.get(i).contains(asignadas.get(j))){

                if(!asignadas.contains(idents.get(i))){

                    asignadas.add(idents.get(i));
                    exprAsignadas.add(expr.get(i));
                    break;

                 }

            }else{

                if(!asignadas.contains(idents.get(i))){

                    List<String> auxIdents = new ArrayList<String>();
                    List<String> auxExpr = new ArrayList<String>();

                    auxIdents.add(idents.get(i));
                    auxExpr.add(expr.get(i));
                    auxIdents.addAll(asignadas);
                    auxExpr.addAll(exprAsignadas);

                    asignadas = auxIdents;
                    exprAsignadas = auxExpr;

               }
           }
        }
     }

     List<List<String>> res = new ArrayList<List<String>>();
     res.add(asignadas);
     res.add(exprAsignadas);
     return res;

     }
     
     
     
     
//Checks if a expression is just an identificator or another expression


public static String saca_string(AST a){
    if (a.getFirstChild() != null){
        if(a.getText().equals("numeroElementos"))
            return a.getFirstChild().getText()+".length";
        else
            return a.getText()+"["+a.getFirstChild().getText()+"]";
    }else{
        return a.getText();
    }   
}





    ///////////////////////////////////////////////////////////////////
    //                      COMPILATOR METHODS                       //
    ///////////////////////////////////////////////////////////////////





//=================================================== HEADER GENERATING METHODS


//------------------------------------------------------INITIALIZES PROGRAM
//Prints the imports and initializes class

public void inicia_programa(){
  try{
      gencode_espacios();
      fichero.write("import java.io.*;\n");

      gencode_espacios();
      fichero.write("import java.util.*;\n");

      gencode_espacios();
      fichero.write("public class Programa{\n\n");

      espacios++;

  }catch(IOException e){}
}




//----------------------------------------------------------- METHOD HEADER

//Prints the header of the method we receive from LPROC:
//public static List<Object> methodName(tipo ident, tipo ident){

public void abre_cabecera(String nombrePrograma){
    try{
        fichero.write("\n");
        gencode_espacios();
        fichero.write("public static List<Object> "+nombrePrograma+"(");
        espacios++;

    }catch(IOException e){}
    }


//---------------------------------------------------- METHOD PARAMETERS

//Receives a list of types and identificators and writes them in the proper
//Java way:
//type1 ident1, ident2; type2 ident2;...; typeN identN;

public void parametros_cabecera(List<String> tipos, List<String> idents){
    try{
      int i;
      for(i=0;i<tipos.size()-1;i++){
        fichero.write(tipos.get(i)+" "+idents.get(i)+", ");
      }
      String s = tipos.get(tipos.size()-1);
      fichero.write(s+" "+idents.get(tipos.size()-1));
    }catch(IOException e){}
  }

  public void cierra_cabecera(){
    try{
      fichero.write("){\n\n");
      espacios++;
      gencode_espacios();
      fichero.write("List<Object> res = new ArrayList<>();\n\n");
    }catch(IOException e){}
  }



//===========================================================LOCAL VARIABLES

//Receives a type and a list of idents and initializes them:
//tipo ident1,ident2,ident3,...,identN;

  public void inicializa_localvars(String tipo, List<String> idents){
    try{
    int i;
    gencode_espacios();
    fichero.write(tipo+" ");
    
    for(i=0;i<idents.size()-1;i++){
      fichero.write(idents.get(i)+", ");
    }
    fichero.write(idents.get(idents.size()-1)+";\n");



    }catch(IOException e){}

  }



//=============================================================== INSTRUCTIONS 

//------------------------------------------------------------ ASSIGNATIONS

//Simple assignation: ident = expr;

public void asigna_simple(String ident, String expr){
  try{
    gencode_espacios();
    fichero.write(ident+" = "+expr+";\n");

  }catch(IOException e){}
}

//Multiple assignation. Receives a list if identificators and a list of
//expressions associated to those idents (in the same order). For every
//element writes "type = ident;" in the file, previous check of the right order.

public void asigna_multiple(List<String> ident, List<String> expr){
  try{
    List<List<String>> l = reordena_expresion(ident,expr);
    List<String> idents = l.get(0);
    List<String> exprs = l.get(1);
    
    
    for(int i=0;i<idents.size();i++){
      gencode_espacios();
      fichero.write(idents.get(i)+" = "+exprs.get(i)+";\n");
    }

  }catch(IOException e){}
}



//---------------------------------------------------------------- LOOPS

//Traduce mientras-->while

public void itera(String expr){
  try{
    gencode_espacios();
    fichero.write("while("+expr+"){\n");
    espacios++;
  }catch(IOException e){}
}

//---------------------------------------------------------------- SELECTIONS


//Traduce si-->if(expr)

public void selecciona(String expr){
  try{
    gencode_espacios();
    fichero.write("if("+expr+"){\n");
    espacios++;

  }catch(IOException e){}
}


//traduce sino-->else

public void selecciona_else(){
  try{
    espacios--;
    gencode_espacios();
    fichero.write("}else{\n");
    espacios++;
  }catch(IOException e){}

}



//------------------------------------------------------------------ RETURN

//The method will return a List of objects, so this method adds to the list
//what we want to return.

public void retorna(List<String> expr){
  try{
    int i;
    fichero.write("\n");
    for(i=0;i<expr.size();i++){
      gencode_espacios();
      fichero.write("res.add("+expr.get(i)+");\n");
    }


  }catch(IOException e){}

}


//Return res

public void devuelve_res(){
  try{
    fichero.write("\n");
    gencode_espacios();
    fichero.write("return res;\n");
  }catch(IOException e){}
}

//------------------------------------------------------CLASS/METHODS CLOSURE


//writes in file "}"

private void fines(){
    try{
        espacios--;
        gencode_espacios();
        fichero.write("}\n");

    }catch(IOException e){}
    }




//======================================================================= MAIN
//This is not really necessary, it's done tio test whether the file generates
//correctly or not

public void jaja(){
    try{
        gencode_espacios();
        fichero.write("public static void main (String args[]){\n");
        espacios++;
    
        gencode_espacios(); 
        fichero.write("int[] mi_vector = {1,2,3,4,5};\n");
    
        gencode_espacios();
        fichero.write("int e = 4;\n");
    
        gencode_espacios();
        fichero.write("System.out.println(buscar(e, mi_vector));\n");
        fines();
        
        
    }catch(IOException e){}
    
}



}


    ///////////////////////////////////////////////////////////////////
    //                       RESULTING GRAMMAR                       //
    ///////////////////////////////////////////////////////////////////



procedure: {open_file(); inicia_programa(); jaja();}
            #(PROCEDURE perfil variableslocales instrucciones)
            {fines();}{fines();}{close_file();}
           ;


perfil: #(PERFIL nombre parametros resultados)
     
        ;

nombre: #(NOMBRE a:IDENT)
   {abre_cabecera(a.getText());}
      ;

parametros {List<String> l1 = new ArrayList<>();
            List<String> l2 = new ArrayList<>();
            List<String> l;} : 
    #(PARAMETROS (l=parametro {l1.add(l.get(0));l2.add(l.get(1));})*)
    {parametros_cabecera(l1,l2);}
    {cierra_cabecera();}
      ;


parametro returns [List<String> l = new ArrayList<>()]{String tip;}: #(a:IDENT tip = tipo)
    {l.add(tip);l.add(a.getText());}
  ;


tipo returns [String s = null]: s = vector
 | s = tipo1
 ;
 
vector returns [String v = null] : #(VECTOR v = tipo1 indice indice)
    {v = v + "[]";}
    ;

tipo1 returns [String s = null]: ENTERO{s = "int";}
    |BOOLEANO {s = "boolean";}
    ;


resultados: #(RESULTADOS (tipo)+)
    ;


indice : NUMERO | IDENT
    ;

variableslocales : #(VARIABLESLOCALES (localvar)+)
    ;

localvar{List<String> id = new ArrayList<>();String t = null;}: 
    (
     #(ENTERO (a:IDENT{id.add(a.getText());})+ {t = "int";})
    |#(BOOLEANO (b:IDENT{id.add(b.getText());})+ {t = "boolean";})
    )
    {inicializa_localvars(t, id);}
    ;

instrucciones: #(INSTRS (instruccion)+)
    ;

instruccion: asignacion
            | iteracion {fines();}
            | seleccion {fines();}
            | retorno
            ;

iteracion {String s;}: #(MIENTRAS s = expresion {itera(s);} (instruccion)+)
        ;
        
retorno {List<String> l = new ArrayList<>();}: #(DEV (a:IDENT {l.add(a.getText());})+)
         {retorna(l); devuelve_res();}
        ;



asignacion{String s1;List<String> l1,l2;}: 
        #(ASIG (
                    (
                    a:IDENT s1 = expresion
                    {asigna_simple(a.getText(), s1);}
                    )
                |
                    (
                    l1 = variables l2 =expresiones
                    {asigna_multiple(l1,l2);}
                    )
                )
        ) ;



variables returns [List<String> l = new ArrayList<>()]: #(VARS (a:IDENT{l.add(a.getText());})+)
            ;

expresiones returns [List<String> l = new ArrayList<>()] {String s;} : #(EXPRS (s = expresion{l.add(s);})+)
            ;


seleccion {String s;}: #(SI s =  expresion {selecciona(s);} instruccion {selecciona_else();} instruccion)
        ;

expresion returns [String s = null] {String s1, s2;}:
         #(Y s1 = expresion s2 = expresion {s = s1+" && "+s2;})
        |#(O s1 = expresion s2 = expresion {s = s1+" || "+s2;})
        |#(MAS s1 = expresion s2 = expresion {s = s1+" + "+s2;})
        |#(MENOS s1 = expresion s2 = expresion {s = s1+" - "+s2;})
        |#(MAYORIGUAL s1 = expresion s2 = expresion {s = s1+" >= "+s2;})
        |#(IGUAL s1 = expresion s2 = expresion {s = s1+" == "+s2;})
        |#(MENORIGUAL s1 = expresion s2 = expresion {s = s1+" <= "+s2;})
        |#(DISTINTO s1 = expresion s2 = expresion {s = s1+" != "+s2;})
        |#(POR s1 = expresion s2 = expresion {s = "("+s1+" * "+s2+")";})
        |#(DIV s1 = expresion s2 = expresion {s = "("+s1+" / "+s2+")";})
        |(#(a:IDENT (NUMERO|IDENT)?){s = saca_string(a);})
        |(CIERTO {s = "true";})
        |(FALSO {s = "false";})
        |(c:NUMERO {s = c.getText();})
        ;

