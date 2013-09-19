package flask.codes;

import flask.BitUtil;
import flask.codes.checker.EvenParityChecker;
import flask.codes.checker.HammingCodeChecker;
import flask.codes.generator.BitPatternSequenceGenerator;
import flask.codes.generator.HammingCodeGenerator;
import flask.type.BitPattern;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class HammingTest {
	Map<BitPattern, BitPattern> sampleCode;

	@Before
	public void setUp() {
		sampleCode = new HashMap<BitPattern, BitPattern>();
		sampleCode.put(new BitPattern(0, 0), new BitPattern(0, 0, 0, 0, 0));
		sampleCode.put(new BitPattern(0, 1), new BitPattern(0, 1, 0, 1, 1));
		sampleCode.put(new BitPattern(1, 0), new BitPattern(1, 0, 1, 0, 1));
		sampleCode.put(new BitPattern(1, 1), new BitPattern(1, 1, 1, 1, 0));
	}

	@Test
	public void hammingDistance() {
		assertThat(BitUtil.hammingDistance(new BitPattern(0, 0, 0), new BitPattern(0, 1, 1)), is(2));
		assertThat(BitUtil.hammingDistance(new BitPattern(1, 0, 1, 0, 1), new BitPattern(1, 1, 1, 1, 0)), is(3));
	}

	@Test
	public void verifyLinearCode() {
		assertThat(BitUtil.verifyLinear(sampleCode), is(true));
	}


	@Test
	public void codeSizeTest() {
		checkCodewordSize(1, 3);
		checkCodewordSize(2, 5);
		checkCodewordSize(3, 6);
		checkCodewordSize(4, 7);
		checkCodewordSize(5, 9);
		checkCodewordSize(6, 10);
		checkCodewordSize(7, 11);
		checkCodewordSize(8, 12);
		checkCodewordSize(9, 13);
		checkCodewordSize(10, 14);
		checkCodewordSize(11, 15);
		checkCodewordSize(12, 17);
		checkCodewordSize(13, 18);
		checkCodewordSize(14, 19);
		checkCodewordSize(15, 20);
		checkCodewordSize(16, 21);
		checkCodewordSize(17, 22);
	}

	private void checkCodewordSize(int datawordSize, int codewordSize) {
		assertThat(HammingCodeGenerator.getCodewordSize(datawordSize), is(codewordSize));
	}

	@Test
	public void evenParity_whenTabIs1() {
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0), 1, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 1), 1, 1);
		checkEvenParity(new BitPattern(0, 0, 1, 0, 0), 1, 1);
		checkEvenParity(new BitPattern(0, 0, 1, 0, 1), 1, 0);
	}

	@Test
	public void evenParity_whenTabIs2() {
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0), 2, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 1), 2, 0);
		checkEvenParity(new BitPattern(0, 0, 1, 0, 0), 2, 1);
		checkEvenParity(new BitPattern(0, 0, 1, 0, 1), 2, 1);
	}

	@Test
	public void evenParity_whenTabIs4() {
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0), 4, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 1), 4, 1);
		checkEvenParity(new BitPattern(0, 0, 1, 0, 0), 4, 0);
		checkEvenParity(new BitPattern(0, 0, 1, 0, 1), 4, 1);
	}

	@Test
	public void evenParity_whenTabIs8() {
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 8, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 1), 8, 1);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 1, 0), 8, 1);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 1, 1), 8, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 0, 1, 0, 0, 0), 8, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 0, 1, 0, 0, 1), 8, 1);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 0, 1, 0, 1, 0), 8, 1);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 0, 1, 0, 1, 1), 8, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 0, 0, 0, 0), 8, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 0, 0, 0, 1), 8, 1);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 0, 0, 1, 0), 8, 1);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 0, 0, 1, 1), 8, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), 8, 0);

		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), 1, 1);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), 2, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), 4, 0);
		checkEvenParity(new BitPattern(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), 8, 0);
	}

	@Test
	public void checkParityIndex() {
		assertThat(HammingCodeGenerator.isParityIndex(0), is(true));
		assertThat(HammingCodeGenerator.isParityIndex(1), is(true));
		assertThat(HammingCodeGenerator.isParityIndex(2), is(false));
		assertThat(HammingCodeGenerator.isParityIndex(3), is(true));
		assertThat(HammingCodeGenerator.isParityIndex(4), is(false));
		assertThat(HammingCodeGenerator.isParityIndex(5), is(false));
		assertThat(HammingCodeGenerator.isParityIndex(6), is(false));
		assertThat(HammingCodeGenerator.isParityIndex(7), is(true));
		assertThat(HammingCodeGenerator.isParityIndex(8), is(false));
	}

	private void checkEvenParity(BitPattern bitPattern, int tab, int parity) {
		assertThat(HammingCodeGenerator.calcEvenParity(bitPattern, tab), is(parity));
	}

	@Test
	public void checkExtendsCodeword_containsOnlyDataword() {
		List<BitPattern> dataword = BitPatternSequenceGenerator.generate(2);
		BitPattern bits = HammingCodeGenerator.makeEmptyCodeword(dataword.get(0), 5);
		checkBits(bits, 0, 0, 0, 0, 0);
		bits = HammingCodeGenerator.makeEmptyCodeword(dataword.get(1), 5);
		checkBits(bits, 0, 0, 0, 0, 1);
		bits = HammingCodeGenerator.makeEmptyCodeword(dataword.get(2), 5);
		checkBits(bits, 0, 0, 1, 0, 0);
		bits = HammingCodeGenerator.makeEmptyCodeword(dataword.get(3), 5);
		checkBits(bits, 0, 0, 1, 0, 1);
	}

	private void checkBits(BitPattern bits, int... values) {
		for (int i = 0; i < bits.length(); i++)
			assertThat(bits.get(i).value(), is(values[i]));
	}

	@Test
	public void generateOfDatawordSizeIs1() {
		Map<BitPattern, BitPattern> result = HammingCodeGenerator.generate(1);
		for (BitPattern b : result.values())
			verifyHammingCode(b);
		assertThat(BitUtil.verifyLinear(result), is(true));
		printCodewords(result);
	}

	@Test
	public void generateOfDatawordSizeIs2() {
		Map<BitPattern, BitPattern> result = HammingCodeGenerator.generate(2);
		for (BitPattern b : result.values())
			verifyHammingCode(b);
		assertThat(BitUtil.verifyLinear(result), is(true));
		printCodewords(result);
	}

	@Test
	public void generateOfDatawordSizeIs3() {
		Map<BitPattern, BitPattern> result = HammingCodeGenerator.generate(3);
		for (BitPattern b : result.values())
			verifyHammingCode(b);
		assertThat(BitUtil.verifyLinear(result), is(true));
		printCodewords(result);
	}

	@Test
	public void generateOfDatawordSizeIs4() {
		Map<BitPattern, BitPattern> result = HammingCodeGenerator.generate(4);
		for (BitPattern b : result.values())
			verifyHammingCode(b);
		assertThat(BitUtil.verifyLinear(result), is(true));
		printCodewords(result);
	}

	private void printCodewords(Map<BitPattern, BitPattern> result) {
		return;
//		for (BitPattern b : result.keySet())
//			System.out.println(b.toString() + " -> " + result.get(b).toString());
//		System.out.println("dmin : " + BitUtil.minimumHammingDistance(result.values()));
	}

	@Test
	public void checkParityBit() {
		verifyHammingCode(new BitPattern(0, 0, 0));
		verifyHammingCode(new BitPattern(1, 1, 1));
		verifyHammingCode(new BitPattern(0, 0, 1, 1, 0, 0, 1));

		verifyHammingCode(new BitPattern(0, 0, 0, 0, 0));
		verifyHammingCode(new BitPattern(1, 0, 0, 1, 1));
		verifyHammingCode(new BitPattern(1, 1, 1, 0, 0));
		verifyHammingCode(new BitPattern(0, 1, 1, 1, 1));
	}

	private void verifyHammingCode(BitPattern codeword) {
		assertThat(HammingCodeChecker.check(codeword), is(true));
	}

}
