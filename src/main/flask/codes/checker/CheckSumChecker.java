package flask.codes.checker;

import flask.codes.generator.CheckSumGenerator;
import flask.type.BitPattern;

public class CheckSumChecker {

	public static boolean check(BitPattern codeword) {
		int syndrome = 0;

		int loop = codeword.length() / CheckSumGenerator.checksumSize;
		int remain = codeword.length() % CheckSumGenerator.checksumSize;

		for (int i = 0; i < loop; i++)
			syndrome += CheckSumGenerator.valueOf(codeword, remain + i * CheckSumGenerator.checksumSize, CheckSumGenerator.checksumSize);
		syndrome += CheckSumGenerator.valueOf(codeword, 0, remain);

		syndrome %= (int) Math.pow(2, CheckSumGenerator.checksumSize);

		return syndrome == 0;
	}

}
//
//
//	// per 16bits
//	public static BitPattern generate(BitPattern dataword) {
//		int sum = 0;
//		int loop = dataword.length() / checksumSize;
//		int remain = dataword.length() % checksumSize;
//
//		for (int i = 0; i < loop; i++)
//			sum += valueOf(dataword, remain + i * checksumSize, checksumSize);
//		sum += valueOf(dataword, 0, remain);
//
//		sum %= (int) Math.pow(2, checksumSize);
//
//		BitPattern checksum = new BitPattern();
//		checksum.set(sum, checksumSize);
//
//		return checksum;
//	}
//
//	public static int valueOf(BitPattern bitPattern, int from, int length) {
//		int value = 0;
//		length += from;
//		for (int i = from; i < length; i++)
//			value += Math.pow(2, length - i - 1) * bitPattern.get(i).value();
//		return value;
//	}