package com.dowloyalty.pojo;

import java.io.Serializable;

import com.dowloyalty.entity.Goods;

public class GoodsVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 394518066806066653L;
	private int id;
	private String exchangePoints;
	private String goodsCategory;
	private Goods goods;

	public GoodsVo() {
		super();
	}

	public GoodsVo(int id, String exchangePoints, Goods goods, String goodsCategory) {
		super();
		this.id = id;
		this.exchangePoints = exchangePoints;
		this.goods = goods;
		this.goodsCategory = goodsCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExchangePoints() {
		return exchangePoints;
	}

	public void setExchangePoints(String exchangePoints) {
		this.exchangePoints = exchangePoints;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

}
