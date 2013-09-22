package flask.codes.generator;

import flask.type.Bit;
import flask.type.BitPattern;

public class CheckSumGenerator {
	public static int checksumSize = 8;

	public static BitPattern generate(BitPattern dataword) {
		BitPattern checkSum = generateCheckSumBits(dataword);
		BitPattern codeword = new BitPattern();
		for (int i = 0; i < dataword.length(); i++)
			codeword.append(new Bit(dataword.get(i).value()));
		for (int i = 0; i < checkSum.length(); i++)
			codeword.append(new Bit(checkSum.get(i).value()));
		return codeword;
	}

	// per 8bits
	public static BitPattern generateCheckSumBits(BitPattern dataword) {
		int sum = 0;
		int loop = dataword.length() / checksumSize;
		int remain = dataword.length() % checksumSize;

		for (int i = 0; i < loop; i++)
			sum += valueOf(dataword, remain + i * checksumSize, checksumSize);
		sum += valueOf(dataword, 0, remain);

		sum %= (int) Math.pow(2, checksumSize);

		BitPattern checksum = new BitPattern();
		checksum.set(sum, checksumSize);
		checksum.complement();
		checksum.set(checksum.toInteger() + 1, checksumSize);

		return checksum; // 보수 취해야함!
	}

	public static int valueOf(BitPattern bitPattern, int from, int length) {
		int value = 0;
		length += from;
		for (int i = from; i < length; i++)
			value += bitPattern.get(i).value() * Math.pow(2, length - i - 1);
		return value;
	}

}
