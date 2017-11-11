package co.com.kallsonys.oms.dto;

import java.util.List;

public class RankingResponseOmsDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;

	private Boolean response;
	private String responseDescription;
	List<ProductRankingResponseOmsDTO> lstProducts;

	public Boolean getResponse() {
		return response;
	}

	public void setResponse(Boolean response) {
		this.response = response;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public List<ProductRankingResponseOmsDTO> getLstProducts() {
		return lstProducts;
	}

	public void setLstProducts(List<ProductRankingResponseOmsDTO> lstProducts) {
		this.lstProducts = lstProducts;
	}

}
