package raven.ui;
import java.awt.event.ActionEvent;

public class ScoreEvent extends ActionEvent{

	private int amount;
	private String team;

	
	
	public ScoreEvent(Object obj, int amount, String team)
	{
		
		
		super(obj, amount, team );
		
		this.amount=amount;
		this.team=team;
	
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public String getTeam()
	{
		return team;
	}
	
	
	
	
}
