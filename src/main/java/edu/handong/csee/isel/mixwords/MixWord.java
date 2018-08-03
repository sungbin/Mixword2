package edu.handong.csee.isel.mixwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MixWord {

	public static void main(String[] args) {
		MixWord mw = new MixWord();
		try {
			mw.run();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void run() throws IOException {
		SupportString ss = new SupportString();
		System.out.print("start Program from... ");

		/* dir에서 파일 가져오는 부분. */
		File dirFile = new File("Data");
		String endsWith = ".txt";
		ArrayList<File> datas = ss.pullFileListFromDir(dirFile, endsWith);

		for (File data : datas) {

			ArrayList<String> allkeywordList = new ArrayList<String>();
			ArrayList<String> oldTokens = new ArrayList<String>();
			Scanner in = new Scanner(System.in);
			String line;
			System.out.println("wait... " + data.getAbsolutePath() + "...");

			try {
				String extractedLine = ss.extractLineFromFile(data);
				// System.out.println(extractedLine);
				oldTokens = ss.tokenizeNewLine(extractedLine);

				while (true) {
					System.out.println(data.getName() + ": push keywords.(end: q), (paste key: shift+insert)");
					line = in.nextLine();
					if (line.equals("q")) {
						break;
					}

					ArrayList<String> keywordList = ss.tokenizeNewLine(line);
					allkeywordList.addAll(keywordList);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

//			System.out.println("old Tokens: " + oldTokens);

			/* make random number and insert to oldTokens */
			Random rad = new Random();
			int randomRange = oldTokens.size(); // 0 ~ oldTokens.size()-1
//			System.out.println("token size: " + oldTokens.size());
			HashSet<Integer> duplicatedN = new HashSet<Integer>();
			for (String keyword : allkeywordList) {
				int randomN = rad.nextInt(randomRange);
				if (duplicatedN.contains(randomN)) {
					while (!duplicatedN.contains(randomN)) {
						randomN = rad.nextInt(randomRange);
						duplicatedN.add(randomN);
						randomRange++;
					}
				}
				else {
//					System.out.println("randomN:" + randomN + ", keyword: " + keyword);
					oldTokens.add(randomN, keyword);
				}
				

			}

//			System.out.println("old size:" + oldTokens.size() + ", random n: " + duplicatedN);
			String finalString = ss.getOneLineFromArrayList(oldTokens);

			System.out.println(finalString + "\n\n");

			ss.makeOut(finalString, data);

		}
		try {
			System.out.println("All files saved in result");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
