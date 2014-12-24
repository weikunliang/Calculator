package edu.cmu.weikunl.p1_liang_weikun;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity{
	
	//the screen
	private EditText Scr;
	//the first number
	private String num1 = "";
	//the second number
	private String num2 = "";
	//the operator
	private String op = "";
	//the final result
	private float result;
	
	//function that calculate the final result based on the operator passed in
	public void calc() {
    	if(op.equals("+")){
    		result = Float.parseFloat(num1) + Float.parseFloat(num2);		
    	}
    	if(op.equals("-")){
    		result = Float.parseFloat(num1) - Float.parseFloat(num2);			
    	}
    	if(op.equals("*")){
    		result = Float.parseFloat(num1) * Float.parseFloat(num2);			
    	}
    	if(op.equals("/")){
    		result = Float.parseFloat(num1) / Float.parseFloat(num2);			
    	}
    }
	
	//what it does when an operator is pressed
	public void operatorPress(String operator){
		if(op == "" && num1 != ""){
			op = operator;
			Scr.setText(num1 + op);
		}
	}
	
	//displays the toast message
	public void toastMsg(String string){
		Context context = getApplicationContext();
		CharSequence message = string;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        Scr = (EditText) findViewById(R.id.output);
        
        //list of buttons
        int idList[] = {R.id.zeroButton, R.id.oneButton, R.id.twoButton, R.id.threeButton, R.id.fourButton,
        		R.id.fiveButton, R.id.sixButton, R.id.sevenButton, R.id.eightButton, R.id.nineButton,
        		R.id.dubZeroButton, R.id.multButton, R.id.divButton, R.id.plusButton, R.id.minusButton,
        		R.id.equalsButton, R.id.clearButton, R.id.decButton
        			};
        
        //loops through the list of buttons and specifies onclick for each
        for (int id:idList) {
        	Button v = (Button) findViewById(id);
        	v.setOnLongClickListener(new View.OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					switch(v.getId()){
        			case R.id.clearButton:
        				num1 = "";
        				num2 = "";
        				op = "";
        				Scr.setText("");
        			
					}
					return true;
				}
        		
        	});
        	v.setOnClickListener(new View.OnClickListener() {
        		public void onClick(View v) {
        			// TODO Auto-generated method stub
        			switch(v.getId()){
        			case R.id.plusButton:
        				operatorPress("+");
        				break;
        			case R.id.minusButton:
        				operatorPress("-");
        				break;
        			case R.id.multButton:
        				operatorPress("*");
        				break;
        			case R.id.divButton:
        				operatorPress("/");
        				break;
        			case R.id.equalsButton:
        				//regex to match the expression
        				String reg = "(\\d*\\.?\\d+)([\\/\\+\\-\\*])+(\\d*\\.?\\d+)";
        				if(Scr.getText().toString().matches(reg)){
        					calc();
	        				Scr.setText(String.valueOf(result));
	        				num1 = "";
	        				num2 = "";
	        				op = "";
	        				break;
        				} else {
        					toastMsg("Not a valid format");
	        				break;
        				}
        			case R.id.clearButton:
        				// clears the last digit based on cases
        				if (num2 != "" && num1 != "" && op != ""){
        					if (num2.length() > 1){
        						num2 = num2.substring(0, num2.length()-1);
        					} else {
        						num2 = "";
        					}
        				} else if (op != "" && num2 == "" && num1 != ""){
        					op = "";
        				} else if(op == "" && num1 != "" & num2 == ""){
        					if (num1.length() > 1){
        						num1 = num1.substring(0, num1.length()-1);
        					} else {
        						num1 = "";
        					}
        				}
        				Scr.setText(num1 + op + num2);
        				break;
        			default:
        				String num = ((Button) v).getText().toString();
        				if(op == ""){
	        				num1 = num1 + num;
	        				Scr.setText(num1);
        				} else {
        					num2 = num2 + num;
        					Scr.setText(num1 + op + num2);
        				}
        				break;
        			}
        		}
        	});	
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

			
		
}

