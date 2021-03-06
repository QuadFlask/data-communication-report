package flask.codes.generator;

import flask.BitUtil;
import flask.type.Bit;
import flask.type.BitPattern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HammingCodeGenerator implements Generator {

	@Override
	public BitPattern generate(BitPattern dataword) {
		int codewordSize = calcCodewordSize(dataword.length());
		BitPattern codeword = makeEmptyCodeword(dataword, codewordSize);

		for (int i = 1; i < codewordSize; i *= 2)
			insertParity(codeword, i);

		return codeword;
	}

	public Map<BitPattern, BitPattern> generateAsSequence(int datawordSize) {
		Map<BitPattern, BitPattern> codes = new HashMap<BitPattern, BitPattern>();
		List<BitPattern> datawordSet = BitPatternSequenceGenerator.generate(datawordSize);

		for (BitPattern dataword : datawordSet)
			codes.put(dataword, generate(dataword));

		return codes;
	}

	public BitPattern makeEmptyCodeword(BitPattern dataword, int size) {
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

	public void insertParity(BitPattern codeword, int parityIndex) {
		codeword.get(parityIndex - 1).set(calcEvenParity(codeword, parityIndex));
	}

	public static int calcEvenParity(BitPattern bitPattern, int tab) {
		int count = 0, index, startIndex;
		int headIndex = tab - 1;

		for (int x = 1; x < bitPattern.length(); x++) {
			startIndex = headIndex + tab * 2 * (x - 1);
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

	public static int calcCodewordSize(int datawordSize) {
		int sum = 0, i = 1;
		do {
			sum += Math.pow(2, i++) - 1;
		} while (datawordSize > sum);
		return datawordSize + i;
	}
}
