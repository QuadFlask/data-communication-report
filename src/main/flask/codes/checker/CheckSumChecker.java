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