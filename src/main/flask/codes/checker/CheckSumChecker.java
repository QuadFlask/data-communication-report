package flask.codes.checker;

import flask.codes.generator.CheckSumGenerator;
import flask.type.BitPattern;

public class CheckSumChecker implements Checker {
	private int checksumSize;

	public CheckSumChecker(int checksumSize) {
		this.checksumSize = checksumSize;
	}

	public boolean check(BitPattern codeword) {
		long syndrome = 0;

		int loop = codeword.length() / checksumSize;
		int remain = codeword.length() % checksumSize;

		for (int i = 0; i < loop; i++)
			syndrome += CheckSumGenerator.valueOf(codeword, remain + i * checksumSize, checksumSize);
		syndrome += CheckSumGenerator.valueOf(codeword, 0, remain);

		syndrome = CheckSumGenerator.wrap(syndrome, checksumSize);
		syndrome ^= (int) Math.pow(2, checksumSize) - 1; // complement with xor

		return syndrome == 0;
	}

}