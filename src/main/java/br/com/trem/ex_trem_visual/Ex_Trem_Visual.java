package br.com.trem.ex_trem_visual;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Scanner;

public class Ex_Trem_Visual extends Application {

    // Definição das variáveis para os trens
    double posicao_inicial_a;
    double posicao_inicial_b;
    double velocidade_a;
    double velocidade_b;
    double tempo_colisao;
    double posicao_colisao;
    int minutos;
    int segundos;
    int horas;
    String horario;

    // Definindo o tamanho da tela
    final double width = 1000;
    final double height = 400;

    // Definindo as representações dos trens
    Rectangle tremA, tremB, corpoA, corpoB;
    Circle rodaA1, rodaA2, rodaB1, rodaB2;
    Text infoColisao;

    public static void main(String[] args) {
        launch(args);
    }

    @Override //Subscreve a classe
    public void start(Stage primaryStage) {

        // Setup do painel
        Pane pane = new Pane();

        // Setup do Scanner para ler dados do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicitar as informações do usuário
        System.out.print("Digite a posição inicial do Trem A (entre 0 e 10.000 km): ");
        posicao_inicial_a = scanner.nextDouble();
        if (posicao_inicial_a < 0 || posicao_inicial_a > 10000) {
            System.out.println("Posição inválida para o Trem A. Deve ser entre 0 e 10.000 km.");
        }

        System.out.print("Digite a velocidade do Trem A (entre 0 e 300 km/h): ");
        velocidade_a = scanner.nextDouble();
        if (velocidade_a < 0 || velocidade_a >= 300) {
            System.out.println("A velocidade do Trem A deve ser maior ou igual a 0 e menor ou igual a 300 km/h.");
        }

        System.out.print("Digite a posição inicial do Trem B (entre 0 e 10.000 km): ");
        posicao_inicial_b = scanner.nextDouble();
        if (posicao_inicial_b < 0 || posicao_inicial_b > 10000) {
            System.out.println("Posição inválida para o Trem B. Deve ser entre 0 e 10.000 km.");
        }

        System.out.print("Digite a velocidade do Trem B (entre 0 e -300 km/h): ");
        velocidade_b = scanner.nextDouble();
        if (velocidade_b > 0 || velocidade_b <= -300) {
            System.out.println("A velocidade do Trem B deve ser negativa e maior ou igual a -300 km/h.");
        }

        // Cálculo do tempo de colisão
        tempo_colisao = (posicao_inicial_b - posicao_inicial_a) / (velocidade_a - velocidade_b); // Fórmula de colisão

        if (tempo_colisao < 0) {
            System.out.println("Os trens não irão se colidir.");
            return; // Não há colisão
        }

        // Calcular a posição da colisão
        posicao_colisao = posicao_inicial_a + (velocidade_a * tempo_colisao);

        // Calcular o horário da colisão
        horas = (int) (tempo_colisao + 17); // Ambos partem as 17h
        minutos = (int) (((tempo_colisao * 3600) / 60) % 60);
        segundos = (int) ((tempo_colisao * 3600) % 60);

        // Corrigir a formatação do horário
        horario = String.format("%02d:%02d:%02d", horas, minutos, segundos);

        // Corpo do trem A
        corpoA = new Rectangle(50, 20, 100, 30); // Corpo do trem A
        corpoA.setFill(Color.RED); // Cor do Trem A
        corpoA.setArcWidth(10); // Arredondando os cantos do trem
        corpoA.setArcHeight(10); // Altura do trem A

        // Corpo do trem B
        corpoB = new Rectangle(50, 20, 100, 30); // Corpo do trem B
        corpoB.setFill(Color.BLUE); // Cor do Trem B
        corpoB.setArcWidth(10); // Arredondando os cantos do trem
        corpoB.setArcHeight(10); // Altura do trem B

        // Rodas do trem A
        rodaA1 = new Circle(0, 0, 8);
        rodaA1.setFill(Color.BLACK);
        rodaA2 = new Circle(0, 0, 8);
        rodaA2.setFill(Color.BLACK);

        // Rodas do trem B
        rodaB1 = new Circle(0, 0, 8);
        rodaB1.setFill(Color.BLACK);
        rodaB2 = new Circle(0, 0, 8);
        rodaB2.setFill(Color.BLACK);

        // Inicializando as posições
        corpoA.setX(50);  // Posição inicial do trem A
        corpoB.setX(900); // Posição inicial do trem B

        // Posicionando as rodas
        rodaA1.setCenterX(corpoA.getX() + 10);
        rodaA1.setCenterY(corpoA.getY() + corpoA.getHeight());
        rodaA2.setCenterX(corpoA.getX() + 90);
        rodaA2.setCenterY(corpoA.getY() + corpoA.getHeight());

        rodaB1.setCenterX(corpoB.getX() + 10);
        rodaB1.setCenterY(corpoB.getY() + corpoB.getHeight());
        rodaB2.setCenterX(corpoB.getX() + 90);
        rodaB2.setCenterY(corpoB.getY() + corpoB.getHeight());

        // Exibindo o texto de colisão
        infoColisao = new Text(400, 50, "");
        infoColisao.setFill(Color.BLACK);

        pane.getChildren().addAll(corpoA, corpoB, rodaA1, rodaA2, rodaB1, rodaB2, infoColisao);

        // Inicializando a Timeline para animação
        Timeline timeline = new Timeline();

        // Criando um KeyFrame para animar os trens
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), e -> {
            // Atualiza as posições dos trens
            double posA = corpoA.getX() + velocidade_a * 0.1;
            double posB = corpoB.getX() + velocidade_b * 0.1;

            corpoA.setX(posA);
            corpoB.setX(posB);

            // Atualiza as posições das rodas
            rodaA1.setCenterX(corpoA.getX() + 10);
            rodaA2.setCenterX(corpoA.getX() + 90);
            rodaB1.setCenterX(corpoB.getX() + 10);
            rodaB2.setCenterX(corpoB.getX() + 90);

            // Verifica se os trens se colidiram
            if (posA >= posB) {
                // Exibe a mensagem de colisão
                infoColisao.setText(String.format("Colisão no KM %.2f as %s", posicao_colisao, horario));
                corpoA.setFill(Color.ORANGE); // Cor de colisão
                corpoB.setFill(Color.ORANGE);
                rodaA1.setFill(Color.ORANGE);
                rodaA2.setFill(Color.ORANGE);
                rodaB1.setFill(Color.ORANGE);
                rodaB2.setFill(Color.ORANGE);
                timeline.stop(); // Para a animação
            }
        });

        // Adicionando o KeyFrame à Timeline
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE); // A animação vai repetir indefinidamente até parar
        timeline.play(); // Inicia a animação

        // Configuração da cena
        Scene scene = new Scene(pane, width, height);
        primaryStage.setTitle("Simulação de Colisão de Trens");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
