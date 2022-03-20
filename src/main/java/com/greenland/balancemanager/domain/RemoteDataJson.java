package com.greenland.balancemanager.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.json.JSONArray;
import org.json.JSONObject;

import com.greenland.balanceManager.java.app.CommonUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The original POJO from the JSON coming from the outside via web service.
 * 
 * @author Yura
 *
 */
@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Deprecated
public class RemoteDataJson {
	
	private @Id @GeneratedValue Long id;
	private List<TxDataRowJson> remoteAccountTxsData;
	private List<TxDataRowJson> localAccountTxsData;

	@Deprecated
    public JSONObject toJSON() {

        final JSONObject jo = new JSONObject();
        
        List<JSONObject> jsonRem = remoteAccountTxsData.stream().map(p -> new JSONObject(p)).collect(Collectors.toList());
        List<JSONObject> jsonLoc = localAccountTxsData.stream().map(p -> new JSONObject(p)).collect(Collectors.toList());

        final JSONArray jsonRemoteAccountTxsData = new JSONArray(jsonRem);
        final JSONArray jsonLocalAccountTxsData = new JSONArray(jsonLoc);
        
        jo.put(CommonUtils.REMOTE_TRANSACTIONS_JSON_KEY, jsonRemoteAccountTxsData);
        jo.put(CommonUtils.LOCAL_TRANSACTIONS_JSON_KEY, jsonLocalAccountTxsData);
        
        return jo;
    }
}
