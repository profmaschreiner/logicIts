/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import logicits.equivalencia.ListaRegras;
import logicits.momentos.Expressoes;
import logicits.momentos.RegraLogica;

/**
 *
 * @author fabio
 */
public class RegraLogicaTeste {

    public RegraLogicaTeste() {
        Random rand = new Random();
        Expressoes dados = new Expressoes();
        List<String> l = dados.getExp();
        String exp = l.get(rand.nextInt(l.size()));
        ListaRegras lr = new ListaRegras();

        RegraLogica r = new RegraLogica(exp);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println(r);
        for (int i = 0; i < lr.getLista().size(); i++) {
            System.out.println(i + " - " + lr.getLista().get(i));
        }
        System.out.println("\n");
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        if (lr.getLista().get(num1).equals(lr.getRegra(r.getReg()))) {
            System.out.println("\n\nResposta correta");
        } else {
            System.out.println("\n\nResposta errada");
        }
    }

}
