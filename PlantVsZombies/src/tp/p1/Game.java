package tp.p1;

import tp.p1.Exceptions.CasillaOcupadaException;
import tp.p1.Exceptions.CommandExecuteException;
import tp.p1.Exceptions.FileContentsException;
import tp.p1.Exceptions.NoSuncoinsException;
import tp.p1.Exceptions.OutOfBoardException;
import tp.p1.GameObject.*;
import tp.p1.factorias.PlantFactory;
import tp.p1.factorias.ZombieFactory;
import tp.p1.interfaz.BoardPrinter;
import tp.p1.managers.PrinterManager;
import tp.p1.managers.SuncoinManager;
import tp.p1.managers.ZombieManager;
import tp.pr1.util.LoadLine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class Game {

	static final int TAM = 32;
	public static final int NFILA = 4;
	public static final int NCOL = 8;
	
	private GameObjectList PlantList;
	private GameObjectList ZombieList;
	
	private int ciclo;

	private ZombieManager ZombieManager;
	private SuncoinManager SuncoinManager;
	private PrinterManager PrinterManager;
	private Random rand;
	private Level level;
	
	private long seed;
	
	private boolean noPrint;
	private boolean winZ;
	private boolean winU; 
	private boolean exit;
	
	public Game (Level level, Random rand, Long seed)
	{
		this.rand = rand;
		this.PlantList = new GameObjectList(TAM);
		this.ZombieList = new GameObjectList(TAM);
		this.ZombieManager = new ZombieManager(level.getNumberOfZombies(), rand);
		this.ciclo = 0;
		this.SuncoinManager = new SuncoinManager();
		this.level=level;
		this.winU = false;
		this.winZ = false; 
		this.seed = seed;
		this.exit = false;
		this.PrinterManager = new PrinterManager();
		this.noPrint = false;
	}
	
	public void reset()
	 {
		 this.ciclo = 0;
		 this.SuncoinManager.resetearSoles();
		 this.ZombieManager.setZombies(this.level.getNumberOfZombies());
		 
		 this.PlantList.resetearLista();
		 this.ZombieList.resetearLista();
		 
	 }
	
	public void actualizarCiclo() {
		this.ciclo = this.ciclo + 1;
	}
	
	 public boolean comprobarDentroTablero(int fila, int columna)
	 {
		 return (fila>=0 && fila <NFILA) && (columna>=0 && columna <NCOL-1);
	 }
	
	public boolean comprobarCoordenadas(int fila,int columna)
	{
		return this.PlantList.estaVacio(fila, columna) && 
				this.ZombieList.estaVacio(fila, columna);
	}
	
	private boolean addZombie()
	{
		boolean anadido=false;
		int numeroAleatorio;
		if (!this.ZombieManager.noQuedanZombies())
		{
			numeroAleatorio=rand.nextInt(4);
			int numRandZombies = rand.nextInt(3);
			
			if (this.comprobarCoordenadas(numeroAleatorio,NCOL - 1))
			{
				Zombie zombie= ZombieFactory.parseRandom(numRandZombies);
				zombie.setCiclo(this.ciclo);
				zombie.setColumna(NCOL - 1);
				zombie.setFila(numeroAleatorio);
				zombie.setGame(this);
				
				
				ZombieList.insertar(zombie, this);
				this.ZombieManager.RestarZombies();
				anadido= true;
			}
		
		}
		return anadido ;
	}
	
	public String CicloToString ()
	{
		return ("Number of cycles: " + this.ciclo);
	}
	
	
	
	public void update() //win es true cuando gana usuario o zombies
	{
		if (this.ZombieManager.isZombieAdded(this.level.getZombieFrequency()))
		{
		this.addZombie();
		}
		this.actualizarCiclo();
		this.PlantList.update();
		this.winZ = this.ZombieList.update();
		
	}
	
	public void zombieAtaca(int fila,int columna,int dano)
	{
		if (!this.PlantList.estaVacio(fila, columna))
		{
			this.PlantList.AtacarObj(fila, columna, dano);
		}
	}
	
	
	public boolean isFinished()
	{
		this.winU = this.winUsuario();
		return (winZ || winU);
	}
	
	public boolean winUsuario()
	{
		return this.ZombieList.tamVacio() && this.ZombieManager.noQuedanZombies();
	}
	
	
	public String devolverString(int fila,int columna) //RELEASE
	{
		String cadena = "" ;
		
		if (!this.PlantList.estaVacio(fila, columna))
		{
			cadena = this.PlantList.toString(fila, columna);
		}
		else if(!this.ZombieList.estaVacio(fila, columna))
		{
			cadena = this.ZombieList.toString(fila,columna);
		}
		else
		{
			cadena="";
		}

		return cadena;
	}
	
	
	public String devolverStringDebugPlant(int fila,int columna) //DEBUG
	{
		String cadena = "";
		
		if (!this.PlantList.estaVacio(fila, columna))
		{
			cadena = this.PlantList.toStringDebug(fila, columna);
		}
		

		return cadena;
	}

	public String devolverStringDebugZombie(int fila,int columna) //DEBUG
	{
		String cadena = "";
		
		if(!this.ZombieList.estaVacio(fila, columna))
		{
			cadena = this.ZombieList.toStringDebug(fila,columna);
		}
		
		return cadena;
	}
	
	public void aumentarSoles(int cicloSalida) {
		this.SuncoinManager.aumentarSoles(cicloSalida, this.ciclo);
	}
	
	
	
	
	public boolean addPlanta(Plant planta) throws NoSuncoinsException, CasillaOcupadaException, OutOfBoardException
	{
		boolean insertada =false;
		
		if( this.SuncoinManager.getSoles() >= planta.getCoste()
				&& comprobarCoordenadas(planta.getFila(),planta.getColumna()))
		{
			if (comprobarDentroTablero(planta.getFila(),planta.getColumna()))
			{
				this.PlantList.insertar(planta, this);
				this.SuncoinManager.restarSoles(planta.getCoste());
				insertada = true;
			}
			else
			{
				throw new OutOfBoardException("Failed to add " + planta.getNombre() + " : ( " + planta.getFila() + " , "
													+ planta.getColumna() + " ) is an invalid position.");
			}
			}
			else if (this.SuncoinManager.getSoles() < planta.getCoste()) throw new NoSuncoinsException("Failed to add " +  planta.getNombre() + " : not enough suncoins to buy it");
			else if (!comprobarCoordenadas(planta.getFila(),planta.getColumna())) 
				{
				throw new CasillaOcupadaException("Failed to add  " +planta.getNombre() +": position ("+ planta.getFila()+
													","+ planta.getColumna()+") is already occupied");
				}	
			return insertada;
	}
	
	 public void infoDebug()
		{
			System.out.println("Seed:" + this.seed);
			System.out.println("Level: " + this.level.toString());
		}	
	 
	public boolean VacioZombie(int fila, int col) {
		return this.ZombieList.estaVacio(fila, col);
	}

	public void atacaZombie(int fila, int col, int dano) {
		this.ZombieList.AtacarObj(fila, col, dano);
		
	}
	public void explotaPetacereza(int fila, int col, int dano) {
		this.PlantList.AtacarObj(fila, col, dano);
		
	}
	
	public void incluirPlanta(Plant plant, int x, int y)
	{
		plant.setColumna(y);
		plant.setFila(x);
		plant.setCicloSalida(this.getCiclo());
		plant.setGame(this);
	}
	
	public void store(BufferedWriter bw) throws IOException {
		try {
			bw.write("cycle: " + this.ciclo);
			bw.newLine();
			bw.write("sunCoins: " + this.SuncoinManager.getSoles());
			bw.newLine();
			bw.write("level: " + this.level.toString());
			bw.newLine();
			bw.write("remZombies: " + this.ZombieManager.getZombies());
			bw.newLine();
			
			bw.write("plantList: ");
			this.PlantList.store(bw);
			bw.newLine();
			bw.write("zombieList: ");
			this.ZombieList.store(bw);
			bw.newLine();
			
		} catch (IOException e) {
			throw e;
		}
		
		
	}

	public void load(BufferedReader br) throws CommandExecuteException, IOException, FileContentsException
	{
		Game game = new Game(this.level, this.rand, this.seed);	 //GAME COPIA
		String[] loadLine = null;
		
		try {
			
			br.readLine(); //Para saltarnos la linea vacia
	
			loadLine = LoadLine.loadLine(br, "cycle", false);
			game.ciclo = Integer.parseInt(loadLine[0]);
		
			loadLine = LoadLine.loadLine(br, "sunCoins", false);
			game.SuncoinManager.setSoles(Integer.parseInt(loadLine[0]));
			

			loadLine = LoadLine.loadLine(br, "level", false);
			game.level = Level.parse(loadLine[0]);
			
			loadLine = LoadLine.loadLine(br, "remZombies", false);
			game.ZombieManager.setZombies(Integer.parseInt(loadLine[0]));
			
			this.load_Lista(br, game);	
		}
		catch (FileContentsException ex)
		{
			throw ex;
		}
		 catch (IOException e) 
		{
			throw e;
		}
		  catch (NumberFormatException ex)
		{
			  throw new CommandExecuteException("The params must be numbers");
		}
		
		this.ciclo = game.ciclo;
		this.SuncoinManager = game.SuncoinManager;
		this.level = game.level;
		this.ZombieManager = game.ZombieManager;
		this.ZombieList = game.ZombieList;
		this.PlantList = game.PlantList;
		this.ZombieList.setGame(this); //HACEMOS UN SET DEL JUEGO ACTUAL A LAS PLANTAS INTRODUCIDAS IGUAL PARA ZOMBIES
		this.PlantList.setGame(this);
		
	}
	
	public void load_Lista ( BufferedReader br, Game game) throws FileContentsException, IOException, NumberFormatException
	{
		
		
		try {
			
			String [] plants = LoadLine.loadLine(br, "plantList", true);
			String [] zombies = LoadLine.loadLine(br, "zombieList", true);
			
			if(plants.length != 0) {
	
				int i = 0;
				
				while (i < plants.length) {
					
					String [] planti = plants[i].trim().split(":");
					Plant gb = PlantFactory.parse(planti[0]);
					gb.setResistencia(Integer.parseInt(planti[1]));
					gb.setFila(Integer.parseInt(planti[2]));
					gb.setColumna(Integer.parseInt(planti[3]));
					gb.setCicloActu(Integer.parseInt(planti[4]));
					gb.setCicloSalida(game.ciclo);
					gb.setGame(game);
					game.PlantList.insertar(gb, game);
					i++;
				}
			}
			if(zombies.length != 0) {
					
				int j = 0;
					
				while (j < zombies.length) {
						
					String [] zombiei = zombies[j].trim().split(":");
					Zombie gb = ZombieFactory.parseString((zombiei[0]));
					gb.setResistencia(Integer.parseInt(zombiei[1]));
					gb.setFila(Integer.parseInt(zombiei[2]));
					gb.setColumna(Integer.parseInt(zombiei[3]));
					gb.setCicloActu(Integer.parseInt(zombiei[4]));
					gb.setCicloSalida(game.ciclo);
					gb.setGame(game);
					game.ZombieList.insertar(gb, game);
					j++;
				}
			}
			
		}
		catch (IOException ex)
		{
			throw ex;
		}
		catch (FileContentsException ex)
		{
			throw ex;
		}
		 catch (NumberFormatException ex)
		{
			  throw ex;
		}
		
	}
	
	//GETTERS AND SETTERS

	public int getCiclo() {
		// TODO Auto-generated method stub
		return this.ciclo;
	}

	public boolean getWinU() {
		// TODO Auto-generated method stub
		return this.winU;
	}

	public boolean getWinZ() {
		// TODO Auto-generated method stub
		return this.winZ;
	}

	public SuncoinManager getSuncoinManager() {
		// TODO Auto-generated method stub
		return this.SuncoinManager;
	}

	public ZombieManager getZombieManager() {
		// TODO Auto-generated method stub
		return this.ZombieManager;
	}

	public Level getlevel() {
		// TODO Auto-generated method stub
		return this.level;
	}

	public long getseed() {
		// TODO Auto-generated method stub
		return this.seed;
	}

	public boolean getExit() {
		// TODO Auto-generated method stub
		return this.exit;
	}
	
	public void exitJuego()
	{
		this.exit = true;
	}
	
	public BoardPrinter getbp()
	{
		return this.PrinterManager.getbp();
	}
	
	public void setBoardPrinter (BoardPrinter bp)
	{
		this.PrinterManager.setbp(bp);
	}

	public boolean getNoPrint() {
		// TODO Auto-generated method stub
		return this.noPrint;
	}
	
	public void setNoPrint(boolean noPrint) {
		this.noPrint = noPrint;
	}
	
}
