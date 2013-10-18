package flask.report;

import flask.BitUtil;
import flask.codes.checker.Checker;
import flask.codes.checker.HammingCodeChecker;
import flask.codes.checker.HammingCodeCorrector;
import flask.codes.generator.HammingCodeGenerator;
import flask.type.BitPattern;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HammingTest {
	HammingCodeGenerator generator;
	Checker checker;
	HammingCodeCorrector corrector;

	@Before
	public void setup() {
		generator = new HammingCodeGenerator();
		checker = new HammingCodeChecker();
		corrector = new HammingCodeCorrector();
	}

	@Test
	public void generateAndPrint() {
		Map<BitPattern, BitPattern> generated = generator.generateAsSequence(4);

		System.out.println("dataword => codeword (result)");
		for (BitPattern dataword : generated.keySet()) {
			BitPattern codeword = generated.get(dataword);
			String result = String.format("%s => %s (%s)",
					dataword.toString(),
					codeword.toString(),
					checkHamming(codeword));
			System.out.println(result);
		}

		assertThat(BitUtil.verifyLinear(generated), is(true));
		System.out.println("Linear");
	}

	private String checkHamming(BitPattern codeword) {
		if (checker.check(codeword)) return "No Error";
		else return "Error occur!";
	}

	@Test
	public void errorDetectionTest() {
		detectCodeIsNotValid(new BitPattern(1, 0, 0, 1, 1, 0, 1));// is not valid bit pattern
		detectCodeIsNotValid(new BitPattern(1, 1, 0, 0, 0, 1, 0));// is not valid bit pattern
		detectCodeIsNotValid(new BitPattern(1, 1, 1, 1, 0, 1, 0));// is not valid bit pattern
	}

	private void detectCodeIsNotValid(BitPattern errorcode) {
		String result = checkHamming(errorcode);
		assertThat(result, is("Error occur!"));
		System.out.println(String.format("%s is not valid", errorcode.toString()));
	}

	private void detectCodeIsValid(BitPattern errorcode) {
		String result = checkHamming(errorcode);
		assertThat(result, is("No Error"));
		System.out.println(String.format("%s is valid", errorcode.toString()));
	}

	@Test
	public void errorCorrectionTest() {
		makeErrorAndCorrect(new BitPattern(0, 1, 1, 1, 1, 0, 0), 0);
		makeErrorAndCorrect(new BitPattern(1, 1, 1, 1, 1, 1, 1), 1);
		makeErrorAndCorrect(new BitPattern(0, 0, 1, 1, 0, 0, 1), 2);
		makeErrorAndCorrect(new BitPattern(0, 1, 0, 0, 1, 0, 1), 3);
		makeErrorAndCorrect(new BitPattern(0, 0, 0, 1, 1, 1, 1), 4);
		makeErrorAndCorrect(new BitPattern(0, 1, 1, 0, 0, 1, 1), 5);
		makeErrorAndCorrect(new BitPattern(1, 0, 1, 0, 1, 0, 1), 6);
	}

	private void makeErrorAndCorrect(BitPattern codeword, int index) {
		assertThat(checkHamming(codeword), is("No Error"));

		System.out.println("Actual : " + codeword.toString());
		makeErrorAt(codeword, index); // make error at index!
		System.out.println("Error  : " + codeword.toString());

		doCorrection(codeword);
	}

	private void doCorrection(BitPattern errorword) {
		detectCodeIsNotValid(errorword);

		corrector.correct(errorword);
		System.out.print(" => [Correction] => ");

		detectCodeIsValid(errorword);
	}

	private void makeErrorAt(BitPattern word, int index) {
		word.get(index).complement();
	}

}
