/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import logicits.arvoreLex.Arvore;
import java.util.Iterator;
import logicits.equivalencia.GeradorDeEquivalencia;
import logicits.javaccList.Sintatico;

/**
 *
 * @author fabio
 */
public class Logicits {
    public static void main(String[] args) {
        Sintatico s = new Sintatico("~(~~p)");
        //Sintatico s = new Sintatico("(p ^ q)");
        //Sintatico s = new Sintatico("p ^ p");
        //Sintatico s = new Sintatico("p v q");
        //Sintatico s = new Sintatico("q ^ p");
        //Sintatico s = new Sintatico("p v q");
        //Sintatico s = new Sintatico("q v p");
        
        //Sintatico s = new Sintatico("p v ~(q ^ r)");  // Não aplicar assoc se tiver negaçõa fora de parenteses
        //Sintatico s = new Sintatico("p v (q v r)");
        //Sintatico s = new Sintatico("(p ^ q) ^ r");
        //Sintatico s = new Sintatico("(p v q )v r");
        
        //Sintatico s = new Sintatico("(p v q) ^ ~(q v r)");  // Não aplicar dist se tiver negação fora de parenteses
        //Sintatico s = new Sintatico("p ^ (q v r)");
        //Sintatico s = new Sintatico("(p v r) ^ ~(p v q)"); 
        //Sintatico s = new Sintatico("~p");
        //Sintatico s = new Sintatico("~(p ^ q)");
        //Sintatico s = new Sintatico("~p v ~q");
        //Sintatico s = new Sintatico("~(p v q)");
        //Sintatico s = new Sintatico("~p ^ ~q");
        //Sintatico s = new Sintatico("p -> q");
        //Sintatico s = new Sintatico("~p v q");
        //Sintatico s = new Sintatico("p <-> q");
        //Sintatico s = new Sintatico("(p -> q) ^ (q -> p)");
        //Sintatico s = new Sintatico("(p ^ q) v (~p ^ ~q)");  // ID apenas para filhos iguais 
                                                              //verificar negações junto com proposição nas equivalencias
        //Sintatico s = new Sintatico("~q -> ~p");
        //Sintatico s = new Sintatico("((p ^ q) -> r)");
        //Sintatico s = new Sintatico("p -> (q -> r)");
        //Sintatico s = new Sintatico("~(~~(~p))");
        
               
        //Sintatico s = new Sintatico("~(p ^ q)");// dm ¬p ∨ ¬q 
        //Sintatico s = new Sintatico("~(p v q)");// dm ¬p ∧ ¬q
        //Sintatico s = new Sintatico("p -> q ");// cond ¬p ∨ q
        //Sintatico s = new Sintatico("p <-> q ");// bicond (p → q) ∧ (q → p) E (p ∧ q) ∨ (¬p ∧ ¬q)        
        //Sintatico s = new Sintatico("p -> q ");// cp ¬p -> ¬q
        //Sintatico s = new Sintatico("(q -> r)^p");// ei p → (q → r)
        /**
         * Prof. Marcos
         * 
         */
        
        //Sintatico s = new Sintatico("(p v q) ^ (r v s)");
        //Sintatico s = new Sintatico("(p v q) ^ (r v s v t)");
        //Sintatico s = new Sintatico("(p ^ q) v (r ^ s ^ t)");
        //Sintatico s = new Sintatico("(p v q) v (r ^ s ^ t)");
        //Sintatico s = new Sintatico("(p ^ q) v (r ^ s v t)");
        //Sintatico s = new Sintatico("(p v q) ^ (r ^ s ^ t)"); 
        //Sintatico s = new Sintatico("~( (p v q) ^ (r v s) )");
        //Sintatico s = new Sintatico("~( (p ^ q) v (r v s) )");
        //Sintatico s = new Sintatico("( (p ^ q) -> (r v s) )"); // ~(p ^ q ) v  (r v s ) 
        //Sintatico s = new Sintatico("( (p ^ q) -> (r v s) )"); // ~(r v s ) ->  ~ (p ^ q )

        

      
        if (!"Erro".equals(s.lista.get(0))) {
            for (Iterator token = s.lista.iterator(); token.hasNext();) {
                Object next = token.next();
                System.out.println(next.toString());
            }
        
        
        Arvore a = new Arvore(s.lista,false);        
        a.imprime();
        
        System.out.println("");        
        
        GeradorDeEquivalencia ge = new GeradorDeEquivalencia(s.lista, a);       
        
        ge.getLista().stream().forEach((equivalencia) -> {
            equivalencia.imprime();
        });
        }
        
    }
}
