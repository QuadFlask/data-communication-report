package flask.report;

import flask.codes.checker.CheckSumChecker;
import flask.codes.checker.Checker;
import flask.codes.generator.CheckSumGenerator;
import flask.codes.generator.Generator;
import flask.type.BitPattern;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChecksumTest {
	public static final int CHECKSUM_SIZE = 8;
	CheckSumGenerator generator;
	Checker checker;

	@Before
	public void setup() {
		generator = new CheckSumGenerator(CHECKSUM_SIZE);
		checker = new CheckSumChecker(CHECKSUM_SIZE);
	}

	@Test
	public void checksumTest() {
		// 0x54 + 0x65 + 0x73 + 0x74 = 0x1A0
		//   0x01
		// + 0xA0
		// ------
		//   0xA1
		// 1's complemnt -> 5E
		checksumTestOf("Test", 0x5E);

		// 0x46 + 0x4C + 0x41 + 0x53 + 0x4B = 0x171
		//   0x01
		// + 0x71
		//-------
		//   0x72
		// 1's complement -> 0x8D
		checksumTestOf("FLASK", 0x8D);

		// 0x44 + 0x61 + 0x74 + 0x61 + 0x20 + 0x63 + 0x6F + 0x6D + 0x6D + 0x75
		// + 0x6E + 0x69 + 0x63 + 0x61 + 0x74 + 0x69 + 0x6F + 0x6E
		//  = 0x710
		//   0x07
		// + 0x10
		// ------
		//   0x17
		// 1's complement -> E8
		checksumTestOf("Data communication", 0xE8);

		// 0x43 + 0x6F + 0x6D + 0x70 + 0x75 + 0x74 + 0x65 + 0x72 + 0x20 + 0x65
		// + 0x6E + 0x67 + 0x69 + 0x6E + 0x65 + 0x65 + 0x72 + 0x69 + 0x6E + 0x67
		//  = 0x7FA
		//   0x07
		// + 0xFA
		// ------
		//  0x101
		//
		//   0x01
		// + 0x01
		// ------
		//   0x02
		// 1's complement -> 0xFD
		checksumTestOf("Computer engineering", 0xFD);
	}

	private void checksumTestOf(String string, int expectedChecksum) {
		BitPattern codeword = new BitPattern(string);  // String to ASCII code
		BitPattern checksum = generator.generate(codeword);

		System.out.print(codeword.toStringASCII());
		System.out.print(" : " + codeword.toString());
		System.out.println("         [" + codeword.toString16() + "  ]");

		assertThat(generator.generateCheckSumBits(codeword).toInteger(), is(expectedChecksum));

		System.out.println("--Checksum added--");
		System.out.print(codeword.toStringASCII());
		System.out.print(" : " + checksum.toString());
		System.out.println(" [" + checksum.toString16() + "]");

		assertThat(checker.check(checksum), is(true));
		System.out.println("Checksum is 0 -> No Error!\n\n");
	}


}
