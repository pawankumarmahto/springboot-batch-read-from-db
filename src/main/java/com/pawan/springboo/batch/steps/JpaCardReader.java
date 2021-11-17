package com.pawan.springboo.batch.steps;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pawan.springboo.batch.entity.DebitCard;
import com.pawan.springboo.batch.repository.DebittCardRepository;

@Component
public class JpaCardReader implements ItemReader<DebitCard> {

	@Autowired
	DebittCardRepository debittCardRepository;

	ItemReader<DebitCard> delegate;

	@Override
	public DebitCard read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (this.delegate == null) {
			this.delegate = new IteratorItemReader<DebitCard>(getData());
		}
		return this.delegate.read();
	}

	private List<DebitCard> getData() {
		List<DebitCard> list = debittCardRepository.findNotExpiredDebitCard();
		return list;
	}
}
