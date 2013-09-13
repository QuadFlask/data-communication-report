package flask.codes.generator;

import flask.BitUtil;
import flask.codes.checker.EvenParityChecker;
import flask.type.Bit;
import flask.type.BitPattern;

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
		codeword.get(parityIndex - 1).set(EvenParityChecker.calcEvenParity(codeword, parityIndex));
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

}
