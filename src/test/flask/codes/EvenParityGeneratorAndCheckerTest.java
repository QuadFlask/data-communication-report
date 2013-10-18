package flask.codes;

import flask.codes.checker.EvenParityChecker;
import flask.codes.generator.EvenParityGenerator;
import flask.type.Bit;
import flask.type.BitPattern;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class EvenParityGeneratorAndCheckerTest {
//	@Test
//	public void evenParityOf00_bit() {
//		Bit[] dataword = toBits(0, 0);
//		Bit[] codeword = EvenParityGenerator.addEvenParity(dataword);
//		compare(codeword, 0, 0, 0);
//	}
//
//	@Test
//	public void evenParityOf01_bit() {
//		Bit[] dataword = toBits(0, 1);
//		Bit[] codeword = EvenParityGenerator.addEvenParity(dataword);
//		compare(codeword, 0, 1, 1);
//	}
//
//	@Test
//	public void evenParityCheckOf110_bit() {
//		Bit[] dataword = toBits(1, 1, 0);
//		compare(EvenParityChecker.extractDataword(dataword), 1, 1);
//		dataword = toBits(1, 0, 1);
//		compare(EvenParityChecker.extractDataword(dataword), 1, 0);
//	}
//
//	@Test
//	public void evenParityCheckOf11_shouldError_bit() {
//		Bit[] dataword = toBits(1, 1, 1);
//		assertThat(EvenParityChecker.extractDataword(dataword), IsNull.nullValue());
//	}
//
//	///////////////////////
//
//	@Test
//	public void evenParityOf00() {
//		BitPattern dataword = new BitPattern(0, 0);
//		BitPattern codeword = EvenParityGenerator.addEvenParity(dataword);
//		compare(dataword.values(), 0, 0, 0);
//	}
//
//	@Test
//	public void evenParityOf01() {
//		BitPattern dataword = new BitPattern(0, 1);
//		BitPattern codeword = EvenParityGenerator.addEvenParity(dataword);
//		compare(dataword.values(), 0, 1, 1);
//	}
//
//	@Test
//	public void evenParityCheckOf110() {
//		BitPattern dataword = new BitPattern(1, 1, 0);
//		compare(EvenParityChecker.extractDataword(dataword.values()), 1, 1);
//		dataword = new BitPattern(1, 0, 1);
//		compare(EvenParityChecker.extractDataword(dataword.values()), 1, 0);
//	}
//
//	@Test
//	public void evenParityCheckOf11_shouldError() {
//		BitPattern dataword = new BitPattern(1, 1, 1);
//		assertThat(EvenParityChecker.extractDataword(dataword.values()), IsNull.nullValue());
//	}
//
//	private Bit[] toBits(int... bitPatterns) {
//		Bit[] result = new Bit[bitPatterns.length];
//		for (int i = 0; i < bitPatterns.length; i++)
//			result[i] = new Bit(bitPatterns[i]);
//		return result;
//	}
//
//	private void compare(Bit[] bits, int... bitPattern) {
//		for (int i = 0; i < bits.length; i++)
//			compareBits(bits[i], bitPattern[i]);
//	}
//
//	private void compareBits(Bit bit, int value) {
//		//assertThat(bit.value(), is(value));
//		assertEquals(bit.value(), value);
//	}

}
