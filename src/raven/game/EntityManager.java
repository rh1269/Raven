package raven.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import raven.game.interfaces.IRavenBot;
import raven.game.interfaces.ITeam;
import raven.utils.Log;


public class EntityManager {
	private static class EntityManagerHolder {
		public static final EntityManager INSTANCE = new EntityManager();
	}
	
	private static int availableID = 0;
	static volatile int lastTeamAssigned = 0;

	
	public static synchronized int getAvailableID() {
		int toReturn = availableID;
		availableID++;
		return toReturn;
	}
	public static EntityManager getInstance() {
		return EntityManagerHolder.INSTANCE;
	}
	
	private Map<Integer, BaseGameEntity> entityMap = new HashMap<Integer, BaseGameEntity>();
	private Map<Integer, IRavenBot> botMap = new HashMap<Integer, IRavenBot>();
	///Why teamList and listOfTeamID's? Because arraylists do not support
	///primitive types, and I need something I can iterate through
	///not a hashmap
	private static Map<Integer, Team> teamList = new HashMap<Integer, Team>();
	private static ArrayList<Team> listOfTeamIDs = new ArrayList<Team>();
	

	public static Team getAvailableTeam() {
		
		////this method is UNSAFE, it is assuming a team
		///has been created!
		
		if(lastTeamAssigned < listOfTeamIDs.size()-1){
			int temporary = lastTeamAssigned;
			lastTeamAssigned++;
			return listOfTeamIDs.get(lastTeamAssigned);
		}
		else{
			System.out.println("(From the else) Last Assigned: " + lastTeamAssigned);
			int temporary = lastTeamAssigned;
			lastTeamAssigned = 0;
			return listOfTeamIDs.get(0);
		}
	}
	
	
	/*
	public static Team getAvailableTeam() {
		if(lastTeamAssigned == -1){
			///First bot to request a team
			System.out.println("Negative one means first assignment");
			System.out.println("Last Assigned: " + lastTeamAssigned);
			lastTeamAssigned = 0;
			return listOfTeamIDs.get(0);
		}
		else if(lastTeamAssigned >= listOfTeamIDs.size()){
			System.out.println("Should be the last team in a list, so, we assign 0");
			System.out.println("Last Assigned: " + lastTeamAssigned);
			lastTeamAssigned = 0;
			return listOfTeamIDs.get(0);
		}
		else
			System.out.println("This is the else Last Assigned: " + lastTeamAssigned);
			System.out.println("Not the first, looks like last time it was probably zero, so team 1");
		lastTeamAssigned++;
		System.out.println("Last Assigned plus 1" + lastTeamAssigned);
		return listOfTeamIDs.get(lastTeamAssigned);		
	}
	*/
	
	
	private EntityManager() {

		
	}
	
	public static void registerEntity(BaseGameEntity entity) {
		Log.trace("ENTITY MANAGER", "Registered entity of type " + entity.entityType() + " and ID " + entity.ID()); 
		getInstance().entityMap.put(entity.ID(), entity);
	}
	
	public static void registerEntity(IRavenBot entity) {
		Log.trace("ENTITY MANAGER", "Registered entity of type " + entity.entityType() + " and ID " + entity.ID()); 
		getInstance().botMap.put(entity.ID(), entity);
	}
	///Have to add the ability to register a team. 
	
	public static void registerEntity(Team newteam){
		Log.trace("ENTITY MANAGER", "Registered entity of type " + newteam.entityType() + " and ID " );
		getInstance().teamList.put(newteam.ID(), newteam);
		getInstance().listOfTeamIDs.add(newteam);
	}

	public static BaseGameEntity getEntityFromID(int receiverID) {
		return getInstance().entityMap.get(receiverID);
	}
	
	public static IRavenBot getBotFromID(int receiverID){
		return getInstance().botMap.get(receiverID);
	}
	
	public static ITeam getTeamFromID(int receiverID){
		return getInstance().teamList.get(receiverID);
	}
	
	public static void removeEntity(BaseGameEntity entity) {
		getInstance().entityMap.remove(entity);
		Log.trace("ENTITY MANAGER", "Removed entity of type " + entity.entityType() + " and ID " + entity.ID()); 
	}

	public static void reset() {
		Log.trace("ENTITY MANAGER", "Cleared entity listing"); 
		getInstance().entityMap.clear();
	}



}
