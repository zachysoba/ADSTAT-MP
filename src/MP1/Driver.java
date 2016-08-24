package MP1;

import java.util.Scanner;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Driver {
	
	public static void main(String[]args){
		
		Scanner sc1 = null;
		int opt, choice;
		double a,b,num, errorToleranceValue;
		String quadEq;
		
		System.out.println("Enter quadratic equations (use only x variable) :");
		sc1 = new Scanner(System.in);
		quadEq = sc1.nextLine();
		
		System.out.println("Non linear equation bisection");
		System.out.println("[0] - Bisection");
		System.out.println("[1] - False Position");
		// [n] - example if you want to add other options
		do{
			sc1 = new Scanner(System.in);
			opt = sc1.nextInt();
		}while(opt < 0 || opt > 1);
		
		if(opt == 0 || opt == 1){
			System.out.println("1st Value");
			sc1 = new Scanner(System.in);
			a = sc1.nextDouble();
			
			System.out.println("2nd Value");
			sc1 = new Scanner(System.in);
			b = sc1.nextDouble();
			
			double temp;
			
			if(a > b){
				temp = a;
				a = b;
				b = temp;
			}
			
			System.out.println("By Iterations[0] or Error Tolerance Value[1]");
			sc1 = new Scanner(System.in);
			choice = sc1.nextInt();
			
			if(choice == 0){
				System.out.println("How many iteratations:");
				sc1 = new Scanner(System.in);
				num = sc1.nextDouble();
				
				if( opt == 0)
					toBisection(a,b,num,quadEq);
				else if( opt == 1)
					toFalsePosition(a,b,num,quadEq);
			} else if(choice == 1){
				System.out.println("Enter Error Tolerance Value:");
				sc1 = new Scanner(System.in);
				errorToleranceValue = sc1.nextDouble();
				
				if( opt == 0)
					toBisectionET(a,b,errorToleranceValue,quadEq);
				else if( opt == 1)
					toFalsePositionET(a,b,errorToleranceValue,quadEq);
			}
				
			
		}//else if(opt == n){} ... //if you added other options
	}

	private static void toFalsePosition(double a, double b, double num, String quadEq) {
		double fA, fB, p, fP, errorFP = 0;
		double pFP[] = new double[2];

		System.out.println("False Position");
		System.out.println("# ||  a  |  b  |  p  | f(p) | error");
		
			for(int ctr1 = 0; ctr1<num; ctr1++){
				
				fA = toEquation(a, quadEq);
				fB = toEquation(b, quadEq);
				
				System.out.println("fA: "+fA+" | fB: "+fB);
				
				p = a - ((fA * (b - a)) / (fB - fA));
				
				if(ctr1 > 0)
					pFP[0] = pFP[1];
				
				pFP[1] = p;
				
				if(ctr1 > 0){
					errorFP = pFP[1] - pFP[0];
					if(errorFP < 0){
						errorFP = errorFP * -1;
					}
				}
				
				fP = toEquation(p, quadEq);
				
				if(ctr1 == 0)
					System.out.println((ctr1+1)+" || "+a+" | "+b+" | "+p+" | "+fP+"  | ");
				else
					System.out.println((ctr1+1)+" || "+a+" | "+b+" | "+p+" | "+fP+"  | "+errorFP);
				
				if(fP < 0){
					a = p;
				}else if(fP > 0){
					b = p;
				}
			}
	}

	private static void toBisection(double a, double b, double num, String quadEq) {
		double p,fP, errorTB;
		
		System.out.println("Bisection");
		System.out.println("# ||  a  |  b  |  p  | f(p) | error");
		
		for(int ctr1 = 0; ctr1<num; ctr1++){
			p = (a + b)/2;
			
			errorTB = b - a;
			if(errorTB < 0){
				errorTB = errorTB * -1;
			}
			
			fP = toEquation(p, quadEq);
			
			System.out.println((ctr1+1)+" || "+a+" | "+b+" | "+p+" | "+fP+"  | "+errorTB);
			
			if(fP < 0){
				a = p;
			}else if(fP > 0){
				b = p;
			}
		}
	}
	
	private static void toFalsePositionET(double a, double b, double num, String quadEq) {
		double fA, fB, p, fP, errorFP = 1;
		double pFP[] = new double[2];
		System.out.println("right way");

		System.out.println("False Position");
		System.out.println("# ||  a  |  b  |  p  | f(p) | error");
		
		int ctr1 = 0;
		
		do{
				
				fA = toEquation(a, quadEq);
				fB = toEquation(b, quadEq);
				
				System.out.println("fA: "+fA+" | fB: "+fB);
				
				p = a - ((fA * (b - a)) / (fB - fA));
				
				if(ctr1 > 0)
					pFP[0] = pFP[1];
				
				pFP[1] = p;
				
				if(ctr1 > 0){
					errorFP = Math.abs(pFP[1] - pFP[0]);
					if(errorFP < 0){
						errorFP = Math.abs(errorFP * -1);
					}
				}
				
				fP = toEquation(p, quadEq);
				
				if(ctr1 == 0)
					System.out.println((ctr1+1)+" || "+a+" | "+b+" | "+p+" | "+fP+"  | ");
				else
					System.out.println((ctr1+1)+" || "+a+" | "+b+" | "+p+" | "+fP+"  | "+errorFP);
				
				if(fP < 0){
					a = p;
				}else if(fP > 0){
					b = p;
				}
				
				ctr1++;
		}while(errorFP >= num);
	}
	
	private static void toBisectionET(double a, double b, double num, String quadEq) {
		double p,fP, errorTB;
		
		System.out.println("Bisection");
		System.out.println("# ||  a  |  b  |  p  | f(p) | error");
		
		int ctr1 = 0;
		do{
			p = (a + b)/2;
			
			errorTB = b - a;
			if(errorTB < 0){
				errorTB = errorTB * -1;
			}
			
			fP = toEquation(p, quadEq);
			
			System.out.println((ctr1+1)+" || "+a+" | "+b+" | "+p+" | "+fP+"  | "+errorTB);
			
			if(fP < 0){
				a = p;
			}else if(fP > 0){
				b = p;
			}
			
			ctr1++;
		}while(errorTB >= num);
	}

	private static double toEquation(double x, String quadEq) {//f(n)
		double fP = 0;
		Expression quadEqE;
		
		quadEqE = new ExpressionBuilder(quadEq)
				.variables("x","pi","e")
				.build()
				.setVariable("x", x)
				.setVariable("pi", Math.PI)
				.setVariable("e", Math.E);
		fP = ((Expression) quadEqE).evaluate();
		
		return fP;
	}
}
