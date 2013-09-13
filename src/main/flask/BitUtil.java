package flask;

import flask.type.Bit;
import flask.type.BitPattern;

import java.util.Collection;
import java.util.Map;

public class BitUtil {
	private BitUtil() {
	}

	public static boolean isEven(int i) {
		return i % 2 == 0;
	}

	public static int countOne(Bit[] dataword) {
		int count = 0;
		for (Bit b : dataword)
			if (b.is1()) count++;
		return count;
	}

	public static int countOne(BitPattern dataword) {
		return countOne(dataword.values());
	}

	public static Bit[] assignBits(Bit[] from, Bit[] to, int count) {
		for (int i = 0; i < count; i++)
			to[i] = new Bit(from[i].value());
		return to;
	}

	public static BitPattern xor(BitPattern p1, BitPattern p2) {
		BitPattern result = new BitPattern();
		int length = Math.min(p1.length(), p2.length());
		for (int i = 0; i < length; i++)
			result.append(new Bit(p1.get(i).value() ^ p2.get(i).value()));
		return result;
	}

	public static BitPattern copy(BitPattern dataword, int length) {
		BitPattern result = new BitPattern();
		for (int i = 0; i < length; i++)
			result.append(dataword.get(i));
		return result;
	}

	public static int hammingDistance(BitPattern p1, BitPattern p2) {
		BitPattern result = BitUtil.xor(p1, p2);
		return countOne(result);
	}

	public static final double log2(int x) {
		return Math.log(x) / Math.log(2);
	}

	public static boolean verifyLinear(Map<BitPattern, BitPattern> sampleCode) {
		for (BitPattern b1 : sampleCode.values())
			for (BitPattern b2 : sampleCode.values()) {
				BitPattern p = BitUtil.xor(b1, b2);
				boolean isAll = false;
				for (BitPattern c : sampleCode.values())
					isAll |= p.equals(c);
				if (!isAll) return false;
			}
		return true;
	}

	public static int minimumHammingDistance(Collection<BitPattern> values) {
		int minimumHammingDistance = Integer.MAX_VALUE;
		for (BitPattern b1 : values)
			for (BitPattern b2 : values)
				if (!b1.equals(b2))
					minimumHammingDistance = Math.min(hammingDistance(b1, b2), minimumHammingDistance);
		return minimumHammingDistance;
	}

	public static int toInteger(Bit[] bits) {
		int integer = 0;
		for (int i = 0; i < bits.length; i++)
			if (bits[i].value() == 1)
				integer += Math.pow(2, bits.length - i - 1);
		return integer;
	}
}
