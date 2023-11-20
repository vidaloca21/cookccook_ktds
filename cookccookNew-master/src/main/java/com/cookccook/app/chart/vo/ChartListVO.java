package com.cookccook.app.chart.vo;

import java.util.List;

public class ChartListVO {
	
	private int chartcnt;
	private List<ChartVO> chartList;
	
	public int getChartcnt() {
		return chartcnt;
	}
	public void setChartcnt(int chartcnt) {
		this.chartcnt = chartcnt;
	}
	public List<ChartVO> getChartList() {
		return chartList;
	}
	public void setChartList(List<ChartVO> chartList) {
		this.chartList = chartList;
	}

}
