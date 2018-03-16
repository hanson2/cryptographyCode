package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Substitution {

	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean notDone = true;
		String temp;
		
		while(notDone){
			System.out.println("Please what you want to do");
			System.out.println("(C)easar/Shift cypher");
			System.out.println("(A)ffine cypher");
			System.out.println("(V)iginere cypher");
			System.out.println("(S)hift test");
			System.out.println("(F)requency Count");
			System.out.println("(T)emp");
			temp = scan.nextLine();
			if(temp.isEmpty()){
				notDone = false;
			}else if(temp.toLowerCase().charAt(0)== 'c'){
				System.out.println("Text to decrypt:");
				temp = scan.nextLine();
				caeser(temp);//refactor later
			}else if(temp.toLowerCase().charAt(0)== 'a'){
				System.out.println("Text to decrypt:");
				temp = scan.nextLine();
				affine(temp);//refactor later
			}else if(temp.toLowerCase().charAt(0)=='v'){
				System.out.println("Text to decrypt:");
				temp = scan.nextLine();
				System.out.println(viginere(temp, scan));
				
			}else if (temp.toLowerCase().charAt(0)=='s') {
				System.out.println("Text to test:");
				temp = scan.nextLine();
				for (int i = 0; i < 10; i++) {
					System.out.println(i+": "+shiftTestCollision(i, temp));
				}
			}else if(temp.toLowerCase().charAt(0)=='f'){
				System.out.println("Text to analyze:");
				temp = scan.nextLine();
				ArrayList<Integer> base = toIntArray(temp);
				double[] ans = frequencyCount(base);
				base = new ArrayList<Integer>();
				base.add(0);
				for (int i = 0; i < ans.length; i++) {
					base.set(0, i);
					System.out.println(toString(base)+ ": "+ ans[i]);
				}
				
			}else if(temp.toLowerCase().charAt(0)=='t'){
				System.out.println(tempFunction(0));
				System.out.println(tempFunction(1));
				System.out.println(tempFunction(2));
				System.out.println(tempFunction(-1));
				System.out.println(tempFunction(46));
				System.out.println(tempFunction(47));
			}
		}
		}
	private static int tempFunction(int fib) {
		// TODO broken
		int ans;
		if (fib<0) {
			return -100;
		}else if(fib==0){
			return 0;
		}else if(fib == 1){
			return 1;
		}else{
			ans=tempFunction(fib-1)+tempFunction(fib-2);
			if (ans<0){
				return -100;
			}else{
				return ans;
			}
		}
	}
		
	@SuppressWarnings("unchecked")
	private static String viginere(String input, Scanner scan){
		//TODO: fix output?
		String temp = "";
		System.out.println("Please choose the correct length:");
		for (int i = 0; i < 26; i++) {
			System.out.println(i+": "+shiftTestCollision(i, input));
		}temp = scan.nextLine();
		int len = Integer.parseInt(temp);
		ArrayList<Integer> base = toIntArray(input);
		//System.out.println(base);
		ArrayList<Integer>[] arrayLists = new ArrayList[len];
		ArrayList<Integer> temp1 = new ArrayList<Integer>();
		for (int i = 0; i < arrayLists.length; i++) {
			arrayLists[i]= new ArrayList<Integer>();
		}
		for (int i = 0; i < base.size(); i++) {
			arrayLists[i%len].add(base.get(i));
		}
		Output[] outputs = new Output[arrayLists.length];
		for (int i = 0; i < arrayLists.length; i++) {//replace with array of Outputs? to get code
			outputs[i]=caeserInt(arrayLists[i]);
		}
		int len1 = outputs[0].base.size();
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < arrayLists.length; j++) {
				if (i<outputs[j].base.size()) {
					temp1.add(outputs[j].base.get(i));	
				}
			}
		}
		ArrayList<Integer> key = new ArrayList<Integer>();
		for (int i = 0; i < outputs.length; i++) {
			key.add(outputs[i].shift);
		}
		return toString(key) + ": " + toString(temp1);
	}
	
	private static int shiftTestCollision(int shift, String input){
		int ans = 0;
		String temp="";
		for (int i = 0; i < shift; i++) {
			temp+=" ";
		}
		temp+=input;
		for (int i = 0; i<input.length();i++){
			if (input.charAt(i)==temp.charAt(i)) {
				ans++;
			}
		}
		return ans;
	}
	
	
	
	
	private static ArrayList<Integer> multiplyBase(ArrayList<Integer> base, int i) {
		// TODO Auto-generated method stub
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (Integer integer : base) {
			ans.add((integer*i)%26);
		}
		return ans;
	}

	private static double[] frequencyCount(ArrayList<Integer> base){
		int len = base.size();
		double[] ans = new double[26];
		for (int i = 0; i < ans.length; i++) {
			ans[i]=0;//instatiate the vector
		}
		for (Integer val : base) {
			ans[val] = (ans[val]*len + 1)/len;
		}
		return ans;
	}

	
	private static double vectorComparison(double[] v1, double[] v2){
		double ans=0;
		if (v1.length == v2.length) {
			for (int i = 0; i < v2.length; i++) {
				ans+= v1[i] * v2[i];
			}
			
		}
		return ans;
	}
	
	@SuppressWarnings("unchecked")
	private static Output caeserInt(ArrayList<Integer> base) {
		double[] a0=a0generator();
		ArrayList<Output> outputs = new ArrayList<Output>();
		for (int i = 1; i < 26; i++) {
			base = incrementBase(base);
			outputs.add(new Output(base, (26-i), vectorComparison(frequencyCount(base), a0)));
		}
		Collections.sort(outputs);
		return outputs.get(0);
	}

	@SuppressWarnings("unchecked")
	private static void caeser(String temp) {
		double[] a0=a0generator();
		ArrayList<Integer> base = toIntArray(temp);
		printLine(0, base);
		ArrayList<Output> outputs = new ArrayList<Output>();
		for (int i = 1; i < 26; i++) {
			base = incrementBase(base);
			outputs.add(new Output(base, (26-i), vectorComparison(frequencyCount(base), a0)));
		}
		Collections.sort(outputs);
		for (int i = 0; i < 3; i++) {
			printLine(outputs.get(i).shift, outputs.get(i).base);
		}
		
		
	}
	@SuppressWarnings("unchecked")
	private static void affine(String temp){
		double[] a0=a0generator();
		ArrayList<Integer> base = toIntArray(temp);
		ArrayList<Integer> orig = toIntArray(temp);
		ArrayList<Integer> base1 = toIntArray(temp);
		printLine(0, base);
		ArrayList<Output> outputs = new ArrayList<Output>();
		int k = 0;
		for (int j = 0; j < 13; j++) {
			k = 2 * j + 1;
			base = orig;
			if (k != 13) {
				for (int i = 1; i < 26; i++) {
					base = base1;
					base = incrementBase(base);
					base1 = base;
					base = multiplyBase(base, k);
					outputs.add(new Output(base, (k * 100) + (26-i), vectorComparison(frequencyCount(base), a0)));
				}
			}
		}
		Collections.sort(outputs);
		for (int i = 0; i < 7; i++) {
			printLine(outputs.get(i).shift, outputs.get(i).base);
		}
		
	}


	private static ArrayList<Integer> incrementBase(ArrayList<Integer> base) {
		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (Integer val : base) {
			if(val<0){
				ans.add(val);
			}else{
				ans.add((val+1)%26);				
			}
		}
		return ans;
	}

	private static void printLine(int b, ArrayList<Integer> base) {
		
		if(b==0){
			System.out.println("The base is");
		}else{
			System.out.println("-"+b);
		}
		System.out.println(toString(base));
	}

	private static ArrayList<Integer> toIntArray(String temp) {
		
		ArrayList<Integer> base = new ArrayList<Integer>();
		int len = temp.length();
		for (int i = 0; i < len; i++) {
			switch(temp.toLowerCase().charAt(i)){
				case 'a': base.add(0);
				break;
				case 'b': base.add(1);
				break;
				case 'c': base.add(2);
				break;
				case 'd': base.add(3);
				break;
				case 'e': base.add(4);
				break;
				case 'f': base.add(5);
				break;
				case 'g': base.add(6);
				break;
				case 'h': base.add(7);
				break;
				case 'i': base.add(8);
				break;
				case 'j': base.add(9);
				break;
				case 'k': base.add(10);
				break;
				case 'l': base.add(11);
				break;
				case 'm': base.add(12);
				break;
				case 'n': base.add(13);
				break;
				case 'o': base.add(14);
				break;
				case 'p': base.add(15);
				break;
				case 'q': base.add(16);
				break;
				case 'r': base.add(17);
				break;
				case 's': base.add(18);
				break;
				case 't': base.add(19);
				break;
				case 'u': base.add(20);
				break;
				case 'v': base.add(21);
				break;
				case 'w': base.add(22);
				break;
				case 'x': base.add(23);
				break;
				case 'y': base.add(24);
				break;
				case 'z': base.add(25);
				break;
								
			}
		}return base;
	}
	private static String toString(ArrayList<Integer> temp){
		String ans = "";
		for (Integer val : temp) {
		  switch(val){
		  	case 0: ans+="a";
			break;
			case 1: ans+="b";
			break;
			case 2: ans+="c";
			break;
			case 3: ans+="d";
			break;
			case 4: ans+="e";
			break;
			case 5: ans+="f";
			break;
			case 6: ans+="g";
			break;
			case 7: ans+="h";
			break;
			case 8: ans+="i";
			break;
			case 9: ans+="j";
			break;
			case 10: ans+="k";
			break;
			case 11: ans+="l";
			break;
			case 12: ans+="m";
			break;
			case 13: ans+="n";
			break;
			case 14: ans+="o";
			break;
			case 15: ans+="p";
			break;
			case 16: ans+="q";
			break;
			case 17: ans+="r";
			break;
			case 18: ans+="s";
			break;
			case 19: ans+="t";
			break;
			case 20: ans+="u";
			break;
			case 21: ans+="v";
			break;
			case 22: ans+="w";
			break;
			case 23: ans+="x";
			break;
			case 24: ans+="y";
			break;
			case 25: ans+="z";
			break;
			
		  }
		}
		
		return ans;
		
	}
	private static double[] a0generator(){
		double[] ans = new double[26];
		ans[0] = .08167;
		ans[1] = .01492;
		ans[2] = .02782;
		ans[3] = .04253;
		ans[4] = .12702;
		ans[5] = .02228;
		ans[6] = .02015;
		ans[7] = .06094;
		ans[8] = .06966;
		ans[9] = .00153;
		ans[10] = .00772;
		ans[11] = .04025;
		ans[12] = .02406;
		ans[13] = .06749;
		ans[14] = .07507;
		ans[15] = .01929;
		ans[16] = .00095;
		ans[17] = .05987;
		ans[18] = .06327;
		ans[19] = .09056;
		ans[20] = .02758;
		ans[21] = .00978;
		ans[22] = .02360;
		ans[23] = .00150;
		ans[24] = .01974;
		ans[25] = .00074;
		return ans;
	}
}
