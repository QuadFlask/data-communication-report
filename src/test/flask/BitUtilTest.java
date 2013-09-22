package flask;

import flask.type.Bit;
import flask.type.BitPattern;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BitUtilTest {
	@Test
	public void toIntegerTest() {
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(0)}), is(0));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(1)}), is(1));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(0), new Bit(1)}), is(1));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(1), new Bit(0)}), is(2));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(1), new Bit(1)}), is(3));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(1), new Bit(0), new Bit(0)}), is(4));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(1), new Bit(0), new Bit(1)}), is(5));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(1), new Bit(1), new Bit(0)}), is(6));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(1), new Bit(1), new Bit(1)}), is(7));
		assertThat(BitUtil.toInteger(new Bit[]{new Bit(1), new Bit(0), new Bit(0), new Bit(0)}), is(8));
	}

	@Test
	public void bitpatternTest() {
		BitPattern b = new BitPattern("abcdefgABCDEFG");

		System.out.println(b.toString());
		System.out.println(b.toString16());
		System.out.println(b.toStringASCII());
	}
}
