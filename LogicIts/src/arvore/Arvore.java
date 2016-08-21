/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore;

import java.util.List;

/**
 *
 * @author fabio
 */
public class Arvore {

    boolean negacao = false;
    String info;
    Arvore dir;
    Arvore esq;

    public Arvore(List<String> exp) {
        this.insere(exp);
    }

    private void insere(List<String> exp) {
        expressao(exp);
    }

    private void expressao(List<String> exp) {
        this.termo(exp);
        if (ehOper(exp.get(0))) {
            this.info = exp.get(0);
            exp.remove(0);
            this.expressao(exp);
        }
    }

    private void termo(List<String> exp) {
        if ("(".equals(exp.get(0))) {
            exp.remove(0);
            this.expressao(exp);
            exp.remove(0);
        } else {
            this.variavel(exp);
        }
    }

    private void variavel(List<String> exp) {
        boolean negLocal = ehNegacao(exp.get(0));
        if (negLocal) {
            exp.remove(exp.get(0));
        }if (exp.size() < 2 && ")".equals(exp.get(1))) {
            esq = new Arvore(exp);
            esq.negacao = negLocal;
        }else{
            dir = new Arvore(exp);
            dir.negacao = negLocal;
        }
        
    }

    public boolean ehOper(String token) {
        return "|-".equals(token) || "&".equals(token) || "|".equals(token)
                || "<->".equals(token) || "->".equals(token) || "<-".equals(token)
                || ">".equals(token) || "<".equals(token);
    }

    public boolean ehNegacao(String token) {
        return "~".equals(token);
    }

}
