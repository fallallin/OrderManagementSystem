package co.com.kallsonys.oms.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.kallsonys.oms.dto.GetProductByIdResponseDTO;
import co.com.kallsonys.oms.dto.ProductRankingRequestOmsDTO;
import co.com.kallsonys.oms.dto.ProductRankingResponseOmsDTO;
import co.com.kallsonys.oms.dto.RankingResponseOmsDTO;



@Remote
public interface IRProductOms {
	
	public RankingResponseOmsDTO getProductRankingResponseDTO(ProductRankingRequestOmsDTO productRankingRequestOmsDTO);
	
	public GetProductByIdResponseDTO getProductById(Integer idProduct);

}
