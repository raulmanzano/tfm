package es.manzano.tfm.filters;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.FilterException;

public interface Filter {

	public void filter(Info previous, Info current) throws FilterException;
}
