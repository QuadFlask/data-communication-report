package flask.type;

@Deprecated
public class BitPattern2 {
	private int value;
	private int[] bitPosition;

	public BitPattern2() {
		bitPosition = new int[Integer.SIZE];
	}

	public int bitCount() {
		return Integer.bitCount(value);
	}

	public void complement() {
		value = ~value;
	}

	public int value() {
		return value;
	}

}
