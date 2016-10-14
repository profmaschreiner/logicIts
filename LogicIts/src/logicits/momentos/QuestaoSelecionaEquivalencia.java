/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.momentos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author fabio
 */
public class QuestaoSelecionaEquivalencia {
    private String questao = "";
    private String[] opcoes = new String[10];
    private SelecionaEquivalencia s;
    private List<Integer> listaRespostas = new ArrayList<>();
    private List<Integer> numeros = new ArrayList<>();

    public QuestaoSelecionaEquivalencia(String exp) {
        s = new SelecionaEquivalencia(exp);

        for (int i = 0; i < 10; i++) {
            numeros.add(i);
        }
        //Embaralhamos os números:
        Collections.shuffle(numeros);
        //Mostramos os primeiros aleatórios
        for (int i = 0; i < s.getListaEq().size(); i++) {
            listaRespostas.add(numeros.get(i));
            opcoes[numeros.get(i)] = numeros.get(i) + " - " + s.getListaEq().get(i).toStringEqui();
        }

        for (int i = 0; i < 10; i++) {
            if (opcoes[i] == null) {
                opcoes[i] = i + " - " + "errado"; //colocar uma expressão aqui
            }
        }

        this.questao = s.toString();
        for (int i = 0; i < 10; i++) {
            this.questao = this.questao+"\n"+opcoes[i];
        }
    }

    @Override
    public String toString() {
        return this.questao;
    }

    
    public String resposta(String respostaDoUsuario) {
        String retorno = "";
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
            retorno="Resposta errada, você não selecionou nenhuma expressão correta!";
        } else if (faltas.isEmpty() && sobras.isEmpty()) {
            retorno = retorno+"\nResposta correta, você acertou todas!";
        } else {
            retorno = "Sua resposta NÃO está completamente certa!";
            for (int i = 0; i < faltas.size(); i++) {
                retorno = retorno+"\n"+faltas.get(i) 
                        + " - " 
                        + s.getListaEq().get(numeros.indexOf(faltas.get(i))) 
                        + " TAMBÉM é equivalente!";
            }
            for (int i = 0; i < sobras.size(); i++) {
                retorno = retorno+"\n"+opcoes[sobras.get(i)] + " NÃO é equivalente!";
            }
        }
        if (!acertos.isEmpty()) {
            retorno = retorno+"\nAs seguintes expressões foram selecionadas corretamente:";
            for (Integer acert : acertos) {
                retorno = retorno+"\n"+acert + " - " + s.getListaEq().get(numeros.indexOf(acert));
            }
        }
        return retorno;
        
    }

}
