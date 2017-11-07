package com.sapient.wellington.pojo;

public class Order {
	private int orderId;
	private int securityId;
	TransactionType transactionType;
	String symbol;
	Integer quantity;
	Double limitPrice;
	String orderInstruction;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getSecurityId() {
		return securityId;
	}

	public void setSecurityId(int securityId) {
		this.securityId = securityId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(Double double1) {
		this.limitPrice = double1;
	}

	public String getOrderInstruction() {
		return orderInstruction;
	}

	public void setOrderInstruction(String orderInstruction) {
		this.orderInstruction = orderInstruction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + securityId;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (securityId != other.securityId)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (transactionType != other.transactionType)
			return false;
		return true;
	}
	
	

}
