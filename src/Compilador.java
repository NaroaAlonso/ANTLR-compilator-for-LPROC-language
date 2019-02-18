// $ANTLR : "Compilador3.g" -> "Compilador.java"$

    import java.util.*;
    import antlr.*;
    import java.io.*;

import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;


public class Compilador extends antlr.TreeParser       implements CompiladorTokenTypes
 {





	///////////////////////////////////////////////////////////////////
	//                  METODOS ESCRIBIR FICHEROS                    //
	///////////////////////////////////////////////////////////////////


//Abrir y cerrar ficheros

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


//Generar espacios

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
	//                      METODOS AUXILIARES                       //
	///////////////////////////////////////////////////////////////////




//----------------------------------------------METODO reordena_expresion:

//Comprueba si hay dependencias y devuelve el orden correcto para hacer asignaciones mútliples

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
     
     
     
     
//Distingue en "expresion si es un IDENT solo o tiene hijos. Según los hijos que tenga,
//imprime una cosa u otra


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
	//                      METODOS COMPILADOR                       //
	///////////////////////////////////////////////////////////////////





//=========================================================== METODOS CABECERA


//---------------------------------------------------------INICIALIZA PROGRAMA
//Imprime los import y abre la cabecera de la clase "Programa"

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




//------------------------------------------------------------CABECERA METODO

//Imprime la cabecera del método. En java sería:
//public static List<Object> nombreMetodo(tipo ident, tipo ident){

public void abre_cabecera(String nombrePrograma){
    try{
    	fichero.write("\n");
        gencode_espacios();
        fichero.write("public static List<Object> "+nombrePrograma+"(");
        espacios++;

    }catch(IOException e){}
    }


//----------------------------------------------------PARAMETROS METODO

//Recibe una lista de tipos y de identificadores y los escribe en el fichero adaptado
//al formato de parametros de entradas de un método en java:
//tipo1 ident1, tipo2 ident2,..., tipoN identN

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



//==============================================================VARIABLES LOCALES

//Recibe un tipo y una lista de idents y escribe en el fichero:
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



//================================================================= INSTRUCCIONES

//--------------------------------------------------------------ASIGNACIONES

//Realiza una asignación simple. Como únicamente recibimos iden y expresión,
//Las escribe en el fichero así: ident = expr;

public void asigna_simple(String ident, String expr){
  try{
  	gencode_espacios();
    fichero.write(ident+" = "+expr+";\n");

  }catch(IOException e){}
}


//Realiza una asignación múltiple. Recibe una lista de idents y una lista de
//expresiones asociadas a esos idents (en el mismo orden). Recorre las dos listas
//y, para todos los elementos escribe tipo = ident;\n, una vez que se han reordenado
//para evitar dependencias con el método reordena_expresion.

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



//----------------------------------------------------------------ITERACIONES

//Traduce mientras-->while

public void itera(String expr){
  try{
  	gencode_espacios();
    fichero.write("while("+expr+"){\n");
    espacios++;
  }catch(IOException e){}
}

//----------------------------------------------------------------SELECCIONES


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



//--------------------------------------------------------------------RETORNO

//Añade a la List<Object> res todo lo que devolvemos en "devuelve"

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


//Retornamos res

public void devuelve_res(){
  try{
  	fichero.write("\n");
  	gencode_espacios();
    fichero.write("return res;\n");
  }catch(IOException e){}
}

//-------------------------------------------------------CIERRE DE CLASE/METODOS


//cada vez que se haga un finmientras,finsi o fin de programa,
//llamamos a este método, que escribe un corchete:

private void fines(){
    try{
        espacios--;
        gencode_espacios();
        fichero.write("}\n");

    }catch(IOException e){}
    }




//======================================================================= MAIN
//Esta clase no hace falta, no pertenece al programa. Se ha hecho para comprobar
//El método.

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



public Compilador() {
	tokenNames = _tokenNames;
}

	public final void procedure(AST _t) throws RecognitionException {
		
		AST procedure_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			open_file(); inicia_programa(); jaja();
			AST __t2 = _t;
			AST tmp1_AST_in = (AST)_t;
			match(_t,PROCEDURE);
			_t = _t.getFirstChild();
			perfil(_t);
			_t = _retTree;
			variableslocales(_t);
			_t = _retTree;
			instrucciones(_t);
			_t = _retTree;
			_t = __t2;
			_t = _t.getNextSibling();
			fines();
			fines();
			close_file();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void perfil(AST _t) throws RecognitionException {
		
		AST perfil_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t4 = _t;
			AST tmp2_AST_in = (AST)_t;
			match(_t,PERFIL);
			_t = _t.getFirstChild();
			nombre(_t);
			_t = _retTree;
			parametros(_t);
			_t = _retTree;
			resultados(_t);
			_t = _retTree;
			_t = __t4;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void variableslocales(AST _t) throws RecognitionException {
		
		AST variableslocales_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t23 = _t;
			AST tmp3_AST_in = (AST)_t;
			match(_t,VARIABLESLOCALES);
			_t = _t.getFirstChild();
			{
			int _cnt25=0;
			_loop25:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==ENTERO||_t.getType()==BOOLEANO)) {
					localvar(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt25>=1 ) { break _loop25; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt25++;
			} while (true);
			}
			_t = __t23;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void instrucciones(AST _t) throws RecognitionException {
		
		AST instrucciones_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t35 = _t;
			AST tmp4_AST_in = (AST)_t;
			match(_t,INSTRS);
			_t = _t.getFirstChild();
			{
			int _cnt37=0;
			_loop37:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_0.member(_t.getType()))) {
					instruccion(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt37>=1 ) { break _loop37; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt37++;
			} while (true);
			}
			_t = __t35;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void nombre(AST _t) throws RecognitionException {
		
		AST nombre_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		
		try {      // for error handling
			AST __t6 = _t;
			AST tmp5_AST_in = (AST)_t;
			match(_t,NOMBRE);
			_t = _t.getFirstChild();
			a = (AST)_t;
			match(_t,IDENT);
			_t = _t.getNextSibling();
			_t = __t6;
			_t = _t.getNextSibling();
			abre_cabecera(a.getText());
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void parametros(AST _t) throws RecognitionException {
		
		AST parametros_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		List<String> l1 = new ArrayList<>();
					List<String> l2 = new ArrayList<>();
					List<String> l;
		
		try {      // for error handling
			AST __t8 = _t;
			AST tmp6_AST_in = (AST)_t;
			match(_t,PARAMETROS);
			_t = _t.getFirstChild();
			{
			_loop10:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==IDENT)) {
					l=parametro(_t);
					_t = _retTree;
					l1.add(l.get(0));l2.add(l.get(1));
				}
				else {
					break _loop10;
				}
				
			} while (true);
			}
			_t = __t8;
			_t = _t.getNextSibling();
			parametros_cabecera(l1,l2);
			cierra_cabecera();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void resultados(AST _t) throws RecognitionException {
		
		AST resultados_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t18 = _t;
			AST tmp7_AST_in = (AST)_t;
			match(_t,RESULTADOS);
			_t = _t.getFirstChild();
			{
			int _cnt20=0;
			_loop20:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==VECTOR||_t.getType()==ENTERO||_t.getType()==BOOLEANO)) {
					tipo(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt20>=1 ) { break _loop20; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt20++;
			} while (true);
			}
			_t = __t18;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final List<String>  parametro(AST _t) throws RecognitionException {
		List<String> l = new ArrayList<>();
		
		AST parametro_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		String tip;
		
		try {      // for error handling
			AST __t12 = _t;
			a = _t==ASTNULL ? null :(AST)_t;
			match(_t,IDENT);
			_t = _t.getFirstChild();
			tip=tipo(_t);
			_t = _retTree;
			_t = __t12;
			_t = _t.getNextSibling();
			l.add(tip);l.add(a.getText());
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return l;
	}
	
	public final String  tipo(AST _t) throws RecognitionException {
		String s = null;
		
		AST tipo_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case VECTOR:
			{
				s=vector(_t);
				_t = _retTree;
				break;
			}
			case ENTERO:
			case BOOLEANO:
			{
				s=tipo1(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return s;
	}
	
	public final String  vector(AST _t) throws RecognitionException {
		String v = null;
		
		AST vector_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t15 = _t;
			AST tmp8_AST_in = (AST)_t;
			match(_t,VECTOR);
			_t = _t.getFirstChild();
			v=tipo1(_t);
			_t = _retTree;
			indice(_t);
			_t = _retTree;
			indice(_t);
			_t = _retTree;
			_t = __t15;
			_t = _t.getNextSibling();
			v = v + "[]";
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return v;
	}
	
	public final String  tipo1(AST _t) throws RecognitionException {
		String s = null;
		
		AST tipo1_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ENTERO:
			{
				AST tmp9_AST_in = (AST)_t;
				match(_t,ENTERO);
				_t = _t.getNextSibling();
				s = "int";
				break;
			}
			case BOOLEANO:
			{
				AST tmp10_AST_in = (AST)_t;
				match(_t,BOOLEANO);
				_t = _t.getNextSibling();
				s = "boolean";
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return s;
	}
	
	public final void indice(AST _t) throws RecognitionException {
		
		AST indice_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case NUMERO:
			{
				AST tmp11_AST_in = (AST)_t;
				match(_t,NUMERO);
				_t = _t.getNextSibling();
				break;
			}
			case IDENT:
			{
				AST tmp12_AST_in = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void localvar(AST _t) throws RecognitionException {
		
		AST localvar_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		AST b = null;
		List<String> id = new ArrayList<>();String t = null;
		
		try {      // for error handling
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ENTERO:
			{
				AST __t28 = _t;
				AST tmp13_AST_in = (AST)_t;
				match(_t,ENTERO);
				_t = _t.getFirstChild();
				{
				int _cnt30=0;
				_loop30:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==IDENT)) {
						a = (AST)_t;
						match(_t,IDENT);
						_t = _t.getNextSibling();
						id.add(a.getText());
					}
					else {
						if ( _cnt30>=1 ) { break _loop30; } else {throw new NoViableAltException(_t);}
					}
					
					_cnt30++;
				} while (true);
				}
				t = "int";
				_t = __t28;
				_t = _t.getNextSibling();
				break;
			}
			case BOOLEANO:
			{
				AST __t31 = _t;
				AST tmp14_AST_in = (AST)_t;
				match(_t,BOOLEANO);
				_t = _t.getFirstChild();
				{
				int _cnt33=0;
				_loop33:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==IDENT)) {
						b = (AST)_t;
						match(_t,IDENT);
						_t = _t.getNextSibling();
						id.add(b.getText());
					}
					else {
						if ( _cnt33>=1 ) { break _loop33; } else {throw new NoViableAltException(_t);}
					}
					
					_cnt33++;
				} while (true);
				}
				t = "boolean";
				_t = __t31;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			inicializa_localvars(t, id);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void instruccion(AST _t) throws RecognitionException {
		
		AST instruccion_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ASIG:
			{
				asignacion(_t);
				_t = _retTree;
				break;
			}
			case MIENTRAS:
			{
				iteracion(_t);
				_t = _retTree;
				fines();
				break;
			}
			case SI:
			{
				seleccion(_t);
				_t = _retTree;
				fines();
				break;
			}
			case DEV:
			{
				retorno(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void asignacion(AST _t) throws RecognitionException {
		
		AST asignacion_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		String s1;List<String> l1,l2;
		
		try {      // for error handling
			AST __t48 = _t;
			AST tmp15_AST_in = (AST)_t;
			match(_t,ASIG);
			_t = _t.getFirstChild();
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IDENT:
			{
				{
				a = (AST)_t;
				match(_t,IDENT);
				_t = _t.getNextSibling();
				s1=expresion(_t);
				_t = _retTree;
				asigna_simple(a.getText(), s1);
				}
				break;
			}
			case VARS:
			{
				{
				l1=variables(_t);
				_t = _retTree;
				l2=expresiones(_t);
				_t = _retTree;
				asigna_multiple(l1,l2);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			_t = __t48;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void iteracion(AST _t) throws RecognitionException {
		
		AST iteracion_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		String s;
		
		try {      // for error handling
			AST __t40 = _t;
			AST tmp16_AST_in = (AST)_t;
			match(_t,MIENTRAS);
			_t = _t.getFirstChild();
			s=expresion(_t);
			_t = _retTree;
			itera(s);
			{
			int _cnt42=0;
			_loop42:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_0.member(_t.getType()))) {
					instruccion(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt42>=1 ) { break _loop42; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt42++;
			} while (true);
			}
			_t = __t40;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void seleccion(AST _t) throws RecognitionException {
		
		AST seleccion_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		String s;
		
		try {      // for error handling
			AST __t61 = _t;
			AST tmp17_AST_in = (AST)_t;
			match(_t,SI);
			_t = _t.getFirstChild();
			s=expresion(_t);
			_t = _retTree;
			selecciona(s);
			instruccion(_t);
			_t = _retTree;
			selecciona_else();
			instruccion(_t);
			_t = _retTree;
			_t = __t61;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void retorno(AST _t) throws RecognitionException {
		
		AST retorno_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		List<String> l = new ArrayList<>();
		
		try {      // for error handling
			AST __t44 = _t;
			AST tmp18_AST_in = (AST)_t;
			match(_t,DEV);
			_t = _t.getFirstChild();
			{
			int _cnt46=0;
			_loop46:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==IDENT)) {
					a = (AST)_t;
					match(_t,IDENT);
					_t = _t.getNextSibling();
					l.add(a.getText());
				}
				else {
					if ( _cnt46>=1 ) { break _loop46; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt46++;
			} while (true);
			}
			_t = __t44;
			_t = _t.getNextSibling();
			retorna(l); devuelve_res();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final String  expresion(AST _t) throws RecognitionException {
		String s = null;
		
		AST expresion_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		AST c = null;
		String s1, s2;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case Y:
			{
				AST __t63 = _t;
				AST tmp19_AST_in = (AST)_t;
				match(_t,Y);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = s1+" && "+s2;
				_t = __t63;
				_t = _t.getNextSibling();
				break;
			}
			case O:
			{
				AST __t64 = _t;
				AST tmp20_AST_in = (AST)_t;
				match(_t,O);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = s1+" || "+s2;
				_t = __t64;
				_t = _t.getNextSibling();
				break;
			}
			case MAS:
			{
				AST __t65 = _t;
				AST tmp21_AST_in = (AST)_t;
				match(_t,MAS);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = s1+" + "+s2;
				_t = __t65;
				_t = _t.getNextSibling();
				break;
			}
			case MENOS:
			{
				AST __t66 = _t;
				AST tmp22_AST_in = (AST)_t;
				match(_t,MENOS);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = s1+" - "+s2;
				_t = __t66;
				_t = _t.getNextSibling();
				break;
			}
			case MAYORIGUAL:
			{
				AST __t67 = _t;
				AST tmp23_AST_in = (AST)_t;
				match(_t,MAYORIGUAL);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = s1+" >= "+s2;
				_t = __t67;
				_t = _t.getNextSibling();
				break;
			}
			case IGUAL:
			{
				AST __t68 = _t;
				AST tmp24_AST_in = (AST)_t;
				match(_t,IGUAL);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = s1+" == "+s2;
				_t = __t68;
				_t = _t.getNextSibling();
				break;
			}
			case MENORIGUAL:
			{
				AST __t69 = _t;
				AST tmp25_AST_in = (AST)_t;
				match(_t,MENORIGUAL);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = s1+" <= "+s2;
				_t = __t69;
				_t = _t.getNextSibling();
				break;
			}
			case DISTINTO:
			{
				AST __t70 = _t;
				AST tmp26_AST_in = (AST)_t;
				match(_t,DISTINTO);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = s1+" != "+s2;
				_t = __t70;
				_t = _t.getNextSibling();
				break;
			}
			case POR:
			{
				AST __t71 = _t;
				AST tmp27_AST_in = (AST)_t;
				match(_t,POR);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = "("+s1+" * "+s2+")";
				_t = __t71;
				_t = _t.getNextSibling();
				break;
			}
			case DIV:
			{
				AST __t72 = _t;
				AST tmp28_AST_in = (AST)_t;
				match(_t,DIV);
				_t = _t.getFirstChild();
				s1=expresion(_t);
				_t = _retTree;
				s2=expresion(_t);
				_t = _retTree;
				s = "("+s1+" / "+s2+")";
				_t = __t72;
				_t = _t.getNextSibling();
				break;
			}
			case IDENT:
			{
				{
				AST __t74 = _t;
				a = _t==ASTNULL ? null :(AST)_t;
				match(_t,IDENT);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case NUMERO:
				{
					AST tmp29_AST_in = (AST)_t;
					match(_t,NUMERO);
					_t = _t.getNextSibling();
					break;
				}
				case IDENT:
				{
					AST tmp30_AST_in = (AST)_t;
					match(_t,IDENT);
					_t = _t.getNextSibling();
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				_t = __t74;
				_t = _t.getNextSibling();
				s = saca_string(a);
				}
				break;
			}
			case CIERTO:
			{
				{
				AST tmp31_AST_in = (AST)_t;
				match(_t,CIERTO);
				_t = _t.getNextSibling();
				s = "true";
				}
				break;
			}
			case FALSO:
			{
				{
				AST tmp32_AST_in = (AST)_t;
				match(_t,FALSO);
				_t = _t.getNextSibling();
				s = "false";
				}
				break;
			}
			case NUMERO:
			{
				{
				c = (AST)_t;
				match(_t,NUMERO);
				_t = _t.getNextSibling();
				s = c.getText();
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return s;
	}
	
	public final List<String>  variables(AST _t) throws RecognitionException {
		List<String> l = new ArrayList<>();
		
		AST variables_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST a = null;
		
		try {      // for error handling
			AST __t53 = _t;
			AST tmp33_AST_in = (AST)_t;
			match(_t,VARS);
			_t = _t.getFirstChild();
			{
			int _cnt55=0;
			_loop55:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==IDENT)) {
					a = (AST)_t;
					match(_t,IDENT);
					_t = _t.getNextSibling();
					l.add(a.getText());
				}
				else {
					if ( _cnt55>=1 ) { break _loop55; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt55++;
			} while (true);
			}
			_t = __t53;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return l;
	}
	
	public final List<String>  expresiones(AST _t) throws RecognitionException {
		List<String> l = new ArrayList<>();
		
		AST expresiones_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		String s;
		
		try {      // for error handling
			AST __t57 = _t;
			AST tmp34_AST_in = (AST)_t;
			match(_t,EXPRS);
			_t = _t.getFirstChild();
			{
			int _cnt59=0;
			_loop59:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_1.member(_t.getType()))) {
					s=expresion(_t);
					_t = _retTree;
					l.add(s);
				}
				else {
					if ( _cnt59>=1 ) { break _loop59; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt59++;
			} while (true);
			}
			_t = __t57;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return l;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"PROCEDURE",
		"PERFIL",
		"NOMBRE",
		"PARAMETROS",
		"RESULTADOS",
		"VARIABLESLOCALES",
		"INSTRS",
		"VARS",
		"EXPRS",
		"IDENT",
		"PA",
		"PC",
		"DEV",
		"COMA",
		"VARIABLES",
		"LOCALES",
		"DP",
		"PyC",
		"INSTRUCCIONES",
		"FIN",
		"ASIG",
		"MIENTRAS",
		"HACER",
		"FINMIENTRAS",
		"SI",
		"ENTONCES",
		"SINO",
		"FINSI",
		"VECTOR",
		"CA",
		"RANGO",
		"CC",
		"ENTERO",
		"BOOLEANO",
		"NUMERO",
		"Y",
		"O",
		"MAYOR",
		"MENOR",
		"MAYORIGUAL",
		"MENORIGUAL",
		"IGUAL",
		"DISTINTO",
		"MAS",
		"MENOS",
		"POR",
		"DIV",
		"FALSO",
		"CIERTO"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 318832640L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 9000327307075584L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	}
	
