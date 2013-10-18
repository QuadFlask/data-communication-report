package flask.codes.generator;

import flask.type.BitPattern;

public interface Generator {

	public BitPattern generate(BitPattern dataword);
}
