package logicits.javaccList;

import java.io.*;
import java.util.*;

public class Teste {

    public static void main(String[] args) {
        List<String> lista = new ArrayList();
        try {
            LogicoLexico analizador = new LogicoLexico(System.in);
            lista = analizador.program();
            //for(int i=0;i<lista.size();i++){
            //  System.out.println(lista.get(i));  
            //}
        } catch (ParseException e) {
            lista.clear();
            lista.add("Errro");
            lista.add(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Erro na analize!!!");
        }

    }
}
