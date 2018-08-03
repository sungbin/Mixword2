package edu.handong.csee.isel.mixwords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
			list.add(tokenizer.nextToken(" "));
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
	
	public File makeOut(String line, File file) throws IOException {
		File curDir = new File("result");

		if (!curDir.exists()) {
			curDir.mkdir();
		}

		File newFile = new File(curDir.getAbsolutePath() + File.separator + file.getName());
		if (newFile.exists()) {
			if (newFile.delete()) {
				System.out.println("successful to delete " + newFile.getName());
			} else {
				System.out.println("fail to delecte " + newFile.getName());
			}
		} else {
			System.out.println("start making " + newFile.getName() + "..");
		}

		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
		OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
		BufferedWriter bf = new BufferedWriter(OutputStreamWriter);

		bf.write(line);
		bf.flush();

		bf.close();
		System.out.println(newFile.getName() + " was made");

		return newFile;

	}
}
