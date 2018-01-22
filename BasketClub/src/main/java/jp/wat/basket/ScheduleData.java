package jp.wat.basket;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "schedule_datas")
public class ScheduleData{

	@Id
	@Column(name="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer seq;
	
	private Integer nendo;
	private Integer month;
	private String col1;
	private String col2;
	private String col3;
	private String col4;
	private String col5;
	private String col6;
	private String col7;
	private String col8;
	private String col9;
	private String col10;
	private byte gameFlag;
	private Integer registUser;
	private LocalDateTime registTime;
	private Integer updateUser;
	private LocalDateTime updateTime;
	
	// JPA requirement
    protected ScheduleData() {}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public int getNendo() {
		return nendo;
	}
	public void setNendo(Integer nendo) {
		this.nendo = nendo;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getCol1() {
		return col1;
	}
	public void setCol1(String col1) {
		this.col1 = col1;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	public String getCol3() {
		return col3;
	}
	public void setCol3(String col3) {
		this.col3 = col3;
	}
	public String getCol4() {
		return col4;
	}
	public void setCol4(String col4) {
		this.col4 = col4;
	}
	public String getCol5() {
		return col5;
	}
	public void setCol5(String col5) {
		this.col5 = col5;
	}
	public String getCol6() {
		return col6;
	}
	public void setCol6(String col6) {
		this.col6 = col6;
	}
	public String getCol7() {
		return col7;
	}
	public void setCol7(String col7) {
		this.col7 = col7;
	}
	public String getCol8() {
		return col8;
	}
	public void setCol8(String col8) {
		this.col8 = col8;
	}
	public String getCol9() {
		return col9;
	}
	public void setCol9(String col9) {
		this.col9 = col9;
	}
	public String getCol10() {
		return col10;
	}
	public void setCol10(String col10) {
		this.col10 = col10;
	}

	public byte getGameFlag() {
		return gameFlag;
	}

	public void setGameFlag(byte gameFlag) {
		this.gameFlag = gameFlag;
	}

	public Integer getRegistUser() {
		return registUser;
	}

	public void setRegistUser(Integer registUser) {
		this.registUser = registUser;
	}

	public LocalDateTime getRegistTime() {
		return registTime;
	}

	public void setRegistTime(LocalDateTime registTime) {
		this.registTime = registTime;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	
}
