package flask.codes.generator;

import flask.BitUtil;
import flask.type.Bit;
import flask.type.BitPattern;

public class CRCGenerator implements Generator {
	private BitPattern polynomial;
	private int crcLength;

	public CRCGenerator(BitPattern polynomial, int crcLength) {
		this.polynomial = polynomial;
		this.crcLength = crcLength;
	}

	@Override
	public BitPattern generate(BitPattern dataword) {
		BitPattern codeword = new BitPattern();
		Bit[] crcBits = getCRC(dataword);

		for (int i = 0; i < dataword.length(); i++)
			codeword.append(dataword.get(i));
		for (int i = 0; i < crcBits.length; i++)
			codeword.append(crcBits[i]);

		return codeword;
	}

	private Bit[] getCRC(BitPattern dataword) {
		BitPattern shiftRegister = new BitPattern();
		shiftRegister.setLength(dataword.length() + crcLength);
		Bit[] shiftRegisterBits = shiftRegister.values();

		for (int i = 0; i < dataword.length(); i++)
			shiftRegisterBits[i].set(dataword.get(i).value());

		for (int i = 0; i < shiftRegisterBits.length; i++) {
			if (BitUtil.isAllZero(shiftRegisterBits, crcLength)) return getTrailingBits(crcLength, shiftRegisterBits);
			if (shiftRegisterBits[i].is1()) divideAt(shiftRegisterBits, polynomial, i);
		}

		return null;
	}

	private Bit[] getTrailingBits(int crc, Bit[] shiftRegister) {
		Bit[] remainder = new Bit[crc];
		for (int j = 0; j < crc; j++) {
			remainder[j] = new Bit();
			remainder[j].set(shiftRegister[shiftRegister.length - crc + j].value());
		}
		return remainder;
	}

	public static void divideAt(Bit[] shiftRegister, BitPattern polynomial, int i) {
		for (int k = 0; k < polynomial.length(); k++)
			shiftRegister[i + k].set(shiftRegister[i + k].value() ^ polynomial.get(k).value());
	}
}
