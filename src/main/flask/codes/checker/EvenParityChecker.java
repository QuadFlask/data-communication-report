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

}
