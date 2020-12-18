package Project.client.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Project.client.connection.ClientConnection;
import Project.client.exceptions.GameEndedException;
import Project.client.exceptions.PlayerNotAllowedException;
import Project.common.exceptions.WrongGameTypeException;
import Project.common.game.GameType;

public class Client {

//	private static final String programName = "Client";
	private ClientConnection connectionToServer;
	private int id;
	private GameType gameType;
	private int[][] pieces;

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Pass the server ip and socket as the command line argument");
			System.exit(1);
		}

		Client client = new Client();
		client.running(args[0], Integer.parseInt(args[1]));

	}

	private void running(String host, int port) {
		connectionToServer = new ClientConnection(); //this);

		try {
			connectionToServer.connect(host, port);
			id = connectionToServer.getId();
			gameType = connectionToServer.getGameType();
			pieces = connectionToServer.getPieces();

			while(true) {
				connectionToServer.read();
				// TODO: do something
				connectionToServer.write();
				// or: connectionToServer.endGame();
			}

		}
		catch (WrongGameTypeException wgtx) {
			System.out.println(wgtx.getMessage());
		}
		catch (PlayerNotAllowedException pnax) {
			System.out.println(pnax.getMessage());
		}
		catch (IOException iox) {
			System.out.println(iox.getMessage());
		}
		catch (GameEndedException gex) {
			System.out.println(gex.getMessage());
		}
		finally {
			try {
				connectionToServer.endConnection();
			}
			catch (IOException iox) {
				System.out.println("Couldn't close the socket");
				System.out.println(iox.getMessage());
			}
		}

	}

	public void notifyClient() {
		//
	}

}
