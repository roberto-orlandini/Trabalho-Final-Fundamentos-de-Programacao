/***********************************************
 *       Trabalho Final - Matrizes em Java
 * 
 *          Roberto Bortolan Orlandini
 *        
 * Fundamentos de Programação - Prof. Filipo Mor
 ***********************************************/
 
import java.util.Random;
import java.util.Scanner;
public class Main
{   
    public static void MostraMatriz(char[][] M)
    {
        // itera por toda linha e coluna, imprimindo seu valor
        for (int i = 0; i < M.length; i++)
        {
            for (int j = 0; j < M[i].length; j++)
            {
                System.out.printf(" %c ", M[i][j]);
            }
            System.out.printf("\n");
        }
    }
    
    public static char[][] DesenhaCaminho(char[][] M, Ponto origem, Ponto destino, char simbolo)
    {
        // calcula as distancias a serem percorridas (positivas ou negativas)
        double distY = destino.getY() - origem.getY();
        double distX = destino.getX() - origem.getX();
        // calcula o deslocamento de cada iteração (distancias / modulo da distancia total)
        double distTotal = Math.abs(distY) + Math.abs(distX);
        double deslocY = distY / distTotal;
        double deslocX = distX / distTotal;
        
        // inicializa as posições que percorrerão a matriz
        double posY = origem.getY();
        double posX = origem.getX();
        for(int avanco = 0; avanco < distTotal; avanco++)
        {
            // desenha o caminho na matriz, não sobrescrevendo os Pontos
            if (M[(int)posY][(int)posX] == '.')
            {
                M[(int)posY][(int)posX] = simbolo;
            }
            
            // faz o incremento das posições
            posY += deslocY;
            posX += deslocX;
        }
        return M;
    }
    public static void main(String[] args)
    {
        Random random = new Random();
        Scanner teclado = new Scanner(System.in);
        
        // Solicita a dimensão da matriz
        int COLUNAS = 0; int LINHAS = 0;     
        
        while (LINHAS < 3)
        {
            System.out.print("Informe o número de linhas (mín. 3): ");
            LINHAS = teclado.nextInt();
        }
        
        while (COLUNAS < 3)
        {
            System.out.print("Informe o número de colunas (mín. 3): ");
            COLUNAS = teclado.nextInt();
        }
        
        // Cria a matriz
        char[][] M = new char[LINHAS][COLUNAS];
        // Popula a matriz com .
        for (int i = 0; i < M.length; i++)
        {
            for (int j = 0; j < M[i].length; j++)
            {
                M[i][j] = '.';
            }
        }
        
        // Cria um vetor para nomear os pontos
        char[] nomePts = {'A', 'B', 'C', 'D'};
        // Cria um vetor de Pontos com 4 posições
        int NUM = 4;
        Ponto[] pontos = new Ponto[NUM];
        
        // Popula o vetor com 4 Pontos
        for (int i = 0; i < NUM; i++)
        {
            // inicializa as coordenadas dos pontos
            int coordX = 0; int coordY = 0;
            
            // loop que gera coordenadas até que sejam diferentes das já existentes
            boolean gerarCoord = true;
            while (gerarCoord)
            {
                // gera valores aleatórios para as coordenadas
                coordX = random.nextInt(COLUNAS);
                coordY = random.nextInt(LINHAS);
                
                gerarCoord = false;
                // percorre cada Ponto anterior no vetor
                for (int j = 0; j < i; j++)
                {
                    // se as coordenadas forem iguais, mantém o bloco while em loop
                    if(pontos[j].getX() == coordX && pontos[j].getY() == coordY)
                    {
                        gerarCoord = true;
                        break;
                    }
                }
            }
            
            // popula a posição i do vetor
            pontos[i] = new Ponto(coordX, coordY);
            System.out.printf("::: Ponto%c: L=%d, C=%d\n", nomePts[i], coordY, coordX);
            // popula a matriz com o rótulo do ponto na posição gerada
            M[coordY][coordX] = nomePts[i];
        }
        System.out.printf("::: >>> Nenhum ponto com coordenadas repetidas! <<<\n\n");
        
        // chama a função para desenhar o caminho entre os pontos
        // une os pontos A e B com +
        M = DesenhaCaminho(M, pontos[0], pontos[1], '+');
        // une os pontos C e D com *
        M = DesenhaCaminho(M, pontos[2], pontos[3], '*');
        
        // imprime a matriz em tela
        MostraMatriz(M);
    }
}
