package Project.server.main;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**G��wna klasa odpowiadjaca za dzia�anie serwera*/
public class Server {
	
	public ServerSocket socket;
	public BufferedReader reader;
	public InputStreamReader inputreader;
	public PrintWriter writer;
	public Socket client_socket;
	public static int port;
	public int connection_iterator;
	public ThreadServerListen listen;
	public boolean started = false;
	/** Skladnia komendy podczas odbioru powinna zawierac informacje , od ktorego klienta pochodzi, jak funkcje
	 * nalezy wywolac oraz argumenty odzdzielone ; . Natomiast podczas wysylania powinna zawierac informacje
	 * ktorego klienta dotyczy , jaka funkcje powinien wykonac klient oraz argumenty oddzielone ;
	 */
	public CommandMaster command_ms;
	
	public int type_Game;
	ServerActivities com_activiti;
	/**Konstruktor serwera */
	public Server(int x) {
		this.port=x;
		type_Game = choose_Type();
		com_activiti = ServerActivities.SEND_ID;
		command_ms = new  CommandMaster(type_Game,com_activiti);
		connection_iterator=0;
		
	}
	/**Funkcja tworzaca nowe gniazdo serwera*/
	public void create_Server() {
		try {
			socket = new ServerSocket(this.port);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*Funkcja wybierajaca typ gry */
	public int choose_Type() {
		//Na chwile obecna wymagany jest tylko wariant gry "chi�skie warcaby "
		return 1;
	}
	/**Funkcja odpowiadajaca za polaczenia z klientem */
	public void estabilish_connection() {
		while(connection_iterator <1) {
		//	System.out.println("jest komenda przed " + command_ms.getCommand());
		//	System.out.println("Przed akceptacja ");
		//	System.out.println(sended);
		//	System.out.println("jest obecnie " + command_ms.game.Players.size() + " graczy");	
			try {
				//System.out.println("Przed akceptacja ");
					client_socket = socket.accept();
				//	System.out.println("Zaakceptowal");
					//	System.out.println(command_ms.activiti);
				if (command_ms.activiti.equals(ServerActivities.LISTEN)) {
					listen = new ThreadServerListen(client_socket, command_ms);
					listen.run();
				//	System.out.println("jest komenda " + command_ms.getCommand());
					if(!started && command_ms.getCommand().equals("-1")) {
			//			System.out.println("Odebralem prosbe o ID");
						command_ms.activiti = ServerActivities.SEND_ID;
					}
				}
				if (command_ms.activiti.equals(ServerActivities.SEND_ID)) {
				//	System.out.println("Rozpoczynam pisanie ID");
					command_ms.CommandMenu();
					ThreadServerWrite write = new ThreadServerWrite(client_socket,command_ms.getCommand());
					write.run();
				//	System.out.println("napisalem id " + command_ms.getCommand());
					if(!started) {
						command_ms.activiti =ServerActivities.LISTEN;
					}
				}
				else if (!command_ms.activiti.equals(ServerActivities.LISTEN)){
					command_ms.CommandMenu();
					ThreadServerWrite write = new ThreadServerWrite(client_socket,command_ms.getCommand());
					write.run();
				}
				}
			catch(IOException e) {
				System.out.println(e);
			}
		}
	}
}
