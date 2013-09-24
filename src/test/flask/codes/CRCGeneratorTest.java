package flask.codes;

import flask.codes.checker.CRCChecker;
import flask.codes.generator.BitPatternSequenceGenerator;
import flask.codes.generator.CRCGenerator;
import flask.type.Bit;
import flask.type.BitPattern;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CRCGeneratorTest {

	private BitPattern sampleDataword;
	private BitPattern sampleDataword2;
	private BitPattern bookSampleCRCCode0;
	private BitPattern bookSampleCRCCode1;
	private BitPattern bookSampleCRCCode2;
	private BitPattern bookSampleCRCCode3;
	private BitPattern bookSampleCRCCode4;
	private BitPattern bookSampleCRCCode5;
	private BitPattern bookSampleCRCCode6;
	private BitPattern bookSampleCRCCode7;
	private BitPattern bookSampleCRCCode8;
	private BitPattern bookSampleCRCCode9;
	private BitPattern bookSampleCRCCode10;
	private BitPattern bookSampleCRCCode11;
	private BitPattern bookSampleCRCCode12;
	private BitPattern bookSampleCRCCode13;
	private BitPattern bookSampleCRCCode14;
	private BitPattern bookSampleCRCCode15;

	private BitPattern polynomial = new BitPattern(1, 0, 1, 1);
	private int crcLength = 3;

	@Before
	public void setUp() {
		sampleDataword = new BitPattern(1, 0, 1, 0, 0, 1, 0, 1, 1);
		sampleDataword2 = new BitPattern(1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0);

		bookSampleCRCCode0 = new BitPattern(0, 0, 0, 0, 0, 0, 0);
		bookSampleCRCCode1 = new BitPattern(0, 0, 0, 1, 0, 1, 1);
		bookSampleCRCCode2 = new BitPattern(0, 0, 1, 0, 1, 1, 0);
		bookSampleCRCCode3 = new BitPattern(0, 0, 1, 1, 1, 0, 1);
		bookSampleCRCCode4 = new BitPattern(0, 1, 0, 0, 1, 1, 1);
		bookSampleCRCCode5 = new BitPattern(0, 1, 0, 1, 1, 0, 0);
		bookSampleCRCCode6 = new BitPattern(0, 1, 1, 0, 0, 0, 1);
		bookSampleCRCCode7 = new BitPattern(0, 1, 1, 1, 0, 1, 0);
		bookSampleCRCCode8 = new BitPattern(1, 0, 0, 0, 1, 0, 1);
		bookSampleCRCCode9 = new BitPattern(1, 0, 0, 1, 1, 1, 0);
		bookSampleCRCCode10 = new BitPattern(1, 0, 1, 0, 0, 1, 1);
		bookSampleCRCCode11 = new BitPattern(1, 0, 1, 1, 0, 0, 0);
		bookSampleCRCCode12 = new BitPattern(1, 1, 0, 0, 0, 1, 0);
		bookSampleCRCCode13 = new BitPattern(1, 1, 0, 1, 0, 0, 1);
		bookSampleCRCCode14 = new BitPattern(1, 1, 1, 0, 1, 0, 0);
		bookSampleCRCCode15 = new BitPattern(1, 1, 1, 1, 1, 1, 1);
	}

	@Test
	public void crc() {
		Bit[] crc = CRCGenerator.crc(sampleDataword, polynomial, crcLength);
		checkSame(crc, 0, 1, 0);

		crc = CRCGenerator.crc(sampleDataword2, polynomial, crcLength);
		checkSame(crc, 1, 0, 0);
	}

	private void checkSame(Bit[] bit, int... value) {
		for (int i = 0; i < bit.length; i++)
			assertThat(bit[i].value(), is(value[i]));
	}

	@Test
	public void sampleGenerateTest() {
		BitPattern codeword = CRCGenerator.generate(sampleDataword, polynomial, crcLength);
		checkSame(codeword.values(), 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0);
	}

	@Test
	public void bookCRCTest() {
		List<BitPattern> datawords = BitPatternSequenceGenerator.generate(4); // 0000 ~ 1111

		check(datawords.get(0), bookSampleCRCCode0.toString()); // "0000000"
		check(datawords.get(1), bookSampleCRCCode1.toString()); // "0001011"
		check(datawords.get(2), bookSampleCRCCode2.toString()); // "0010110"
		check(datawords.get(3), bookSampleCRCCode3.toString()); // "0011101"
		check(datawords.get(4), bookSampleCRCCode4.toString()); // "0100111"
		check(datawords.get(5), bookSampleCRCCode5.toString()); // "0101100"
		check(datawords.get(6), bookSampleCRCCode6.toString()); // "0110001"
		check(datawords.get(7), bookSampleCRCCode7.toString()); // "0111010"
		check(datawords.get(8), bookSampleCRCCode8.toString()); // "1000101"
		check(datawords.get(9), bookSampleCRCCode9.toString()); // "1001110"
		check(datawords.get(10), bookSampleCRCCode10.toString()); // "1010011"
		check(datawords.get(11), bookSampleCRCCode11.toString()); // "1011000"
		check(datawords.get(12), bookSampleCRCCode12.toString()); // "1100010"
		check(datawords.get(13), bookSampleCRCCode13.toString()); // "1101001"
		check(datawords.get(14), bookSampleCRCCode14.toString()); // "1110100"
		check(datawords.get(15), bookSampleCRCCode15.toString()); // "1111111"

	}

	private void check(BitPattern dataword, String booksCode) {
		System.out.print("Checking crc (dataword: " + dataword.toString() + ")");
		BitPattern generatedCodeword = CRCGenerator.generate(dataword, polynomial, crcLength);
		assertThat(generatedCodeword.toString(), is(booksCode));
		System.out.println(" generated codeword: '" + generatedCodeword.toString() + "' is match with '" + booksCode + "'");
	}

	@Test
	public void checkCRC() {
		CRCCheckerTest(bookSampleCRCCode0, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode1, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode2, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode3, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode4, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode5, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode6, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode7, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode8, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode9, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode10, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode11, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode12, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode13, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode14, polynomial, crcLength);
		CRCCheckerTest(bookSampleCRCCode15, polynomial, crcLength);
	}


	private void CRCCheckerTest(BitPattern bookSampleCRCCode, BitPattern polynomial, int crcLength) {
		System.out.print("Checking CRC: '" + bookSampleCRCCode.toString() + "' with polynomial: '" + polynomial.toString() + "' and CRC's size = " + crcLength);
		assertThat(CRCChecker.check(bookSampleCRCCode, polynomial, crcLength), is(true));
		System.out.println(" -> No error!");
	}

	@Test
	public void errorTest() {
		BitPattern errorSampleCRCCode5 = bookSampleCRCCode5.clone();
		BitPattern errorSampleCRCCode6 = bookSampleCRCCode6.clone();
		BitPattern errorSampleCRCCode7 = bookSampleCRCCode7.clone();
		BitPattern errorSampleCRCCode8 = bookSampleCRCCode8.clone();
		BitPattern errorSampleCRCCode9 = bookSampleCRCCode9.clone();
		errorSampleCRCCode5.get(0).complement(); // make error at 0;
		errorSampleCRCCode6.get(1).complement(); // make error at 1;
		errorSampleCRCCode7.get(3).complement(); // make error at 3;
		errorSampleCRCCode8.get(2).complement(); // make error at 2;
		errorSampleCRCCode9.get(5).complement(); // make error at 5;
		assertThat(CRCChecker.check(errorSampleCRCCode5, polynomial, crcLength), is(false)); // is error?
		assertThat(CRCChecker.check(errorSampleCRCCode6, polynomial, crcLength), is(false)); // is error?
		assertThat(CRCChecker.check(errorSampleCRCCode7, polynomial, crcLength), is(false)); // is error?
		assertThat(CRCChecker.check(errorSampleCRCCode8, polynomial, crcLength), is(false)); // is error?
		assertThat(CRCChecker.check(errorSampleCRCCode9, polynomial, crcLength), is(false)); // is error?
		System.out.println("errorTest: passed");
	}

	@Test
	public void failingCheckTest() {
		assertThat(CRCChecker.check(new BitPattern(0, 0, 1, 0, 1, 1, 1), polynomial, crcLength), is(false));
	}

}
