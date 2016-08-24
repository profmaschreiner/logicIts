/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import logicits.arvoreLex.Arvore;
import java.util.Iterator;
import logicits.javaccList.Sintatico;

/**
 *
 * @author fabio
 */
public class Logicits {
    public static void main(String[] args) {
        //Sintatico s = new Sintatico("p");
        //Sintatico s = new Sintatico("p & p");
        //Sintatico s = new Sintatico("p | p");
        //Sintatico s = new Sintatico("p & q");
        //Sintatico s = new Sintatico("q & p");
        //Sintatico s = new Sintatico("p | q");
        //Sintatico s = new Sintatico("q | p");
        //Sintatico s = new Sintatico("p & (q | r)");
        //Sintatico s = new Sintatico("p | (q & r)");
        Sintatico s = new Sintatico("(p & q) | (p & r)");
        //Sintatico s = new Sintatico("~~p");
        //Sintatico s = new Sintatico("~(p & q)");
        //Sintatico s = new Sintatico("~p | ~q");
        //Sintatico s = new Sintatico("~(p | q)");
        //Sintatico s = new Sintatico("~p & ~q");
        //Sintatico s = new Sintatico("p -> q");
        //Sintatico s = new Sintatico("~p | q");
        //Sintatico s = new Sintatico("p <-> q");
        //Sintatico s = new Sintatico("(p -> q) & (q -> p)");
        //Sintatico s = new Sintatico("(p & q) | (~p & ~q)");
        //Sintatico s = new Sintatico("~q -> ~p");
        //Sintatico s = new Sintatico("((p & q) -> r)");
        //Sintatico s = new Sintatico("p -> (q -> r)");
        //Sintatico s = new Sintatico("~(~~(~p))");
        
        if (!"Erro".equals(s.lista.get(0))) {
            for (Iterator token = s.lista.iterator(); token.hasNext();) {
                Object next = token.next();
                System.out.println(next.toString());
            }
        }
        
        Arvore a = new Arvore(s.lista,false);        
        a.imprime();
    }
}
