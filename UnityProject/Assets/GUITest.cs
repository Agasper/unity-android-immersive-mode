using UnityEngine;
using System.Collections;

public class GUITest : MonoBehaviour {

    string area = "testArea";
    string field = "testField";
	void Start () {
	
	}
	
	// Update is called once per frame
	void OnGUI () {
		area = GUI.TextArea(new Rect(0,0,Screen.width, Screen.height*0.25f), area);
        field = GUI.TextField(new Rect(0, Screen.height * 0.3f, Screen.width, Screen.height * 0.05f), field);
	}
}
