package com.pawan.springboo.batch.steps;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.pawan.springboo.batch.entity.DebitCard;

@Component
public class DebitCardProcessor implements ItemProcessor<DebitCard, DebitCard> {

	@Override
	public DebitCard process(DebitCard dc) throws Exception {
		System.out.println("MyBatchProcessor : Processing data : " + dc);

		if (dc.getExpiredDate().before(new Timestamp(new Date().getTime()))) {
			dc.setStatus("E");
		}
		return dc;
	}
}
