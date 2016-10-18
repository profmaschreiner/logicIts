/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import java.util.Iterator;
import logicits.arvoreLex.Arvore;
import logicits.equivalencia.GeradorDeEquivalencia;
import logicits.javacclist.Sintatico;

/**
 *
 * @author fabio
 */
public class GeradorTeste {

    public GeradorTeste() {
        //Sintatico s = new Sintatico("");
        //Sintatico s = new Sintatico("~(~~p)");                //id
        //Sintatico s = new Sintatico("p ^ p");                 //id 
        //Sintatico s = new Sintatico("~p");                    //id
        //Sintatico s = new Sintatico("~(~~(~p))");             //id 
        //Sintatico s = new Sintatico("(p ^ q)");               //com
        //Sintatico s = new Sintatico("q ^ p");                 //com
        //Sintatico s = new Sintatico("(p v q) ^ ~(q v r)");    //com
        //Sintatico s = new Sintatico("(p v r) ^ ~(p v q)");    //com
        //Sintatico s = new Sintatico("(q -> r) ^ p");          //com 
        //Sintatico s = new Sintatico("(p ^ q) ^ r");           //com assoc 
        //Sintatico s = new Sintatico("(p v q )v r");           //com assoc cond
        //Sintatico s = new Sintatico("p v (q v r)");           //com assoc cond 
        //Sintatico s = new Sintatico("p ^ (q v r)");           //com dist
        //Sintatico s = new Sintatico("~p v q");                //com cond 
        //Sintatico s = new Sintatico("p v ~(q ^ r)");          //com cond
        //Sintatico s = new Sintatico("p v q");                 //com cond 
        //Sintatico s = new Sintatico("~(p v q)");              //com cond dm-------dm ¬p ∧ ¬q
        //Sintatico s = new Sintatico("~p v ~q");               //com cond dm
        //Sintatico s = new Sintatico("~(p v q)");              //com cond dm
        //Sintatico s = new Sintatico("(p ^ q) v (~p ^ ~q)");   //com cond bicond
        //Sintatico s = new Sintatico("~(p ^ q)");              //com dm
        //Sintatico s = new Sintatico("~p ^ ~q");               //com dm
        //Sintatico s = new Sintatico("~(p ^ q)");              //com dm------------dm ¬p ∨ ¬q
        //Sintatico s = new Sintatico("(p -> q) ^ (q -> p)");   //com bicond
        //Sintatico s = new Sintatico("~q -> ~p");              //cond cp        
        //Sintatico s = new Sintatico("p -> q ");               //cond cp---------cond ¬p ∨ q
        //Sintatico s = new Sintatico("((p ^ q) -> r)");        //cond cp ei
        //Sintatico s = new Sintatico("p -> (q -> r)");         //cond cp ei
        //Sintatico s = new Sintatico("p -> q ");               //cond cp---------- cond ¬p ∨ q  
        //Sintatico s = new Sintatico("p <-> q ");              //bicond----------- (p → q) ∧ (q → p) E (p ∧ q) ∨ (¬p ∧ ¬q)
        /**
         * Prof. Marcos
         *
         */

        //Sintatico s = new Sintatico("(p ^ q) v (q ^ p)");     //id dist 
        //Sintatico s = new Sintatico("(p v q) ^ (r ^ s ^ t)"); //com 
        //Sintatico s = new Sintatico("(p v q) ^ (r v s)");     //com  
        //Sintatico s = new Sintatico("(p v q) ^ (r v s v t)"); //com
        //Sintatico s = new Sintatico("~(~p ^ ~q)");            //com dm 
        //Sintatico s = new Sintatico("(p ^ q) v (r ^ s ^ t)"); //com cond
        //Sintatico s = new Sintatico("(p v q) v (r ^ s ^ t)"); //com cond 
        //Sintatico s = new Sintatico("(p ^ q) v (r ^ s v t)"); //com cond
        //Sintatico s = new Sintatico("(~p v q)");              //com cond
        //Sintatico s = new Sintatico("(p -> q) v (q -> p)");   //com cond
        //Sintatico s = new Sintatico("(p ^ q) v (~q ^ ~p)");   //com cond bicond 
        //Sintatico s = new Sintatico("~( (p v q) ^ (r v s) )");//com dm 
        //Sintatico s = new Sintatico("~( (p ^ q) v (r v s) )");//com dm cond
        //Sintatico s = new Sintatico("(~p v ~q)");             //com dm cond
        //Sintatico s = new Sintatico("(p -> q) ^ (q -> p)");   //com bicond
        //Sintatico s = new Sintatico("( (p ^ q) -> (r v s) )");//cond cp ei
        //Sintatico s = new Sintatico("( (p ^ q) -> (r v s) )");//cond cp ei
        //Sintatico s = new Sintatico("( (p ^ q) -> r)");       //cond cp ei
        Sintatico s = new Sintatico("( r -> (p -> q))");      //cond cp ei  
        //Sintatico s = new Sintatico("p -> q <-> r");
        if (!"Erro".equals(s.lista.get(0))) {
            for (Iterator token = s.lista.iterator(); token.hasNext();) {
                Object next = token.next();
                System.out.println(next.toString());
            }

            Arvore a = new Arvore(s.lista, false);
            a.imprime();

            System.out.println("");

            GeradorDeEquivalencia ge = new GeradorDeEquivalencia(s.lista, a);

            ge.getLista().stream().forEach((equivalencia) -> {
                equivalencia.imprime();
            });
        }

    }

}
