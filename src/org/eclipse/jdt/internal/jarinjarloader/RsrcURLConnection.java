package org.eclipse.jdt.internal.jarinjarloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;


/**
 * This class will be compiled into the binary jar-in-jar-loader.zip. This ZIP is used for the
 * "Runnable JAR File Exporter"
 *
 * @since 3.5
 */
public class RsrcURLConnection extends URLConnection {

	private ClassLoader classLoader;

	public RsrcURLConnection(URL url, ClassLoader classLoader) {
		super(url);
		this.classLoader= classLoader;
	}

	@Override
	public void connect() throws IOException {
	}

	@Override
	public InputStream getInputStream() throws IOException {
		String file= URLDecoder.decode(url.getFile(), JIJConstants.UTF8_ENCODING);
		InputStream result= classLoader.getResourceAsStream(file);
		if (result == null) {
			throw new MalformedURLException("Could not open InputStream for URL '" + url + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return result;
	}


}