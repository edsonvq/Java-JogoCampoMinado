package com.github.edsonvq.campominado.view;

import com.github.edsonvq.campominado.exeptions.ExplosionException;
import com.github.edsonvq.campominado.exeptions.ExitException;
import com.github.edsonvq.campominado.models.Table;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Edson
 */
public class TableConsole {

    private Table tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TableConsole(Table tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;

            while (continuar) {
                cicloJogo();

                System.out.println("Outra partida? (S/n) ");
                String resposta = entrada.nextLine();

                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        } catch (ExitException e) {
            System.out.println("Tchau!!");
        } finally {
            entrada.close();
        }
    }

    private void cicloJogo() {
        try {
            while (!tabuleiro.objetivoAlcacado()) {
                System.out.println(tabuleiro);
                String digitado = capturarValorDigitado("Digite (x, y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();

                digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar");

                if ("1".equals(digitado)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                } else {
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }
            }

            System.out.println(tabuleiro);
            System.out.println("Voce ganhou!!!");
        } catch (ExplosionException e) {
            System.out.println(tabuleiro);
            System.out.println("Voce perdeu!");
        }
    }

    private String capturarValorDigitado(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if ("sair".equalsIgnoreCase(digitado)) {
            throw new ExitException();
        }

        return digitado;
    }
}
