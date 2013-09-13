package flask.type;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BitTest {

	@Test
	public void complementTest() {
		Bit bit = new Bit(0);
		assertThat(bit.value(), is(0));
		bit.complement();
		assertThat(bit.value(), is(1));
		bit.complement();
		assertThat(bit.value(), is(0));
	}


}
