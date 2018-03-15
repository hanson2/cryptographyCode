package basic;

import java.util.ArrayList;

public class Output implements Comparable{
	public ArrayList<Integer> base;
	public int shift;
	public double correctness;
	
	public Output(ArrayList<Integer> base, int shift, double correctness){
		this.base = base;
		this.shift = shift;
		this.correctness = correctness;
	}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		
		return (int) ((((Output)arg0).getCorrectness()-this.correctness)*1000);
		//return (int) ((this.correctness-((Output)arg0).getCorrectness())*1000);
	}
	public double getCorrectness(){
		return this.correctness;
	}

}


