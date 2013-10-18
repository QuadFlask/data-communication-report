package flask.codes.generator;

import flask.BitUtil;
import flask.type.Bit;
import flask.type.BitPattern;

public class EvenParityGenerator implements Generator {

	@Override
	public BitPattern generate(BitPattern dataword) {
		BitPattern codeword = BitUtil.copy(dataword, dataword.length());
		Bit parity = new Bit();

		if (!BitUtil.isEven(BitUtil.countOne(dataword))) parity.complement();

		codeword.append(parity);
		return codeword;
	}

}
