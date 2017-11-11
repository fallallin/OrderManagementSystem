package co.com.kallsonys.oms.ejb;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import co.com.kallsonys.oms.dto.GetProductByIdResponseDTO;
import co.com.kallsonys.oms.dto.ProductRankingRequestOmsDTO;
import co.com.kallsonys.oms.dto.ProductRankingResponseOmsDTO;
import co.com.kallsonys.oms.dto.RankingResponseOmsDTO;
import co.com.kallsonys.oms.interfaces.ILProductOms;
import co.com.kallsonys.oms.interfaces.IRProductOms;
import co.com.kalsonys.oms.util.Util;

/**
 * Session Bean implementation class ImposicionISEJB
 */
@Stateless(name = "ProductOmsEJB")
@LocalBean
public class ProductOmsEJB implements ILProductOms, IRProductOms {

	private final static Logger logger = Logger.getLogger(ProductOmsEJB.class.getName());

	//TODO CAMBIAR 
	//private final static String IP_DATA_SERVCE = "192.168.1.2";
	private final static String IP_DATA_SERVCE = "localhost";
	private final static String PORT_DATA_SERVICE = "8081";

	@Override
	public RankingResponseOmsDTO getProductRankingResponseDTO(
			ProductRankingRequestOmsDTO productRankingRequestOmsDTO) {

		RankingResponseOmsDTO respuesta = new RankingResponseOmsDTO();

		String url = "http://" + IP_DATA_SERVCE + ":" + PORT_DATA_SERVICE
				+ "/DataServiceWeb/service/Orders/getProductRanking?initalPage="
				+ productRankingRequestOmsDTO.getInitalPage() + "&finalPage="
				+ productRankingRequestOmsDTO.getFinalPage() + "&initialDate="
				+ Util.DateFormater(productRankingRequestOmsDTO.getInitialDate()) + "&finalDate="
				+ Util.DateFormater(productRankingRequestOmsDTO.getFinalDate());

		ClientRequest request = new ClientRequest(url);

		request.accept("application/json");
		// request.body("application/json", consultaPrefacturaWSDTO);

		try {

			// ClientResponse<String> response = request.get(String.class);

			ClientResponse<HashMap> response = request.get(HashMap.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			// BufferedReader br = new BufferedReader(
			// new InputStreamReader(new
			// ByteArrayInputStream(response.getEntity().getBytes())));
			//
			// String output;
			// System.out.println("Output from Server .... \n");
			// while ((output = br.readLine()) != null) {
			// System.out.println(output);
			// }

			ObjectMapper o = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			o.setDateFormat(df);
			respuesta = o.readValue(o.writeValueAsBytes(response.getEntity()), RankingResponseOmsDTO.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return respuesta;

	}
	
	@Override
	public GetProductByIdResponseDTO getProductById(Integer idProduct) {

		GetProductByIdResponseDTO respuesta = new GetProductByIdResponseDTO();

		String url = "http://" + IP_DATA_SERVCE + ":" + PORT_DATA_SERVICE
				+ "/DataServiceWeb/service/Products/" + String.valueOf(idProduct);

		ClientRequest request = new ClientRequest(url);
		request.accept("application/json");

		try {

			ClientResponse<HashMap> response = request.get(HashMap.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			ObjectMapper o = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			o.setDateFormat(df);
			respuesta = o.readValue(o.writeValueAsBytes(response.getEntity()), respuesta.getClass());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return respuesta;

	}
}
