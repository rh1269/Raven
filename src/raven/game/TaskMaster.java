package raven.game;

import raven.game.*;
//import raven.game.EntityManager.EntityManagerHolder;




//public class EntityManager {
//	private static class EntityManagerHolder {
//		public static final EntityManager INSTANCE = new EntityManager();
//	}



/** Singleton needed of this class
 * @author Brendan
 *
 */
//public class TaskMaster extends BaseGameEntity {
public class TaskMaster {
	
	private static class TaskMasterHolder{
		public static final TaskMaster TM_INSTANCE = new TaskMaster();
	}

	
//	protected TaskMaster(int id, Team team) {
//		super(id);
//		owningTeam = team;
//		// TODO Auto-generated constructor stub
//	}
	
	public static TaskMaster getMaster() {
		return TaskMasterHolder.TM_INSTANCE;
	}
	
	

//	@Override
//	public void render() {
//		// TODO Auto-generated method stub
		
//	}
	
	/**
	 * This runs the RavenTask version of getNewTask with TASK_NONE, to avoid 
	 * re-writing the same code. 
	 * @return
	 */
	public RavenTask getNewTask(RavenBot bot){
		return getNewTask(RavenTask.TASK_NONE, bot);
	}
	
	/**
	 * A version of getNewTask that accepts the current task for the purposes of arbitration. 
	 * @param cur_task
	 * @return a string of the new tasks
	 */
	public RavenTask getNewTask(RavenTask cur_task, RavenBot bot){
		
		if (bot.getTeam().teamHasCaptain()){
			return RavenTask.TASK_BODYGUARD;
		}
		else {
			
			return RavenTask.TASK_CAPTAIN;
		}
		
			//TODO
			//We need task arbitraion here. This is the magic.

	}

	///TODO Is there a captain? Make a captain!
	
	///TODO Other cases
	///If there is already a captain, let's figure out a new role
	///wingman
	///Medic
	
	private TaskMaster() {
	
	}
	
	public void doYouExist(){
		System.out.println("YES THE TASKMASTER EXISTS");
	}
	
}
