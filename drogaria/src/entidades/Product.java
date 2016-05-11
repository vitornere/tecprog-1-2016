package entidades;

public class Product {

	protected String name;
	protected String maker;
	protected String recommendation; // uso adulto, infantil, todos os p�blicos etc
	protected String validity;

	public Product() {
	}

	public Product(String nameProduct, String makerProduct, String recommendationProduct, String validityProduct) {
		this.name = nameProduct;
		this.maker = makerProduct;
		this.recommendation = recommendationProduct;
		this.validity = validityProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

}
