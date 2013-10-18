package flask.type;

import java.util.ArrayList;
import java.util.List;

public class BitPattern {
	private List<Bit> bits = new ArrayList<Bit>();

	public BitPattern(int... values) {
		for (int i = 0; i < values.length; i++) {
			bits.add(new Bit(values[i]));
		}
	}

	public BitPattern(String str) {
		int length;
		char[] chars = str.toCharArray();
		for (char c : chars) {
			String binary = Integer.toBinaryString(c);
			length = 8 - binary.length();
			for (int i = 0; i < length; i++)
				append(new Bit(0));
			for (int i = 0; i < binary.length(); i++)
				append(new Bit(Integer.parseInt("" + binary.charAt(i))));
		}
	}

	public BitPattern() {
	}

	public void append(Bit bit) {
		bits.add(bit);
	}

	public void removeBack() {
		bits.remove(length() - 1);
	}

	public void complement() {
		for (Bit bit : bits)
			bit.complement();
	}

	public Bit value(int index) {
		return bits.get(index);
	}

	public int length() {
		return bits.size();
	}

	public Bit[] values() {
		return bits.toArray(new Bit[length()]);
	}

	public Bit get(int index) {
		return bits.get(index);
	}

	public void set(long decimal, int length) {
		bits.clear();
		String binary = Long.toBinaryString(decimal);
		for (int i = 0; i < length - binary.length(); i++)
			append(new Bit(0));
		for (int i = Math.abs(Math.min(length - binary.length(), 0)); i < binary.length(); i++)
			append(new Bit(Integer.parseInt("" + binary.charAt(i))));
	}

	public void setLength(int length) {
		bits.clear();
		for (int i = 0; i < length; i++)
			bits.add(new Bit());
	}

	public void leftRotateShift() {
		Bit head = bits.get(0);
		bits.remove(0);
		append(head);
	}

	public void leftShift() {
		bits.remove(0);
		append(new Bit());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BitPattern) {
			BitPattern p = (BitPattern) obj;
			Bit[] values = p.values();
			for (int i = 0; i < values.length; i++)
				if (!values[i].equals(this.value(i)))
					return false;
			return true;
		} else return false;
	}

	@Override
	public String toString() {
		String str = "";
		for (Bit bit : bits)
			str += bit.value();
		return str;
	}

	@Override
	public BitPattern clone() {
		BitPattern cloned = new BitPattern();
		for (Bit bit : bits)
			cloned.append(bit.clone());
		return cloned;
	}

	public String toString16() {
		String str = "";
		int sum;
		for (int k = 0; k < bits.size() / 8; k++) {
			sum = 0;
			for (int i = 0; i < 8; i++)
				sum += bits.get(k * 8 + i).value() * Math.pow(2, 7 - i);
			str += "" + Integer.toHexString(sum).toUpperCase();
		}
		return str;
	}

	public String toStringASCII() {
		String str = "";
		char sum;
		for (int k = 0; k < bits.size() / 8; k++) {
			sum = 0;
			for (int i = 0; i < 8; i++)
				sum += bits.get(k * 8 + i).value() * Math.pow(2, 7 - i);
			str += "" + Character.toString(sum);
		}
		return str;
	}

	public Integer toInteger() {
		String str = "";
		for (Bit bit : bits)
			str += bit.value();
		return Integer.parseInt(str, 2);
	}

	public Long toLong() {
		String str = "";
		for (Bit bit : bits)
			str += bit.value();
		return Long.getLong(str, 2);
	}
}
