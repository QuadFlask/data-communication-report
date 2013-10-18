package flask.codes.generator;

import flask.type.Bit;
import flask.type.BitPattern;

public class CheckSumGenerator implements Generator {
	private int checksumSize;

	public CheckSumGenerator(int checksumSize) {
		this.checksumSize = checksumSize;
	}

	public BitPattern generate(BitPattern dataword) {
		BitPattern checkSum = generateCheckSumBits(dataword);
		BitPattern codeword = new BitPattern();

		for (int i = 0; i < dataword.length(); i++)
			codeword.append(new Bit(dataword.get(i).value()));
		for (int i = 0; i < checkSum.length(); i++)
			codeword.append(new Bit(checkSum.get(i).value()));

		return codeword;
	}

	public BitPattern generateCheckSumBits(BitPattern dataword) {
		long sum = 0;
		int loop = dataword.length() / checksumSize;
		int remain = dataword.length() % checksumSize;

		for (int i = 0; i < loop; i++)
			sum += valueOf(dataword, remain + i * checksumSize, checksumSize);
		sum += valueOf(dataword, 0, remain);

		sum = wrap(sum, checksumSize);

		BitPattern checksum = new BitPattern();
		checksum.set(sum, checksumSize);
		checksum.complement();

		return checksum;
	}

	public static long wrap(long sum, int wrapSize) {
		long wrapedSum = 0;
		long pow = (int) Math.pow(2, wrapSize) - 1;

		while (sum > 0) {
			long wraped = sum & pow;
			wrapedSum += wraped;
			sum >>= wrapSize;
		}

		return (wrapedSum > pow) ? wrap(wrapedSum, wrapSize) : wrapedSum;
	}

	public static int valueOf(BitPattern bitPattern, int from, int length) {
		int value = 0;
		length += from;
		for (int i = from; i < length; i++)
			value += bitPattern.get(i).value() * Math.pow(2, length - i - 1);
		return value;
	}

}
