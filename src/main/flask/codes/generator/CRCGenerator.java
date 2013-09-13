package flask.codes.generator;

import flask.type.Bit;
import flask.type.BitPattern;

public class CRCGenerator {

	public static BitPattern generate(BitPattern dataword, Bit[] polynomial, int crcLength){
		BitPattern codeword = new BitPattern();
		Bit[] crcBits = crc(dataword, polynomial, crcLength);

		for (int i = 0; i < dataword.length(); i++)
			codeword.append(dataword.get(i));
		for (int i = 0; i < crcBits.length; i++)
			codeword.append(crcBits[i]);

		return codeword;
	}

	public static Bit[] crc(BitPattern dataword, Bit[] polynomial, int crcLength) {
		BitPattern shiftRegister = new BitPattern();
		shiftRegister.setLength(dataword.length() + crcLength);
		Bit[] shiftRegisterBits = shiftRegister.values();

		for (int i = 0; i < dataword.length(); i++)
			shiftRegisterBits[i].set(dataword.get(i).value());

		for (int i = 0; i < shiftRegisterBits.length; i++) {
			if (isAllZero(shiftRegisterBits, crcLength)) return getTrailingBits(crcLength, shiftRegisterBits);
			if (shiftRegisterBits[i].is1()) divideAt(shiftRegisterBits, polynomial, i);
		}

		return null;
	}

	public static void divideAt(Bit[] shiftRegister, Bit[] polynomial, int i) {
		for (int k = 0; k < polynomial.length; k++)
			shiftRegister[i + k].set(shiftRegister[i + k].value() ^ polynomial[k].value());
	}

	public static Bit[] getTrailingBits(int crc, Bit[] shiftRegister) {
		Bit[] remainder = new Bit[crc];
		for (int j = 0; j < crc; j++) {
			remainder[j] = new Bit();
			remainder[j].set(shiftRegister[shiftRegister.length - crc + j].value());
		}
		return remainder;
	}

	public static boolean isAllZero(Bit[] shiftRegister, int crc) {
		boolean has1 = false;
		for (int j = 0; j < shiftRegister.length - crc; j++)
			has1 |= shiftRegister[j].is1();
		return !has1;
	}

}
