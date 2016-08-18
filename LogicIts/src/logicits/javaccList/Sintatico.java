/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.javaccList;

import com.sun.imageio.plugins.common.InputStreamAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sun.net.www.content.text.PlainTextInputStream;

/**
 *
 * @author fabio
 */
public class Sintatico {

    public List<String> lista = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sintatico sint = new Sintatico("p <-> q");
        
    }

    public Sintatico(String s) {
        try {
            StringReader stringReader = new StringReader(s);
            LogicoLexico analizador = new LogicoLexico(stringReader);

            this.lista = analizador.program();

        } catch (ParseException e) {
            this.lista.clear();
            this.lista.add("Erro");
            this.lista.add(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Erro na analize!!!");
        }
    }

}
