/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.momentos;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import logicits.arvoreLex.Arvore;
import logicits.equivalencia.Equivalencia;
import logicits.equivalencia.GeradorDeEquivalencia;
import logicits.javaccList.Sintatico;

/**
 *
 * @author fabio
 */
public class SelecionaEquivalencia {
    private String questao = "Dada a expressão lógicaa seguir: \n";
    List<Equivalencia> listaEq;

    public SelecionaEquivalencia(String expr) {
        Sintatico s = new Sintatico(expr);
        if (!"Erro".equals(s.lista.get(0))) {
            for (Iterator token = s.lista.iterator(); token.hasNext();) {
                Object next = token.next();
                System.out.println(next.toString());
            }

            Arvore a = new Arvore(s.lista, false);

            GeradorDeEquivalencia gerador = new GeradorDeEquivalencia(s.lista, a);
            listaEq = gerador.getEquivalencias();                        
            
            this.questao = this.questao + a.toString();
            this.questao = this.questao + "\nSelecione todas as expressões equivalentes:";
            
        } else {
            this.questao = "";
            for (int i = 0; i < s.lista.size(); i++) {
                this.questao = this.questao + s.lista.get(i);
            }
        }
        
    }

    @Override
    public String toString() {
        return questao;
    }

    public List<Equivalencia> getListaEq() {
        return listaEq;
    }

    
    
    
    
    
}
