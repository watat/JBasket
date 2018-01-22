package jp.wat.basket.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.wat.basket.ScheduleData;

@Repository
//@Service
public class ScheduleDaoImpl implements ScheduleDao<ScheduleData> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	@Override
	@SuppressWarnings("unchecked")
	public List<ScheduleData> getMonthlyList(int nendo) {
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("nendo", nendo);
		
		System.out.println(param);
		System.out.println(nendo);
		
		// ******************************************************************************************
		// nullになってしまうのは、アノテーションを正しく使えていないからか？
		//　https://teratail.com/questions/68121
	    // ******************************************************************************************
		
		return jdbcTemplate.query(
				"SELECT * FROM schedule_datas s WHERE s.nendo = ?",
			    new BeanPropertyRowMapper<ScheduleData>(ScheduleData.class),
			    nendo);
		
//		return jdbcTemplate.query(
//				"SELECT * FROM schedule_datas s WHERE s.nendo = :nendo",
//				param,
//			    new BeanPropertyRowMapper<ScheduleData>(ScheduleData.class));
		
		//return list;
		
		
		// ******************************************************************************************
		// ここから下は　バックアップ
		// ******************************************************************************************
		
//		Session session = entityManager.unwrap(Session.class);
//		
//		List<ScheduleData> list = entityManager
//			    .createNativeQuery("select * FROM schedule_datas s WHERE s.nendo = :nendo", ScheduleData.class)
//			    .setParameter("nendo", nendo)
//			    .getResultList();
//		
//		session.close();
//		return list;
		
		
		
//		
//		// ******************************************************************************************
//		// TODO session.createQuery ではなく、　entityManager.createNativeQuery　を使うのが正しいのか？？？？
//		// ******************************************************************************************
//	
//		Session session = entityManager.unwrap(Session.class);
//		
//		//String str = "SELECT * FROM schedule_datas s WHERE s.nendo = :nendo"; 
////		String str = "FROM schedule_datas s WHERE s.nendo = :nendo"; 
////		Query query = session.createQuery(str)
////				.setParameter("nendo", nendo); 
////		List<ScheduleData> list = query.list();
////		session.close();
//		
//		// スケジュールデータ取得		
//		// TODO NamedQueryを使用する方法でうまくいかない。SQLの管理はEntityにまとめたいので後でやり方確認。
//	
////		List<ScheduleData> list = entityManager.createNamedQuery("findByNendo")
////		.setParameter("nendo", nendo)
////		.getResultList();
//		
////		Query query = entityManager.createNativeQuery("SELECT * FROM schedule_datas s WHERE s.nendo =?1")
////			    .setParameter(1, nendo);
////		List<ScheduleData> list = query.getResultList();
//
//		
//		
//		// ******************************************************************************************
//		// TODO hibernate.cfg.xml を作成する必要があるみたい。。。
//		// http://www.techscore.com/tech/Java/Others/Hibernate/02-2/
//		// ******************************************************************************************
//
//		
//		// ******************************************************************************************
//		// 下記は実行時にエラーが発生する
//		// org.hibernate.MappingException: Unknown entityエラー発生
//		// 対策は、http://www.techscore.com/tech/Java/Others/Hibernate/05-2/　参照。やっぱり「hibernate.cfg.xml」みたい
//		
//		
//		// マッピングファイルは自動生成できるみたい
//		// http://d.hatena.ne.jp/getbean/20071217/1197873708
//		// ******************************************************************************************
//		List<ScheduleData> list = entityManager
//			    .createNativeQuery("select * FROM schedule_datas s WHERE s.nendo = :nendo", ScheduleData.class)
//			    .setParameter("nendo", nendo)
//			    .getResultList();	
//		
//		// ******************************************************************************************
//		// 下記はデータの取得はできるが、objectクラスになってしまう
//		// ******************************************************************************************
////		List<ScheduleData> list = entityManager
////			    .createNativeQuery("select * FROM schedule_datas s WHERE s.nendo = :nendo")
////			    .setParameter("nendo", nendo)
////			    .getResultList();	
//		// ******************************************************************************************
//
//		
//		// デバッグ出力
//		// 中身を確認する方法は要調査
//		System.out.println("【list:】" + list);
//		//System.out.println(String.valueOf(list.get(0).getSeq()));
//		//System.out.println(list.get(0).getCol1());
//		//System.out.println(list.get(0).getCol2());
//		//System.out.println(list.get(0).getCol3());
//		
//		session.close();
//		return list;
	}
}
