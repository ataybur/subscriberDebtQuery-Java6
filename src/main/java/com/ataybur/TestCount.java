package com.ataybur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.ataybur.constants.Constants;

public class TestCount {

	private List<String> addList = new ArrayList<>();

	public static void main(String[] args) {
		TestCount testCount = new TestCount();
		testCount.testCount();
		testCount.writeFile1();
	}

	public void testCount() {
		try (Stream<String> stream = Files.lines(Paths.get(Constants.FILE_NAME))) {
			// stream.filter((s) -> s.getBytes().length !=
			// 61).forEach(System.out::println);
//			stream.filter((s) -> s.length() != Constants.PART_SIZE - 2).forEach(System.out::println);
			stream.filter((s) -> s.length() == Constants.PART_SIZE - 2).forEach(addList::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile1() {
		try {
			File fout = new File(Constants.FILE_NAME_2);
			FileOutputStream fos;

			fos = new FileOutputStream(fout);
//			int nThreads = Runtime.getRuntime().availableProcessors();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//			addList = addList.subList(0, nThreads);
			addList.forEach((s) -> {
				try {
					bw.write(s);
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			bw.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
