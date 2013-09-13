package flask.codes;

import flask.codes.checker.CheckSumChecker;
import flask.codes.generator.CheckSumGenerator;
import flask.type.BitPattern;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckSumTest {

	@Test
	public void valueOfTest() {
		BitPattern bitPattern = new BitPattern(1, 0, 0, 1, 0, 0, 1);
		assertThat(CheckSumGenerator.valueOf(bitPattern, 0, 2), is(2));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 0, 3), is(4));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 0, 4), is(9));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 0, 5), is(18));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 0, 6), is(36));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 0, 7), is(73));

		assertThat(CheckSumGenerator.valueOf(bitPattern, 1, 1), is(0));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 1, 2), is(0));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 1, 3), is(1));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 1, 4), is(2));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 1, 5), is(4));
		assertThat(CheckSumGenerator.valueOf(bitPattern, 1, 6), is(9));
	}

	//@Ignore
	@Test
	public void checksumGenerateTest() {
		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0); // 15bits
		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), 1); // 15bits
		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1), 9); // 15bits

		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0); // 16bits
		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), 1);
		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0), 2);
		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0), 6);
		testCheckSum(new BitPattern(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), (int) Math.pow(2, 15));
		testCheckSum(new BitPattern(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), (int) Math.pow(2, 16) - 1);

		testCheckSum(new BitPattern(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 1);  // 17bits
		testCheckSum(new BitPattern(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), 2);
		testCheckSum(new BitPattern(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1), 4);

		testCheckSum(new BitPattern(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 1); // 18bits
		testCheckSum(new BitPattern(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 2);
		testCheckSum(new BitPattern(1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 3);
		testCheckSum(new BitPattern(1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), 4);

		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
									0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0); // 32bits

		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
									0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 1); // 32bits

		testCheckSum(new BitPattern(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
									0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), 2); // 32bits

		testCheckSum(new BitPattern(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
									1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), 2); // 32bits
		// checksum %= 2^16
	}

	private void testCheckSum(BitPattern dataword, int checksumValue) {
		assertThat(CheckSumGenerator.generateCheckSumBits(dataword).toInteger(), is(checksumValue));
	}

	@Test
	public void checkGeneratedCodewords() {
		BitPattern codeword = CheckSumGenerator.generate(new BitPattern(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		assertThat(CheckSumChecker.check(codeword), is(true));
	}
}
