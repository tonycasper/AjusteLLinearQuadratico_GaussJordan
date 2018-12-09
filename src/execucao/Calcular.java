package execucao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calcular {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numeroElementos;

		iniciarCalculo();
	}

	private static void iniciarCalculo() {

		int numeroN = 0;
		double opcao = 0;
		// variavel auxiliar para separar x de y
		Boolean variavelX = true;

		// Array das variaveis x
		List<ModelVar> elementosX = new ArrayList<ModelVar>();
		// Array das variaveis y
		List<ModelVar> elementosY = new ArrayList<ModelVar>();
		// prepara pra ler a variavel de entrada
		Scanner sc = new Scanner(System.in);

		
		//inicia o looping infinito
		do {
			System.out.println("###############################");
			System.out.println("## Digite o valor dos pontos    ##");
			System.out.println("## da coordenada nasequencia:   ##");
			System.out.println("## N, x_1, y_1 ... x_n, y_n     ##");
			System.out.println("## Apertando o enter após       ##");
			System.out.println("## digitar cada numero da coordenada.");
			System.out.print("Digite os valores em seguida do enter: ");			
			
			if (numeroN == 0) {
				//recebe o numero N
				numeroN = Integer.parseInt(sc.nextLine());
				System.out.println();
				continue;
				
			//verifica se é uma variavel do tipo x ou y
			} else if (variavelX) {		
				//instancia um objeto do tipo x para armazenar na lista
				ModelVar x = new ModelVar();
				//captura o objeto 
				x.valor = Double.parseDouble(sc.nextLine());
				x.tipo = "x";
				elementosX.add(x);
				System.out.println();
				variavelX = false;
				continue;
				
			} else {			
				//instancia um objeto do tipo y e armazena numa lista 
				ModelVar y = new ModelVar();
				y.tipo = "y";
				y.valor = Double.parseDouble(sc.nextLine());
				//armazena o objeto na lista 
				elementosY.add(y);
				System.out.println();
				if(elementosY.size() >= numeroN) {
					calcular(numeroN,elementosX,elementosY);
				}
				variavelX = true;
				continue;
			}
		} while (true);
	}

	public static void calcular(int n, List<ModelVar> x, List<ModelVar> y) {
		//instancia dos valores do sistema linear
		double somatoriaX = 0;
		double somatoriaY = 0;
		double somatoriaQuaX = 0;
		double xVsY = 0;
		
		double escaloL2_2 = 0;
		double escaloL2_3 = 0;
		double escaloL2_1 = 0;
		
		double coeAngular = 0;
		double coeLinear = 0;
		
		for (int i = 0; i < x.size(); i++) {			
			somatoriaX += x.get(i).valor;
		}
		
		for (int i = 0; i < y.size(); i++) {
			somatoriaY+= y.get(i).valor;
		}
		for (int i = 0; i < y.size(); i++) {
			somatoriaQuaX += Math.pow(x.get(i).valor,2);
		}
		for (int i = 0; i < x.size(); i++) {
			xVsY += x.get(i).valor * y.get(i).valor;
		}
		
		//faz o escalonamento (poderia ter separado em um metodo)
		double m1 = somatoriaX/n; 
		
		escaloL2_1 = somatoriaX - (n*m1);
		
		escaloL2_2 = somatoriaQuaX - (somatoriaX*m1);
		
		escaloL2_3 = xVsY - (somatoriaY*m1);
		
		//calcula o coeficiente angular
		coeAngular = escaloL2_3/escaloL2_2;
		
		//calcula o coeficiente linear
		coeLinear = (somatoriaY - somatoriaX*coeLinear)/n;
		
		System.out.println("###### Matriz Aumentada #######");
		System.out.println("###############################");
		System.out.println("##"+n+" | "+somatoriaX+" | "+somatoriaY+"##");
		System.out.println("##"+somatoriaX+" | "+somatoriaQuaX+" | "+xVsY+"##");
		System.out.println("#################################");
		System.out.println("");
		System.out.println("");
		
		System.out.println("###### Matriz Escalonada #######");
		System.out.println("###############################");
		System.out.println("##"+n+" | "+somatoriaX+" | "+somatoriaY+"##");
		System.out.println("##"+0+" | "+escaloL2_2+" | "+escaloL2_3+"##");
		System.out.println("#################################");		
		System.out.println("");
		System.out.println("");
		
		System.out.println("######## Coeficientes #########");
		System.out.println("###############################");
		System.out.println("###O Coeficiente Angular e igual a:###");
		System.out.println("##########"+coeAngular+"##########");
		System.out.println("###O Coeficiente Linear e igual a:###");	
		System.out.println("###########"+coeLinear+"###########");	
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

	}

}
