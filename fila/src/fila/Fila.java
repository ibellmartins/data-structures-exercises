package fila;
import java.util.Random;

class Fila {
    private int[] clientes;
    private int frente;
    private int ultimo;
    private int capacidade;
    private int tamanho;

    public Fila(int capacidade) { 
        this.capacidade = capacidade;
        clientes = new int[capacidade];
        frente = 0;
        ultimo = -1;
        tamanho = 0;
    }

    public void enqueue(int cliente) {
        if (tamanho < capacidade) {
        	ultimo = (ultimo + 1) % capacidade;
        	clientes[ultimo] = cliente;
            tamanho++;
        }
    }

    public Integer dequeue() { 
        if (tamanho > 0) {
            int cliente = clientes[frente];
            frente = (frente + 1) % capacidade;
            tamanho--;
            return cliente;
        } else {
        	return null; 
        }
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int size() {
        return tamanho;
    }
    
    public static void main(String[] args) {
        int[] minsTeste = {30, 60, 120, 480};

        for (int n : minsTeste) {
        	executarFila(n);
        }
    }

    public static void executarFila(int n) {
        Fila fila = new Fila(n * 2); 
        Random random = new Random();
        
        int atendidos = 0;
        int totalEspera = 0;
        int maiorEspera = 0;

        for (int minuto = 0; minuto < n; minuto++) {
            if (!fila.isEmpty()) {
                int tempoEntrada = fila.dequeue();
                int tempoEspera = minuto - tempoEntrada;
                totalEspera += tempoEspera;
                maiorEspera = Math.max(maiorEspera, tempoEspera);
                atendidos++;
            }

            int k = random.nextInt(3); 
            if (k == 1) {
                fila.enqueue(minuto); 
            } else if (k == 2) {
                fila.enqueue(minuto); 
                fila.enqueue(minuto); 
            }
        }

        double mediaTempo; 
        if (atendidos > 0) {
        	mediaTempo = (double) totalEspera / atendidos;
        } else {
        	mediaTempo = 0;
        }

        System.out.printf("minutos: %d%n", n);
        System.out.printf("clientes atendidos: %d%n", atendidos);
        System.out.printf("tempo médio de espera: %.2f minutos%n", mediaTempo);
        System.out.printf("maior tempo de espera: %d minutos%n%n", maiorEspera);
    }
}
