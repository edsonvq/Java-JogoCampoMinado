package com.github.edsonvq.campominado;

import com.github.edsonvq.campominado.models.Table;
import com.github.edsonvq.campominado.view.TableConsole;

/**
 *
 * @author Edson
 */
public class Main {
    public static void main(String[] args) {

        Table tabuleiro = new Table(6, 6, 6);
        new TableConsole(tabuleiro);

    }
}
