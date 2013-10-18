package flask.codes.checker;

import flask.codes.generator.HammingCodeGenerator;
import flask.type.Bit;
import flask.type.BitPattern;

public class HammingCodeCorrector {

	public BitPattern correct(BitPattern errorCodeword) {
		BitPattern parities = new BitPattern();

		for (int i = errorCodeword.length() - 1; i >= 0; i--) {
			if (HammingCodeGenerator.isParityIndex(i)) {
				int calcedParity = HammingCodeGenerator.calcEvenParity(errorCodeword, i + 1);
				parities.append(new Bit(calcedParity));
			}
		}

		int errorBitLocation = parities.toInteger() - 1;
		errorCodeword.get(errorBitLocation).complement();

		return errorCodeword;
	}
}
