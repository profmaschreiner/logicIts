/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import logicits.momentos.Expressoes;
import logicits.momentos.CalculadoraLogica;
import logicits.momentos.QuestaoDigitaEquivalencia;
import logicits.momentos.QuestaoRegraLogica;
import logicits.momentos.QuestaoSelecionaEquivalencia;


/**
 *
 * @author fabio
 */
public class Logicits {

    public static void main(String[] args) {
        Random rand = new Random();
        Expressoes dados = new Expressoes();
        List<String> l = dados.getExp();
        String exp = l.get(rand.nextInt(l.size()));
        
        //new GeradorTeste();                 //configuração interna
        
        //CalculadoraLogica qc = new CalculadoraLogica(exp);             //digitar expressão no terminal  
        //QuestaoRegraLogica qr = new QuestaoRegraLogica(exp);//digitar numero da regra no terminal
        //QuestaoSelecionaEquivalencia qs = new QuestaoSelecionaEquivalencia(exp);//digitar numeros das expressoes (separando por ,) no terminal
        QuestaoDigitaEquivalencia qd = new QuestaoDigitaEquivalencia(exp);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println(qd);
        
        Scanner sc = new Scanner(System.in);
        String expEq = sc.nextLine();
        
        String res = qd.resposta(expEq);
        System.out.println(res);
    }
}
