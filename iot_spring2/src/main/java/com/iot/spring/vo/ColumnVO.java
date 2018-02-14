package com.iot.spring.vo;

public class ColumnVO {
	private String columnName;
	private String columnDefault;
	private String isNullable;
	private String dataType;
	private int characterLength;
	private String columnType;
	private String columnKey;
	private String columnComment;
	@Override
	public String toString() {
		return "ColumnVO [columnName=" + columnName + ", columnDefault=" + columnDefault + ", isNullable=" + isNullable
				+ ", dataType=" + dataType + ", characterLength=" + characterLength + ", columnType=" + columnType
				+ ", columnKey=" + columnKey + ", columnComment=" + columnComment + "]";
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnDefault() {
		return columnDefault;
	}
	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getCharacterLength() {
		return characterLength;
	}
	public void setCharacterLength(int characterLength) {
		this.characterLength = characterLength;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
}
