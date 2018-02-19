package com.iot.spring.vo;

public final class SqlVO {
	private String sqlTa;
	private String selectDatabase;
	


	@Override
	public String toString() {
		return "SqlVO [sqlTa=" + sqlTa + ", selectDatabase=" + selectDatabase + "]";
	}

	public String getSelectDatabase() {
		return selectDatabase;
	}

	public void setSelectDatabase(String selectDatabase) {
		this.selectDatabase = selectDatabase;
	}

	public String getSqlTa() {
		return sqlTa;
	}

	public void setSqlTa(String sqlTa) {
		this.sqlTa = sqlTa;
	}
}
