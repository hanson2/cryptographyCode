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
			System.out.println("(S)ubstitution");
			System.out.println("(T)est shift");
			System.out.println("(F)requency Count");
			temp = scan.nextLine();
			if(temp.isEmpty()){
				notDone = false;
			}else if(temp.toLowerCase().charAt(0)== 'c'){
				System.out.println("Text to decrypt:");
				temp = scan.nextLine();
				System.out.println(caeser(temp));
			}else if(temp.toLowerCase().charAt(0)== 'a'){
				System.out.println("Text to decrypt:");
				temp = scan.nextLine();
				System.out.println(affine(temp));
			}else if(temp.toLowerCase().charAt(0)=='v'){
				System.out.println("Text to decrypt:");
				temp = scan.nextLine();
				System.out.println(viginere(temp, scan));
				
			}else if (temp.toLowerCase().charAt(0)=='s') {
				System.out.println("Not Yet Implemented");
				//temp = scan.nextLine();
				//add subsititution function
			}else if (temp.toLowerCase().charAt(0)=='t') {
				System.out.println("Text to test:");
				temp = scan.nextLine();
				for (int i = 0; i < 10; i++) {
					System.out.println(i+": "+shiftTestCollision(i, temp));
				}
			}else if(temp.toLowerCase().charAt(0)=='f'){
				System.out.println("Text to analyze:");
				temp = scan.nextLine();
				ArrayList<Integer> base = Helper.toIntArray(temp);
				double[] ans = frequencyCount(base);
				base = new ArrayList<Integer>();
				base.add(0);
				for (int i = 0; i < ans.length; i++) {
					base.set(0, i);
					System.out.println(Helper.toString(base)+ ": "+ ans[i]);
				}
				
			}
		}
	}
	@SuppressWarnings("unchecked")
	private static String viginere(String input, Scanner scan){
		//TODO: fix output?
		String temp = "";
		System.out.println("Please choose the correct length:");//change to param
		for (int i = 0; i < 26; i++) {
			System.out.println(i+": "+shiftTestCollision(i, input));
		}temp = scan.nextLine();
		int len = Integer.parseInt(temp);
		ArrayList<Integer> base = Helper.toIntArray(input);
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
		return Helper.toString(key) + ": " + Helper.toString(temp1);
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
		double[] a0=Helper.a0generator();
		ArrayList<Output> outputs = new ArrayList<Output>();
		for (int i = 1; i < 26; i++) {
			base = incrementBase(base);
			outputs.add(new Output(base, (26-i), vectorComparison(frequencyCount(base), a0)));
		}
		Collections.sort(outputs);
		return outputs.get(0);
	}

	@SuppressWarnings("unchecked")
	private static String caeser(String temp) {
		String ans = "";
		double[] a0=Helper.a0generator();
		ArrayList<Integer> base = Helper.toIntArray(temp);
		ans = printLine(0, base);
		ArrayList<Output> outputs = new ArrayList<Output>();
		for (int i = 1; i < 26; i++) {
			base = incrementBase(base);
			outputs.add(new Output(base, (26-i), vectorComparison(frequencyCount(base), a0)));
		}
		Collections.sort(outputs);
		for (int i = 0; i < 3; i++) {
			ans = ans + printLine(outputs.get(i).shift, outputs.get(i).base);
		}
		return ans;
		
		
	}
	@SuppressWarnings("unchecked")
	private static String affine(String temp){
		String ans = "";
		double[] a0=Helper.a0generator();
		ArrayList<Integer> base = Helper.toIntArray(temp);
		ArrayList<Integer> orig = Helper.toIntArray(temp);
		ArrayList<Integer> base1 = Helper.toIntArray(temp);
		ans = printLine(0, base);
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
			ans = ans + printLine(outputs.get(i).shift, outputs.get(i).base);
		}
		return ans;
		
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

	private static String printLine(int b, ArrayList<Integer> base) {
		String ans = "";
		if(b==0){
			ans = "The base is:"+'\n';
		}else{
			ans = "-"+b+'\n';
		}
		ans = ans +Helper.toString(base)+'\n';
		return ans;
	}
	
}
