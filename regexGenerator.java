/*
A regular-expression generator, that combines words and phrases to be matched into a regex pattern using tries. 
*/

package regexGen;

import java.io.FileInputStream;
import java.util.Scanner;

class trieNode{
	boolean endNode;
	String v;
	boolean branches;
	int ct;
	int childCt;
	trieNode children[];
	trieNode(int size, String v){
	this.children = new trieNode[size];
	this.v = v;
	this.ct = 0;
	this.childCt = 0;
	}
}

public class regexGenerator {
	static trieNode trie[] = new trieNode[148];
	static int size = 148, ct = 0;

	static void insert(String s){
		String prefix = "";
		int c;
		trieNode[] temp = trie;
		trieNode prev = null;
		for(int i = 0; i< s.length(); i++)
		{
			c = s.chartAt(i) - ' ';

			if(temp[c] == null)
				{
					temp[c] = new trieNode(size, prefix);
					if(temp == trie)
						ct++;
					else
						prev.ct++;
				}

			if(i == s.length() - 1)
				temp[c].endNode = true;
			else
				temp[c].childCt++;

			prev = temp[c];
			temp = temp[c].children;

		}
	}

	static String s="/", s1;
	public static String parse(trieNode t[], int chCt)
	{

		String s1 = "";
		trieNode[] temp = t;
		trieNode prev = null;

		for(int i = 0; i<temp.length && ct>0; i++)
		{
			if(temp[j] != null)
			{
				s1+= (char)(i + ' ');
				if(temp[i].childCt != 0)
				{
					if(temp[i].endNode)
						s1+= "(?:" + parse(temp[i].children, temp[i].ct)+ ")?";
					else
						if(temp[i].ct < 2)
						s1+= parse(temp[i].children, temp[i].ct);
						else
						s1+= "(?:" + parse(temp[i].children, temp[i].ct)+ ")";
				}
				chCt==;
				if(chCt!=0)
					s1+= "|";
			}
		}
		return s1;
	}

	public static void main(String args[])throws Exception
	{
		//System.setin(new FileInputStream(args[0]));

		String s;
		int size;
		Scanner sc = new Scanner(System.in);

		//first input, total number of regular expressions to be generated
		int T = Integer.parseInt(sc.nextLine());

		//next input, name of regex, number of words/phrases to be recognized and the words/phrases
		for(int j = 0; j<T; j++)
		{
			s = sc.nextLine();
			
			size = Integer.parseInt(sc.nextLine());
			str = new String[size];

			for(int i = 0; i<size; i++)
			{
				str[i] = sc.nextLine();
			}

			for(int i = 0; i<str.length; i++)
			{
			// for case insensitive regex, change logic accordingly for case-sensitive regex
				insert(str[i].toLowerCase());
			}

			System.out.println(s + ": /" + parse(trie,ct)+ "/,");

			//re-initialize
			trie = new trieNode[148];
			ct = 0;
	}
}
