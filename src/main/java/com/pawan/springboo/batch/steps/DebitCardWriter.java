package com.pawan.springboo.batch.steps;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pawan.springboo.batch.entity.DebitCard;
import com.pawan.springboo.batch.repository.DebittCardRepository;
import com.pawan.springboo.batch.service.JpaDebitCardService;

@Component
public class DebitCardWriter implements ItemWriter<DebitCard> {

	@Autowired
	DebittCardRepository debittCardRepository;

	@Autowired
	JpaDebitCardService jpaDebitCardService;
	
	@Override
	public void write(List<? extends DebitCard> list) throws Exception {
		
		if (list != null && !list.isEmpty()) {
			//debittCardRepository.saveAllAndFlush(list);
			list.forEach(x-> jpaDebitCardService.saveDebitCard(x));
		}
	}
}
