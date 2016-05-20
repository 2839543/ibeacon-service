package com.ces.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ces.dao.PlayListCesDao;
import com.ces.model.PlayListCesModel;
import com.ces.util.Xml2JsonUtil;

public class PlayListCesAction {
	public static void insertPlayList(File file){
		String request = Xml2JsonUtil.xml2JSON(file);
		JSONObject  object = JSONObject.fromObject(request);
		JSONObject  busap = JSONObject.fromObject(object.get("Busap"));
        JSONArray playList = JSONArray.fromObject(busap.get("Play"));
        JSONObject  attributes = JSONObject.fromObject(busap.get("attributes"));
       String group = attributes.get("group")+"";
        String createTime = attributes.get("createTime")+"";
        String date = attributes.get("date")+"";
        String version = attributes.get("version")+"";
        Iterator<JSONObject> it = playList.iterator();
        List<PlayListCesModel> list = new ArrayList<PlayListCesModel>();
        while(it.hasNext()){
        	PlayListCesModel model = new PlayListCesModel();
        	JSONObject jsonObject = it.next();
        	JSONArray nameList = JSONArray.fromObject(jsonObject.get("Name"));
        	model.setName(nameList.get(0)+""); 
        	model.setSub_name(model.getName().split("#")[0]); 
        	JSONArray longList = JSONArray.fromObject(jsonObject.get("Long"));
        	model.setPlay_duration(Integer.parseInt(longList.get(0)+""));
        	JSONArray startList = JSONArray.fromObject(jsonObject.get("Start"));
        	model.setPlay_time(startList.get(0)+""); 
        	JSONArray fileNameList = JSONArray.fromObject(jsonObject.get("FileName"));
//        	System.out.println(fileNameList.get(0));
        	model.setFileName(fileNameList.get(0)+""); 
        	JSONArray typeList = JSONArray.fromObject(jsonObject.get("Type"));
        	model.setType(typeList.get(0).equals("节目")?1:2); 
        	JSONArray sizeList = JSONArray.fromObject(jsonObject.get("Size"));
        	model.setSize(Integer.parseInt(sizeList.get(0)+"")); 
//        	System.out.println(jsonObject.get("Name"));
//        	System.out.println(jsonObject.get("Long"));
//        	System.out.println(jsonObject.get("Start"));
//        	System.out.println(jsonObject.get("FileName"));
//        	System.out.println(jsonObject.get("Type"));
//        	System.out.println(jsonObject.get("Size"));
        	model.setPlay_date(date);
        	model.setVersion(Integer.parseInt(version)); 
        	model.setCity_id(0);
        	list.add(model);
        }
        PlayListCesDao dao = new PlayListCesDao();
        System.out.println(dao.batchInsert(list));
	}
	public static void main(String[] args) {
		insertPlayList(new File("D:\\play_ok_LS.xml"));
	}
}
