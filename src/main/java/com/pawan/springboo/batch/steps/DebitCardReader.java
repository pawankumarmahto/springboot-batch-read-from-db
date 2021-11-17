package com.pawan.springboo.batch.steps;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.pawan.springboo.batch.entity.DebitCard;

@Component
public class DebitCardReader extends JdbcCursorItemReader<DebitCard> implements ItemReader<DebitCard>{
	
	public DebitCardReader(@Autowired DataSource primaryDataSource) {
		setDataSource(primaryDataSource);
		setSql("SELECT * FROM debit_card u WHERE u.status not in ('E')");
		setFetchSize(100);
		setRowMapper(new DebitCardMapper());
	}
	
	public class DebitCardMapper implements RowMapper<DebitCard> {
		@Override
		public DebitCard mapRow(ResultSet rs, int rowNum) throws SQLException {
			DebitCard dc  = new DebitCard();
			dc.setCardNo(rs.getLong("card_no"));
			dc.setExpiredDate(rs.getTimestamp("Expired_Date"));
			dc.setStatus(rs.getString("status"));
			return dc;
		}
	}
}
