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
        List<Integer> listaRespostas = new ArrayList<>();
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numeros.add(i);
        }
        //Embaralhamos os números:
        Collections.shuffle(numeros);
        //Mostramos os primeiros aleatórios
        for (int i = 0; i < s.getListaEq().size(); i++) {
            listaRespostas.add(numeros.get(i));
            opcoes[numeros.get(i)] = numeros.get(i)+" - "+s.getListaEq().get(i).toStringEqui();
        }

        for (int i = 0; i < 10; i++) {
            if (opcoes[i] == null) {
                opcoes[i] = i+" - "+"errado"; //colocar uma expressão aqui
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
        String[] entradaUsuario = respostaDoUsuario.split(",");
        List<Integer> listRespostaUsuario = new ArrayList<>();
        for (int i = 0; i < entradaUsuario.length; i++) {
            listRespostaUsuario.add(Integer.parseInt(entradaUsuario[i]));
        }
        List<Integer> acertos = new ArrayList<>();
        List<Integer> faltas = new ArrayList<>();
        List<Integer> sobras = new ArrayList<>();
        for (Integer resUsr : listRespostaUsuario) {
            if (listaRespostas.contains(resUsr)) {
                acertos.add(resUsr);
            } else {
                sobras.add(resUsr);
            }
        }
        for (Integer res : listaRespostas) {
            if (!listRespostaUsuario.contains(res)) {
                faltas.add(res);
            }
        }

        if (acertos.isEmpty()) {
            System.out.println("Resposta errada, você não selecionou nenhuma expressão correta!");
        } else if (faltas.isEmpty() && sobras.isEmpty()) {
            System.out.println("Resposta correta, você acertou todas!");
        } else {
            System.out.println("Sua resposta NÃO está completamente certa!");
            for (int i = 0; i < faltas.size(); i++) {
                System.out.println(faltas.get(i) + " - " + s.getListaEq().get(numeros.indexOf(faltas.get(i))) + " TAMBÉM é equivalente!");
            }
            for (int i = 0; i < sobras.size(); i++) {
                System.out.println(opcoes[sobras.get(i)] + " NÃO é equivalente!");
            }
        }
        if (!acertos.isEmpty()) {
            System.out.println("As seguintes expressões foram selecionadas corretamente:");
            for (Integer acert : acertos) {
                System.out.println(acert+" - "+s.getListaEq().get(numeros.indexOf(acert)));
            }
        }
    }

}
