package co.com.kallsonys.oms.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.kallsonys.oms.dto.GetProductByIdResponseDTO;
import co.com.kallsonys.oms.dto.GetProductRankingResponseDTO;
import co.com.kallsonys.oms.dto.ProductRankingRequestOmsDTO;
import co.com.kallsonys.oms.dto.ProductRankingResponseOmsDTO;
import co.com.kallsonys.oms.dto.RankingResponseOmsDTO;
import co.com.kallsonys.oms.interfaces.IRProductOms;

/*
 * Proceso de Cartera.s
 * 
 * Metodos HTTP - REST:
 * GET  - consultar - leer
 * POST - insertar  - crear
 * PUT  - actualizar 
 * DELETE - Borar
 * 
 * */
@Path("/OrderManagementSystemServices")
@Stateless
public class OrderManagementSystemServices {

	@EJB(lookup = "java:global/OrderManagementSystemEAR/OrderManagementSystemEJB/ProductOmsEJB!co.com.kallsonys.oms.interfaces.IRProductOms")
	IRProductOms productOmsEJB;

	@GET
	@Path("/getProductRanking")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GetProductRankingResponseDTO> getProductRanking(@QueryParam("initalPage") Integer initalPage,
			@QueryParam("finalPage") Integer finalPage, @QueryParam("initialDate") Date initialDate,
			@QueryParam("finalDate") Date finalDate) {

		List<GetProductRankingResponseDTO> lstGetProductRankingResponseDTO = new ArrayList<GetProductRankingResponseDTO>();
		RankingResponseOmsDTO rankingResponseOmsDTO = this.productOmsEJB.getProductRankingResponseDTO(
				new ProductRankingRequestOmsDTO(initalPage, finalPage, initialDate, finalDate));

		GetProductRankingResponseDTO getProductRankingResponseDTO;

		for (ProductRankingResponseOmsDTO item : rankingResponseOmsDTO.getLstProducts()) {

			getProductRankingResponseDTO = new GetProductRankingResponseDTO();
			getProductRankingResponseDTO.setId(item.getIdProduct());
			getProductRankingResponseDTO.setQuantity(item.getQuantity());

			GetProductByIdResponseDTO productResp = this.productOmsEJB.getProductById(item.getIdProduct());

			if (productResp != null && productResp.getResponse() && !productResp.getListProducts().isEmpty()) {
				getProductRankingResponseDTO.setName(productResp.getListProducts().get(0).getName());
				getProductRankingResponseDTO.setPrice(productResp.getListProducts().get(0).getPrice());
			}
			lstGetProductRankingResponseDTO.add(getProductRankingResponseDTO);
		}
		return lstGetProductRankingResponseDTO;
	}

}