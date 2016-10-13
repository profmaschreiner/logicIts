/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import logicits.momentos.Expressoes;
import logicits.momentos.SelecionaEquivalencia;

/**
 *
 * @author fabio
 */
public class SelecionaEquivalenciaTeste {

    public SelecionaEquivalenciaTeste() {
        Random rand = new Random();
        Expressoes dados = new Expressoes();
        List<String> l = dados.getExp();
        String exp = l.get(rand.nextInt(l.size()));
        String[] opcoes = new String[10];
        SelecionaEquivalencia s = new SelecionaEquivalencia(exp);
        List<Integer> respostas = new ArrayList<>();
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i < 10; i++) { 
            numeros.add(i);
        }
        //Embaralhamos os números:
        Collections.shuffle(numeros);
        //Mostramos os primeiros aleatórios
        for (int i = 0; i < s.getListaEq().size(); i++) {
            respostas.add(numeros.get(i));
            opcoes[numeros.get(i)] = s.getListaEq().get(i).toStringEqui();
        }
        
        
        
        for (int i = 0; i < 10; i++) {
            if (opcoes[i]==null) {
                opcoes[i]="errado";
            }
        }
       
        

        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println(s);
        for (int i = 0; i < 10; i++) {
            System.out.println(opcoes[i]);
        }
        System.out.println("\n");
        Scanner sc = new Scanner(System.in);
        String respostaDoUsuario = sc.nextLine();
        String[] listRespDoUsuario = respostaDoUsuario.split(",");
        List<Integer> listResInt = new ArrayList<>();
        for (int i = 0; i < listRespDoUsuario.length; i++) {
            listResInt.add(Integer.parseInt(listRespDoUsuario[i]));
        }
        
//        if (listResInt.size() == respostas.size()) {
//            System.out.println("\n\nResposta correta");
//        } else {
//            System.out.println("\n\nResposta errada");
//        }

    }

}
