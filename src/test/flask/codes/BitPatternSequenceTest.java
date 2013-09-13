package flask.codes;

import flask.codes.generator.BitPatternSequenceGenerator;
import flask.type.BitPattern;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BitPatternSequenceTest {

	@Test
	public void generate1Test() {
		List<BitPattern> sequence = BitPatternSequenceGenerator.generate(1);
		assertThat(sequence.get(0), is(new BitPattern(0)));
		assertThat(sequence.get(1), is(new BitPattern(1)));
	}

	@Test
	public void generate2Test() {
		List<BitPattern> sequence = BitPatternSequenceGenerator.generate(2);
		assertThat(sequence.get(0), is(new BitPattern(0, 0)));
		assertThat(sequence.get(1), is(new BitPattern(0, 1)));
		assertThat(sequence.get(2), is(new BitPattern(1, 0)));
		assertThat(sequence.get(3), is(new BitPattern(1, 1)));
	}

	@Test
	public void generate3Test() {
		List<BitPattern> sequence = BitPatternSequenceGenerator.generate(3);
		assertThat(sequence.get(0), is(new BitPattern(0, 0, 0)));
		assertThat(sequence.get(1), is(new BitPattern(0, 0, 1)));
		assertThat(sequence.get(2), is(new BitPattern(0, 1, 0)));
		assertThat(sequence.get(3), is(new BitPattern(0, 1, 1)));
		assertThat(sequence.get(4), is(new BitPattern(1, 0, 0)));
		assertThat(sequence.get(5), is(new BitPattern(1, 0, 1)));
		assertThat(sequence.get(6), is(new BitPattern(1, 1, 0)));
		assertThat(sequence.get(7), is(new BitPattern(1, 1, 1)));
	}

}
