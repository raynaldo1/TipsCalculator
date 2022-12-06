package github.comraynaldo1;

public class Calculadora {

    static double gorjeta (double valor, double percentual) {
        return valor * percentual / 100.0;
    }

    static double[] gorjeta (double valor) {
        double[] saida = new double[3];
        for (int i=0; i <3; i++) {
            double percentual=0;

            switch (i) {
                case 0:
                    percentual=5;
                    break;
                case 1:
                    percentual=10;
                    break;
                case 2:
                    percentual=15;
                    break;
            }

            saida[i] = gorjeta(valor, percentual);
        }
        return saida;
    }
}
