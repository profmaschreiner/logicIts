/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits;

import java.util.ArrayList;
import java.util.List;
import logicits.javaccList.*;

/**
 *
 * @author fabio
 */
public class LogicIts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> lista = new ArrayList();
        try {
            
            LogicoLexico analizador = new LogicoLexico(System.in);
            lista = analizador.program();
        } catch (ParseException e) {
            lista.clear();
            lista.add("Errro");
            lista.add(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Erro na analize!!!");
        }

    }

}
