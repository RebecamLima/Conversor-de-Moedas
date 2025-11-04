package org.example;

import com.google.gson.Gson;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {


    private static void exibirHistorico(List<HistoricoRegistro> historico) {
        System.out.println("\n=== HISTÓRICO DE CONVERSÕES ===");
        if (historico.isEmpty()) {
            System.out.println("Nenhuma conversão realizada ainda.");
            return;
        }


        for (HistoricoRegistro registro : historico) {
            System.out.println(registro);
        }
        System.out.println("=================================");
    }

    public static void main(String[] args) {

        ConsultaCambio consulta = new ConsultaCambio();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;


        List<HistoricoRegistro> historicoConversoes = new ArrayList<>();

        while (opcao != 8) {
            System.out.println("\n*************************************************");
            System.out.println("Bem-vindo(a) ao Conversor de Moedas G9 ONE!");
            System.out.println("Selecione a opção de conversão:");
            System.out.println("1. USD (Dólar) -> BRL (Real)");
            System.out.println("2. BRL (Real) -> USD (Dólar)");
            System.out.println("3. EUR (Euro) -> BRL (Real)");
            System.out.println("4. BRL (Real) -> EUR (Euro)");
            System.out.println("5. USD (Dólar) -> ARS (Peso Argentino)");
            System.out.println("6. ARS (Peso Argentino) -> USD (Dólar)");
            System.out.println("7. Visualizar Histórico");
            System.out.println("8. Sair");
            System.out.println("*************************************************");
            System.out.print("Escolha uma opção (1-8): ");

            try {
                opcao = scanner.nextInt();

                if (opcao >= 1 && opcao <= 6) {
                    // Lógica de Conversão
                    String moedaBase = "";
                    String moedaAlvo = "";

                    switch (opcao) {
                        case 1: moedaBase = "USD"; moedaAlvo = "BRL"; break;
                        case 2: moedaBase = "BRL"; moedaAlvo = "USD"; break;
                        case 3: moedaBase = "EUR"; moedaAlvo = "BRL"; break;
                        case 4: moedaBase = "BRL"; moedaAlvo = "EUR"; break;
                        case 5: moedaBase = "USD"; moedaAlvo = "ARS"; break;
                        case 6: moedaBase = "ARS"; moedaAlvo = "USD"; break;
                    }

                    System.out.print("Digite o valor a ser convertido de " + moedaBase + " para " + moedaAlvo + ": ");
                    double valorParaConverter = scanner.nextDouble();

                    String jsonResposta = consulta.buscaTaxa(moedaBase, moedaAlvo);

                    Gson gson = new Gson();
                    TaxaDeCambio taxaObjeto = gson.fromJson(jsonResposta, TaxaDeCambio.class);
                    double taxa = taxaObjeto.getConversionRate();

                    double valorConvertido = valorParaConverter * taxa;

                    System.out.println("\n--- RESULTADO ---");
                    System.out.println("Valor Original: " + String.format("%.2f", valorParaConverter) + " " + moedaBase);
                    System.out.println("Taxa Aplicada: " + taxa);
                    System.out.println("Valor Convertido: " + String.format("%.2f", valorConvertido) + " " + moedaAlvo);
                    System.out.println("-----------------");

                    historicoConversoes.add(
                            new HistoricoRegistro(
                                    moedaBase + " -> " + moedaAlvo,
                                    valorParaConverter,
                                    valorConvertido
                            )
                    );

                } else if (opcao == 7) {

                    exibirHistorico(historicoConversoes);

                } else if (opcao == 8) {
                    System.out.println("Obrigado por usar o Conversor de Moedas! Encerrando...");
                } else {
                    System.out.println("Opção inválida. Por favor, escolha um número entre 1 e 8.");
                }

            } catch (java.util.InputMismatchException e) {
                System.err.println("\n⚠️ Entrada inválida. Por favor, digite apenas números.");
                scanner.next();
            } catch (RuntimeException e) {
                System.err.println("\n❌ Erro durante a conversão: " + e.getMessage());
            }
        }

        scanner.close();
    }
}