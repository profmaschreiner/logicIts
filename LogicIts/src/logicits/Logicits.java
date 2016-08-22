/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import arvore.Arvore;
import java.util.Iterator;
import logicits.javaccList.Sintatico;

/**
 *
 * @author fabio
 */
public class Logicits {
    public static void main(String[] args) {
        Sintatico s = new Sintatico("(p&w)->s");
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
