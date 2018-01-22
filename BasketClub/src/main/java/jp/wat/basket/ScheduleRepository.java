package jp.wat.basket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleData, Integer> {
	
	/**
	 * スケジュール情報を全件取得する
	 */
	public List<ScheduleData> findAll();
	
	/**
	 * 年度をキーにスケジュール情報を取得する
	*/
	//@Query(value="select * from schedule_datas where nendo = ?1 ", nativeQuery = true)   // ネイティブQueryの書き方（DB名 DB項目を使用）
	@Query(value="select s from ScheduleData s where nendo = ?1 ")	                       // @Queryの標準の書き方（エンティティクラス名 エンティティクラスのカラム名を使用）
	public List<ScheduleData> findByNendo(Integer nendo);

}
