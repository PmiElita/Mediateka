package com.mediateka.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.mediateka.annotation.Controller;
import com.mediateka.annotation.Request;
import com.mediateka.annotation.Roles;
import com.mediateka.model.MapEvent;
import com.mediateka.model.enums.Role;
import com.mediateka.model.enums.State;
import com.mediateka.service.MapEventService;

@Controller
public class MapEventController {
	
	@Request(url = "map", method = "get")
	@Roles({Role.ADMIN})
	public static void getChat(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, ReflectiveOperationException {

		request.getRequestDispatcher("pages/admin/map.jsp").forward(
				request, response);
	}

	
	@Request(url="eventsMap", method ="get")	
	public static void getEventsMap(HttpServletRequest request, HttpServletResponse response) throws SQLException, ReflectiveOperationException, IOException{
		List<MapEvent> mapEvents = MapEventService.getActiveMapEvents();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(mapEvents));
	}
	
	@Request(url="saveMapEventChanges", method ="post")
	@Roles({ Role.ADMIN})
	public static void  saveMapEventChanges(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, ReflectiveOperationException{
		String jsonArrayString = request.getParameter("json");
		if (jsonArrayString!=null){
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(jsonArrayString);
			List<MapEvent> mapEvents = getMapEventList(jsonArray);
			for (MapEvent map : mapEvents){
				if (map.getId()==null){
					map.setAdminId(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
			        MapEventService.saveMapEvent(map);
				} else {
					MapEventService.updateMapEvent(map);
				}
			}
			
		}
	}
	
	private static List<MapEvent> getMapEventList(JSONArray jsonArray){
		List<MapEvent> result = new ArrayList<MapEvent>();
		
		for (int i=0; i<jsonArray.size(); i++){
			JSONObject jsonObject = (JSONObject)jsonArray.get(i);
			MapEvent mapEvent= new MapEvent();
			if (jsonObject.get("id")!=null){
			mapEvent.setId(Integer.parseInt(jsonObject.get("id").toString()));
			mapEvent.setAdminId(Integer.parseInt(jsonObject.get("adminId").toString()));
			}
			mapEvent.setName(jsonObject.get("name").toString());
			mapEvent.setDescription(jsonObject.get("description").toString());
			mapEvent.setAdress(jsonObject.get("adress").toString());
			mapEvent.setLatitude(Double.parseDouble(jsonObject.get("latitude").toString()));
			mapEvent.setLongitude(Double.parseDouble(jsonObject.get("longitude").toString()));
			mapEvent.setState(State.valueOf(jsonObject.get("state").toString().toUpperCase()));
			result.add(mapEvent);
		}
		
		return result;
		
	}
}
