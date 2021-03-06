package name.blah;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Roguelike extends Activity {
    /** Called when the activity is first created. */
	private TextView tv;
	private RoguelikeMap map;
	private Player plr;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tv = (TextView) findViewById(R.id.textView1);
        map = new RoguelikeMap();
        plr = new Player(map);
        
        tv.setText(map.toString());
        tv.setTypeface(Typeface.MONOSPACE);
        //setContentView(tv);
        
    }
    
    public void regen(View view)
    {
    	map = null;
    	map = new RoguelikeMap();
    	tv.setText(map.toString());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
     menu.add("Regenerate");
     return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
     
     switch(item.getItemId())
     {
     default:
    	tv.setText("Regenerating...");
      	map = null;
  		map = new RoguelikeMap();
  		tv.setText(map.toString());
  		return true;
     }

    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
    	
    	switch(keyCode)
    	{
    	case KeyEvent.KEYCODE_DPAD_LEFT:
    		this.plr.move(Direction.WEST, this.map);
    		break;
    		
    	case KeyEvent.KEYCODE_DPAD_RIGHT:
    		this.plr.move(Direction.EAST, this.map);
    		break;
    		
    	case KeyEvent.KEYCODE_DPAD_UP:
    		this.plr.move(Direction.NORTH, this.map);
    		break;
    		
    	case KeyEvent.KEYCODE_DPAD_DOWN:
    		this.plr.move(Direction.SOUTH, this.map);
    		break;
    	}
    	this.redraw();
    	return true;
    }
    
    public void movementKeys(View v)
    {
    	switch(v.getId())
    	{
    	case R.id.btndown:
    		this.plr.move(Direction.SOUTH, this.map);
    		break;
    		
    	case R.id.btnleft:
    		this.plr.move(Direction.WEST, this.map);
    		break;
    		
    	case R.id.btnright:
    		this.plr.move(Direction.EAST, this.map);
    		break;
    		
    	case R.id.btnup:
    		this.plr.move(Direction.NORTH, this.map);
    		break;
    	}
    	this.redraw();
    }
    
    private void redraw()
    {
    	tv.setText(map.toString());
    }
}