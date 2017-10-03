using System.Collections;
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public class CreateGrid : MonoBehaviour {
	public int height;
	public int width;
	public int spaceBetween;

	// Use this for initialization
	void Start () {
		createMap();
		createMapFromImg("C:/Users/wizard man/Documents/GitHub/TowerDefenseVR/Assets/maps/map_test.png");		
	}

	public static Texture2D LoadPNG(string filePath) {
		Texture2D tex = null;
		byte[] fileData;

		if (File.Exists(filePath))     {
			print("loaded image");
			fileData = File.ReadAllBytes(filePath);
			tex = new Texture2D(1,1);
			tex.LoadImage(fileData);
		}
		return tex;
	}

	void createMap() {
		ArrayList tiles = new ArrayList();
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				GameObject tile = GameObject.CreatePrimitive(PrimitiveType.Plane);
				tile.name = "Tile" + i + "_" + j;
				tile.transform.position = new Vector3(i*(10+spaceBetween), 0, j*(10+spaceBetween));
				tiles.Add(tile);
			}
		}
	}

	void createMapFromImg(string path) {
		Texture2D tex = LoadPNG(path);
		Color pixel_colour = tex.GetPixel(4,1);
		if(pixel_colour == Color.black) {
			print(pixel_colour + " => path");
		} else if(pixel_colour == Color.green) {
			print(pixel_colour + " => ground");
		} else if(pixel_colour == Color.blue) {
			print(pixel_colour + " => start");
		} else if(pixel_colour == Color.red) {
			print(pixel_colour + " => end");
		} else {
			print(pixel_colour + " => unknown");
		}
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
