package flask.codes.checker;

import flask.BitUtil;
import flask.type.Bit;
import flask.type.BitPattern;

public class EvenParityChecker {

	public static boolean check(Bit[] bits) {
		return BitUtil.isEven(BitUtil.countOne(bits));
	}

	public static Bit[] extract(Bit[] codeword) {
		if (!check(codeword)) return null;

		Bit[] dataword = new Bit[codeword.length - 1];
		BitUtil.assignBits(codeword, dataword, dataword.length);

		return dataword;
	}

	public static int calcEvenParity(BitPattern bitPattern, int tab) {
		int count = 0;
		int headIndex = tab - 1;
		int index, startIndex;

		for (int x = 1; x < 10; x++) {
			startIndex = headIndex + tab * 2 * (x - 1); // 1+0
			for (int i = 0; i < tab; i++) {
				index = startIndex + i;
				if (index < bitPattern.length())
					if (bitPattern.get(index).is1()) count++;
			}
		}
		return count % 2;
	}

}
