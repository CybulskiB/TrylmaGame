package Project.server.main;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Project.Game.*;
/**G��wna klasa odpowiadjaca za dzia�anie serwera*/
public class Server {
	
	public ServerSocket socket;
	public BufferedReader reader;
	public InputStreamReader inputreader;
	public PrintWriter writer;
	public Socket client_socket;
	public static int port;
	public int connection_iterator=0;
	/** Skladnia komendy podczas odbioru powinna zawierac informacje , od ktorego klienta pochodzi, jak funkcje
	 * nalezy wywolac oraz argumenty odzdzielone ; . Natomiast podczas wysylania powinna zawierac informacje
	 * ktorego klienta dotyczy , jaka funkcje powinien wykonac klient oraz argumenty oddzielone ;
	 */
	public CommandMaster command_ms;
	
	public int type_Game;
	public Game game;
	
	public Server(int x) {
		this.port=x;
		type_Game = choose_Type();
			if(type_Game ==1) {
				game = new TrylmaGame();
				command_ms = new  CommandMaster(game);
			}
		
	}
	public void create_Server() {
		try {
			socket = new ServerSocket(this.port);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void listen() {
		command_ms.setCommand("");
		int c;
		while(command_ms.getCommand().isEmpty()) {
			try {
				try {
					client_socket = socket.accept();
					}
				catch (IOException e) {
					System.out.println(e);
				}
				inputreader = new InputStreamReader(client_socket.getInputStream());
				reader = new BufferedReader(inputreader);
				command_ms.setCommand(reader.readLine());
				
			}
			catch(IOException e) {
				System.out.println(e);
			}
		}
	}
	public int choose_Type() {
		//Na chwile obecna wymagany jest tylko wariant gry "chi�skie warcaby "
		return 1;
	}
	public void estabilishConnection() {
		while(connection_iterator <1) {
			try {
				client_socket = socket.accept();
				ThreadServerWrite write = new ThreadServerWrite(client_socket,command_ms.getCommand());
				write.run();
				}
			catch(IOException e) {
				System.out.println(e);
			}
		}
	}
}
