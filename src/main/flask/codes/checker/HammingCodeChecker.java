package flask.codes.checker;

import flask.type.BitPattern;

public class HammingCodeChecker {

	public static boolean check(BitPattern codeword) {
		for (int i = 1; i < codeword.length(); i *= 2)
			if (!checkParity(codeword, i)) return false;
		return true;
	}

	private static boolean checkParity(BitPattern bitPattern, int parityIndex) {
		int headIndex = parityIndex - 1;//1
		int syndrome = 0;
		int index, startIndex;

		for (int x = 1; x <= bitPattern.length(); x++) {
			startIndex = headIndex + parityIndex * 2 * (x - 1); // 1+0
			for (int i = 0; i < parityIndex; i++) {
				index = startIndex + i; // 1~2
				if (index < bitPattern.length())
					if (bitPattern.get(index).is1()) syndrome++;
			}
		}

		return syndrome % 2 == 0;
	}
}
