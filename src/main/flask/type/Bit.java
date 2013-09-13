package flask.type;

public class Bit {
	public static final Bit ZERO = new Bit(0);
	public static final Bit ONE = new Bit(1);

	private int value = 0;

	public Bit() {
		set(0);
	}

	public Bit(int bit) {
		set(bit);
	}

	public Bit(Bit bit) {
		set(bit.value());
	}

	public void set(int bit) {
		if (bit == 0) value = 0;
		else value = 1;
	}

	public void complement() {
		value = (value == 0) ? 1 : 0;
	}

	public int value() {
		return value;
	}

	public boolean is1() {
		return value == 1;
	}

	public boolean is0() {
		return value == 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Bit)
			return ((Bit) obj).value() == value;
		else return false;
	}

	@Override
	public Object clone() {
		Bit c = (Bit) new Object();
		c.set(value);
		return c;
	}
}
