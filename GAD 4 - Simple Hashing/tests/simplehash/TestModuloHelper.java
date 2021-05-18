package tests.simplehash;

import gad.simplehash.ModuloHelper;

public class TestModuloHelper implements ModuloHelper {
	@Override
	public int doTheMagic(int i, int divisor) {
		return i%divisor;
	}
}
