package co.com.kallsonys.oms.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.kallsonys.oms.dto.GetProductByIdResponseDTO;
import co.com.kallsonys.oms.dto.ProductRankingRequestOmsDTO;
import co.com.kallsonys.oms.dto.ProductRankingResponseOmsDTO;
import co.com.kallsonys.oms.dto.RankingResponseOmsDTO;



@Local
public interface ILProductOms {
	
	public RankingResponseOmsDTO getProductRankingResponseDTO(
			ProductRankingRequestOmsDTO productRankingRequestOmsDTO);
	
	public GetProductByIdResponseDTO getProductById(Integer idProduct);

}
