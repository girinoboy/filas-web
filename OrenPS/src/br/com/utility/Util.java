package br.com.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {

	/**
     * Ler o arquivo de uma url.
     * @param	String	nomeArquivo
     * @param	String	diretorio
     * @throws	Exception
     */
    public static String lerArquivoURL(URL url) throws FileNotFoundException, IOException{
    	
    	// cria o objeto url
		// cria o objeto httpurlconnection
		HttpURLConnection connection =
			(HttpURLConnection) url.openConnection();

		// seta o metodo
		connection.setRequestProperty("Request-Method","GET");

		// seta a variavel para ler o resultado
		connection.setDoInput(true);
		connection.setDoOutput(false);

		// conecta com a url destino
		connection.connect();

		// abre a conexão pra input
		BufferedReader br =
			new BufferedReader(new InputStreamReader(connection.getInputStream()));

		// le ate o final
		StringBuffer newData = new StringBuffer(20000);
		String s = "";
		while (null != ((s = br.readLine()))) {
			newData.append(s);
		}
		
		br.close();
		return newData.toString();		
    }

}
