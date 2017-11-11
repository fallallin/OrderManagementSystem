package co.com.kallsonys.oms.dto;



public class ProductRankingResponseOmsDTO 
extends AbstractDTO {
	private static final long serialVersionUID = 1L;

	private Integer idProduct;
	private Integer quantity;
	

	public ProductRankingResponseOmsDTO() {
		super();
	}

	public ProductRankingResponseOmsDTO(Integer idProduct, Integer quantity) {
		super();
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
