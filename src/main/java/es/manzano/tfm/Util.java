package es.manzano.tfm;

public class Util {

	public static String extractPageNameFromURLString(String urlString) {
		if (urlString == null)
			return null;
		int lastSlash = urlString.lastIndexOf("/");
		// if (lastSlash==-1) lastSlash = 0;
		String pageAndExtensions = urlString.substring(lastSlash + 1);
		int lastQuestion = pageAndExtensions.lastIndexOf("?");
		if (lastQuestion == -1)
			lastQuestion = pageAndExtensions.length();
		String result = pageAndExtensions.substring(0, lastQuestion);

		int lastpoint = result.lastIndexOf(".");
		if (lastpoint != -1) {
			result = result.substring(0, lastpoint);
		}
		return result;
	}

}
