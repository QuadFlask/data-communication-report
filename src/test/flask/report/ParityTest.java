package flask.report;

import flask.codes.checker.Checker;
import flask.codes.checker.EvenParityChecker;
import flask.codes.generator.BitPatternSequenceGenerator;
import flask.codes.generator.EvenParityGenerator;
import flask.codes.generator.Generator;
import flask.type.BitPattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParityTest {
	Generator generator;
	Checker checker;

	@Before
	public void setup() {
		generator = new EvenParityGenerator();
		checker = new EvenParityChecker();
	}

	@Test
	public void generateAndPrint() {
		List<BitPattern> asciiCodes = BitPatternSequenceGenerator.generate(7);

		for (BitPattern asciiCode : asciiCodes) {
			BitPattern codeword = generator.generate(asciiCode);
			Character ch = toCharacter(codeword);
			if ('!' <= ch && ch <= '~') {
				String result = String.format("[%c] %s => %s (%s)",
						ch.charValue(),
						asciiCode.toString(),
						codeword.toString(),
						checkParity(codeword));
				System.out.println(result);
			}
		}
	}

	private Character toCharacter(BitPattern bitPattern) {
		return (char) bitPattern.toInteger().intValue();
	}

	private String checkParity(BitPattern bits) {
		if (checker.check(bits)) return "No Error";
		else return "Error occur!";
	}

	@Test
	public void errorDetectionTest() {
		detection(new BitPattern(0, 0, 0, 0, 0, 0, 0, 1));
		detection(new BitPattern(1, 1, 0, 1, 0, 1, 1, 0));
		detection(new BitPattern(1, 1, 0, 0, 1, 1, 0, 1));
	}

	private void detection(BitPattern errorcode) {
		String result = checkParity(errorcode);
		assertThat(result, is("Error occur!"));
		System.out.println(String.format("'%s' is not valid", errorcode.toString()));
	}

}
