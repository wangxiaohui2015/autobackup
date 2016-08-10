package com.my.autobackup.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestHashUtil {

	@Test
	public void testGetSHA256HashValue() throws Exception {
		String hash = "251AFD3F20AB4A307ECD50F3F84FD34097F2888505642DACCE06C6FFA048CCF0";
		String message = "test message";
		byte[] bytes = HashUtil.getSHA256HashValue(message.getBytes());
		String str = HashUtil.getSHA256HashStringValue(bytes);
		assertEquals(hash, str);
	}

	@Test
	public void testGetSHA256HashStringValue() throws Exception {
		String hash = "3F0A377BA0A4A460ECB616F6507CE0D8CFA3E704025D4FDA3ED0C5CA05468728";
		String message = "test message";
		String str = HashUtil.getSHA256HashStringValue(message
				.getBytes("UTF-8"));
		assertEquals(hash, str);
	}
}
