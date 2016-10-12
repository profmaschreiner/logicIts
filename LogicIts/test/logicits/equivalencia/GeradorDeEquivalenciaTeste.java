/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.equivalencia;

import logicits.arvoreLex.Arvore;
import logicits.javaccList.Sintatico;
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
        exe("(p ^ q)"," p");
        exe("p ^ p"," q");
        exe("p v q","  ");
        exe("q ^ p","  ");
        exe("p v q","  ");
        exe("q v p","  ");

        exe("p v ~(q ^ r)","  ");  // Não aplicar assoc se tiver negaçõa fora de parenteses
        exe("p v (q v r)","  ");
        exe("(p ^ q) ^ r","  ");
        exe("(p v q )v r","  ");
        exe("(p v q) ^ ~(q v r)","  ");  // Não aplicar dist se tiver negação fora de parenteses
        exe("p ^ (q v r)","  ");
        exe("(p v r) ^ ~(p v q)","  "); 
        exe("~p","  ");
        exe("~(p ^ q)","  ");
        exe("~p v ~q","  ");
        exe("~(p v q)","  ");
        exe("~p ^ ~q","  ");
        exe("p -> q","  ");
        exe("~p v q","  ");
        exe("p <-> q","  ");
        exe("(p -> q) ^ (q -> p)","  ");
        exe("(p ^ q) v (~p ^ ~q)","  ");  // ID apenas para filhos iguais 
        //verificar negações junto com proposição nas equivalencias
        exe("~q -> ~p","  ");
        exe("((p ^ q) -> r)","  ");
        exe("p -> (q -> r)","  ");
        exe("~(~~(~p))","  ");
        exe("~(p ^ q)","  ");// dm ¬p ∨ ¬q 
        exe("~(p v q)","  ");// dm ¬p ∧ ¬q
        exe("p -> q ","  ");// cond ¬p ∨ q
        exe("p <-> q ","  ");// bicond (p → q) ∧ (q → p) E (p ∧ q) ∨ (¬p ∧ ¬q)        
        exe("p -> q ","  ");// cp ¬p -> ¬q
        exe("(q -> r)^p","  ");// ei p → (q → r)
        /**
         * Prof. Marcos
         *
         */
        exe("(p v q) ^ (r v s)","  ");
        exe("(p v q) ^ (r v s v t)","  ");
        exe("(p ^ q) v (r ^ s ^ t)","  ");
        exe("(p v q) v (r ^ s ^ t)","  ");
        exe("(p ^ q) v (r ^ s v t)","  ");
        exe("(p v q) ^ (r ^ s ^ t)","  "); 
        exe("~( (p v q) ^ (r v s) )","  ");
        exe("~( (p ^ q) v (r v s) )","  ");
        exe("( (p ^ q) -> (r v s) )", "( ~( p ^ q) v( r v s))");
        exe("( (p ^ q) -> (r v s) )","  "); // ~(r v s ) ->  ~ (p ^ q )

    }

    private boolean exe(String inicial, String esperada) {
        Sintatico s = new Sintatico(inicial);
        assertEquals(true, new GeradorDeEquivalencia(s.lista, new Arvore(s.lista, false)).gerou(esperada));
        return true;
    }
}
