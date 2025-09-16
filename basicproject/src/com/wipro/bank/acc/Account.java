package com.wipro.bank.acc;

public abstract class Account {
	protected int tenure;
	protected float principle;
	protected float rateOfInterest;
	public void setInterest(int age,String gender)
	{
		if(gender.equals("male") && age<60)
		{
			this.rateOfInterest=9.8f;
		}
		else {
			this.rateOfInterest=10.5f;
		}
		if(gender.equals("female") && age<58)
		{
			this.rateOfInterest=10.2f;
		}
		else {
			this.rateOfInterest=10.8f;
		}
	}
	public float calculateMaturityAmount(float totalPrincipleDeposited,float MaturityInterest)
	{
		return totalPrincipleDeposited+MaturityInterest;
	}
	public abstract float calculateInterest();
	public abstract float calculateAmountDeposited();

}
