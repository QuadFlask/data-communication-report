package flask.codes.checker;

import flask.BitUtil;
import flask.codes.generator.CRCGenerator;
import flask.type.Bit;
import flask.type.BitPattern;

public class CRCChecker implements Checker {
	private BitPattern polynomial;
	private int crcLength;

	public CRCChecker(BitPattern polynomial, int crcLength) {
		this.polynomial = polynomial;
		this.crcLength = crcLength;
	}

	@Override
	public boolean check(BitPattern codeword) {
		Bit[] code = codeword.values();

		for (int i = 0; i < code.length - crcLength; i++)
			if (code[i].is1()) CRCGenerator.divideAt(code, polynomial, i);

		boolean syndrome = BitUtil.isAllZero(code, 0);

		return syndrome;
	}
}
