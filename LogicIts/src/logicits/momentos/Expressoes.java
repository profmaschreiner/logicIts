/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.momentos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class Expressoes {

    private List<String> exp = new ArrayList<>();

    public Expressoes() {
        this.exp.add("p ^ p");
        this.exp.add("~p");
        this.exp.add("~(~~(~p))");
        this.exp.add("(p ^ q)");
        this.exp.add("q ^ p");
        this.exp.add("(p v q) ^ ~(q v r)");
        this.exp.add("(p v r) ^ ~(p v q)");
        this.exp.add("(q -> r) ^ p");
        this.exp.add("(p ^ q) ^ r");
        this.exp.add("(p v q )v r");
        this.exp.add("p v (q v r)");
        this.exp.add("p ^ (q v r)");
        this.exp.add("~p v q");
        this.exp.add("p v ~(q ^ r)");
        this.exp.add("p v q");
        this.exp.add("~(p v q)");
        this.exp.add("~p v ~q");
        this.exp.add("~(p v q)");
        this.exp.add("(p ^ q) v (~p ^ ~q)");
        this.exp.add("~(p ^ q)");
        this.exp.add("~p ^ ~q");
        this.exp.add("~(p ^ q)");
        this.exp.add("(p -> q) ^ (q -> p)");
        this.exp.add("~q -> ~p");
        this.exp.add("p -> q ");
        this.exp.add("((p ^ q) -> r)");
        this.exp.add("p -> (q -> r)");
        this.exp.add("p -> q ");
        this.exp.add("p <-> q ");
        this.exp.add("(p ^ q) v (q ^ p)");
        this.exp.add("(p v q) ^ (r ^ s ^ t)");
        this.exp.add("(p v q) ^ (r v s)");
        this.exp.add("(p v q) ^ (r v s v t)");
        this.exp.add("~(~p ^ ~q)");
        this.exp.add("(p ^ q) v (r ^ s ^ t)");
        this.exp.add("(p v q) v (r ^ s ^ t)");
        this.exp.add("(p ^ q) v (r ^ s v t)");
        this.exp.add("(~p v q)");
        this.exp.add("(p -> q) v (q -> p)");
        this.exp.add("(p ^ q) v (~q ^ ~p)");
        this.exp.add("~( (p v q) ^ (r v s) )");
        this.exp.add("~( (p ^ q) v (r v s) )");
        this.exp.add("(~p v ~q)");
        this.exp.add("(p -> q) ^ (q -> p)");
        this.exp.add("( (p ^ q) -> (r v s) )");
        this.exp.add("( (p ^ q) -> (r v s) )");
        this.exp.add("( (p ^ q) -> r)");
        this.exp.add("( r -> (p -> q))");

    }

    public List<String> getExp() {
        return exp;
    }

}
