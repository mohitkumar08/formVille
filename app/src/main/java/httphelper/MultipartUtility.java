package httphelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * This utility class provides an abstraction layer for sending multipart HTTP
 * POST requests to a web server.
 *
 * @author www.codejava.net
 */
public class MultipartUtility {
	/**
	 * LINE_FEED variable
	 */
	private static final String LINE_FEED = "\r\n";
	/**
	 * boundary variable
	 */
	transient private final String boundary;
	/**
	 * instance of HttpURLConnection
	 */
	transient private final HttpURLConnection httpConn;
	/**
	 * instance of OutputStream
	 */
	transient private final OutputStream outputStream;
	/**
	 * instance of PrintWriter
	 */
	transient private final PrintWriter writer;

	/**
	 * constructor
	 */
	public MultipartUtility( final String requestURL, final String charset )
			throws IOException {
		super();
		// creates a unique boundary based on time stamp
		boundary = "===" + System.currentTimeMillis() + "===";

		final URL url = new URL(requestURL);
		httpConn = ( HttpURLConnection ) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true); // indicates POST method
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
		httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
		httpConn.setRequestProperty("Test", "Bonjour");
		outputStream = httpConn.getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
				true);
	}

	/**
	 * add file part
	 */
	public void addFilePart( final String fieldName, final File uploadFile )
			throws IOException {
		final String fileName = uploadFile.getName();
		writer.append("--" + boundary).append(LINE_FEED);
		writer.append(
				"Content-Disposition: form-data; name=\"" + fieldName
						+ "\"; filename=\"" + fileName + "\"")
				.append(LINE_FEED);
		writer.append(
				"Content-Type: "
						+ URLConnection.guessContentTypeFromName(fileName))
				.append(LINE_FEED);
		writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
		writer.append(LINE_FEED);
		writer.flush();

		final FileInputStream inputStream = new FileInputStream(uploadFile);
		final byte[] buffer = new byte[4096];
		int bytesRead;
		while ( - 1 != (bytesRead = inputStream.read(buffer)) ) {
			outputStream.write(buffer, 0, bytesRead);
		}

		/*while ( Constant.MINUS_ONE != inputStream.read(buffer) ) {
			bytesRead = inputStream.read(buffer);
			outputStream.write(buffer, 0, bytesRead);
		}*/


		outputStream.flush();
		inputStream.close();

		writer.append(LINE_FEED);
		writer.flush();
	}

	/**
	 * Adds a header field to the request.
	 *
	 * @param name  - name of the header field
	 * @param value - value of the header field
	 */
	public void addHeaderField( final String name, final String value ) {
		writer.append(name + ": " + value).append(LINE_FEED);
		writer.flush();
	}

	/**
	 * Completes the request and receives response from the server.
	 *
	 * @return a list of Strings as response in case the server returned
	 * status OK, otherwise an exception is thrown.
	 * @throws java.io.IOException
	 */
	public JSONObject finish() throws IOException {
		JSONObject jsonObject = null;
		writer.append(LINE_FEED).flush();
		writer.append("--" + boundary + "--").append(LINE_FEED);
		writer.close();

		// checks server's status code first
		final int status = httpConn.getResponseCode();
		if ( status == HttpURLConnection.HTTP_OK ) {
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
			/*String line;
			while ( null != (line = reader.readLine()) ) {
				response.add(line);
			}*/

			StringBuilder stringBuilder = new StringBuilder();
			String line;

			while ( (line = bufferedReader.readLine()) != null ) {
				stringBuilder.append(line + '\n');
			}
			String jsonString = stringBuilder.toString();
			try {
				jsonObject = new JSONObject(jsonString);
			} catch ( JSONException e ) {
				e.printStackTrace();
			}
			/*
			while ( null != reader.readLine() ) {
				line = reader.readLine();
				response.add(line);
			}
*/
			bufferedReader.close();
			httpConn.disconnect();
		} else {
			throw new IOException("Server returned non-OK status: " + status);
		}

		return jsonObject;
	}
}