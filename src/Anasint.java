// $ANTLR : "Anasint.g" -> "Anasint.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class Anasint extends antlr.LLkParser       implements AnasintTokenTypes
 {

protected Anasint(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Anasint(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected Anasint(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Anasint(TokenStream lexer) {
  this(lexer,1);
}

public Anasint(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void procedure() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST procedure_AST = null;
		AST a_AST = null;
		AST b_AST = null;
		AST c_AST = null;
		
		try {      // for error handling
			perfil();
			a_AST = (AST)returnAST;
			variableslocales();
			b_AST = (AST)returnAST;
			instrucciones();
			c_AST = (AST)returnAST;
			match(Token.EOF_TYPE);
			if ( inputState.guessing==0 ) {
				procedure_AST = (AST)currentAST.root;
				procedure_AST = (AST)astFactory.make( (new ASTArray(4)).add(astFactory.create(PROCEDURE,"PROCEDURE")).add(a_AST).add(b_AST).add(c_AST));
				currentAST.root = procedure_AST;
				currentAST.child = procedure_AST!=null &&procedure_AST.getFirstChild()!=null ?
					procedure_AST.getFirstChild() : procedure_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = procedure_AST;
	}
	
	public final void perfil() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST perfil_AST = null;
		Token  a = null;
		AST a_AST = null;
		AST b_AST = null;
		AST c_AST = null;
		
		try {      // for error handling
			a = LT(1);
			a_AST = astFactory.create(a);
			match(IDENT);
			AST tmp2_AST = null;
			tmp2_AST = astFactory.create(LT(1));
			match(PA);
			parametros();
			b_AST = (AST)returnAST;
			AST tmp3_AST = null;
			tmp3_AST = astFactory.create(LT(1));
			match(PC);
			AST tmp4_AST = null;
			tmp4_AST = astFactory.create(LT(1));
			match(DEV);
			AST tmp5_AST = null;
			tmp5_AST = astFactory.create(LT(1));
			match(PA);
			tipos();
			c_AST = (AST)returnAST;
			AST tmp6_AST = null;
			tmp6_AST = astFactory.create(LT(1));
			match(PC);
			if ( inputState.guessing==0 ) {
				perfil_AST = (AST)currentAST.root;
				perfil_AST = (AST)astFactory.make( (new ASTArray(4)).add(astFactory.create(PERFIL,"PERFIL")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOMBRE,"NOMBRE")).add(a_AST))).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PARAMETROS,"PARAMETROS")).add(b_AST))).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(RESULTADOS,"RESULTADOS")).add(c_AST))));
				currentAST.root = perfil_AST;
				currentAST.child = perfil_AST!=null &&perfil_AST.getFirstChild()!=null ?
					perfil_AST.getFirstChild() : perfil_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = perfil_AST;
	}
	
	public final void variableslocales() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST variableslocales_AST = null;
		AST a_AST = null;
		
		try {      // for error handling
			AST tmp7_AST = null;
			tmp7_AST = astFactory.create(LT(1));
			match(VARIABLES);
			AST tmp8_AST = null;
			tmp8_AST = astFactory.create(LT(1));
			match(LOCALES);
			AST tmp9_AST = null;
			tmp9_AST = astFactory.create(LT(1));
			match(DP);
			variables();
			a_AST = (AST)returnAST;
			if ( inputState.guessing==0 ) {
				variableslocales_AST = (AST)currentAST.root;
				variableslocales_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(VARIABLESLOCALES,"VARIABLESLOCALES")).add(a_AST));
				currentAST.root = variableslocales_AST;
				currentAST.child = variableslocales_AST!=null &&variableslocales_AST.getFirstChild()!=null ?
					variableslocales_AST.getFirstChild() : variableslocales_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = variableslocales_AST;
	}
	
	public final void instrucciones() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST instrucciones_AST = null;
		
		try {      // for error handling
			match(INSTRUCCIONES);
			match(DP);
			{
			_loop18:
			do {
				if ((_tokenSet_3.member(LA(1)))) {
					instruccion();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop18;
				}
				
			} while (true);
			}
			match(FIN);
			if ( inputState.guessing==0 ) {
				instrucciones_AST = (AST)currentAST.root;
				instrucciones_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(INSTRS,"INSTRUCCIONES")).add(instrucciones_AST));
				currentAST.root = instrucciones_AST;
				currentAST.child = instrucciones_AST!=null &&instrucciones_AST.getFirstChild()!=null ?
					instrucciones_AST.getFirstChild() : instrucciones_AST;
				currentAST.advanceChildToEnd();
			}
			instrucciones_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = instrucciones_AST;
	}
	
	public final void parametros() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parametros_AST = null;
		
		try {      // for error handling
			boolean synPredMatched5 = false;
			if (((LA(1)==VECTOR||LA(1)==ENTERO||LA(1)==BOOLEANO))) {
				int _m5 = mark();
				synPredMatched5 = true;
				inputState.guessing++;
				try {
					{
					parametro();
					match(COMA);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched5 = false;
				}
				rewind(_m5);
inputState.guessing--;
			}
			if ( synPredMatched5 ) {
				parametro();
				astFactory.addASTChild(currentAST, returnAST);
				match(COMA);
				parametros();
				astFactory.addASTChild(currentAST, returnAST);
				parametros_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==VECTOR||LA(1)==ENTERO||LA(1)==BOOLEANO)) {
				parametro();
				astFactory.addASTChild(currentAST, returnAST);
				parametros_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==PC)) {
				parametros_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = parametros_AST;
	}
	
	public final void tipos() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST tipos_AST = null;
		
		try {      // for error handling
			boolean synPredMatched9 = false;
			if (((LA(1)==VECTOR||LA(1)==ENTERO||LA(1)==BOOLEANO))) {
				int _m9 = mark();
				synPredMatched9 = true;
				inputState.guessing++;
				try {
					{
					tipo();
					match(COMA);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched9 = false;
				}
				rewind(_m9);
inputState.guessing--;
			}
			if ( synPredMatched9 ) {
				tipo();
				astFactory.addASTChild(currentAST, returnAST);
				match(COMA);
				tipos();
				astFactory.addASTChild(currentAST, returnAST);
				tipos_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==VECTOR||LA(1)==ENTERO||LA(1)==BOOLEANO)) {
				tipo();
				astFactory.addASTChild(currentAST, returnAST);
				tipos_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==PC)) {
				tipos_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = tipos_AST;
	}
	
	public final void parametro() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parametro_AST = null;
		
		try {      // for error handling
			tipo();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp15_AST = null;
			tmp15_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp15_AST);
			match(IDENT);
			parametro_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = parametro_AST;
	}
	
	public final void tipo() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST tipo_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case VECTOR:
			{
				AST tmp16_AST = null;
				tmp16_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp16_AST);
				match(VECTOR);
				match(PA);
				tipo();
				astFactory.addASTChild(currentAST, returnAST);
				match(PC);
				match(CA);
				indice();
				astFactory.addASTChild(currentAST, returnAST);
				match(RANGO);
				indice();
				astFactory.addASTChild(currentAST, returnAST);
				match(CC);
				tipo_AST = (AST)currentAST.root;
				break;
			}
			case ENTERO:
			{
				AST tmp22_AST = null;
				tmp22_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp22_AST);
				match(ENTERO);
				tipo_AST = (AST)currentAST.root;
				break;
			}
			case BOOLEANO:
			{
				AST tmp23_AST = null;
				tmp23_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp23_AST);
				match(BOOLEANO);
				tipo_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = tipo_AST;
	}
	
	public final void variables() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST variables_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case VECTOR:
			case ENTERO:
			case BOOLEANO:
			{
				decl_vars();
				astFactory.addASTChild(currentAST, returnAST);
				variables();
				astFactory.addASTChild(currentAST, returnAST);
				variables_AST = (AST)currentAST.root;
				break;
			}
			case INSTRUCCIONES:
			{
				variables_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = variables_AST;
	}
	
	public final void decl_vars() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST decl_vars_AST = null;
		AST a_AST = null;
		AST b_AST = null;
		
		try {      // for error handling
			tipo();
			a_AST = (AST)returnAST;
			vars();
			b_AST = (AST)returnAST;
			AST tmp24_AST = null;
			tmp24_AST = astFactory.create(LT(1));
			match(PyC);
			if ( inputState.guessing==0 ) {
				decl_vars_AST = (AST)currentAST.root;
				decl_vars_AST = (AST)astFactory.make( (new ASTArray(2)).add(a_AST).add(b_AST));
				currentAST.root = decl_vars_AST;
				currentAST.child = decl_vars_AST!=null &&decl_vars_AST.getFirstChild()!=null ?
					decl_vars_AST.getFirstChild() : decl_vars_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		returnAST = decl_vars_AST;
	}
	
	public final void vars() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST vars_AST = null;
		
		try {      // for error handling
			boolean synPredMatched15 = false;
			if (((LA(1)==IDENT))) {
				int _m15 = mark();
				synPredMatched15 = true;
				inputState.guessing++;
				try {
					{
					match(IDENT);
					match(COMA);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched15 = false;
				}
				rewind(_m15);
inputState.guessing--;
			}
			if ( synPredMatched15 ) {
				AST tmp25_AST = null;
				tmp25_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp25_AST);
				match(IDENT);
				match(COMA);
				vars();
				astFactory.addASTChild(currentAST, returnAST);
				vars_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==IDENT)) {
				AST tmp27_AST = null;
				tmp27_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp27_AST);
				match(IDENT);
				vars_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = vars_AST;
	}
	
	public final void instruccion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST instruccion_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case IDENT:
			case PA:
			{
				asignacion();
				astFactory.addASTChild(currentAST, returnAST);
				instruccion_AST = (AST)currentAST.root;
				break;
			}
			case MIENTRAS:
			{
				iteracion();
				astFactory.addASTChild(currentAST, returnAST);
				instruccion_AST = (AST)currentAST.root;
				break;
			}
			case SI:
			{
				seleccion();
				astFactory.addASTChild(currentAST, returnAST);
				instruccion_AST = (AST)currentAST.root;
				break;
			}
			case DEV:
			{
				retorno();
				astFactory.addASTChild(currentAST, returnAST);
				instruccion_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = instruccion_AST;
	}
	
	public final void asignacion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST asignacion_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case IDENT:
			{
				asignacionsimple();
				astFactory.addASTChild(currentAST, returnAST);
				asignacion_AST = (AST)currentAST.root;
				break;
			}
			case PA:
			{
				asignacionmultiple();
				astFactory.addASTChild(currentAST, returnAST);
				asignacion_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = asignacion_AST;
	}
	
	public final void iteracion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST iteracion_AST = null;
		
		try {      // for error handling
			AST tmp28_AST = null;
			tmp28_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp28_AST);
			match(MIENTRAS);
			match(PA);
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			match(PC);
			match(HACER);
			bloque();
			astFactory.addASTChild(currentAST, returnAST);
			match(FINMIENTRAS);
			iteracion_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = iteracion_AST;
	}
	
	public final void seleccion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST seleccion_AST = null;
		
		try {      // for error handling
			boolean synPredMatched26 = false;
			if (((LA(1)==SI))) {
				int _m26 = mark();
				synPredMatched26 = true;
				inputState.guessing++;
				try {
					{
					match(SI);
					match(PA);
					expr();
					match(PC);
					match(ENTONCES);
					bloque();
					match(SINO);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched26 = false;
				}
				rewind(_m26);
inputState.guessing--;
			}
			if ( synPredMatched26 ) {
				AST tmp33_AST = null;
				tmp33_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp33_AST);
				match(SI);
				match(PA);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				match(PC);
				match(ENTONCES);
				bloque();
				astFactory.addASTChild(currentAST, returnAST);
				match(SINO);
				bloque();
				astFactory.addASTChild(currentAST, returnAST);
				match(FINSI);
				seleccion_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==SI)) {
				AST tmp39_AST = null;
				tmp39_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp39_AST);
				match(SI);
				match(PA);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				match(PC);
				match(ENTONCES);
				bloque();
				astFactory.addASTChild(currentAST, returnAST);
				match(FINSI);
				seleccion_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = seleccion_AST;
	}
	
	public final void retorno() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST retorno_AST = null;
		
		try {      // for error handling
			AST tmp44_AST = null;
			tmp44_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp44_AST);
			match(DEV);
			match(PA);
			exprs();
			astFactory.addASTChild(currentAST, returnAST);
			match(PC);
			match(PyC);
			retorno_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = retorno_AST;
	}
	
	public final void asignacionsimple() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST asignacionsimple_AST = null;
		
		try {      // for error handling
			AST tmp48_AST = null;
			tmp48_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp48_AST);
			match(IDENT);
			AST tmp49_AST = null;
			tmp49_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp49_AST);
			match(ASIG);
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			match(PyC);
			asignacionsimple_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = asignacionsimple_AST;
	}
	
	public final void asignacionmultiple() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST asignacionmultiple_AST = null;
		AST a_AST = null;
		Token  b = null;
		AST b_AST = null;
		AST c_AST = null;
		
		try {      // for error handling
			AST tmp51_AST = null;
			tmp51_AST = astFactory.create(LT(1));
			match(PA);
			vars();
			a_AST = (AST)returnAST;
			AST tmp52_AST = null;
			tmp52_AST = astFactory.create(LT(1));
			match(PC);
			b = LT(1);
			b_AST = astFactory.create(b);
			match(ASIG);
			AST tmp53_AST = null;
			tmp53_AST = astFactory.create(LT(1));
			match(PA);
			exprs();
			c_AST = (AST)returnAST;
			AST tmp54_AST = null;
			tmp54_AST = astFactory.create(LT(1));
			match(PC);
			match(PyC);
			if ( inputState.guessing==0 ) {
				asignacionmultiple_AST = (AST)currentAST.root;
				asignacionmultiple_AST =
				(AST)astFactory.make( (new ASTArray(3)).add(b_AST).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(VARS,"VARIABLES")).add(a_AST))).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(EXPRS,"EXPRESIONES")).add(c_AST))));
				currentAST.root = asignacionmultiple_AST;
				currentAST.child = asignacionmultiple_AST!=null &&asignacionmultiple_AST.getFirstChild()!=null ?
					asignacionmultiple_AST.getFirstChild() : asignacionmultiple_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = asignacionmultiple_AST;
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_AST = null;
		
		try {      // for error handling
			boolean synPredMatched39 = false;
			if (((_tokenSet_10.member(LA(1))))) {
				int _m39 = mark();
				synPredMatched39 = true;
				inputState.guessing++;
				try {
					{
					expr2();
					{
					switch ( LA(1)) {
					case Y:
					{
						match(Y);
						break;
					}
					case O:
					{
						match(O);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched39 = false;
				}
				rewind(_m39);
inputState.guessing--;
			}
			if ( synPredMatched39 ) {
				expr2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case Y:
				{
					AST tmp56_AST = null;
					tmp56_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp56_AST);
					match(Y);
					break;
				}
				case O:
				{
					AST tmp57_AST = null;
					tmp57_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp57_AST);
					match(O);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				expr_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_10.member(LA(1)))) {
				expr2();
				astFactory.addASTChild(currentAST, returnAST);
				expr_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = expr_AST;
	}
	
	public final void exprs() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exprs_AST = null;
		
		try {      // for error handling
			boolean synPredMatched35 = false;
			if (((_tokenSet_10.member(LA(1))))) {
				int _m35 = mark();
				synPredMatched35 = true;
				inputState.guessing++;
				try {
					{
					expr();
					match(COMA);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched35 = false;
				}
				rewind(_m35);
inputState.guessing--;
			}
			if ( synPredMatched35 ) {
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				match(COMA);
				exprs();
				astFactory.addASTChild(currentAST, returnAST);
				exprs_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_10.member(LA(1)))) {
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				exprs_AST = (AST)currentAST.root;
			}
			else if ((LA(1)==PC)) {
				exprs_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = exprs_AST;
	}
	
	public final void bloque() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bloque_AST = null;
		
		try {      // for error handling
			{
			_loop29:
			do {
				if ((_tokenSet_3.member(LA(1)))) {
					instruccion();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop29;
				}
				
			} while (true);
			}
			bloque_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = bloque_AST;
	}
	
	public final void indice() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST indice_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case NUMERO:
			{
				AST tmp59_AST = null;
				tmp59_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp59_AST);
				match(NUMERO);
				indice_AST = (AST)currentAST.root;
				break;
			}
			case IDENT:
			{
				AST tmp60_AST = null;
				tmp60_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp60_AST);
				match(IDENT);
				indice_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
		returnAST = indice_AST;
	}
	
	public final void expr2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr2_AST = null;
		
		try {      // for error handling
			boolean synPredMatched44 = false;
			if (((_tokenSet_10.member(LA(1))))) {
				int _m44 = mark();
				synPredMatched44 = true;
				inputState.guessing++;
				try {
					{
					termino();
					{
					switch ( LA(1)) {
					case MAYOR:
					{
						match(MAYOR);
						break;
					}
					case MENOR:
					{
						match(MENOR);
						break;
					}
					case MAYORIGUAL:
					{
						match(MAYORIGUAL);
						break;
					}
					case MENORIGUAL:
					{
						match(MENORIGUAL);
						break;
					}
					case IGUAL:
					{
						match(IGUAL);
						break;
					}
					case DISTINTO:
					{
						match(DISTINTO);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched44 = false;
				}
				rewind(_m44);
inputState.guessing--;
			}
			if ( synPredMatched44 ) {
				termino();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case MAYOR:
				{
					AST tmp61_AST = null;
					tmp61_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp61_AST);
					match(MAYOR);
					break;
				}
				case MENOR:
				{
					AST tmp62_AST = null;
					tmp62_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp62_AST);
					match(MENOR);
					break;
				}
				case MAYORIGUAL:
				{
					AST tmp63_AST = null;
					tmp63_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp63_AST);
					match(MAYORIGUAL);
					break;
				}
				case MENORIGUAL:
				{
					AST tmp64_AST = null;
					tmp64_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp64_AST);
					match(MENORIGUAL);
					break;
				}
				case IGUAL:
				{
					AST tmp65_AST = null;
					tmp65_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp65_AST);
					match(IGUAL);
					break;
				}
				case DISTINTO:
				{
					AST tmp66_AST = null;
					tmp66_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp66_AST);
					match(DISTINTO);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				termino();
				astFactory.addASTChild(currentAST, returnAST);
				expr2_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_10.member(LA(1)))) {
				termino();
				astFactory.addASTChild(currentAST, returnAST);
				expr2_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = expr2_AST;
	}
	
	public final void termino() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST termino_AST = null;
		
		try {      // for error handling
			boolean synPredMatched49 = false;
			if (((_tokenSet_10.member(LA(1))))) {
				int _m49 = mark();
				synPredMatched49 = true;
				inputState.guessing++;
				try {
					{
					termino2();
					{
					switch ( LA(1)) {
					case MAS:
					{
						match(MAS);
						break;
					}
					case MENOS:
					{
						match(MENOS);
						break;
					}
					case POR:
					{
						match(POR);
						break;
					}
					case DIV:
					{
						match(DIV);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched49 = false;
				}
				rewind(_m49);
inputState.guessing--;
			}
			if ( synPredMatched49 ) {
				termino2();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case MAS:
				{
					AST tmp67_AST = null;
					tmp67_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp67_AST);
					match(MAS);
					break;
				}
				case MENOS:
				{
					AST tmp68_AST = null;
					tmp68_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp68_AST);
					match(MENOS);
					break;
				}
				case POR:
				{
					AST tmp69_AST = null;
					tmp69_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp69_AST);
					match(POR);
					break;
				}
				case DIV:
				{
					AST tmp70_AST = null;
					tmp70_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp70_AST);
					match(DIV);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				termino();
				astFactory.addASTChild(currentAST, returnAST);
				termino_AST = (AST)currentAST.root;
			}
			else if ((_tokenSet_10.member(LA(1)))) {
				termino2();
				astFactory.addASTChild(currentAST, returnAST);
				termino_AST = (AST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = termino_AST;
	}
	
	public final void termino2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST termino2_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case NUMERO:
			{
				AST tmp71_AST = null;
				tmp71_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp71_AST);
				match(NUMERO);
				termino2_AST = (AST)currentAST.root;
				break;
			}
			case FALSO:
			{
				AST tmp72_AST = null;
				tmp72_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp72_AST);
				match(FALSO);
				termino2_AST = (AST)currentAST.root;
				break;
			}
			case CIERTO:
			{
				AST tmp73_AST = null;
				tmp73_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp73_AST);
				match(CIERTO);
				termino2_AST = (AST)currentAST.root;
				break;
			}
			default:
				boolean synPredMatched53 = false;
				if (((LA(1)==IDENT))) {
					int _m53 = mark();
					synPredMatched53 = true;
					inputState.guessing++;
					try {
						{
						match(IDENT);
						match(CA);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched53 = false;
					}
					rewind(_m53);
inputState.guessing--;
				}
				if ( synPredMatched53 ) {
					AST tmp74_AST = null;
					tmp74_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp74_AST);
					match(IDENT);
					match(CA);
					expr();
					astFactory.addASTChild(currentAST, returnAST);
					match(CC);
					termino2_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched55 = false;
					if (((LA(1)==IDENT))) {
						int _m55 = mark();
						synPredMatched55 = true;
						inputState.guessing++;
						try {
							{
							match(IDENT);
							match(PA);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched55 = false;
						}
						rewind(_m55);
inputState.guessing--;
					}
					if ( synPredMatched55 ) {
						AST tmp77_AST = null;
						tmp77_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp77_AST);
						match(IDENT);
						match(PA);
						exprs();
						astFactory.addASTChild(currentAST, returnAST);
						match(PC);
						termino2_AST = (AST)currentAST.root;
					}
					else if ((LA(1)==IDENT)) {
						AST tmp80_AST = null;
						tmp80_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp80_AST);
						match(IDENT);
						termino2_AST = (AST)currentAST.root;
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_16);
				} else {
				  throw ex;
				}
			}
			returnAST = termino2_AST;
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
		
		protected void buildTokenTypeASTClassMap() {
			tokenTypeToASTClassMap=null;
		};
		
		private static final long[] mk_tokenSet_0() {
			long[] data = { 2L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
		private static final long[] mk_tokenSet_1() {
			long[] data = { 262144L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
		private static final long[] mk_tokenSet_2() {
			long[] data = { 4194304L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
		private static final long[] mk_tokenSet_3() {
			long[] data = { 302080000L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
		private static final long[] mk_tokenSet_4() {
			long[] data = { 32768L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
		private static final long[] mk_tokenSet_5() {
			long[] data = { 163840L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
		private static final long[] mk_tokenSet_6() {
			long[] data = { 172032L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
		private static final long[] mk_tokenSet_7() {
			long[] data = { 210457591808L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
		private static final long[] mk_tokenSet_8() {
			long[] data = { 2129920L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
		private static final long[] mk_tokenSet_9() {
			long[] data = { 3665911808L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
		private static final long[] mk_tokenSet_10() {
			long[] data = { 6755674318970880L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
		private static final long[] mk_tokenSet_11() {
			long[] data = { 34361999360L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
		private static final long[] mk_tokenSet_12() {
			long[] data = { 3355443200L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
		private static final long[] mk_tokenSet_13() {
			long[] data = { 51539607552L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
		private static final long[] mk_tokenSet_14() {
			long[] data = { 1683629441024L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
		private static final long[] mk_tokenSet_15() {
			long[] data = { 140222094540800L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
		private static final long[] mk_tokenSet_16() {
			long[] data = { 2251284419870720L, 0L};
			return data;
		}
		public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
		
		}
