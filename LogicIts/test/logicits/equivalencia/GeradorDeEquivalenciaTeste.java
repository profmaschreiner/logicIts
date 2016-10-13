/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.equivalencia;

import logicits.arvoreLex.Arvore;
import logicits.javacclist.Sintatico;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fabio
 */
public class GeradorDeEquivalenciaTeste {

    @Test
    public void teste() {
        exe("~(~~p)","( ~ p ^ ~ p)"); 
        exe("(p ^ p)","p");
        exe("p ^ q","q ^ p");
        exe("p v q","q v p");
        exe("q ^ p","p ^ q");
        exe("p v q","~q -> p");
        exe("q v p","~p -> q");

        exe("p v ~(q ^ r)","(q ^ r) -> p");  
        exe("p v (q v r)","~(q v r) -> p");
        exe("(p ^ q) ^ r","p ^ (q ^ r)");
        exe("(p v q ) v r","p v (q v r)");
        exe("(p v q) ^ ~(q v r)","~(q v r) ^ (p v q)");
        exe("p ^ (q v r)","(p ^ q) v (p ^ r)");
        exe("(p v r) ^ ~(p v q)","~(p v q) ^ (p v r)"); 
        exe("~p","~p v ~p");
        exe("~(p ^ q)","~p v ~q");
        exe("~p v ~q","~(p ^ q)");
        exe("~(p v q)","~p ^ ~q");
        exe("~p ^ ~q","~(p v q)");
        exe("p -> q","~p v q");
        exe("~p v q","p -> q");
        exe("p <-> q","(p -> q) ^ (q -> p)");
        exe("(p -> q) ^ (q -> p)","p <-> q");
        exe("(p ^ q) v (p ^ ~q)","p ^ (q v ~q)"); 
        exe("~q -> ~p","p -> q");
        exe("((p ^ q) -> r)","p -> (q -> r)");
        exe("p -> (q -> r)","(p ^ q) -> r");
        exe("~(~~(~p))","p ^ p");
        exe("~(p ^ q)","~p v ~q");// dm ¬p ∨ ¬q 
        exe("~(p v q)","~p ^ ~q");// dm ¬p ∧ ¬q
        exe("p -> q ","~p v q");// cond ¬p ∨ q
        exe("p <-> q ","(p -> q) ^ (q -> p)");// bicond (p → q) ∧ (q → p) E (p ∧ q) ∨ (¬p ∧ ¬q)        
        exe("p <-> q ","(p ^ q) v (~p ^ ~q)");
        exe("p -> q ","~q -> ~p");   // cp ¬p -> ¬q ----- verificar mas acho que esta errado
        exe("(q -> r) ^ p"," p ^ (q -> r) ");
        /**
         * Prof. Marcos
         *
         */
        exe("(p v q) ^ (r v s)","(r v s) ^ (p v q)");
        exe("(p v q) ^ (r v s v t)","(r v s v t) ^ (p v q)");
        exe("(p ^ q) v (r ^ s ^ t)","(r ^ s ^ t) v (p ^ q)");
        exe("(p v q) v (r ^ s ^ t)","(r ^ s ^ t) v (p v q)");
        exe("(p ^ q) v (r ^ s v t)","~(p ^ q) -> (r ^ s v t)");
        exe("(p v q) ^ (r ^ s ^ t)","(r ^ s ^ t) ^ (p v q) "); 
        exe("~( (p v q) ^ (r v s) )","( ~(p v q) v ~(r v s) )");
        exe("~( (p ^ q) v (r v s) )","(~(p ^ q) ^ ~(r v s))");
        exe("( (p ^ q) -> (r v s) )", "(~( p ^ q) v( r v s))");
        exe("( (p ^ q) -> (r v s) )"," ~(r v s ) ->  ~ (p ^ q )");

    }

    private boolean exe(String inicial, String esperada) {
        Sintatico s = new Sintatico(inicial);
        assertEquals(true, new GeradorDeEquivalencia(s.lista, new Arvore(s.lista, false)).gerou(esperada));
        return true;
    }
}
