/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import logicits.arvoreLex.Arvore;
import logicits.equivalencia.Equivalencia;
import logicits.equivalencia.GeradorDeEquivalencia;
import logicits.equivalencia.ListaRegras;
import logicits.javaccList.Sintatico;
import logicits.momentos.DigitaEquivalencia;
import logicits.momentos.Expressoes;

/**
 *
 * @author fabio
 */
public class DigitaEquivalenciaTeste {

    public DigitaEquivalenciaTeste() {
        Random rand = new Random();
        Expressoes dados = new Expressoes();
        List<String> l = dados.getExp();
        String exp = l.get(rand.nextInt(l.size()));
        DigitaEquivalencia questao = new DigitaEquivalencia(exp);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println(questao);

        Scanner sc = new Scanner(System.in);
        String resUsr = sc.nextLine();

        Sintatico sinResUsr = new Sintatico(resUsr);
        if (!"Erro".equals(sinResUsr.lista.get(0))) {
            for (Iterator token = sinResUsr.lista.iterator(); token.hasNext();) {
                Object next = token.next();
                //System.out.println(next.toString());
            }

            Arvore respostaDoUsuario = new Arvore(sinResUsr.lista, false);

            System.out.println(respostaDoUsuario);
            System.out.println(questao.getResposta());

        } else {
            System.out.println("Expressão inválida");
            for (int i = 0; i < sinResUsr.lista.size(); i++) {
                System.out.println(sinResUsr.lista.get(i));
            }
        }

    }

}
