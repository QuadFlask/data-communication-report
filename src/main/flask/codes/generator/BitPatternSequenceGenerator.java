package flask.codes.generator;

import flask.type.BitPattern;

import java.util.ArrayList;
import java.util.List;

public class BitPatternSequenceGenerator {

	public static List<BitPattern> generate(int length) {
		int l = (int) Math.pow(2, length);
		List<BitPattern> sequence = new ArrayList<BitPattern>(l);
		BitPattern bitPattern;

		for (int i = 0; i < l; i++) {
			bitPattern = new BitPattern();
			bitPattern.set(i, length);
			sequence.add(bitPattern);
		}

		return sequence;
	}

}