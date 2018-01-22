package jp.wat.basket.dao;

import java.io.Serializable;
import java.util.List;

public interface ScheduleDao<T> extends Serializable {
	
	public List<T> getMonthlyList(int nendo);

}
