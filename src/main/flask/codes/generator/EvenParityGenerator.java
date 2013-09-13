package flask.codes.generator;

import flask.BitUtil;
import flask.type.Bit;
import flask.type.BitPattern;

public class EvenParityGenerator {

	public static Bit[] addEvenParity(Bit[] dataword) {
		Bit[] codeword = new Bit[dataword.length + 1];
		Bit parity = new Bit();

		BitUtil.assignBits(dataword, codeword, dataword.length);
		if (BitUtil.isEven(BitUtil.countOne(dataword))) parity.complement();

		codeword[dataword.length] = parity;
		return codeword;
	}

	public static BitPattern addEvenParity(BitPattern dataword) {
		BitPattern codeword = BitUtil.copy(dataword, dataword.length());
		Bit parity = new Bit();

		if (BitUtil.isEven(BitUtil.countOne(dataword))) parity.complement();

		codeword.append(parity);
		return codeword;
	}
}
