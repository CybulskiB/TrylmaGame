package Project.CommandMaster.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import Project.common.game.Game;
import Project.common.game.TrylmaGame;
import Project.server.main.CommandMaster;
import Project.server.main.ServerActivities;



public class CommandMastertest {
	public Game game;
	
	@Test
	public void ConstructorTest() {
		game = new TrylmaGame();
		ServerActivities activity = ServerActivities.SEND_ID;
		CommandMaster command_ms = new CommandMaster(1,activity,2);
		assertEquals(true,command_ms.before_start);
		assertEquals(game.getClass(),command_ms.game.getClass());
		assertTrue(command_ms.getCommand().equals(""));
		assertEquals(command_ms.activiti,activity);
	}
	@Test
	public void setgetTest() {
		game = new TrylmaGame();
		ServerActivities activity = ServerActivities.SEND_ID;
		CommandMaster command_ms = new CommandMaster(1,activity,2);
		String test = "TEST";
		String test2 = "New test";
		
		command_ms.setCommand(test);
		assertEquals(test,command_ms.getCommand());
		
		command_ms.setCommand(test2);
		assertEquals(test2,command_ms.getCommand());
	}
	
	@Test
	public void setIDcommandTest() {
		ServerActivities activity = ServerActivities.SEND_ID;
		CommandMaster command_ms = new CommandMaster(1, activity,6);
		command_ms.CommandMenu();
		
		assertTrue(command_ms.getCommand().equals("1;1;"));
		assertEquals(1,command_ms.game.Players.size());
		
		command_ms.CommandMenu();
		assertEquals(2,command_ms.game.Players.size());
		assertTrue(command_ms.getCommand().equals("1;2;"));
		
		command_ms.CommandMenu();
		assertEquals(3,command_ms.game.Players.size());
		assertTrue(command_ms.getCommand().equals("1;3;"));
		
		command_ms.CommandMenu();
		assertEquals(4,command_ms.game.Players.size());
		assertTrue(command_ms.getCommand().equals("1;4;"));
		
		command_ms.CommandMenu();
		assertEquals(5,command_ms.game.Players.size());
		assertTrue(command_ms.getCommand().equals("1;5;"));
		
		command_ms.CommandMenu();
		assertEquals(6,command_ms.game.Players.size());
		assertTrue(command_ms.getCommand().equals("1;6;"));
		
		command_ms.CommandMenu();
		assertEquals(6,command_ms.game.Players.size());
		assertTrue(command_ms.getCommand().equals("1;-1;")); 
	}
	
	@Test
	public void sendGameInfotest() {
		ServerActivities activity = ServerActivities.SEND_GAME_INFORMATION;
		CommandMaster command_ms = new CommandMaster(1, activity,6);
		command_ms.CommandMenu();
		 
		String cmd1 = "2;0;1;";
		String cmd2 = "2;1;1;";
		String cmd3 = "2;2;1;";
		String cmd4 = "2;3;1;";
		String cmd5 = "2;4;1;";
		String cmd6 = "2;5;1;";
		String cmd7 = "2;6;1;";
	
		command_ms.CommandMenu();
		assertEquals(cmd1,command_ms.getCommand());
		
		command_ms.game.add_Player();
		command_ms.CommandMenu();
		assertEquals(cmd2,command_ms.getCommand());
		
		command_ms.game.add_Player();
		command_ms.CommandMenu();
		assertEquals(cmd3,command_ms.getCommand());
		
		command_ms.game.add_Player();
		command_ms.CommandMenu();
		assertEquals(cmd4,command_ms.getCommand());
		
		command_ms.game.add_Player();
		command_ms.CommandMenu();
		assertEquals(cmd5,command_ms.getCommand());
		
		command_ms.game.add_Player();
		command_ms.CommandMenu();
		assertEquals(cmd6,command_ms.getCommand());
		
		command_ms.game.add_Player();
		command_ms.CommandMenu();
		assertEquals(cmd7,command_ms.getCommand());
		
		command_ms.game.add_Player();
		command_ms.CommandMenu();
		assertEquals(cmd7,command_ms.getCommand());
	}
	@Test
	public void sendStartInfoTest() {
		ServerActivities activity = ServerActivities.SEND_START_GAME;
		CommandMaster command_ms = new CommandMaster(1, activity,2);
		command_ms.CommandMenu();
		String cmd = "3;";
		
		assertEquals(cmd,command_ms.getCommand());
	}
	
	@Test
	public void sendBoardTest() {
		ServerActivities activity = ServerActivities.SEND_BOARD;
		CommandMaster command_ms = new CommandMaster(1, activity,2);
		command_ms.CommandMenu();
		String cmd = "4";
		
		assertEquals(cmd,command_ms.getCommand());
	}
	
	@Test
	public void sendTurntest() {

		ServerActivities activity = ServerActivities.SEND_WHOSE_TURN;
		CommandMaster command_ms = new CommandMaster(1, activity,2);
		String cmd0 = "5;";
		
		command_ms.game.open_Waitingroom(6);
		command_ms.game.add_Player();
		command_ms.game.add_Player();
		command_ms.game.add_Player();
		command_ms.game.add_Player();
		command_ms.game.add_Player();
		command_ms.game.add_Player();
		command_ms.game.create_Queue();
		command_ms.CommandMenu();
		
		int x =command_ms.game.Queue.get(command_ms.game.in_Queue);
		String help = Integer.toString(x);
		String cmd1=cmd0 + help +";";
		//System.out.println(cmd1);
		assertEquals(cmd1,command_ms.getCommand());
		command_ms.game.increase_Queue();
		
		x =command_ms.game.Queue.get(command_ms.game.in_Queue);
		help = Integer.toString(x);
		cmd1=cmd0 + help +";";
		command_ms.CommandMenu();
		//System.out.println(cmd1);
		assertEquals(cmd1,command_ms.getCommand());
		command_ms.game.increase_Queue();
	
		x =command_ms.game.Queue.get(command_ms.game.in_Queue);
		help = Integer.toString(x);
		cmd1=cmd0 + help +";";
		command_ms.CommandMenu();
		//System.out.println(cmd1);
		assertEquals(cmd1,command_ms.getCommand());
		command_ms.game.increase_Queue();
		
		x =command_ms.game.Queue.get(command_ms.game.in_Queue);
		help = Integer.toString(x);
		cmd1=cmd0 + help +";";
		command_ms.CommandMenu();
		//System.out.println(cmd1);
		assertEquals(cmd1,command_ms.getCommand());
		command_ms.game.increase_Queue();
		
		x =command_ms.game.Queue.get(command_ms.game.in_Queue);
		help = Integer.toString(x);
		cmd1=cmd0 + help +";";
		command_ms.CommandMenu();
		//System.out.println(cmd1);
		assertEquals(cmd1,command_ms.getCommand());
		command_ms.game.increase_Queue();
		
		x =command_ms.game.Queue.get(command_ms.game.in_Queue);
		help = Integer.toString(x);
		cmd1=cmd0 + help +";";
		command_ms.CommandMenu();
		//System.out.println(cmd1);
		assertEquals(cmd1,command_ms.getCommand());
		command_ms.game.increase_Queue();
		
		x =command_ms.game.Queue.get(command_ms.game.in_Queue);
		help = Integer.toString(x);
		cmd1=cmd0 + help +";";
		command_ms.CommandMenu();
		//System.out.println(cmd1);
		assertEquals(cmd1,command_ms.getCommand());
		command_ms.game.increase_Queue(); 
	}
	@Test
	public void sendEndInfoTest() {
		ServerActivities activity = ServerActivities.SEND_END_GAME;
		CommandMaster command_ms = new CommandMaster(1, activity,2);
		int x = 4;
		command_ms.game.set_winner(x);
		String cmd = "6;4;";
		
		command_ms.CommandMenu();
		assertEquals(cmd,command_ms.getCommand());
	}
}
