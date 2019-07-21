package es.manzano.tfm.filters;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.FilterException;
import es.manzano.tfm.exceptions.PreviousAllowedFilterException;

public class PreviousAllowedFilter  implements Filter {

	private Set<String> graph = new HashSet<String>();

	public PreviousAllowedFilter() {
		
		try {
			Configuration config = new PropertiesConfiguration("paginas.properties");
			String[] paginas = config.getStringArray("paginas");
			for (String origen : paginas) {
				String[] destinos = config.getStringArray(origen);
				for (String destino : destinos) {
					this.graph.add(origen + destino);	
				}			
			}
		} catch (ConfigurationException e) {
			System.out.println(e);
		}
		
		
		
		
//		this.graph.add("login" + "login");
//		this.graph.add("login" + "pagina1");
//		this.graph.add("pagina1" + "pagina2");
//		this.graph.add("pagina2" + "pagina2");
//		this.graph.add("pagina1" + "logout");
//		this.graph.add("pagina2" + "pagina3");
//		this.graph.add("pagina2" + "logout");
//		this.graph.add("pagina3" + "logout");
//		this.graph.add("pagina3" + "pagina1");
//		this.graph.add("logout" + "login");

	}

	public void filter(Info previousInfo, Info currentInfo) throws FilterException {

		if (!(this.graph.contains(currentInfo.getPrevious() + currentInfo.getCurrent())))
			throw new PreviousAllowedFilterException("Acceso desde pagina no permitida");

	}

}
