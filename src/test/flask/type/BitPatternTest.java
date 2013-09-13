package flask.type;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BitPatternTest {
	@Test
	public void create() {
		BitPattern bitPattern = new BitPattern(0);
		assertThat(bitPattern.value(0).value(), is(0));

		bitPattern = new BitPattern(1, 0, 0, 1);
		compare(bitPattern, 1, 0, 0, 1);
	}

	@Test
	public void complement() {
		BitPattern bitPattern = new BitPattern(1, 0, 0, 1);
		bitPattern.complement();
		compare(bitPattern, 0, 1, 1, 0);
	}

	@Test
	public void setTest() {
		BitPattern b = new BitPattern();
		b.set(5, 3);
		compare(b, 1, 0, 1);
		assertThat(b.toInteger(), is(5));

		b.set(5, 4);
		compare(b, 0, 1, 0, 1);
		assertThat(b.toInteger(), is(5));
	}

	private void compare(BitPattern bitPattern, int... expected) {
		for (int i = 0; i < bitPattern.length(); i++)
			assertThat(bitPattern.value(i).value(), is(expected[i]));
	}


}
