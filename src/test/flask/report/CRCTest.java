package flask.report;

import flask.codes.checker.CRCChecker;
import flask.codes.checker.Checker;
import flask.codes.generator.BitPatternSequenceGenerator;
import flask.codes.generator.CRCGenerator;
import flask.codes.generator.Generator;
import flask.type.Bit;
import flask.type.BitPattern;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CRCTest {

	BitPattern sampleDataword;
	BitPattern sampleDataword2;
	BitPattern[] bookSampleCRCCodes;

	BitPattern polynomial = new BitPattern(1, 0, 1, 1);
	int crcLength = 3;

	Generator generator;
	Checker checker;

	@Before
	public void setUp() {
		sampleDataword = new BitPattern(1, 0, 1, 0, 0, 1, 0, 1, 1);
		sampleDataword2 = new BitPattern(1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0);
		bookSampleCRCCodes = new BitPattern[16];

		bookSampleCRCCodes[0] = new BitPattern(0, 0, 0, 0, 0, 0, 0);
		bookSampleCRCCodes[1] = new BitPattern(0, 0, 0, 1, 0, 1, 1);
		bookSampleCRCCodes[2] = new BitPattern(0, 0, 1, 0, 1, 1, 0);
		bookSampleCRCCodes[3] = new BitPattern(0, 0, 1, 1, 1, 0, 1);
		bookSampleCRCCodes[4] = new BitPattern(0, 1, 0, 0, 1, 1, 1);
		bookSampleCRCCodes[5] = new BitPattern(0, 1, 0, 1, 1, 0, 0);
		bookSampleCRCCodes[6] = new BitPattern(0, 1, 1, 0, 0, 0, 1);
		bookSampleCRCCodes[7] = new BitPattern(0, 1, 1, 1, 0, 1, 0);
		bookSampleCRCCodes[8] = new BitPattern(1, 0, 0, 0, 1, 0, 1);
		bookSampleCRCCodes[9] = new BitPattern(1, 0, 0, 1, 1, 1, 0);
		bookSampleCRCCodes[10] = new BitPattern(1, 0, 1, 0, 0, 1, 1);
		bookSampleCRCCodes[11] = new BitPattern(1, 0, 1, 1, 0, 0, 0);
		bookSampleCRCCodes[12] = new BitPattern(1, 1, 0, 0, 0, 1, 0);
		bookSampleCRCCodes[13] = new BitPattern(1, 1, 0, 1, 0, 0, 1);
		bookSampleCRCCodes[14] = new BitPattern(1, 1, 1, 0, 1, 0, 0);
		bookSampleCRCCodes[15] = new BitPattern(1, 1, 1, 1, 1, 1, 1);

		generator = new CRCGenerator(polynomial, crcLength);
		checker = new CRCChecker(polynomial, crcLength);
	}

//	@Test
//	public void crc() {
//		Bit[] crc = generator.crc(sampleDataword);
//		checkSame(crc, 0, 1, 0);
//
//		crc = generator.crc(sampleDataword2);
//		checkSame(crc, 1, 0, 0);
//	}

	private void checkSame(Bit[] bit, int... value) {
		for (int i = 0; i < bit.length; i++)
			assertThat(bit[i].value(), is(value[i]));
	}

	@Test
	public void sampleGenerateTest() {
		BitPattern codeword = generator.generate(sampleDataword);
		checkSame(codeword.values(), 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0);
	}

	@Test
	public void bookCRCTest() {
		List<BitPattern> datawords = BitPatternSequenceGenerator.generate(4); // 0000 ~ 1111

		check(datawords.get(0), bookSampleCRCCodes[0].toString()); // "0000000"
		check(datawords.get(1), bookSampleCRCCodes[1].toString()); // "0001011"
		check(datawords.get(2), bookSampleCRCCodes[2].toString()); // "0010110"
		check(datawords.get(3), bookSampleCRCCodes[3].toString()); // "0011101"
		check(datawords.get(4), bookSampleCRCCodes[4].toString()); // "0100111"
		check(datawords.get(5), bookSampleCRCCodes[5].toString()); // "0101100"
		check(datawords.get(6), bookSampleCRCCodes[6].toString()); // "0110001"
		check(datawords.get(7), bookSampleCRCCodes[7].toString()); // "0111010"
		check(datawords.get(8), bookSampleCRCCodes[8].toString()); // "1000101"
		check(datawords.get(9), bookSampleCRCCodes[9].toString()); // "1001110"
		check(datawords.get(10), bookSampleCRCCodes[10].toString()); // "1010011"
		check(datawords.get(11), bookSampleCRCCodes[11].toString()); // "1011000"
		check(datawords.get(12), bookSampleCRCCodes[12].toString()); // "1100010"
		check(datawords.get(13), bookSampleCRCCodes[13].toString()); // "1101001"
		check(datawords.get(14), bookSampleCRCCodes[14].toString()); // "1110100"
		check(datawords.get(15), bookSampleCRCCodes[15].toString()); // "1111111"

	}

	private void check(BitPattern dataword, String booksCode) {
		System.out.print("Checking crc (dataword: " + dataword.toString() + ")");
		BitPattern generatedCodeword = generator.generate(dataword);
		assertThat(generatedCodeword.toString(), is(booksCode));
		System.out.println(" generated codeword: '" + generatedCodeword.toString() + "' is match with '" + booksCode + "'");
	}

	@Test
	public void checkCRC() {
		for (BitPattern bookSampleCRCCode : bookSampleCRCCodes)
			CRCCheckerTest(bookSampleCRCCode);
	}


	private void CRCCheckerTest(BitPattern bookSampleCRCCode) {
		System.out.print("Checking CRC: '" + bookSampleCRCCode.toString() + "' with polynomial: '" + polynomial.toString() + "' and CRC's size = " + crcLength);
		assertThat(checker.check(bookSampleCRCCode), is(true));
		System.out.println(" -> No error!");
	}

	@Test
	public void errorTest() {
		BitPattern errorSampleCRCCode5 = bookSampleCRCCodes[5].clone();
		BitPattern errorSampleCRCCode6 = bookSampleCRCCodes[6].clone();
		BitPattern errorSampleCRCCode7 = bookSampleCRCCodes[7].clone();
		BitPattern errorSampleCRCCode8 = bookSampleCRCCodes[8].clone();
		BitPattern errorSampleCRCCode9 = bookSampleCRCCodes[9].clone();
		errorSampleCRCCode5.get(0).complement(); // make error at 0;
		errorSampleCRCCode6.get(1).complement(); // make error at 1;
		errorSampleCRCCode7.get(3).complement(); // make error at 3;
		errorSampleCRCCode8.get(2).complement(); // make error at 2;
		errorSampleCRCCode9.get(5).complement(); // make error at 5;
		assertThat(checker.check(errorSampleCRCCode5), is(false)); // is error?
		assertThat(checker.check(errorSampleCRCCode6), is(false)); // is error?
		assertThat(checker.check(errorSampleCRCCode7), is(false)); // is error?
		assertThat(checker.check(errorSampleCRCCode8), is(false)); // is error?
		assertThat(checker.check(errorSampleCRCCode9), is(false)); // is error?
		System.out.println("errorTest: passed");
	}

	@Test
	public void failingCheckTest() {
		assertThat(checker.check(new BitPattern(0, 0, 1, 0, 1, 1, 1)), is(false));
	}

}
