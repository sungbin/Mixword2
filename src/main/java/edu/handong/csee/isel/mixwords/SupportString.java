package edu.handong.csee.isel.mixwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SupportString {
	public ArrayList<File> pullFileListFromDir(File dir, String endsWith) {
		
		ArrayList<File> datas = new ArrayList<File>();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File[] fileList = dir.listFiles();

		for (File tempFile : fileList) {
			if (tempFile.isFile()) {
				if (tempFile.getName().endsWith(endsWith)) {
					datas.add(tempFile);
				}
			}
		}
		int i;
		System.out.println(datas);
		return datas;
	}
	
	public String extractLineFromFile(File data) throws IOException {
		String extractedLine = "";

		FileInputStream fileInputStream = new FileInputStream(data);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
		BufferedReader reader = new BufferedReader(inputStreamReader);

		String line = "";
		while ((line = reader.readLine()) != null) {
			extractedLine += (line + " ");
		}

		return extractedLine;
	}
	
	public ArrayList<String> tokenizeNewLine(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str);

		int i;
		ArrayList<String> list = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			// System.out.println(tokenizer.nextToken());
			list.add(tokenizer.nextToken());
		}
		return list;
	}
	
	public String getOneLineFromArrayList(ArrayList<String> list) {
		String newStr = "";
		
		for(String str : list) {
			newStr += (str + " ");
		}
		
		return newStr.trim();
	}
}
