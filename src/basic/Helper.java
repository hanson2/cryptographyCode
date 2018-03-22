package basic;

import java.util.ArrayList;

public class Helper {

public static ArrayList<Integer> toIntArray(String temp) {
		
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
	public static String toString(ArrayList<Integer> temp){
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
	public static double[] a0generator(){
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
