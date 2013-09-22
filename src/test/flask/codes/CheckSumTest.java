package flask.codes;

import flask.codes.checker.CheckSumChecker;
import flask.codes.generator.CheckSumGenerator;
import flask.type.BitPattern;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckSumTest {

	@Test
	public void generateChecksumBitsTest() {
		BitPattern dataword = new BitPattern(0, 0, 0, 0, 0, 0, 0, 0);
		System.out.println(dataword.toString());
		BitPattern checksum = CheckSumGenerator.generateCheckSumBits(dataword);
		System.out.println(checksum.toString());
		assertThat(checksum.toInteger(), is(0));
		assertThat(CheckSumChecker.check(checksum), is(true));
	}
}
