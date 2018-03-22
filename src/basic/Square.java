package basic;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Square {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean notDone = true;
		String temp;
		
		while(notDone){
			System.out.println("Please what you want to do");
			System.out.println("(P)layfair Cipher");
			temp = scan.nextLine();
			temp = temp.toLowerCase();
			if(temp.isEmpty()){
				notDone = false;
			}else if(temp.charAt(0) == 'p'){
				System.out.println("Please enter code to decypher:");
				temp = scan.nextLine();
				System.out.println("Please enter the password:");
				String key = scan.nextLine();
				System.out.println(playFair(temp, key));
			}
			
		}
	}
	
	private static String playFair(String input, String password) {//TODO: how do I get numbers out?
		String ans = "";
		
		char[][]key = generateKey(password);
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				System.out.println(key[i][j]);
			}
		}
		
		for (int k = 0; k < password.length(); k++) {
			int[] loc1 = findLocation(key, password.charAt(k));
			int[] loc2 = findLocation(key, password.charAt(k+1));
			if (loc1.equals(loc2)) {//same thing twice
				ans = ans + key[loc1[0]][loc1[1]]+key[loc1[0]][loc1[1]];
			}else if(loc1[0]==loc2[0]){//same colomn
				if(loc1[1]==0){
					loc1[1]=5;
				}
				if(loc2[1]==0){
					loc2[1]=5;
				}
				ans = ans + key[loc1[0]][(loc1[1]-1)]+key[loc2[0]][(loc2[1]-1)];
			}else if(loc1[1]==loc2[1]){//same row
				if(loc1[0]==0){
					loc1[0]=5;
				}
				if(loc2[0]==0){
					loc2[0]=5;
				}
				ans+=key[(loc1[0]-1)][loc1[1]]+key[(loc2[0]-1)%5][loc2[1]];
			}else{//different row and column
				ans+=key[loc1[0]][loc2[1]]+key[loc2[0]][loc1[1]];
			}
			k++;//go by 2 each cycle
		}
		return ans;
		
	}
	
	private static char[][] generateKey(String password){//TODO: fix key generation
		char[][] key = new char[5][5];
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				key[i][j] = 'a';
			}
		}
		TreeSet<Character> inputs = new TreeSet<Character>();
		int k = 0;
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				if (k<password.length()) {
					if (inputs.contains(password.charAt(k))) {
						if (password.charAt(i) == 'j') {
							key[i][j] = 'i';
							inputs.add('i');
							inputs.add('j');
						}
						else{
							key[i][j] = password.charAt(k);
						}
					}
					k++;
				}else{
					if (!inputs.contains('a')) {
						inputs.add('a');
						key[i][j]='a';
					}else if (!inputs.contains('b')) {
						inputs.add('b');
						key[i][j]='b';
					}else if (!inputs.contains('c')) {
						inputs.add('c');
						key[i][j]='c';
					}else if (!inputs.contains('d')) {
						inputs.add('d');
						key[i][j]='d';
					}else if (!inputs.contains('e')) {
						inputs.add('e');
						key[i][j]='e';
					}else if (!inputs.contains('f')) {
						inputs.add('f');
						key[i][j]='f';
					}else if (!inputs.contains('g')) {
						inputs.add('g');
						key[i][j]='g';
					}else if (!inputs.contains('h')) {
						inputs.add('h');
						key[i][j]='h';
					}else if (!inputs.contains('i')) {
						inputs.add('i');
						key[i][j]='i';
					}else if (!inputs.contains('k')) {
						inputs.add('k');
						key[i][j]='k';
					}else if (!inputs.contains('l')) {
						inputs.add('l');
						key[i][j]='l';
					}else if (!inputs.contains('m')) {
						inputs.add('m');
						key[i][j]='m';
					}else if (!inputs.contains('n')) {
						inputs.add('n');
						key[i][j]='n';
					}else if (!inputs.contains('o')) {
						inputs.add('o');
						key[i][j]='o';
					}else if (!inputs.contains('p')) {
						inputs.add('p');
						key[i][j]='p';
					}else if (!inputs.contains('q')) {
						inputs.add('q');
						key[i][j]='q';
					}else if (!inputs.contains('r')) {
						inputs.add('r');
						key[i][j]='r';
					}else if (!inputs.contains('s')) {
						inputs.add('s');
						key[i][j]='s';
					}else if (!inputs.contains('t')) {
						inputs.add('t');
						key[i][j]='t';
					}else if (!inputs.contains('u')) {
						inputs.add('u');
						key[i][j]='u';
					}else if (!inputs.contains('v')) {
						inputs.add('v');
						key[i][j]='v';
					}else if (!inputs.contains('w')) {
						inputs.add('w');
						key[i][j]='w';
					}else if (!inputs.contains('x')) {
						inputs.add('x');
						key[i][j]='x';
					}else if (!inputs.contains('y')) {
						inputs.add('y');
						key[i][j]='y';
					}else if (!inputs.contains('z')) {
						inputs.add('z');
						key[i][j]='z';
					}
				}
			}
		}
		return key;
	}
	
	private static int[] findLocation(char[][] key, char letter){
		int[] ans = new int[2];
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				if (letter == key[i][j]){
					ans[0] = i;
					ans[1] = j;
					return ans;
				}
			}
		}
		return ans;
	}
	
	
}
