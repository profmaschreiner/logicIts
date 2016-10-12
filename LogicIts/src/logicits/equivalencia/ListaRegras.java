/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.equivalencia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class ListaRegras {

    private List<String> lista = null;

    public ListaRegras() {
        lista = new ArrayList<>();
        this.lista.add("Idempotêcia (ID)");
        this.lista.add("Comutacção (COM)");
        this.lista.add("Associação (ASSOC)");
        this.lista.add("Distributiva (DIST)");
        this.lista.add("Dupla Negação (DN)");
        this.lista.add("De Morgan (DM)");
        this.lista.add("Condicional (COND)");
        this.lista.add("Bicondicional (BICOND)");
        this.lista.add("Contraposição (CP)");
        this.lista.add("Exportação-Importação (EI)");
    }

    public List<String> getLista() {
        return lista;
    }

    public String getRegra(Regra regra) {
        switch (regra) {
            case ID:
                return this.getLista().get(0);
            case COM:
                return this.getLista().get(1);
            case ASSOC:
                return this.getLista().get(2);
            case DIST:
                return this.getLista().get(3);
            case DN:
                return this.getLista().get(4);
            case DM:
                return this.getLista().get(5);
            case COND:
                return this.getLista().get(6);
            case BICOND:
                return this.getLista().get(7);
            case CP:
                return this.getLista().get(8);
            case EI:
                return this.getLista().get(9);

        }
        return "";

    }
}
