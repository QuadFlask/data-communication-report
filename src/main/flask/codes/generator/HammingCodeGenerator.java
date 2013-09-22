package flask.codes.generator;

import flask.BitUtil;
import flask.codes.checker.EvenParityChecker;
import flask.codes.checker.HammingCodeChecker;
import flask.type.Bit;
import flask.type.BitPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HammingCodeGenerator {

	// dataword size : k
	// codeword size : n
	// parity size : n-k
	public static Map<BitPattern, BitPattern> generate(int datawordSize) {
		Map<BitPattern, BitPattern> codes = new HashMap<BitPattern, BitPattern>();
		List<BitPattern> keyset = BitPatternSequenceGenerator.generate(datawordSize);
		int codewordSize = getCodewordSize(datawordSize);

		for (BitPattern key : keyset) {
			BitPattern value = makeEmptyCodeword(key, codewordSize);
			for (int i = 1; i < codewordSize; i *= 2)
				insertParity(value, i);
			codes.put(key, value);
		}

		return codes;
	}

	public static BitPattern makeEmptyCodeword(BitPattern dataword, int size) {
		BitPattern code = new BitPattern();
		int index = 0;
		for (int i = 0; i < size; i++) {
			Bit bit = new Bit();
			if (!isParityIndex(i))
				bit.set(dataword.get(index++).value());
			code.append(bit);
		}
		return code;
	}

	public static void insertParity(BitPattern codeword, int parityIndex) {
		codeword.get(parityIndex - 1).set(calcEvenParity(codeword, parityIndex));
	}

	public static int calcEvenParity(BitPattern bitPattern, int tab) {
		int count = 0;
		int headIndex = tab - 1;
		int index, startIndex;

		for (int x = 1; x < bitPattern.length(); x++) {
			startIndex = headIndex + tab * 2 * (x - 1); // 1+0
			for (int i = 0; i < tab; i++) {
				index = startIndex + i;
				if (index < bitPattern.length())
					if (bitPattern.get(index).is1()) count++;
			}
		}

		return count % 2;
	}


	public static boolean isParityIndex(int index) {
		index++;
		return index == Math.pow(2, (int) BitUtil.log2(index));
	}

	public static int getCodewordSize(int datawordSize) {
		int sum = 0;
		int i = 1;
		do {
			sum += Math.pow(2, i++) - 1;
		} while (datawordSize > sum);
		return datawordSize + i;
	}

	public static BitPattern correction(BitPattern errorCodeword) {
		if (HammingCodeChecker.check(errorCodeword)) return errorCodeword; // valid codeword

		BitPattern parities = new BitPattern();
		for (int i = errorCodeword.length() - 1; i >= 0; i--) {
			if (isParityIndex(i)) {
				Bit parity = errorCodeword.get(i);
				int calcedParity = calcEvenParity(errorCodeword, i + 1);

				parities.append(new Bit(calcedParity));
			}
		}

		int errorBitLocation = parities.toInteger() - 1;
		errorCodeword.get(errorBitLocation).complement();

		return errorCodeword;
	}


}
