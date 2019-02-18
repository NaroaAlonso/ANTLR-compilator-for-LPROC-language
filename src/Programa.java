import java.io.*;
import java.util.*;
public class Programa{

   public static void main (String args[]){
      int[] mi_vector = {1,2,3,4,5};
      int e = 4;
      System.out.println(buscar(e, mi_vector));
   }

   public static List<Object> buscar(int e, int[] v){

         List<Object> res = new ArrayList<>();

         boolean resultado;
         int elemento, i;
         resultado = false;
         i = 1;
         while(resultado == false && i <= v.length){
            elemento = v[i];
            if(elemento == e){
               resultado = true;
            }else{
               i = i + 1;
            }
         }

         res.add(resultado);
         res.add(i);

         return res;
      }
   }
