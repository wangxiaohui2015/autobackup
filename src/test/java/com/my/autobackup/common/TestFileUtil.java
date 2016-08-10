package com.my.autobackup.common;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class TestFileUtil {

	@Test
	public void testIsSubFile1() {
		File file1 = new File("D:\\a\\b\\c");
		File file2 = new File("D:\\a\\b");
		boolean result = FileUtil.isSubFile(file1, file2);
		assertTrue(result);
	}

	@Test
	public void testIsSubFile2() {
		File file1 = new File("D:\\a\\b\\c");
		File file2 = new File("D:\\a\\b");
		boolean result = FileUtil.isSubFile(file2, file1);
		assertTrue(result);
	}

	@Test
	public void testIsSubFile3() {
		File file1 = new File("D:\\a\\b\\c");
		File file2 = new File("D:\\a\\b\\d");
		boolean result = FileUtil.isSubFile(file1, file2);
		assertTrue(!result);
	}

	@Test
	public void testIsSubFile4() {
		File file1 = new File("D:\\a\\b\\c");
		File file2 = new File("D:/a/b");
		boolean result = FileUtil.isSubFile(file1, file2);
		assertTrue(result);
	}

	@Test
	public void testIsSubFile5() {
		File file1 = new File("D:\\a\\b\\c");
		File file2 = new File("D:/a/b");
		boolean result = FileUtil.isSubFile(file2, file1);
		assertTrue(result);
	}

	@Test
	public void testIsSubFile6() {
		File file1 = new File("D:\\a\\b\\c");
		File file2 = new File("D:\\a\\b\\c");
		boolean result = FileUtil.isSubFile(file2, file1);
		assertTrue(result);
	}
}
