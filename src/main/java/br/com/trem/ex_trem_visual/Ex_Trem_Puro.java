package aula07;

import java.util.InputMismatchException;
import java.util.Scanner;

//Exercicio de Trem - Lucas Duarte Bareli DS13

public class Ex_Trem_Puro {
    public static void main(String[] args) {
        // Definição das variáveis para os trens
        double posicao_inicial_a;
        double posicao_inicial_b;
        double velocidade_a;
        double velocidade_b;
        double tempo;
        int minutos;
        int segundos;
        int horas;
        String opcao;
        String horario = "";

        Scanner scanner = new Scanner(System.in);

        // Primeira pergunta para o usuário
        System.out.print("Você deseja descobrir sobre os Trens A e B? (sim/nao): ");
        opcao = scanner.nextLine();

        // Se o usuário digitar "nao", encerra o programa imediatamente
        if (opcao.equalsIgnoreCase("nao")) {
            System.out.println("Não quer descobrir sobre os trens.\n" + "PESOU O CLIMA!\n");
            System.out.println("FIM DO PROGRAMA");
            return; // Sai do programa (;-;)
        }

        // Caso o usuário tenha respondido "sim", entra no loop para calcular a colisão
        while (true) {
            try {
                // POSIÇÃO INICIAL DO TREM A
                System.out.print("Digite a posição inicial do Trem A (entre 0 e 10.000 km): ");
                posicao_inicial_a = scanner.nextDouble();
                if (posicao_inicial_a < 0 || posicao_inicial_a > 10000) {
                    System.out.println("Posição inválida para o Trem A. Deve ser entre 0 e 10.000 km.");
                    continue; // Volta para o início do loop
                }

                // VELOCIDADE DO TREM A
                System.out.print("Digite a velocidade do Trem A (entre 0 e 300 km/h): ");
                velocidade_a = scanner.nextDouble();
                if (velocidade_a < 0 || velocidade_a >= 300) {
                    System.out.println("A velocidade do Trem A deve ser maior ou igual a 0 e menor ou igual a 300 km/h.");
                    continue;
                }

                // POSIÇÃO INICIAL DO TREM B
                System.out.print("Digite a posição inicial do Trem B (entre 0 e 10.000 km): ");
                posicao_inicial_b = scanner.nextDouble();
                if (posicao_inicial_b < 0 || posicao_inicial_b > 10000) {
                    System.out.println("Posição inválida para o Trem B. Deve ser entre 0 e 10.000 km.");
                    continue; // Volta para o início do loop
                }

                // VELOCIDADE DO TREM B
                System.out.print("Digite a velocidade do Trem B (entre 0 e -300 km/h): ");
                velocidade_b = scanner.nextDouble();
                if (velocidade_b > 0 || velocidade_b <= -300) {
                    System.out.println("A velocidade do Trem B deve ser negativa e maior ou igual a -300 km/h.");
                    continue;
                }

                // Se ambos os trens estão parados
                if (velocidade_a == 0 && velocidade_b == 0) {
                    System.out.println("Os trens estão parados e não irão se colidir.");
                    continue;
                }

                // Cálculo do tempo de colisão
                tempo = (posicao_inicial_a - posicao_inicial_b) / (velocidade_b - velocidade_a); // Fórmula de colisão
                if (tempo < 0) {
                    System.out.println("Os trens não irão se colidir.");
                    continue; // Não há colisão
                }

                // Calcular a posição da colisão
                double posicao_colisao = posicao_inicial_a + (velocidade_a * tempo);

                // Calcular o horário da colisão
                horas = (int) (tempo + 17); // Ambos partem as 17h
                minutos = (int) (((tempo * 3600)/60) % 60);
                segundos = (int) ((tempo * 3600) % 60);

                // Corrigir a formatação do horário
                horario = String.format("%02d:%02d:%02d", horas, minutos, segundos);

                // Exibe a hora e a posição da colisão
                System.out.printf("\nA colisão de trens acontecerá no KM %2.0f e ocorrerá " +
                        "após %2.0f segundos no horario de %s", posicao_colisao, tempo * 3600, horario);

                // Perguntar se deseja realizar outra simulação
                System.out.print("\nDeseja realizar outra simulação? (sim/nao): ");
                opcao = scanner.next();
                if (opcao.equalsIgnoreCase("nao")) {
                    break; // Sai do loop
                }

            } catch (InputMismatchException e) {
                System.out.println("\nVocê digitou um caractere inválido." +
                        "\nPor favor, tente novamente: ");
                scanner.nextLine();  // Limpa o buffer de entrada
            }
        }

        System.out.println("FIM DO PROGRAMA"); // Aqui pesou muito...
    }
}
