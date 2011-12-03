package raven.goals;

import raven.game.RavenBot;
import raven.math.Vector2D;
import raven.ui.GameCanvas;

public class Goal_Evade extends GoalComposite<RavenBot> {
	
	//the approximate time the bot should take to travel the target location
	double     m_dTimeToReachPos;

	//this records the time this goal was activated
	double     elapsedTime;

	
private RavenBot target;
	
	public Goal_Evade(RavenBot m_pOwner, RavenBot target) {
		super(m_pOwner, Goal.GoalType.goal_evade);
		this.target = target;
		m_dTimeToReachPos = 0.0;
		
	}
	
	@Override
	public void activate() {
		m_iStatus = Goal.CurrentStatus.active;
		m_pOwner.getSteering().evadeOn();
		m_pOwner.getSteering().seekOff();
		//m_pOwner.getSteering().arriveOff();
		m_pOwner.getSteering().separationOff();
		m_pOwner.getSteering().wallAvoidanceOff();
		m_pOwner.getSteering().wanderOff();//*/
		m_pOwner.getSteering().setTargetAgent1(target);
		
		
		
		
		
		///======
	
		
		
		
		
		
		
		//check to see if an enemy is there
	

		
		Vector2D toTarget = m_pOwner.pos().sub(m_pOwner.getTargetBot().pos());
		
		
		//if the bot is able to shoot the target (there is LOS between bot and
		//target), then select a tactic to follow while shooting
		if (/*m_pOwner.getTargetSys().isTargetShootable()*/ m_pOwner.health()<(.65*m_pOwner.maxHealth())&& toTarget.length()<7)
		{
			m_pOwner.getBrain().removeAllSubgoals();
		/*	//if the bot has space to strafe then do so
			if (m_pOwner.canStepLeft() != null || m_pOwner.canStepRight() != null)
			{
				AddSubgoal(new Goal_DodgeSideToSide(m_pOwner));
			}

			//if not able to strafe, head directly at the target's position 
			else
			{
				AddSubgoal(new Goal_SeekToPosition(m_pOwner, m_pOwner.getTargetBot().pos()));
			}*/
			
			//AddSubgoal(new Goal_Evade(m_pOwner,m_pOwner.getTargetBot()));
			m_pOwner.getSteering().setTarget(target.pos());
			m_pOwner.getSteering().evadeOn();
			
		}
		if ( toTarget.length()>7)
		{
			m_iStatus = Goal.CurrentStatus.completed;
			return;
		}

		//if the target is not visible, go hunt it.
		//else
		//{
		//	AddSubgoal(new Goal_HuntTarget(m_pOwner));
		//}
	
		
	
		//================
		
		
	
		
		
		
	}
	
	@Override
	public raven.goals.Goal.CurrentStatus process(double delta) {
		activateIfInactive();
		return m_iStatus;
	}

	@Override
	public void terminate() {
		m_pOwner.getSteering().evadeOff();
		m_iStatus = Goal.CurrentStatus.completed;
	}
	
/*	@Override
	public void render() {
		// do nothing
		m_SubGoals.get(0).render();
	}
*/
	@Override
	public void render(){
		if (m_iStatus == Goal.CurrentStatus.active)
		{
			GameCanvas.greenBrush();
			GameCanvas.blackPen();
			GameCanvas.circle(target.pos(), 3);
		}

		else if (m_iStatus == Goal.CurrentStatus.inactive)
		{

			GameCanvas.redBrush();
			GameCanvas.blackPen();
			GameCanvas.circle(target.pos(), 3);
		}
	}

	
}
