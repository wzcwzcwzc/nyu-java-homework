package randomwords;

import java.io.*;
import java.util.*;
import javafx.util.Pair;
/**
 * @author Barry
 * */
public class RandomWords {

	private String filename;

	private static final int SIZE = 10;
	/**
	 * choose 10 different words from file and use wordList to store
	 */
	private List<String> wordList;
	/**
	 * come up with different pair of words from wordList and store in pairs
	 */
	private List<Pair<String, String>> pairs;

	public RandomWords() {
		wordList = new ArrayList<>(SIZE);
		pairs = new ArrayList<>(SIZE);
	}
	
	public RandomWords(String filename) throws IOException{
		wordList = new ArrayList<>();

		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			Map<Integer, String> wordMap = new HashMap<>();
			int i = 0;
			while(line != null){
				line = line.trim();
				wordMap.put(i, line);
				i++;
				line = reader.readLine();
			}
			// get 10 random different words into list
			wordList = randomWordList(wordMap);
			// generate different pairs
			pairs = generatePairs(wordList);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}finally {
			if(reader == null){
				System.out.println("the file is not open");
				System.exit(0);
			}else{
				reader.close();
			}
		}
	}
	
	public static int levenshteinDistance(String word1, String word2) {
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];

		for(int i = 1; i <= word1.length(); i++){
			dp[i][0] = i;
		}

		for(int i = 1; i <= word2.length(); i++){
			dp[0][i] = i;
		}

		for(int i = 1; i <= word1.length(); i++){
			for(int j = 1; j <= word2.length(); j++){
				if(word1.charAt(i - 1) == word2.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1];
				}else {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
				}
			}
		}
		return dp[word1.length()][word2.length()];
	}

	static class PairComparator implements Comparator<Pair<String, String>>{
		// minHeap, sort the Pair in ascending order
		@Override
		public int compare(Pair<String, String> p1, Pair<String, String> p2){
			// get distance of each pair and compare the distance value
			int dis1 = levenshteinDistance(p1.getKey(), p1.getValue());
			int dis2 = levenshteinDistance(p2.getKey(), p2.getValue());
			// do compare, minHeap
			if(dis1 == dis2){
				return 0;
			}else if (dis1 > dis2){
				return 1;
			}else{
				return -1;
			}
		}
	}

	public List<Pair<String, String>> sortedPairsComparator() {
		PairComparator pc = new PairComparator();
		pairs.sort(pc);
		return pairs;
	}
	
	public List<Pair<String, String>> sortedPairsLambda() {
		pairs.sort((a, b) -> (levenshteinDistance(a.getKey(), a.getValue()) - levenshteinDistance(b.getKey(), b.getValue())));
		return pairs;
	}

	public List<Pair<String, String>> generatePairs(List<String> wordList){
		List<Pair<String, String>> pairs = new ArrayList<>();
		if(wordList == null || wordList.size() == 0){
			return pairs;
		}
		for(int i = 0; i < wordList.size() - 1; i++){
			for(int j = i + 1; j < wordList.size(); j++){
				Pair<String, String> pair = new Pair<>(wordList.get(i), wordList.get(j));
				pairs.add(pair);
			}
		}
		return pairs;
	}

	public List<String> randomWordList(Map<Integer, String> wordMap){
		Set<String> wordSet = new HashSet<>();
		List<String> wordList = new ArrayList<>();
		if(wordMap == null || wordMap.size() < SIZE){
			return wordList;
		}
		int j = 0;
		while(j < SIZE){
			int key = (int)(Math.random() * 100);
			String str = wordMap.get(key);
			if(!wordSet.contains(str)){
				wordSet.add(str);
				wordList.add(str);
				j++;
			}
		}
		return wordList;
	}
	
	public static void main(String[] args) throws IOException {

		RandomWords rw = new RandomWords("data/words");
		List<Pair<String, String>> sort1 = rw.sortedPairsComparator();
		List<Pair<String, String>> sort2 = rw.sortedPairsLambda();

		// true
		System.out.println(sort1.equals(sort2));

		/* test for comparator and lambda
		Pair<String, String> p1 = new Pair<>("abc", "ab");//1
		Pair<String, String> p2 = new Pair<>("kitten", "sitting");//3
		Pair<String, String> p3 = new Pair<>("ab", "ab"); //0
		List<Pair<String, String>> pairs = new ArrayList<>();
		pairs.add(p1);
		pairs.add(p2);
		pairs.add(p3);
		for (int i = 0; i < pairs.size(); i++){
			System.out.println(pairs.get(i).getKey() + " " + pairs.get(i).getValue());
		}
//		PairComparator pc = new PairComparator();
//		pairs.sort(pc);
//		pairs.sort((a, b) -> (levenshteinDistance(a.getKey(), a.getValue()) - levenshteinDistance(b.getKey(), b.getValue())));
		for (int i = 0; i < pairs.size(); i++){
			System.out.println(pairs.get(i).getKey() + " " + pairs.get(i).getValue());
		}
*/
	}
}
