package es.manzano.tfm.filters;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.TokenFilterException;

public class TokenFilter  implements Filter {

	public void filter(Info previous, Info current) throws TokenFilterException {

		if (previous.getTokenCurrent() != null) {
			if (current.getTokenPrevious() != null) {
				if (!current.getTokenPrevious().equalsIgnoreCase(previous.getTokenCurrent()))
					throw new TokenFilterException("Token diferentes");
			} else {
				throw new TokenFilterException("Token diferentes uno null");
			}
		} else {
			if (current.getTokenPrevious() != null) {
				throw new TokenFilterException("Token diferentes uno null");
			}
		}

	}
}
