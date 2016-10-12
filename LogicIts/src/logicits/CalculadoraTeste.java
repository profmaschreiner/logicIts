/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import java.util.Scanner;
import logicits.momentos.Calculadora;

/**
 *
 * @author fabio
 */
public class CalculadoraTeste {

    public CalculadoraTeste() {
        System.out.println("Digite a expressão:");
        Scanner sc = new Scanner(System.in);
        String exp = sc.nextLine();
        Calculadora c = new Calculadora(exp);
        System.out.println(c);
    }

}
