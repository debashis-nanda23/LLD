package com.debashis.coffee.main;

import java.util.List;

import com.debashis.coffee.decorator.CaramelSyrupDecorator;
import com.debashis.coffee.decorator.Coffee;
import com.debashis.coffee.decorator.ExtraSugarDecorator;
import com.debashis.coffee.enums.CoffeeType;
import com.debashis.coffee.enums.ToppingType;
import com.debashis.coffee.factory.CoffeeFactory;
import com.debashis.coffee.state.ReadyState;
import com.debashis.coffee.state.VendingMachineState;

public class CoffeeVendingMachine {

	private static final CoffeeVendingMachine INSTANCE=new CoffeeVendingMachine();
	private VendingMachineState state;
	private Coffee selectedCoffe;
	private int moneyInserted;
	
	private CoffeeVendingMachine() {
		this.state=new ReadyState();
		moneyInserted=0;
	}
	
	public static CoffeeVendingMachine getInstance() {
		return INSTANCE;
	}
	
	public void selectCoffee(CoffeeType type,List<ToppingType> toppings) {
		Coffee coffee=CoffeeFactory.createCoffee(type);
		for(ToppingType topping:toppings) {
			switch(topping) {
			case EXTRA_SUGAR:
				coffee=new ExtraSugarDecorator(coffee);
				break;
			case CARAMEL_SYRUP:
				coffee=new CaramelSyrupDecorator(coffee);
				break;
			}
		}
		this.state.selectCoffee(this, coffee);
	}
	
	public void insertMoney(int amount) {
		state.insertMoney(this, amount);
	}
	
	public void dispenseCoffee() {
		state.dispenseCoffee(this);
	}
	
	public void cancel() {
		state.cancel(this);
	}
	
	public void reset() {
		this.selectedCoffe=null;
		this.moneyInserted=0;
	}

	public VendingMachineState getState() {
		return state;
	}

	public void setState(VendingMachineState state) {
		this.state = state;
	}

	public Coffee getSelectedCoffe() {
		return selectedCoffe;
	}

	public void setSelectedCoffe(Coffee selectedCoffe) {
		this.selectedCoffe = selectedCoffe;
	}

	public int getMoneyInserted() {
		return moneyInserted;
	}

	public void setMoneyInserted(int moneyInserted) {
		this.moneyInserted = moneyInserted;
	}
	
	
}
