/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.momentos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import logicits.arvoreLex.Arvore;
import logicits.equivalencia.Equivalencia;
import logicits.equivalencia.GeradorDeEquivalencia;
import logicits.equivalencia.ListaRegras;
import logicits.javacclist.Sintatico;

/**
 *
 * @author fabio
 */
public class DigitaEquivalencia {

    private String questao = "Dada a seguinte expressão lógica: \n";
    private List<Equivalencia> resposta = new ArrayList<>();
    private List<Equivalencia> outrasEquivalencias = new ArrayList<>();

    public DigitaEquivalencia(String expr) {
        Sintatico s = new Sintatico(expr);
        if (!"Erro".equals(s.lista.get(0))) {
            for (Iterator token = s.lista.iterator(); token.hasNext();) {
                Object next = token.next();
                //System.out.println(next.toString());
            }

            Arvore a = new Arvore(s.lista, false);
            this.questao = this.questao + a.toString() + "\n";
            GeradorDeEquivalencia gerador = new GeradorDeEquivalencia(s.lista, a);
            List<Equivalencia> l = gerador.getEquivalencias();
            this.outrasEquivalencias = l;
            Random rand = new Random();
            Equivalencia eq = l.get(rand.nextInt(l.size()));
            this.outrasEquivalencias.remove(eq);
            this.resposta.add(eq);
            this.questao = this.questao + "\nAplique a regra " + new ListaRegras().getRegra(eq.getRegra()) + " \ne digite a expressão equivalente\n";
            for (int i = 0; i < l.size(); i++) {
                if (eq.getRegra().equals(l.get(i).getRegra())) {
                    this.resposta.add(l.get(i));
                }
            }        

        } else {
            this.questao = "";
            for (int i = 0; i < s.lista.size(); i++) {
                this.questao = this.questao + s.lista.get(i);
            }
        }
    }

    public List<Equivalencia> getResposta() {
        return resposta;
    }

    @Override
    public String toString() {
        return this.questao;
    }

    public List<Equivalencia> getOutrasEquivalencias() {
        return outrasEquivalencias;
    }
    

}
