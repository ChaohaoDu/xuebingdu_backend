package com.xuebingdu.frontend;

import java.nio.ByteBuffer;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;

public class UUIDUtil {
	public static String getBase64() {
		Base64 base64 = new Base64();
		UUID uuid = UUID.randomUUID();
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		return base64.encodeBase64URLSafeString(bb.array());
	}
}
