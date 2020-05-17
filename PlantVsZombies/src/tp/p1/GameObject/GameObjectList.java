package tp.p1.GameObject;

import java.io.BufferedWriter;
import java.io.IOException;

import tp.p1.Game;

public class GameObjectList {

		private int tam;
		
		private GameObject[] GameObjectList;
		
		public GameObjectList(int maximoTam)
		{
			this.tam = 0;
			this.GameObjectList = new GameObject[maximoTam];
		}

		public void insertar(GameObject gameObj, Game game) {
			
			this.GameObjectList[this.tam] = gameObj;
			this.tam++;
		}

		private boolean ObjIsDead (int pos)
		{
			return this.GameObjectList[pos].isDead();
		}
		
		private void eliminarObj(int posicion)
		{
			for(int j=posicion;j<this.tam;j++)
			{
				this.GameObjectList[j] = this.GameObjectList[j+1];
			}
			this.tam--;
			
		}
		
		private void quitarResistencia(int pos, int dano)
		{
			this.GameObjectList[pos].quitarResistencia(dano);
		}
		
		private int buscarInterno(int fila, int columna)
		{
			boolean encontrado = false;
			int i=0;
			while(i<this.tam && !encontrado)
			{
				if (GameObjectList[i].getFila() == fila && 
						GameObjectList[i].getColumna() == columna)
				{
					encontrado = true;
				}
				i++;
			}
			if(!encontrado)
			{
				i=-1;
			}
			return i;
		}
		
		
		public boolean estaVacio(int fila, int columna)
		{
			return this.buscarInterno(fila, columna) == -1;
		}
		
		
		public boolean AtacarObj(int fila,int columna, int dano)
		{
			boolean encontrado=false;
			int objpos = this.buscarInterno(fila, columna);
			
			if(objpos !=-1)
			{
				this.quitarResistencia(objpos - 1, dano);
				
				if (!this.ObjIsDead(objpos - 1))
				{
				encontrado=true;
				}
				else
				{
					this.eliminarObj(objpos - 1);
				}
			}
				return encontrado;
			}

		public void resetearLista() 
		{
			this.tam = 0;
		}

		public String toString(int fila, int columna) 
		{
			
			int i = this.buscarInterno(fila, columna);
			
			return this.GameObjectList[i-1].toString();
		}
		
		
	
		public boolean update()
		{
			boolean update = false;
			int i = 0;
			while (i < this.tam && !update)
			{
				update = this.GameObjectList[i].update();
				i++;
			}
								
			return update;
		}

		public boolean tamVacio()
		{
			return this.tam ==0;
		}

		public String toStringDebug(int fila, int columna)
		{
			int i = this.buscarInterno(fila, columna);
			return this.GameObjectList[i-1].toStringDebug();
		}

		public void store(BufferedWriter bw) throws IOException {

			for(int i = 0; i < this.tam ; i++)
			{
				try
				{
				bw.write(this.GameObjectList[i].letra + ":" +
						this.GameObjectList[i].resistencia + ":" + this.GameObjectList[i].fila
						+ ":" + this.GameObjectList[i].columna + ":" + this.GameObjectList[i].cicloActua );
				
				if(this.tam - i != 1) bw.write(", ");
				}
				catch (IOException e)
				{
					throw e;
				}
			}
			
		}
		
		public void setGame (Game game)
		{
			for (int i = 0; i < this.tam ; i++)
			{
				this.GameObjectList[i].setGameObj(game);
			}
		}
		
	}

