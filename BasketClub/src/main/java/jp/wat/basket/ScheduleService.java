package jp.wat.basket;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository repository;
	
	public List<ScheduleData> getScheduleData(Integer nendo){
	
		return repository.findByNendo(nendo);
		//return repository.findAll();
	}
	
    @Transactional
    public void save(ScheduleData scheduleData) {
        repository.save(scheduleData);
    }

}
