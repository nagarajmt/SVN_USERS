package org.eclipse.jdt.internal.jarinjarloader;

import java.io.IOException;
import java.net.URL;

/**
 * This class will be compiled into the binary jar-in-jar-loader.zip. This ZIP is used for the
 * "Runnable JAR File Exporter"
 *
 * Handle URLs with protocol "rsrc". "rsrc:path/file.ext" identifies the content accessible as
 * classLoader.getResourceAsStream("path/file.ext"). "rsrc:path/" identifies a base-path for
 * resources to be searched. The spec "file.ext" is combined to "rsrc:path/file.ext".
 *
 * @since 3.5
 */
public class RsrcURLStreamHandler extends java.net.URLStreamHandler {

	private ClassLoader classLoader;

	public RsrcURLStreamHandler(ClassLoader classLoader) {
    	this.classLoader = classLoader;
	}

	@Override
	protected java.net.URLConnection openConnection(URL u) throws IOException {
    	return new RsrcURLConnection(u, classLoader);
    }

	@Override
    protected void parseURL(URL url, String spec, int start, int limit) {
    	String file;
    	if (spec.startsWith(JIJConstants.INTERNAL_URL_PROTOCOL_WITH_COLON))
    		file = spec.substring(5);
    	else if (url.getFile().equals(JIJConstants.CURRENT_DIR))
    		file = spec;
    	else if (url.getFile().endsWith(JIJConstants.PATH_SEPARATOR))
    		file = url.getFile() + spec;
		else if (JIJConstants.RUNTIME.equals(spec))
    		file = url.getFile();
    	else
    		file = spec;
    	setURL(url, JIJConstants.INTERNAL_URL_PROTOCOL, "", -1, null, null, file, null, null);	 //$NON-NLS-1$
    }

}
