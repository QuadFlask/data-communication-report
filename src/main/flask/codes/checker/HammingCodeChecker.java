package flask.codes.checker;

import flask.type.BitPattern;

public class HammingCodeChecker implements Checker {

	public boolean check(BitPattern codeword) {
		for (int i = 1; i < codeword.length(); i *= 2)
			if (!checkParity(codeword, i)) return false;
		return true;
	}

	private boolean checkParity(BitPattern bitPattern, int parityIndex) {
		int headIndex = parityIndex - 1;
		int syndrome = 0, index, startIndex;

		for (int x = 1; x <= bitPattern.length(); x++) {
			startIndex = headIndex + parityIndex * 2 * (x - 1);
			for (int i = 0; i < parityIndex; i++) {
				index = startIndex + i;
				if (index < bitPattern.length())
					if (bitPattern.get(index).is1()) syndrome++;
			}
		}

		return syndrome % 2 == 0;
	}
}
