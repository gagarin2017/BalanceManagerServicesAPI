package com.greenland.balancemanager.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The input data coming from the front end application (React for example)
 * 
 * @author Yura
 *
 */
@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InputTxData {
	
	private List<TxDataRow> remoteAccountTxsData;
	private List<TxDataRow> localAccountTxsData;

}
