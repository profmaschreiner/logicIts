/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.momentos;

import java.util.Iterator;
import logicits.arvoreLex.Arvore;
import logicits.equivalencia.GeradorDeEquivalencia;
import logicits.javaccList.Sintatico;

/**
 *
 * @author fabio
 */
public class Calculadora {

    private GeradorDeEquivalencia gerador = null;
    private String result = "";

    public Calculadora(String str) {
        Sintatico s = new Sintatico(str);
        if (!"Erro".equals(s.lista.get(0))) {
            for (Iterator token = s.lista.iterator(); token.hasNext();) {
                Object next = token.next();
                //System.out.println(next.toString());
            }

            Arvore a = new Arvore(s.lista, false);

            gerador = new GeradorDeEquivalencia(s.lista, a);

            gerador.getLista().stream().forEach((equivalencia) -> {
                result = result + equivalencia.toString()+"\n";
            });
        } else {
            for (int i = 0; i < s.lista.size(); i++) {
                result = result + s.lista.get(i);
            }
        }
    }

    public GeradorDeEquivalencia getGerador() {
        return gerador;
    }

    @Override
    public String toString() {
        return result;
    }
    
}

