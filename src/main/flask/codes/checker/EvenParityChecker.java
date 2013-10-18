package flask.codes.checker;

import flask.BitUtil;
import flask.type.Bit;
import flask.type.BitPattern;

public class EvenParityChecker implements Checker {
	@Override
	public boolean check(BitPattern codeword) {
		return BitUtil.isEven(BitUtil.countOne(codeword.values()));
	}
}